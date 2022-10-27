package queivan.fetcher.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import queivan.fetcher.domain.enums.LogType;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LogDto {
    private UUID id;
    private LogType type;
    private String content;
    private String issuerId;
    private LocalDateTime date;
}