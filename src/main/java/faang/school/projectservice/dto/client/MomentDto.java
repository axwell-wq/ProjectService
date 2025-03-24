package faang.school.projectservice.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MomentDto {
    private Long id;
    private String name;
    private String description;
}
