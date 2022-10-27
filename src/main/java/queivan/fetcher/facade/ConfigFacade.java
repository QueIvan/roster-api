package queivan.fetcher.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.fetcher.domain.ConfigDto;
import queivan.fetcher.service.ConfigService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ConfigFacade {
    private final ConfigService service;

    public ConfigDto getConfigById(UUID id, String issuerId) {
        return service.getConfigById(id, issuerId);
    }

    public List<ConfigDto> getAllConfigs(String issuerId) {
        return service.getAllConfigs(issuerId);
    }

    public List<ConfigDto> getAllActiveConfigs(String issuerId) {
        return service.getAllActiveConfigs(issuerId);
    }

    public List<ConfigDto> getAllConfigsByEmail(String creatorEmail, String issuerId) {
        return service.getAllConfigsByEmail(creatorEmail, issuerId);
    }

    public Boolean fetchConfig(UUID id, String issuerId) throws IOException, InterruptedException {
        return service.fetchConfig(id, issuerId);
    }

    public Boolean fetchAllConfigs(String issuerId) throws IOException, InterruptedException {
        return service.fetchAllConfigs(issuerId);
    }

    public ConfigDto createConfig(ConfigDto dto, String issuerId) {
        return service.createConfig(dto, issuerId);
    }

    public ConfigDto updateConfig(ConfigDto dto, String issuerId) {
        return service.updateConfig(dto, issuerId);
    }

    public void deleteConfig(UUID id, String issuerId) {
        service.deleteConfig(id, issuerId);
    }

}
