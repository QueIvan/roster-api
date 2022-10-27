package queivan.fetcher.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.fetcher.domain.PathDto;
import queivan.fetcher.service.PathService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PathFacade {
    private final PathService service;

    public PathDto getPathById(UUID id, String issuerId) {
        return service.getPathById(id, issuerId);
    }

    public List<PathDto> getAllPaths(String issuerId) {
        return service.getAllPaths(issuerId);
    }

    public List<PathDto> getAllActivePaths(String issuerId) {
        return service.getAllActivePaths(issuerId);
    }

    public List<PathDto> getAllPathsByEmail(String creatorEmail, String issuerId) {
        return service.getAllPathsByEmail(creatorEmail, issuerId);
    }
    public List<PathDto> getAllMatchingPaths(String searchQuery, String issuerId) {
        return service.getAllMatchingPaths(searchQuery, issuerId);
    }

    public PathDto createPath(PathDto dto, String issuerId) {
        return service.createPath(dto, issuerId);
    }

    public PathDto updatePath(PathDto dto, String issuerId) {
        return service.updatePath(dto, issuerId);
    }

    public void deletePath(UUID id, String issuerId) {
        service.deletePath(id, issuerId);
    }

}
