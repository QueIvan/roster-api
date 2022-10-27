package queivan.fetcher.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PathDto {
    private UUID id;
    private String title;
    private String abbreviation;
    private String imageUrl;
    private List<ConfigDto> configs;
    private EmailAuthorizationDto authorized;
}