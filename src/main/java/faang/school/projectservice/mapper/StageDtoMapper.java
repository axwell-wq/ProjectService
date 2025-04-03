package faang.school.projectservice.mapper;

import faang.school.projectservice.dto.client.StageDto;
import faang.school.projectservice.model.stage.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StageDtoMapper {

    @Mapping(source = "project.id", target = "projectId")
    StageDto toDto(Stage stage);

    @Mapping(source = "projectId", target = "project.id")
    Stage toEntity(StageDto stageDto);
}
