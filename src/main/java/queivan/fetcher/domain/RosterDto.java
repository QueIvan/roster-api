package queivan.fetcher.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RosterDto {
    private UUID id;
    private String name;
    private String json;
}
