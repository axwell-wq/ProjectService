package faang.school.projectservice.dto.client;

import faang.school.projectservice.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageDto {
    private Long stageId;
    private String stageName;
    private List<StageRolesDto> stageRolesDtoList;
    private Long projectId;
}
