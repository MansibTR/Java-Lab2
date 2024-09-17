package ca.bcit.comp2522.fantasycreatures;

/**
 * The {@code Elf} class represents an Elf, a subclass of {@code Creature},
 * with additional attributes and behavior related to Mana.
 * The Elf can cast spell, dealing damage to other creatures,
 * and can restore its mana.
 *
 * <p>
 * Validates that the Elf's mana is between
 * {@value MIN_MANA} and {@value MAX_MANA}.
 * </p>
 *
 * @author Ben Henry, Mansib Talukder
 * @version 1.0
 */
public class Elf extends Creature {
    
    private static final int MIN_MANA       = 0;
    private static final int MAX_MANA       = 50;
    private static final int MANA_COST      = 5;
    private static final int MANA_DAMAGE    = 10;
    
    private int mana;
    
    /**
     * Constructs a new {@code Elf} object.
     *
     * @param name          name. Must not be null or empty.
     * @param dateOfBirth   date of birth. Must not be in the future.
     * @param mana          mana of the elf. Must be between {@value MIN_MANA} and {@value MAX_MANA}.
     *
     * @throws IllegalArgumentException if the name, date of birth, or mana are invalid.
     */
    public Elf(final String name, final Date dateOfBirth, final int mana)
            throws IllegalArgumentException {
        
        super(name, dateOfBirth);
        
        validateMana();
        this.mana = mana;
    }
    
    /**
     * Getter method for mana.
     * @return mana of the elf
     */
    public int getMana() {
        return this.mana;
    }
    
    /**
     * Provides a detailed description of the elf,
     * including its name, date of birth, age, health, and mana.
     *
     * @return the elf's details.
     */
    @Override
    public String getDetails() {
        StringBuilder sb;
        String creatureDetails;
        String string;
        
        sb = new StringBuilder();
        creatureDetails = super.getDetails();
        
        sb.append(creatureDetails);
        sb.append("\nMana: ");
        sb.append(mana);
        
        string = sb.toString();
        
        return string;
    }
    
    /**
     * This method validates the mana of the elf.
     * Mana must be between {@value MIN_MANA} and {@value MAX_MANA}.
     *
     * @throws IllegalArgumentException if mana is less than {@value MIN_MANA} or greater than {@value MAX_MANA}.
     */
    private void validateMana() {
        if (mana < MIN_MANA || mana > MAX_MANA) {
            throw new IllegalArgumentException("Mana must be between" +
                                               MIN_MANA + " and " + MAX_MANA);
        }
    }
    
    /**
     * Allows the elf to cast a spell on another creature, reducing the target's health by
     * a fixed amount. The elf must have enough mana (at least {@value MANA_COST}) to
     * perform this action.
     *
     * @param creature the target creature to cast spell on.
     *
     * @throws LowManaException if the elf's mana is too low to cast spell.
     */
    public void castSpell(final Creature creature)
            throws LowManaException {
        
        if (mana < MANA_COST) {
            throw new LowManaException("Not enough Mana!");
        }
        
        mana -= MANA_COST;
        creature.takeDamage(MANA_DAMAGE);
    }
    
    /**
     * Restores the elf's mana by a specified amount.
     * The mana cannot exceed {@value MAX_MANA}.
     *
     * @param amount the amount of mana to restore.
     */
    public void restoreMana(final int amount) {
        mana += amount;
        
        if (mana > MAX_MANA) {
            mana = MAX_MANA;
        }
    }
}
