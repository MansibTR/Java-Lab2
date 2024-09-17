package ca.bcit.comp2522.fantasycreatures;

/**
 * {@code LowManaException} is a checked exception that is thrown when
 * an elf attempts to perform an action, such as casting spell, but does not
 * have sufficient mana to do so.
 *
 * <p>
 * This exception ensures that certain actions can only be performed when the
 * elf's mana meets the minimum required threshold.
 * </p>
 *
 * @author Ben Henry, Mansib Talukder
 * @version 1.0
 */
public class LowManaException extends Exception {
    LowManaException(final String message) {
        super(message);
    }
}
