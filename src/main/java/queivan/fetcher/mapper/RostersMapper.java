package queivan.fetcher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.fetcher.domain.Rosters;
import queivan.fetcher.domain.RostersDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RostersMapper {
    RostersDto mapRosterToRosterDto(Rosters roster);
    Rosters mapRosterDtoToRoster(RostersDto dto);
}
