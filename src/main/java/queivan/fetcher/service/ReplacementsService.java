package queivan.fetcher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import queivan.fetcher.domain.LogDto;
import queivan.fetcher.domain.Replacements;
import queivan.fetcher.service.repositories.ReplacementsRepository;

@Service
@RequiredArgsConstructor
public class ReplacementsService {

    private final ReplacementsRepository repository;
    private final LogService logger;

    public Replacements getReplacementsForSearchTitle(String searchQuery, String issuerId){
        Replacements replacement = repository.findByName(searchQuery);
        logger.info(LogDto.builder()
                .content(String.format("Fetch replacement for query: %s", searchQuery))
                .issuerId(issuerId)
                .build());
        return replacement;
    }

}
