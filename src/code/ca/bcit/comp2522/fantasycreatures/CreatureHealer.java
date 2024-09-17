package ca.bcit.comp2522.fantasycreatures;

import java.util.Random;

/**
 * The {@code CreatureHealer} class represents a CreatureHealer, a subclass of {@code Creature},
 * with additional attributes and behavior related to Heal.
 * The CreatureHealer can heal, increasing health of other creatures.
 *
 * @author Ben Henry, Mansib Talukder
 * @version 1.0
 */
public class CreatureHealer extends Creature {
    
    private static final int MIN_HEAL = 0;
    private static final int MAX_HEAL = 100;
    
    private int heal;
    
    /**
     * Constructs a new {@code CreatureHealer} object.
     *
     * @param name        name. Must not be null or empty.
     * @param dateOfBirth date of birth. Must not be in the future.
     *
     * @throws IllegalArgumentException if the name is invalid or if the date of birth is in the future.
     */
    public CreatureHealer(final String name, final Date dateOfBirth)
            throws IllegalArgumentException {
        
        super(name, dateOfBirth);
    }
    
    /**
     * Heals another creature by a random amount.
     * Heal amount must not be negative.
     * @param creature The Creature to heal
     */
    public void heal(final Creature creature) {
        Random random;
        int healAmount;
        
        random = new Random();
        
        healAmount = random.nextInt(MAX_HEAL);
        
        creature.heal(healAmount);
    }
}
