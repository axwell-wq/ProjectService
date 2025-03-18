package faang.school.projectservice.controller;

import faang.school.projectservice.dto.client.ProjectDto;
import faang.school.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ProjectDto createProject(@RequestBody ProjectDto projectDto) {
        return projectService.createProject(projectDto);
    }

    @PutMapping("/update/{id}")
    public ProjectDto updateProject(@PathVariable Long id,@RequestBody ProjectDto projectDto) {
        return projectService.updateProject(id, projectDto);
    }

    @GetMapping("/getAllProject/{id}")
    public List<ProjectDto> getAllProject(@PathVariable List<Long> id) {
        return projectService.getAllProject(id);
    }

    @GetMapping("/getByIdProject/{id}")
    public ProjectDto getByIdProject(@PathVariable Long id) {
        return projectService.getByIdProject(id);
    }
}
