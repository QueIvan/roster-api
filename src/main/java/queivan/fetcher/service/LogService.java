package queivan.fetcher.service;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import queivan.fetcher.domain.Log;
import queivan.fetcher.domain.LogDto;
import queivan.fetcher.domain.enums.LogType;
import queivan.fetcher.mapper.LogMapper;
import queivan.fetcher.service.repositories.LogRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository repository;
    private final LogMapper mapper;

    //region GETTERS

    public List<LogDto> getAllLogs(String issuerId){
        List<Log> fetched = repository.findAll();
        info(LogDto.builder()
                .content("Fetching all logs")
                .issuerId(issuerId)
                .build());
        return mapper.mapLogListToLogDtoList(fetched);
    }

    //endregion

    //region SETTER

    public void createLog(@NotNull LogDto dto){
        dto.setDate(LocalDateTime.now());
        repository.save(mapper.mapLogDtoToLog(dto));
    }

    //endregion

    //region LOG METHODS

    public void info(@NotNull LogDto dto){
        dto.setType(LogType.INFO);
        log.info(dto.getContent());
        createLog(dto);
    }

    public void error(@NotNull LogDto dto){
        dto.setType(LogType.ERROR);
        log.error(dto.getContent());
        createLog(dto);
    }

    //endregion

}
