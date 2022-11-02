package queivan.fetcher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import queivan.fetcher.domain.LogDto;
import queivan.fetcher.domain.Roster;
import queivan.fetcher.domain.RosterDto;
import queivan.fetcher.mapper.RosterMapper;
import queivan.fetcher.service.repositories.RosterRepository;

@Service
@RequiredArgsConstructor
public class RosterService {
    private final RosterRepository repository;
    private final RosterMapper mapper;
    private final LogService logger;

    public RosterDto getRosterByName(String name, String issuerId){
        Roster roster = repository.findByName(name);
        logger.info(LogDto.builder()
                .content(String.format("Fetching roster with name: %s", name))
                .issuerId(issuerId)
                .build());
        return mapper.mapRosterToRosterDto(roster);
    }

    @Transactional
    public RosterDto createNewRoster(RosterDto dto, String issuerId) {
        if(doesRosterExists(dto)) deleteRoster(dto.getName(), issuerId);
        Roster roster = repository.save(mapper.mapRosterDtoToRoster(dto));
        logger.info(LogDto.builder()
                .content(String.format("Created new roster with id: %s", roster.getId()))
                .issuerId(issuerId)
                .build());
        return mapper.mapRosterToRosterDto(roster);
    }

    public void deleteRoster(String name, String issuerId){
        repository.deleteByName(name);
        logger.info(LogDto.builder()
                .content(String.format("Deleted roster with name: %s", name))
                .issuerId(issuerId)
                .build());
    }

    private boolean doesRosterExists(RosterDto dto) {
        return repository.existsByName(dto.getName());
    }

}
