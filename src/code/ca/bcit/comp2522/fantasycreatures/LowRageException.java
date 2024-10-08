package ca.bcit.comp2522.fantasycreatures;

/**
 * {@code LowRageException} is an unchecked exception that is thrown when
 * an Orc cannot go berserk due to insufficient rage.
 * It extends {@code RuntimeException}.
 *
 * @author Ben Henry
 * @author Mansib Talukder
 * @version 1.0
 */
public class LowRageException extends RuntimeException {
    LowRageException(final String message) {
        super(message);
    }
}
