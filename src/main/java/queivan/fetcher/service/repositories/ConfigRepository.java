package queivan.fetcher.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import queivan.fetcher.domain.Config;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConfigRepository extends JpaRepository<Config, UUID> {
    @Query(value = "SELECT cast(configs.id as varchar) as id FROM configs, email_authorizations WHERE email_authorizations.email = ?1", nativeQuery = true)
    List<UUID> findAllIdsByEmail(String email);
    @Query(value = "SELECT cast(configs.id as varchar) as id FROM configs, email_authorizations WHERE email_authorizations.status = ?1", nativeQuery = true)
    List<UUID> findAllIdsByStatus(Boolean status);
    boolean existsByName(String name);
    boolean existsByWikiPath(String path);
}
