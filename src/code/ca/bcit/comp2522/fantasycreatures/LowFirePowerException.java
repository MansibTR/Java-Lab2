package ca.bcit.comp2522.fantasycreatures;

/**
 * {@code LowFirePowerException} is a checked exception that is thrown when
 * a dragon attempts to perform an action, such as breathing fire, but does not
 * have sufficient firepower to do so.
 *
 * <p>
 * This exception ensures that certain actions can only be performed when the
 * dragon's firepower meets the minimum required threshold.
 * </p>
 *
 * @author Ben Henry, Mansib Talukder
 * @version 1.0
 */
public class LowFirePowerException extends Exception {
    LowFirePowerException(final String message) {
        super(message);
    }
}
