package queivan.fetcher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.fetcher.domain.Replacements;
import queivan.fetcher.facade.ReplacementsFacade;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"https://roster.queivan.com", "https://192.168.1.231:3000"})
@RestController
@RequestMapping("/replacements")
@RequiredArgsConstructor
public class ReplacementsController {
    private final ReplacementsFacade facade;

    @GetMapping(value = "/{query}", produces = APPLICATION_JSON_VALUE)
    public Replacements getReplacementsForSearchTitle(@PathVariable("query") String searchQuery, @RequestHeader("X-IssuerId") String issuerId){
        return facade.getReplacementsForSearchTitle(searchQuery, issuerId);
    }

}
