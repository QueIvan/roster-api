package queivan.fetcher.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import queivan.fetcher.domain.Config;
import queivan.fetcher.domain.ConfigDto;
import queivan.fetcher.domain.LogDto;
import queivan.fetcher.exceptions.ConfigFoundException;
import queivan.fetcher.exceptions.ConfigNotFoundException;
import queivan.fetcher.mapper.ConfigMapper;
import queivan.fetcher.service.repositories.ConfigRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfigService {
    private final ConfigRepository repository;
    private final FetcherService fetcher;
    private final ConfigMapper mapper;
    private final LogService logger;

    //region GETTERS

    public ConfigDto getConfigById(UUID id, String issuerId) {
        Config configs = repository.findById(id).orElseThrow(ConfigNotFoundException::new);
        logger.info(LogDto.builder()
                .content(String.format("Fetching config with id: %s [issuerId: %s]", id, issuerId))
                .issuerId(issuerId)
                .build());
        return mapper.mapConfigToConfigDto(configs);
    }

    public List<ConfigDto> getAllConfigs(String issuerId) {
        List<Config> configs = repository.findAll();
        logger.info(LogDto.builder()
                .content(String.format("Fetching all saved configs [issuerId: %s]", issuerId))
                .issuerId(issuerId)
                .build());
        return mapper.mapConfigListToConfigDtoList(configs);
    }

    public List<ConfigDto> getAllActiveConfigs(String issuerId) {
        List<UUID> configIds = repository.findAllIdsByStatus(true);
        List<Config> configs = repository.findAllById(configIds);
        logger.info(LogDto.builder()
                .content(String.format("Fetching all authorized configs [issuerId: %s]", issuerId))
                .issuerId(issuerId)
                .build());
        return mapper.mapConfigListToConfigDtoList(configs);
    }

    public List<ConfigDto> getAllConfigsByEmail(String creatorEmail, String issuerId) {
        List<UUID> configIds = repository.findAllIdsByEmail(creatorEmail);
        List<Config> configs = repository.findAllById(configIds);
        logger.info(LogDto.builder()
                .content(String.format("Fetching all configs, created by %s [issuerId: %s]", creatorEmail, issuerId))
                .issuerId(issuerId)
                .build());
        return mapper.mapConfigListToConfigDtoList(configs);
    }

    //endregion

    //region OPERATIONS

    public Boolean fetchConfig(UUID id, String issuerId) throws IOException, InterruptedException {
        System.out.println(id);
        doesConfigExistById(id, issuerId);
        Boolean result = fetcher.fetchConfig(id);
        logger.info(LogDto.builder()
                .content(String.format("Fetching data about config with id: %s from wiki [issuerId: %s]", id, issuerId))
                .issuerId(issuerId)
                .build());
        return result;
    }

    public Boolean fetchAllConfigs(String issuerId) throws IOException, InterruptedException {
        Boolean result = fetcher.fetchAllConfigs();
        logger.info(LogDto.builder()
                .content(String.format("Fetching data about all configs from wiki [issuerId: %s]", issuerId))
                .issuerId(issuerId)
                .build());
        return result;
    }

    public ConfigDto createConfig(ConfigDto dto, String issuerId) {
        doesConfigNotExist(dto, issuerId);
        Config config = repository.save(mapper.mapConfigDtoToConfig(dto));
        logger.info(LogDto.builder()
                .content(String.format("Created new config with id: %s [issuerId: %s]", config.getId(), issuerId))
                .issuerId(issuerId)
                .build());
        return mapper.mapConfigToConfigDto(config);
    }

    public ConfigDto updateConfig(ConfigDto dto, String issuerId){
        doesConfigExistById(dto.getId(), issuerId);
        Config config = repository.save(mapper.mapConfigDtoToConfig(dto));
        logger.info(LogDto.builder()
                .content(String.format("Updated config with id: %s [issuerId: %s]", config.getId(), issuerId))
                .issuerId(issuerId)
                .build());
        return mapper.mapConfigToConfigDto(config);
    }

    public void deleteConfig(UUID id, String issuerId) {
        doesConfigExistById(id, issuerId);
        repository.deleteById(id);
        logger.info(LogDto.builder()
                .content(String.format("Deleted config with id: %s [issuerId: %s]", id, issuerId))
                .issuerId(issuerId)
                .build());
    }

    //endregion

    //region CHECKERS

    private void doesConfigExistById(UUID id, String issuerId){
        if(!repository.existsById(id)) {
            logger.error(LogDto.builder()
                    .content(String.format("Config with id: %s does not exist [issuerId: %s]", id, issuerId))
                    .issuerId(issuerId)
                    .build());
            throw new ConfigNotFoundException();
        }
    }

    private void doesConfigNotExistByName(String name, String issuerId){
        if(repository.existsByName(name)) {
            logger.error(LogDto.builder()
                    .content(String.format("Config with name: %s already exist [issuerId: %s]", name, issuerId))
                    .issuerId(issuerId)
                    .build());
            throw new ConfigFoundException();
        }
    }

    private void doesConfigNotExistByPath(String path, String issuerId){
        if(repository.existsByPath(path)) {
            logger.error(LogDto.builder()
                    .content(String.format("Config with path: %s already exist [issuerId: %s]", path, issuerId))
                    .issuerId(issuerId)
                    .build());
            throw new ConfigFoundException();
        }
    }

    private void doesConfigNotExist(@NotNull ConfigDto dto, String issuerId){
        doesConfigNotExistByName(dto.getName(), issuerId);
        doesConfigNotExistByPath(dto.getPath(), issuerId);
    }

    //endregion

}
