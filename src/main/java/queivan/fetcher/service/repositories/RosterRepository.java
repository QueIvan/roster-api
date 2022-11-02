package queivan.fetcher.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import queivan.fetcher.domain.Roster;

import java.util.UUID;

@Repository
public interface RosterRepository extends JpaRepository<Roster, UUID> {
    Roster findByName(String name);

    boolean existsByName(String name);
    void deleteByName(String name);
}
