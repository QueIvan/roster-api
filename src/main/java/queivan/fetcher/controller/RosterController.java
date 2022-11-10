package queivan.fetcher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.fetcher.domain.RosterDto;
import queivan.fetcher.facade.RosterFacade;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"https://roster.queivan.com", "https://192.168.1.231:3000", "https://172.20.10.4:3000"})
@RestController
@RequestMapping("/rosters")
@RequiredArgsConstructor
public class RosterController {
    private final RosterFacade facade;

    @GetMapping(value = "/{name}", produces = APPLICATION_JSON_VALUE)
    public RosterDto getRosterByName(@PathVariable("name") String name, @RequestHeader("X-IssuerId") String issuerId) {
        return facade.getRosterByName(name, issuerId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public RosterDto createNewRoster(@RequestBody RosterDto dto, @RequestHeader("X-IssuerId") String issuerId) {
        return facade.createNewRoster(dto, issuerId);
    }
}
