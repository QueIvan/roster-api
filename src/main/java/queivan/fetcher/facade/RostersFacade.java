package queivan.fetcher.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.fetcher.domain.RostersDto;
import queivan.fetcher.service.RostersService;

@Component
@RequiredArgsConstructor
public class RostersFacade {
    private final RostersService service;

    public RostersDto getRosterByName(String name, String issuerId){
        return service.getRosterByName(name, issuerId);
    }

    public RostersDto createNewRoster(RostersDto dto, String issuerId) {
        return service.createNewRoster(dto, issuerId);
    }
}
