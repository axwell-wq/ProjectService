package faang.school.projectservice.service.stage;

import faang.school.projectservice.dto.client.StageDto;
import faang.school.projectservice.dto.client.StageFilterDto;
import faang.school.projectservice.dto.client.StageRolesDto;
import faang.school.projectservice.mapper.StageDtoMapper;
import faang.school.projectservice.mapper.StageRolesMapper;
import faang.school.projectservice.model.Project;
import faang.school.projectservice.model.stage.Stage;
import faang.school.projectservice.model.stage.StageRoles;
import faang.school.projectservice.repository.ProjectJpaRepository;
import faang.school.projectservice.repository.StageJpaRepository;
import faang.school.projectservice.service.StageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StageServiceImpl implements StageService {

    private final StageJpaRepository stageJpaRepository;
    private final ProjectJpaRepository projectJpaRepository;
    private final StageDtoMapper stageMapper;
    private final StageRolesMapper stageRolesMapper;

    public StageDto createStage(StageDto stageDto) {
        Project project = projectJpaRepository.findById(stageDto.getProjectId()).orElseThrow(
                () -> new EntityNotFoundException("Проект не найден"));

        Stage stage = stageJpaRepository.save(stageMapper.toEntity(stageDto));

        stage.setProject(project);

        return stageMapper.toDto(stage);
    }

    public List<StageDto> getStageByRoles(StageFilterDto filterDto) {
        List<StageRoles> stageRoles = filterDto.getRoles().stream()
                .map(stageRolesMapper::toEntity)
                .toList();

        List<Stage> stageList = stageJpaRepository.findByProjectIdAndStageRoles(filterDto.getProjectId(), stageRoles);

        return stageList.stream()
                .map(stageMapper::toDto)
                .toList();
    }
}
