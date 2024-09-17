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
    
    private static final int MIN_RAGE                       = 0;
    private static final int MAX_RAGE                       = 30;
    private static final int ENRAGE_VALUE                   = 15;
    private static final int BERSERK_COST                   = 30;
    private static final int INITIAL_DAMAGE_MODIFIER        = 1;
    private static final int BERSERK_DAMAGE_MODIFIER        = 2;
    private static final int INITIAL_DAMAGE_VULNERABILITY   = 1;
    private static final int BERSERK_DAMAGE_VULNERABILITY   = 2;
    private static final int BASE_DAMAGE                    = 15;
    
    private int rage;
    private int damageMultiplier;
    private int damageVulnerabilityMultiplier;
    
    
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
        this.damageMultiplier = INITIAL_DAMAGE_MODIFIER;
        this.damageVulnerabilityMultiplier = INITIAL_DAMAGE_VULNERABILITY;
        
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
     * Increases the orc's rage value by {@value ENRAGE_VALUE}, up to {@value MAX_RAGE}.
     */
    public void enrage() {
        rage += ENRAGE_VALUE;
        
        if (rage > MAX_RAGE) {
            rage = MAX_RAGE;
        }
    }
    
    /**
     * Allows the orc to go berserk, increasing its damage multiplier
     * to {@value BERSERK_DAMAGE_MODIFIER} and its damage taken multiplier
     * to {@value BERSERK_DAMAGE_VULNERABILITY}.
     * The orc must have enough rage (at least {@value BERSERK_COST}) to
     * perform this action, and it will be consumed if it does.
     *
     * @throws LowRageException if the orc's rage is too low to berserk
     */
    public void berserk()
            throws LowRageException {
        
        if (rage < BERSERK_COST) {
            throw new LowRageException("Not enough Rage!");
        }
        
        rage -= BERSERK_COST;
        damageMultiplier = BERSERK_DAMAGE_MODIFIER;
        damageVulnerabilityMultiplier = BERSERK_DAMAGE_VULNERABILITY;
        
    }
    
    /**
     * Allows the orc to strike another creature dealing {@value BASE_DAMAGE} damage multiplied
     * by its damage multiplier ({@value INITIAL_DAMAGE_MODIFIER} or {@value BERSERK_DAMAGE_MODIFIER}).
     *
     * @param creature the target creature to strike
     */
    public void cleave(final Creature creature) {
        
        creature.takeDamage(BASE_DAMAGE * damageMultiplier);
        
    }
    
    /**
     * Reduces the orc's health by a specified damage amount
     * multiplied by the orc's damage vulnerability multiplier
     * ({@value INITIAL_DAMAGE_VULNERABILITY} or {@value BERSERK_DAMAGE_VULNERABILITY}).
     *
     * @see Creature#takeDamage
     *
     * @param damage the amount of damage. Must be a non-negative value.
     *
     * @throws DamageException if the damage value is negative.
     */
    @Override
    public void takeDamage(final int damage) throws DamageException {
        
        super.takeDamage(damage * damageVulnerabilityMultiplier);

    }
}
