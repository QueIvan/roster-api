package queivan.fetcher.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import queivan.fetcher.domain.EmailAuthorization;

import java.util.UUID;

@Repository
public interface EmailAuthorizationRepository extends JpaRepository<EmailAuthorization, UUID> {
    boolean existsByPathId(UUID targetId);
    boolean existsByConfigId(UUID targetId);

    EmailAuthorization findByPathId(UUID targetId);

    EmailAuthorization findByConfigId(UUID targetId);
}
