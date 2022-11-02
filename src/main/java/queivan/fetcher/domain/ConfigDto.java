package queivan.fetcher.domain;

import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ConfigDto {
    private UUID id;
    private String name;
    private String wikiPath;
    private String rosterPath;
    private Set<String> ignoredHeaders;
    private String splitPath;
    private Set<String> splitHeaders;
    private OptionsConfigDto options;
    private List<CorrectionsConfigDto> corrections;
    private EmailAuthorizationDto authorized;
}
