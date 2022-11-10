package queivan.fetcher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.fetcher.domain.ConfigDto;
import queivan.fetcher.facade.ConfigFacade;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"https://roster.queivan.com", "https://192.168.1.231:3000", "https://172.20.10.4:3000"})
@RestController
@RequestMapping("/configs")
@RequiredArgsConstructor
public class ConfigController {
    private final ConfigFacade facade;

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ConfigDto getConfigById(@PathVariable("id") UUID id, @RequestHeader("X-IssuerId") String issuerId) {
        return facade.getConfigById(id, issuerId);
    }

    @GetMapping(value = "/fetch", produces = APPLICATION_JSON_VALUE)
    public Boolean fetchAllConfigs( @RequestHeader("X-IssuerId") String issuerId) throws IOException, InterruptedException {
        return facade.fetchAllConfigs(issuerId);
    }

    @GetMapping(value = "/{id}/fetch", produces = APPLICATION_JSON_VALUE)
    public Boolean fetchConfig(@PathVariable("id") UUID id, @RequestHeader("X-IssuerId") String issuerId) throws IOException, InterruptedException {
        return facade.fetchConfig(id, issuerId);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ConfigDto> getAllConfig(@RequestHeader("X-IssuerId") String issuerId) {
        return facade.getAllConfigs(issuerId);
    }

    @GetMapping(value = "/active", produces = APPLICATION_JSON_VALUE)
    public List<ConfigDto> getAllActiveConfigs(@RequestHeader("X-IssuerId") String issuerId) {
        return facade.getAllActiveConfigs(issuerId);
    }


    @GetMapping(value = "/{email}/all", produces = APPLICATION_JSON_VALUE)
    public List<ConfigDto> getAllConfigsByEmail(@PathVariable("email") String creatorEmail, @RequestHeader("X-IssuerId") String issuerId) {
        return facade.getAllConfigsByEmail(creatorEmail, issuerId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConfigDto createConfig(@RequestBody ConfigDto dto, @RequestHeader("X-IssuerId") String issuerId) {
        return facade.createConfig(dto, issuerId);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConfigDto updateConfig(@RequestBody ConfigDto dto, @RequestHeader("X-IssuerId") String issuerId) {
        return facade.updateConfig(dto, issuerId);
    }

    @DeleteMapping(value = "/{id}/delete")
    public void deleteConfig(@PathVariable("id") UUID id, @RequestHeader("X-IssuerId") String issuerId) {
        facade.deleteConfig(id, issuerId);
    }

}
