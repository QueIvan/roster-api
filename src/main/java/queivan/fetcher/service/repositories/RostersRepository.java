package queivan.fetcher.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import queivan.fetcher.domain.Rosters;

import java.util.UUID;

@Repository
public interface RostersRepository extends JpaRepository<Rosters, UUID> {
    Rosters findByName(String name);

    boolean existsByName(String name);
}
