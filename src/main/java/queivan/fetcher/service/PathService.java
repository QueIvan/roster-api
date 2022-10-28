package queivan.fetcher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import queivan.fetcher.domain.*;
import queivan.fetcher.exceptions.ConfigNotFoundException;
import queivan.fetcher.exceptions.PathNotFoundException;
import queivan.fetcher.mapper.PathMapper;
import queivan.fetcher.service.repositories.PathRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PathService {
    private final PathRepository repository;
    private final PathMapper mapper;
    private final LogService logger;

    //region GETTERS

    public PathDto getPathById(UUID id, String issuerId) {
        Path paths = repository.findById(id).orElseThrow(PathNotFoundException::new);
        logger.info(LogDto.builder()
                .content(String.format("Fetching path with id: %s", id))
                .issuerId(issuerId)
                .build());
        return mapper.mapPathToPathDto(paths);
    }

    public List<PathDto> getAllPaths(String issuerId) {
        List<Path> paths = repository.findAll();
        logger.info(LogDto.builder()
                .content("Fetching all saved paths")
                .issuerId(issuerId)
                .build());
        return mapper.mapPathListToPathDtoList(paths);
    }

    public List<PathDto> getAllActivePaths(String issuerId) {
        List<UUID> pathIds = repository.findAllIdsByStatus(true);
        List<Path> paths = repository.findAllById(pathIds);
        logger.info(LogDto.builder()
                .content("Fetching all authorized paths")
                .issuerId(issuerId)
                .build());
        return mapper.mapPathListToPathDtoList(paths);
    }

    public List<PathDto> getAllPathsByEmail(String creatorEmail, String issuerId) {
        List<UUID> pathIds = repository.findAllIdsByEmail(creatorEmail);
        List<Path> paths = repository.findAllById(pathIds);
        logger.info(LogDto.builder()
                .content(String.format("Fetching all paths, created by %s", creatorEmail))
                .issuerId(issuerId)
                .build());
        return mapper.mapPathListToPathDtoList(paths);
    }

    public List<PathDto> getAllMatchingPaths(String searchQuery, String issuerId){
        List<Path> paths = repository.findAllByTitleContainingAndAbbreviationContaining(searchQuery);
        logger.info(LogDto.builder()
                .content(String.format("Fetching all paths matching search query: %s", searchQuery))
                .issuerId(issuerId)
                .build());
        return mapper.mapPathListToPathDtoList(paths);
    }

    //endregion

    //region OPERATIONS

    public PathDto createPath(PathDto dto, String issuerId) {
        doesPathExist(dto.getTitle(), issuerId);
        Path path = repository.save(mapper.mapPathDtoToPath(dto));
        logger.info(LogDto.builder()
                .content(String.format("Created new path with id: %s", path.getId()))
                .issuerId(issuerId)
                .build());
        return mapper.mapPathToPathDto(path);
    }

    public PathDto updatePath(PathDto dto, String issuerId){
        doesPathExistById(dto.getId(), issuerId);
        Path path = repository.save(mapper.mapPathDtoToPath(dto));
        logger.info(LogDto.builder()
                .content(String.format("Updated path with id: %s", path.getId()))
                .issuerId(issuerId)
                .build());
        return mapper.mapPathToPathDto(path);
    }

    public void deletePath(UUID id, String issuerId) {
        doesPathExistById(id, issuerId);
        repository.deleteById(id);
        logger.info(LogDto.builder()
                .content(String.format("Deleted path with id: %s", id))
                .issuerId(issuerId)
                .build());
    }

    //endregion

    //region CHECKERS

    private void doesPathExistById(UUID id, String issuerId){
        if(!repository.existsById(id)) {
            logger.error(LogDto.builder()
                    .content(String.format("Path with id: %s does not exist", id))
                .issuerId(issuerId)
                    .build());
            throw new ConfigNotFoundException();
        }
    }

    private void doesPathExist(String title, String issuerId) {
        if(repository.existsByTitle(title)){
            logger.error(LogDto.builder()
                    .content(String.format("Path with title: %s already exist", title))
                .issuerId(issuerId)
                    .build());
            throw new ConfigNotFoundException();
        }
    }

    //endregion
}
