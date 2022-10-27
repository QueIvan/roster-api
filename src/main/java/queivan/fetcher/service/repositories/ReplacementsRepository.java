package queivan.fetcher.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import queivan.fetcher.domain.Replacements;

import java.util.UUID;

@Repository
public interface ReplacementsRepository extends JpaRepository<Replacements, UUID> {
    Replacements findByName(String searchQuery);
}
