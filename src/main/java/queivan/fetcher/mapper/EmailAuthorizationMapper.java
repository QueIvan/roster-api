package queivan.fetcher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.fetcher.domain.EmailAuthorization;
import queivan.fetcher.domain.EmailAuthorizationDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmailAuthorizationMapper {
    EmailAuthorizationDto mapEmailAuthorizationToEmailAuthorizationDto(EmailAuthorization authorization);
}
