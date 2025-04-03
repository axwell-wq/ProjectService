package faang.school.projectservice.dto.client;

import faang.school.projectservice.model.TeamRole;
import lombok.Data;

import java.util.List;

@Data
public class StageFilterDto {
    private Long projectId;
    private List<TeamRole> roles;
}
