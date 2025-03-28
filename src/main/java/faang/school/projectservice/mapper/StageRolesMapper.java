package faang.school.projectservice.mapper;

import faang.school.projectservice.dto.client.StageRolesDto;
import faang.school.projectservice.model.stage.StageRoles;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StageRolesMapper {

    StageRolesDto toDto(StageRoles stageRoles);

    StageRoles toEntity(StageRolesDto stageRolesDto);
}
