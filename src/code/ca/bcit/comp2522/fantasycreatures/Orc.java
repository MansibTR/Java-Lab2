package ca.bcit.comp2522.fantasycreatures;

/**
 * The {@code Orc} class represents an Orc, a subclass of {@code Creature},
 * with additional attributes and behavior related to Rage.
 * The Orc can go berserk, increasing rage, and dealing damage to other creatures.
 *
 * <p>
 * Validates that the Orc's rage is between
 * {@value MIN_RAGE} and {@value MAX_RAGE}.
 * </p>
 *
 * @author Ben Henry, Mansib Talukder
 * @version 1.0
 */
public class Orc extends Creature {
    
    private static final int MIN_RAGE                   = 0;
    private static final int MAX_RAGE                   = 30;
    private static final int RAGE_COST                  = 5;
    private static final int RAGE_DOUBLE_DAMAGE         = 30;
    private static final int DOUBLE_DAMAGE_CONDITION    = 20;
    
    private int rage;
    
    /**
     * Constructs a new {@code Orc} object.
     *
     * @param name          name. Must not be null or empty.
     * @param dateOfBirth   date of birth. Must not be in the future.
     * @param rage          rage of the orc. Must be between {@value MIN_RAGE} and {@value MAX_RAGE}.
     *
     * @throws IllegalArgumentException if the name is invalid or if the date of birth is in the future.
     */
    public Orc(final String name, final Date dateOfBirth, final int rage)
            throws IllegalArgumentException {
        super(name, dateOfBirth);
        
        validateRage();
        this.rage = rage;
    }
    
    /**
     * Getter method for rage.
     * @return rage of the orc
     */
    public int getRage() {
        return this.rage;
    }
    
    /**
     * Provides a detailed description of the orc,
     * including its name, date of birth, age, health, and rage.
     *
     * @return the orc's details.
     */
    @Override
    public String getDetails() {
        StringBuilder sb;
        String creatureDetails;
        String string;
        
        sb = new StringBuilder();
        creatureDetails = super.getDetails();
        
        sb.append(creatureDetails);
        sb.append("\nRage: ");
        sb.append(rage);
        
        string = sb.toString();
        
        return string;
    }
    
    /**
     * This method validates the rage of the orc.
     * Rage must be between {@value MIN_RAGE} and {@value MAX_RAGE}.
     *
     * @throws IllegalArgumentException if rage is less than {@value MIN_RAGE} or greater than {@value MAX_RAGE}.
     */
    private void validateRage() {
        if (rage < MIN_RAGE || rage > MAX_RAGE) {
            throw new IllegalArgumentException("Rage must be between" +
                                               MIN_RAGE + " and " + MAX_RAGE);
        }
    }
    
    /**
     * Allows the orc to go berserk, reducing the target's health by
     * a fixed amount. The orc must have enough rage (at least {@value RAGE_COST}) to
     * perform this action.
     *
     * @param creature the target creature to berserk on
     *
     * @throws LowRageException if the orc's rage is too low to berserk
     */
    public void berserk(final Creature creature)
            throws LowRageException {
        
        if (rage < RAGE_COST) {
            throw new LowRageException("Not enough Rage!");
        }
        
        rage += RAGE_COST;
        
        if (rage > DOUBLE_DAMAGE_CONDITION) {
            creature.takeDamage(RAGE_DOUBLE_DAMAGE);
        }
    }
}
