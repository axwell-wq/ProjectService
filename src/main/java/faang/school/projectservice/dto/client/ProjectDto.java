package faang.school.projectservice.dto.client;

import faang.school.projectservice.model.ProjectStatus;
import faang.school.projectservice.model.ProjectVisibility;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;

    @NotNull
    private String name;
    private String description;
    private Long ownerId;
    private ProjectStatus status;
    private ProjectVisibility visibility;
}
