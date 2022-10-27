package queivan.fetcher.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LogType {
    INFO("info"),
    ERROR("error"),
    DEBUG("debug");

    private final String type;

}
