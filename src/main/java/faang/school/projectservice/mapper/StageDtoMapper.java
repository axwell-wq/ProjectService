package faang.school.projectservice.mapper;

import faang.school.projectservice.dto.client.StageDto;
import faang.school.projectservice.model.stage.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StageDtoMapper {

    StageDto toDto(Stage stage);

    Stage toEntity(StageDto stageDto);
}
