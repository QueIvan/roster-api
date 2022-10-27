package queivan.fetcher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.fetcher.domain.Config;
import queivan.fetcher.domain.ConfigDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConfigMapper {
    List<ConfigDto> mapConfigListToConfigDtoList(List<Config> configs);
    Config mapConfigDtoToConfig(ConfigDto dto);
    ConfigDto mapConfigToConfigDto(Config config);
}
