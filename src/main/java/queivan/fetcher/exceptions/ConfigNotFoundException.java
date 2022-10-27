package queivan.fetcher.exceptions;

public class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException() {
        super("Config with given content does not exist in DB");
    }
}
