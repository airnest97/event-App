package africa.semicolon.eventBrite.exceptions;

public class UserDoesNotExistException extends EventBriteException {

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
