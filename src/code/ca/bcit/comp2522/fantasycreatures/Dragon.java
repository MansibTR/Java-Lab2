package ca.bcit.comp2522.fantasycreatures;

/**
 * The {@code Dragon} class represents a Dragon, a subclass of {@code Creature},
 * with additional attributes and behavior related to firepower.
 * The Dragon can breathe fire, dealing damage to other creatures,
 * and can restore its firepower.
 * <p>
 * Validates that the Dragon's firepower is between
 * {@value MIN_FIREPOWER} and {@value MAX_FIREPOWER}.
 * </p>
 *
 * @author Ben Henry, Mansib Talukder
 * @version 1.0
 */
public class Dragon extends Creature{

    private static final int MIN_FIREPOWER = 0;
    private static final int MAX_FIREPOWER = 100;
    private static final int FIRE_BREATH_COST = 10;
    private static final int FIRE_BREATH_DAMAGE = 20;

    private int firepower;
    
    /**
     * Constructs a new {@code Dragon} object
     *
     * @param name the name of the dragon. Must not be null or empty.
     * @param dateOfBirth the date of birth of the dragon. Must not be in the future.
     * @param firepower the initial firepower of the dragon. Must be between {@value MIN_FIREPOWER} and {@value MAX_FIREPOWER}.
     * @throws IllegalArgumentException if the name, date of birth, or firepower are invalid.
     */
    public Dragon(final String name, final Date dateOfBirth, final int firepower)
            throws IllegalArgumentException {
        
        super(name, dateOfBirth);
        this.firepower = firepower;
    }
    
    /**
     * Getter method for firepower.
     * @return current firepower of the Dragon
     */
    public int getFirepower() {
        return this.firepower;
    }
    
    /**
     * Provides a detailed description of the dragon,
     * including its name, date of birth, age, health, and firepower.
     *
     * @return the dragon's details.
     */
    @Override
    public String getDetails() {
        StringBuilder sb;
        String creatureDetails;
        String string;
        
        sb = new StringBuilder();
        creatureDetails = super.getDetails();
        
        sb.append(creatureDetails);
        sb.append("\nFire Power: ");
        sb.append(firepower);
        
        string = sb.toString();

        return string;

    }
    
    /**
     * This method validates the firepower of the dragon.
     * Firepower must be between {@value MIN_FIREPOWER} and {@value MAX_FIREPOWER}.
     *
     * @throws IllegalArgumentException if firepower is less than {@value MIN_FIREPOWER} or greater than {@value MAX_FIREPOWER}.
     */
    private void validateFirepower() {
        if (firepower < MIN_FIREPOWER || firepower > MAX_FIREPOWER) {
            throw new IllegalArgumentException("Firepower must be between" +
                    MIN_FIREPOWER + " and " + MAX_FIREPOWER);
        }
    }
    
    /**
     * Allows the dragon to breathe fire on another creature, reducing the target's health by
     * a fixed amount. The dragon must have enough firepower (at least {@value FIRE_BREATH_COST}) to
     * perform this action.
     *
     * @param creature the target creature to breathe fire on.
     * @throws LowFirePowerException if the dragon's firepower is too low to breathe fire.
     */
    public void breatheFire(final Creature creature)
            throws LowFirePowerException {
        
        if (firepower < FIRE_BREATH_COST) {
            throw new LowFirePowerException("Not enough firepower!");
        }
        
        firepower -= FIRE_BREATH_COST;
        creature.takeDamage(FIRE_BREATH_DAMAGE);

    }
    
    /**
     * Restores the dragon's firepower by a specified amount.
     * The firepower cannot exceed {@value MAX_FIREPOWER}.
     *
     * @param amount the amount of firepower to restore.
     */
    public void restoreFirePower(final int amount) {
        firepower += amount;

        if (firepower > MAX_FIREPOWER) {
            firepower = MAX_FIREPOWER;
        }
    }
}
