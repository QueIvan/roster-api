package queivan.fetcher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import queivan.fetcher.domain.LogDto;
import queivan.fetcher.domain.Rosters;
import queivan.fetcher.domain.RostersDto;
import queivan.fetcher.mapper.RostersMapper;
import queivan.fetcher.service.repositories.RostersRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RostersService {
    private final RostersRepository repository;
    private final RostersMapper mapper;
    private final LogService logger;

    public RostersDto getRosterByName(String name, String issuerId){
        Rosters roster = repository.findByName(name);
        logger.info(LogDto.builder()
                .content(String.format("Fetching roster with name: %s", name))
                .issuerId(issuerId)
                .build());
        return mapper.mapRosterToRosterDto(roster);
    }

    public RostersDto createNewRoster(RostersDto dto, String issuerId) {
        if(doesRosterExists(dto)) deleteRoster(dto.getId(), issuerId);
        Rosters roster = repository.save(mapper.mapRosterDtoToRoster(dto));
        logger.info(LogDto.builder()
                .content(String.format("Created new roster with id: %s", roster.getId()))
                .issuerId(issuerId)
                .build());
        return mapper.mapRosterToRosterDto(roster);
    }

    public void deleteRoster(UUID id, String issuerId){
        repository.deleteById(id);
        logger.info(LogDto.builder()
                .content(String.format("Deleted roster with id: %s", id))
                .issuerId(issuerId)
                .build());
    }

    private boolean doesRosterExists(RostersDto dto) {
        if(dto.getId() != null) return repository.existsById(dto.getId()) && repository.existsByName(dto.getName());
        else return repository.existsByName(dto.getName());
    }

}
