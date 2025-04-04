package faang.school.projectservice.dto.client;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MomentDto {
    private Long id;

    @NotBlank
    private String name;
    private String description;
}
