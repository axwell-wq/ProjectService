package faang.school.projectservice.controller;

import faang.school.projectservice.dto.client.StageDto;
import faang.school.projectservice.dto.client.StageFilterDto;
import faang.school.projectservice.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stage")
public class StageController {

    private final StageService stageService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public StageDto createStage(@RequestBody StageDto stageDto) {
        return stageService.createStage(stageDto);

    }

    @GetMapping("/getstagesyroles")
    public List<StageDto> getStageByRoles(@RequestBody StageFilterDto filterDto) {
        return stageService.getStageByRoles(filterDto);
    }

    @DeleteMapping("/delete/{stageId}")
    public void deleteStage(@PathVariable Long stageId) {
        stageService.deleteStage(stageId);
    }

    @PostMapping("/{stageId}/tasktransfer/{stageTransferId}")
    public void taskTransfer(@PathVariable Long stageId,@PathVariable Long stageTransferId) {
        stageService.taskTransfer(stageId, stageTransferId);
    }

    @GetMapping("/getallstage/{projectId}")
    public List<StageDto> getAllStage(@PathVariable Long projectId) {
     return stageService.getAllStage(projectId);
    }

    @GetMapping("/getbyid/{id}")
    public StageDto getById(@PathVariable Long id) {
        return stageService.getById(id);
    }
}
