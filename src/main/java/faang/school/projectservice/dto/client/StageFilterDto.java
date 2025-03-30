package faang.school.projectservice.dto.client;

import lombok.Data;

import java.util.List;

@Data
public class StageFilterDto {
    private Long projectId;
    private List<StageRolesDto> roles;
}
