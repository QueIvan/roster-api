package queivan.fetcher.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.fetcher.domain.LogDto;
import queivan.fetcher.service.LogService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LogFacade {
    private final LogService service;

    public List<LogDto> getAllLogs(String issuerId) {
        return service.getAllLogs(issuerId);
    }

}
