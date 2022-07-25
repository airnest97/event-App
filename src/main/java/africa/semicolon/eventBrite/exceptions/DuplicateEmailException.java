package africa.semicolon.eventBrite.exceptions;

public class DuplicateEmailException extends EventBriteException{

    public DuplicateEmailException(String message) {
        super(message);
    }
}
