package faang.school.projectservice.service;

import faang.school.projectservice.dto.client.StageDto;
import faang.school.projectservice.dto.client.StageFilterDto;

import java.util.List;

public interface StageService {

    StageDto createStage(StageDto stageDto);

    List<StageDto> getStageByRoles(StageFilterDto filterDto);

    void deleteStage(Long stageId);

    void taskTransfer(Long stageId, Long stageTransferId);

    List<StageDto> getAllStage(Long projectId);

    StageDto getById(Long id);
}
