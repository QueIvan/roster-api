package queivan.fetcher.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OptionsConfigDto {
    private Boolean ignore;
    private Boolean training;
    private Boolean ranks;
    private Boolean leo;
    private Boolean certifications;
    private Boolean removeLogo;
    private Boolean subdivision;
    private Boolean pathed;
}
