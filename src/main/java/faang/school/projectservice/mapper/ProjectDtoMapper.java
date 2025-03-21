package faang.school.projectservice.mapper;

import faang.school.projectservice.dto.client.ProjectDto;
import faang.school.projectservice.model.Project;
import faang.school.projectservice.repository.ProjectJpaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProjectDtoMapper {

    @Mapping(source = "parentProject.id", target = "parentProjectId")
    ProjectDto toDto(Project projectJpaRepository);

    @Mapping(source = "parentProjectId", target = "parentProject.id")
    Project toEntity(ProjectDto projectDto);
}
