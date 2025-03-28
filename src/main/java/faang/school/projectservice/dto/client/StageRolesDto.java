package faang.school.projectservice.dto.client;

import faang.school.projectservice.model.TeamRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageRolesDto {
    private TeamRole teamRole;
    private Integer count;
}
