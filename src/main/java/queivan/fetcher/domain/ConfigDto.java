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
    private String path;
    private Set<String> ignoredHeaders;
    private Boolean split;
    private String splitHeader;
    private OptionsConfigDto options;
    private List<CorrectionsConfigDto> corrections;
    private EmailAuthorizationDto authorized;
}
