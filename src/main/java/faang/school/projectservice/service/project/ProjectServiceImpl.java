package faang.school.projectservice.service.project;

import faang.school.projectservice.dto.client.FilterProjectDto;
import faang.school.projectservice.dto.client.ProjectDto;
import faang.school.projectservice.dto.client.ProjectUpdateDto;
import faang.school.projectservice.mapper.ProjectDtoMapper;
import faang.school.projectservice.model.Project;
import faang.school.projectservice.model.ProjectStatus;
import faang.school.projectservice.model.ProjectVisibility;
import faang.school.projectservice.repository.ProjectJpaRepository;
import faang.school.projectservice.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectDtoMapper mapper;

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        if (projectJpaRepository.existsByOwnerIdAndName(projectDto.getOwnerId(), projectDto.getName())) {
            throw new IllegalArgumentException("такое имя уже существует");
        }
        projectDto.setStatus(ProjectStatus.CREATED);
        projectJpaRepository.save(mapper.toEntity(projectDto));
        return projectDto;
    }

    @Override
    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        Project project = projectJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("проект не найден"));

        project.setDescription(projectDto.getDescription());
        project.setStatus(project.getStatus());

        Project updateProject = projectJpaRepository.save(project);
        return mapper.toDto(updateProject);
    }

    @Override
    public List<ProjectDto> getAllProjectById(List<Long> id) {
        List<Project> allById = projectJpaRepository.findAllById(id);
        return allById.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> getAllProject() {
        List<Project> allById = projectJpaRepository.findAll();
        return allById.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getByIdProject(Long id) {
        Project project = projectJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("нет такого проекта"));
        return mapper.toDto(project);
    }

    @Override
    public List<ProjectDto> getAllProjectsByPrivate(Long userId) {
        List<Project> projects = projectJpaRepository.findAll();

        return projects.stream()
                .filter(project -> project.getVisibility()
                        .equals(ProjectVisibility.PUBLIC) || project.getOwnerId().equals(userId))
                .map(mapper::toDto)
                .toList();
    }

    public ProjectDto createChildProject(ProjectDto projectDto) {
        Project parentProject = projectJpaRepository.findById(projectDto.getParentProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Проект не существует"));

        Project childProject = mapper.toEntity(projectDto);
        childProject.setParentProject(parentProject);
        childProject.setStatus(ProjectStatus.CREATED);
        childProject.setVisibility(parentProject.getVisibility());

        parentProject.getChildren().add(childProject);
        Project savedChildProject = projectJpaRepository.save(childProject);

        return mapper.toDto(savedChildProject);
    }

    public ProjectDto updateChildProject(ProjectUpdateDto projectDto) {
        Project project = updateFieldProject(projectDto);

        List<Project> childProjects = projectJpaRepository.getProjectsByParentProjectId(project.getId());

        for (Project childProject : childProjects) {
            if (!childProject.getStatus().equals(project.getStatus())) {
                childProject.setStatus(project.getStatus());
            }
        }

        projectJpaRepository.saveAll(childProjects);

        if (project.getVisibility().equals(ProjectVisibility.PRIVATE)) {
            for (Project childProject : projectJpaRepository.getProjectsByParentProjectId(project.getId())) {
                childProject.setVisibility(project.getVisibility());
                projectJpaRepository.save(childProject);
            }
        }

        project.setChildren(projectJpaRepository.getProjectsByParentProjectId(project.getId()));

        return mapper.toDto(projectJpaRepository.save(project));
    }

    public List<ProjectDto> getChildrenProjects(FilterProjectDto filterProjectDto) {
        List<Project> projects = projectJpaRepository.getChildrenProjects(
                filterProjectDto.getParentProjectId(),
                filterProjectDto.getName(),
                filterProjectDto.getStatus()
        );

        return projects.stream()
                .map(mapper::toDto)
                .toList();
    }

    private Project updateFieldProject(ProjectUpdateDto projectDto) {
        Project project = projectJpaRepository.findById(projectDto.getId()).orElseThrow(
                () -> new EntityNotFoundException("Проект не существует"));

        if (projectDto.getStatus() != null) {
            project.setStatus(projectDto.getStatus());
        } else if (projectDto.getVisibility() != null) {
            project.setVisibility(projectDto.getVisibility());
        }

        return project;
    }
}
