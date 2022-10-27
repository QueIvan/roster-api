package queivan.fetcher.exceptions;

public class ConfigFoundException extends RuntimeException {
    public ConfigFoundException() {
        super("Config with given content already exist in DB");
    }
}
