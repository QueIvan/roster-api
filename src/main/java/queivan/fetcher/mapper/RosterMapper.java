package queivan.fetcher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.fetcher.domain.Roster;
import queivan.fetcher.domain.RosterDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RosterMapper {
    RosterDto mapRosterToRosterDto(Roster roster);
    Roster mapRosterDtoToRoster(RosterDto dto);
}
