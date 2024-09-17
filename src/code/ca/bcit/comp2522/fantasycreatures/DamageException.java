package ca.bcit.comp2522.fantasycreatures;

/**
 * {@code DamageException} is an unchecked exception that is thrown when
 * a negative damage value is provided.
 * It extends {@code IllegalArgumentException}.
 *
 * @author Ben Henry, Mansib Talukder
 * @version 1.0
 */
public class DamageException extends IllegalArgumentException {
    DamageException(String message) {
        super(message);
    }
}
