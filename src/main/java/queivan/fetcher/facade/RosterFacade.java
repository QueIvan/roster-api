package queivan.fetcher.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.fetcher.domain.RosterDto;
import queivan.fetcher.service.RosterService;

@Component
@RequiredArgsConstructor
public class RosterFacade {
    private final RosterService service;

    public RosterDto getRosterByName(String name, String issuerId){
        return service.getRosterByName(name, issuerId);
    }

    public RosterDto createNewRoster(RosterDto dto, String issuerId) {
        return service.createNewRoster(dto, issuerId);
    }
}
