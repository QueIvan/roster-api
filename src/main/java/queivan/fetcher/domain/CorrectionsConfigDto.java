package queivan.fetcher.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CorrectionsConfigDto {
    private UUID id;
    private String target;
    private String category;
    private String value;
}
