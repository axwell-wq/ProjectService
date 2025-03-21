package faang.school.projectservice.service;

import faang.school.projectservice.dto.client.ProjectDto;

import java.util.List;

public interface ProjectService {

    ProjectDto createProject(ProjectDto projectDto);

    ProjectDto updateProject(Long id, ProjectDto projectDto);

    List<ProjectDto> getAllProjectById(List<Long> id);

    List<ProjectDto> getAllProject();

    ProjectDto getByIdProject(Long id);

    List<ProjectDto> getAllProjectsByPrivate(Long userId);

    ProjectDto createChildProject(ProjectDto projectDto);
}
