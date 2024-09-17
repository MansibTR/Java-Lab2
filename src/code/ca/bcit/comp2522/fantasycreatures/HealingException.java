package ca.bcit.comp2522.fantasycreatures;

/**
 * {@code HealingException} is an unchecked exception that is thrown when
 * a negative healing amount is provided.
 * It extends {@code IllegalArgumentException}.
 *
 * @author Ben Henry, Mansib Talukder
 * @version 1.0
 */
public class HealingException extends IllegalArgumentException {
    HealingException(String message) {
        super(message);
    }
}
