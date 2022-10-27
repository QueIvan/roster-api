package queivan.fetcher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.fetcher.domain.PathDto;
import queivan.fetcher.facade.PathFacade;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"https://roster.queivan.com", "https://192.168.1.231:3000"})
@RestController
@RequestMapping("/paths")
@RequiredArgsConstructor
public class PathController {
    private final PathFacade facade;

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public PathDto getPathById(@PathVariable("id") UUID id, @RequestHeader("X-IssuerId") String issuerId) {
        return facade.getPathById(id, issuerId);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<PathDto> getAllPath(@RequestHeader("X-IssuerId") String issuerId) {
        return facade.getAllPaths(issuerId);
    }

    @GetMapping(value = "/active", produces = APPLICATION_JSON_VALUE)
    public List<PathDto> getAllActivePaths(@RequestHeader("X-IssuerId") String issuerId) {
        return facade.getAllActivePaths(issuerId);
    }


    @GetMapping(value = "/{email}/all", produces = APPLICATION_JSON_VALUE)
    public List<PathDto> getAllPathsByEmail(@PathVariable("email") String creatorEmail, @RequestHeader("X-IssuerId") String issuerId) {
        return facade.getAllPathsByEmail(creatorEmail, issuerId);
    }

    @GetMapping(value = "/search/{query}", produces = APPLICATION_JSON_VALUE)
    public List<PathDto> getAllMatchingPaths(@PathVariable("query") String searchQuery, @RequestHeader("X-IssuerId") String issuerId) {
        return facade.getAllMatchingPaths(searchQuery, issuerId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public PathDto createPath(@RequestBody PathDto dto, @RequestHeader("X-IssuerId") String issuerId) {
        return facade.createPath(dto, issuerId);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public PathDto updatePath(@RequestBody PathDto dto, @RequestHeader("X-IssuerId") String issuerId) {
        return facade.updatePath(dto, issuerId);
    }

    @DeleteMapping(value = "/{id}/delete")
    public void deletePath(@PathVariable("id") UUID id, @RequestHeader("X-IssuerId") String issuerId) {
        facade.deletePath(id, issuerId);
    }
}
