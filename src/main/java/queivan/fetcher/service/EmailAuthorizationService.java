package queivan.fetcher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import queivan.fetcher.domain.EmailAuthorization;
import queivan.fetcher.domain.EmailAuthorizationDto;
import queivan.fetcher.domain.LogDto;
import queivan.fetcher.exceptions.AuthorizationNotFoundException;
import queivan.fetcher.mapper.EmailAuthorizationMapper;
import queivan.fetcher.service.repositories.EmailAuthorizationRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailAuthorizationService {
    private final EmailAuthorizationRepository repository;
    private final EmailAuthorizationMapper mapper;
    private final LogService logger;

    //region OPERATIONS

    public EmailAuthorizationDto toggleElementAuthorization(UUID targetId, String issuerId) {
        EmailAuthorization authorization = getElement(targetId);
        logger.info(LogDto.builder()
                .content(String.format("Fetched authorization request with id: %s", authorization.getId()))
                .issuerId(issuerId)
                .build());
        authorization.setStatus(!authorization.getStatus());
        repository.save(authorization);
        logger.info(LogDto.builder()
                .content(String.format("Toggle authorization status of request with id: %s", authorization.getId()))
                .issuerId(issuerId)
                .build());
        return mapper.mapEmailAuthorizationToEmailAuthorizationDto(authorization);
    }

    public EmailAuthorization getElement(UUID targetId) {
        if(repository.existsByPathId(targetId)) return repository.findByPathId(targetId);
        else if(repository.existsByConfigId(targetId)) return repository.findByConfigId(targetId);
        else throw new AuthorizationNotFoundException();
    }

    //endregion

}
