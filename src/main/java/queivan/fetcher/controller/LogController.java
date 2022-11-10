package queivan.fetcher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.fetcher.domain.LogDto;
import queivan.fetcher.facade.LogFacade;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"https://roster.queivan.com", "https://192.168.1.231:3000", "https://172.20.10.4:3000"})
@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {
    private final LogFacade facade;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<LogDto> getAllLogs(@RequestHeader("X-IssuerId") String issuerId) {
        return facade.getAllLogs(issuerId);
    }

}
