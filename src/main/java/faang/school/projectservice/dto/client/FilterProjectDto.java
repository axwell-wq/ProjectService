package faang.school.projectservice.dto.client;

import faang.school.projectservice.model.ProjectStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterProjectDto {

    @NotNull
    private Long parentProjectId;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
}
