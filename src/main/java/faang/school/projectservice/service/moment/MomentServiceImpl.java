package faang.school.projectservice.service.moment;

import faang.school.projectservice.dto.client.MomentDto;
import faang.school.projectservice.mapper.MomentDtoMapper;
import faang.school.projectservice.model.Moment;
import faang.school.projectservice.model.Project;
import faang.school.projectservice.model.ProjectVisibility;
import faang.school.projectservice.repository.MomentRepository;
import faang.school.projectservice.repository.ProjectJpaRepository;
import faang.school.projectservice.service.MomentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MomentServiceImpl implements MomentService {

    private final MomentRepository momentRepository;
    private final ProjectJpaRepository projectJpaRepository;
    private final MomentDtoMapper mapper;

    public MomentDto createMoment(MomentDto momentDto, Long projectId) {
        Project project = projectJpaRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Проект не найден"));

        if (!project.getVisibility().equals(ProjectVisibility.PUBLIC)) {
            throw new EntityNotFoundException("Проект закрыт для вас");
        }

        Moment moment = momentRepository.save(mapper.toEntity(momentDto));

        return mapper.toDto(moment);
    }
}
