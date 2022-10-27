package queivan.fetcher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.fetcher.domain.Log;
import queivan.fetcher.domain.LogDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogMapper{
    List<LogDto> mapLogListToLogDtoList(List<Log> fetched);
    Log mapLogDtoToLog(LogDto dto);
}
