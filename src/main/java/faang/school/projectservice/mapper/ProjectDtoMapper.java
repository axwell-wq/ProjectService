package faang.school.projectservice.mapper;

import faang.school.projectservice.dto.client.ProjectDto;
import faang.school.projectservice.model.Project;
import faang.school.projectservice.repository.ProjectJpaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProjectDtoMapper {

    @Mapping(source = "owner.id", target = "ownerId")
    ProjectDto toDto(Project projectJpaRepository);

    @Mapping(source = "ownerId", target = "owner.id")
    Project toEntity(ProjectDto projectDto);
}
