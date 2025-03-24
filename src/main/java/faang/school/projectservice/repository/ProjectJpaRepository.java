package faang.school.projectservice.repository;

import faang.school.projectservice.model.Project;
import faang.school.projectservice.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectJpaRepository extends JpaRepository<Project, Long> {
    @Query(
            "SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " +
                    "FROM Project p " +
                    "WHERE p.ownerId = :ownerId AND p.name = :name"
    )
    boolean existsByOwnerIdAndName(Long ownerId, String name);

    List<Project> getProjectsByParentProjectId(Long id);

    @Query("SELECT p FROM Project p " +
            "WHERE p.parentProject.id = :parentProjectId " +
            "AND (:name IS NULL OR p.name = :name) " +
            "AND (:status IS NULL OR p.status = :status)")
    List<Project> getChildrenProjects(@Param("parentProjectId") Long parentProjectId,
                                      @Param("name") String name,
                                      @Param("status") ProjectStatus status);
}

