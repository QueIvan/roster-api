package queivan.fetcher.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CorrectionsConfigDto {
    private String target;
    private String category;
    private String value;
    private String before;
}
