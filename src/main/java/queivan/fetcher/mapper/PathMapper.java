package queivan.fetcher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.fetcher.domain.Path;
import queivan.fetcher.domain.PathDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PathMapper {
    PathDto mapPathToPathDto(Path paths);
    List<PathDto> mapPathListToPathDtoList(List<Path> paths);

    Path mapPathDtoToPath(PathDto dto);
}
