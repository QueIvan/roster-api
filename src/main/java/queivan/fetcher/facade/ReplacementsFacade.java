package queivan.fetcher.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.fetcher.domain.Replacements;
import queivan.fetcher.service.ReplacementsService;

@Component
@RequiredArgsConstructor
public class ReplacementsFacade {
    private final ReplacementsService service;

    public Replacements getReplacementsForSearchTitle(String searchQuery, String issuerId){
        return service.getReplacementsForSearchTitle(searchQuery, issuerId);
    }

}
