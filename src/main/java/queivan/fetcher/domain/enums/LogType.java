package queivan.fetcher.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LogType {
    INFO("info"),
    ERROR("error");

    private final String type;

}
