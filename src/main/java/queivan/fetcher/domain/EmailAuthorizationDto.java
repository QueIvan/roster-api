package queivan.fetcher.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmailAuthorizationDto {
    private String email;
    @Builder.Default
    private Boolean status = false;
}
