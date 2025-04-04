package faang.school.projectservice.service.stage;

import faang.school.projectservice.dto.client.StageDto;
import faang.school.projectservice.dto.client.StageFilterDto;
import faang.school.projectservice.dto.client.StageRolesDto;
import faang.school.projectservice.mapper.StageDtoMapper;
import faang.school.projectservice.mapper.StageRolesMapper;
import faang.school.projectservice.model.Project;
import faang.school.projectservice.model.Task;
import faang.school.projectservice.model.stage.Stage;
import faang.school.projectservice.model.stage.StageRoles;
import faang.school.projectservice.repository.ProjectJpaRepository;
import faang.school.projectservice.repository.StageJpaRepository;
import faang.school.projectservice.service.StageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        StageDto outStageDto = stageMapper.toDto(stage);

        outStageDto.setStageRolesDtoList(stageDto.getStageRolesDtoList());

        for (StageRolesDto rolesDto : stageDto.getStageRolesDtoList()) {
            stageJpaRepository.saveStageRole(rolesDto.getTeamRole().toString(), rolesDto.getCount(), stage.getStageId());
        }

        return outStageDto;
    }

    public List<StageDto> getStageByRoles(StageFilterDto filterDto) {
        List<Stage> stageList = stageJpaRepository.findStagesByProjectAndRoles(filterDto.getProjectId(), filterDto.getRoles());

        return stageList.stream()
                .map(stageMapper::toDto)
                .toList();
    }

    public void deleteStage(Long stageId) {
        if (stageJpaRepository.existsById(stageId)) {
            stageJpaRepository.deleteById(stageId);
        } else {
            throw new EntityNotFoundException("Нет такого проекта");
        }
    }

    public void taskTransfer(Long stageId, Long stageTransferId) {
        Stage stage = stageJpaRepository.findById(stageId).orElseThrow(
                () -> new EntityNotFoundException("Нет такого проекта"));

        Stage stageTransfer = stageJpaRepository.findById(stageTransferId).orElseThrow(()
                -> new EntityNotFoundException("Нет такого проекта"));

        List<Task> taskList = stage.getTasks();
        List<Task> taskListTransfer = stageTransfer.getTasks();
        taskListTransfer.addAll(taskList);

        stageTransfer.setTasks(taskListTransfer);

        stageJpaRepository.save(stageTransfer);
    }

    public List<StageDto> getAllStage(Long projectId) {
        Project project = projectJpaRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Проект не найден"));

        List<Stage> stageList = project.getStages();

        return stageList.stream()
                .map(stageMapper::toDto)
                .toList();
    }

    public StageDto getById(Long id) {
        return stageMapper.toDto(stageJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Нет такого этапа")));
    }
}
