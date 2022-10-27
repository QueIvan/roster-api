package queivan.fetcher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.fetcher.domain.EmailAuthorizationDto;
import queivan.fetcher.facade.EmailAuthorizationFacade;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"https://roster.queivan.com", "https://192.168.1.231:3000"})
@RestController
@RequestMapping("/authorize")
@RequiredArgsConstructor
public class EmailAuthorizationController {
    private final EmailAuthorizationFacade facade;

    @PutMapping(value="/{id}", produces = APPLICATION_JSON_VALUE)
    public EmailAuthorizationDto toggleElementAuthorization(@PathVariable("id") UUID id, @RequestHeader("X-IssuerId") String issuerId){
        return facade.toggleElementAuthorization(id, issuerId);
    }

}
