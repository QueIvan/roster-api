package queivan.fetcher.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import queivan.fetcher.domain.Path;

import java.util.List;
import java.util.UUID;

@Repository
public interface PathRepository extends JpaRepository<Path, UUID> {
    @Modifying
    @Query(value = "SELECT cast(paths.id as varchar) as id FROM paths, email_authorizations WHERE email_authorizations.email = ?1", nativeQuery = true)
    List<UUID> findAllIdsByEmail(String email);

    @Modifying
    @Query(value = "SELECT cast(paths.id as varchar) as id FROM paths, email_authorizations WHERE email_authorizations.status = ?1", nativeQuery = true)
    List<UUID> findAllIdsByStatus(Boolean status);

    boolean existsByTitle(String title);

    @Query(value = "SELECT DISTINCT * from paths where lower(paths.title) like concat('%', ?1, '%') or lower(paths.abbreviation) like concat('%', ?1, '%')", nativeQuery = true)
    List<Path> findAllMatching(String query);
}