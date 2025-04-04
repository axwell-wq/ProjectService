package faang.school.projectservice.repository;

import faang.school.projectservice.model.TeamRole;
import faang.school.projectservice.model.stage.Stage;
import faang.school.projectservice.model.stage.StageRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageJpaRepository extends JpaRepository<Stage, Long> {

    @Query("SELECT DISTINCT s FROM Stage s " +
            "JOIN s.stageRoles sr " +
            "WHERE s.project.id = :projectId " +
            "AND sr.teamRole IN :roles")
    List<Stage> findStagesByProjectAndRoles(
            @Param("projectId") Long projectId,
            @Param("roles") List<TeamRole> roles
    );

    @Modifying
    @Query(
            value = "INSERT INTO project_stage_roles (role, count, project_stage_id) " +
                    "VALUES (:role, :count, :stageId)",
            nativeQuery = true
    )
    @Transactional
    void saveStageRole(
            @Param("role") String role,
            @Param("count") Integer count,
            @Param("stageId") Long stageId
    );
}
