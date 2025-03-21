package faang.school.projectservice.dto.client;

import faang.school.projectservice.model.ProjectStatus;
import faang.school.projectservice.model.ProjectVisibility;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDto {
    private Long id;

    @NotNull
    private String name;
    private String description;
    private Long ownerId;
    private ProjectStatus status;
    private ProjectVisibility visibility;
    private Long parentProjectId;
    private List<Long> childrenId;
}
