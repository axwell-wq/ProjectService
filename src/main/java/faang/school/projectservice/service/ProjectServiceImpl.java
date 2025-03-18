package faang.school.projectservice.service;

import faang.school.projectservice.dto.client.ProjectDto;
import faang.school.projectservice.mapper.ProjectDtoMapper;
import faang.school.projectservice.model.Project;
import faang.school.projectservice.model.ProjectStatus;
import faang.school.projectservice.repository.ProjectJpaRepository;
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

    public ProjectDto createProject(ProjectDto projectDto) {
        if (projectJpaRepository.existsByOwnerIdAndName(projectDto.getOwnerId(), projectDto.getName())) {
            throw new IllegalArgumentException("такое имя уже существует");
        }
        projectDto.setStatus(ProjectStatus.CREATED);
        projectJpaRepository.save(mapper.toEntity(projectDto));
        return projectDto;
    }

    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        Project project = projectJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("проект не найден"));

        project.setDescription(projectDto.getDescription());
        project.setStatus(project.getStatus());

        Project updateProject = projectJpaRepository.save(project);
        return mapper.toDto(updateProject);
    }

    public List<ProjectDto> getAllProject(List<Long> id) {
        List<Project> allById = projectJpaRepository.findAllById(id);
        return allById.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ProjectDto getByIdProject(Long id) {
        Project project = projectJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("нет такого проекта"));
       return mapper.toDto(project);
    }

    //Получить все проекты с фильтрами по названию или статусу.
    // У проекта также должен быть признак приватности.
    // Если проект приватный, то по поиску он должен быть видим только своим участникам.
}
