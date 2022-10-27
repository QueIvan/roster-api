package queivan.fetcher.exceptions;

public class AuthorizationNotFoundException extends RuntimeException {
    public AuthorizationNotFoundException() {
        super("Authorization with given content does not exist in DB");
    }
}
