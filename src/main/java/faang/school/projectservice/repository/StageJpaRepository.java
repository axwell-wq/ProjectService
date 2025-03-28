package faang.school.projectservice.repository;

import faang.school.projectservice.model.stage.Stage;
import faang.school.projectservice.model.stage.StageRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageJpaRepository extends JpaRepository<Stage, Long> {

    List<Stage> findByProjectIdAndStageRoles(Long projectId, List<StageRoles> stageRoles);
}
