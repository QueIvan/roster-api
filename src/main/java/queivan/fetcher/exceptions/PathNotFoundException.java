package queivan.fetcher.exceptions;

public class PathNotFoundException extends RuntimeException {
    public PathNotFoundException() {
        super("Path with given content does not exist in DB");
    }
}
