package queivan.fetcher.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.fetcher.domain.EmailAuthorizationDto;
import queivan.fetcher.service.EmailAuthorizationService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmailAuthorizationFacade {
    private final EmailAuthorizationService service;

    public EmailAuthorizationDto toggleElementAuthorization(UUID id, String issuerId) {
        return service.toggleElementAuthorization(id, issuerId);
    }

}
