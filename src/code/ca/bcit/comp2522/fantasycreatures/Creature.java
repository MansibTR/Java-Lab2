package ca.bcit.comp2522.fantasycreatures;

import java.time.LocalDate;

/**
 * The {@code Creature} class represents a creature with
 * a name, date of birth, and health status.
 *
 * <p>
 * Provides methods to calculate age, take damage,
 * heal, and check if the creature is alive.
 * </p>
 *
 * <p>
 * Validates that name is not null or empty, and the date of birth
 * is not in the future. The creature's health starts at {@value MAX_HP} and
 * cannot exceed this value or fall below zero.
 * </p>
 *
 * Custom exceptions are thrown for invalid damage or healing amounts.
 *
 * @author Ben Henry
 * @author Mansib Talukder
 * @version 1.0
 */
public class Creature {

    private static final int ZERO       = 0;
    private static final int ZERO_HP    = 0;
    private static final int MAX_HP     = 100;

    private final String    name;
    private final Date      dateOfBirth;

    private int health;
    
    /**
     * Constructs a new {@code Creature} object.
     *
     * @param name          name. Must not be null or empty.
     * @param dateOfBirth   date of birth. Must not be in the future.
     *
     * @throws IllegalArgumentException if the name is invalid or if the date of birth is in the future.
     */
    public Creature(final String name, final Date dateOfBirth)
            throws IllegalArgumentException {
        
        validateName(name);
        validateDateOfBirth(dateOfBirth);
        
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = MAX_HP;
    }
    
    /**
     * Getter method for name.
     * @return name of the creature
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter method for dateOfBirth.
     * @return dateOfBirth of the creature.
     */
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    /**
     * Calculates the age of the creature in years based on its date of birth.
     *
     * @return the age of the creature in years.
     */
    public int getAgeYears() {
        
        LocalDate today;
        int currentYear;
        int birthYear;
        int age;

        today = LocalDate.now();
        
        currentYear = today.getYear();
        birthYear = dateOfBirth.getYear();
        
        age = currentYear - birthYear;

        return age;
    }
    
    /**
     * Provides a detailed description of the creature,
     * including its name, date of birth, age, and health.
     *
     * @return the creature's details.
     */
    public String getDetails() {
        
        StringBuilder   sb;
        String          string;
        
        sb = new StringBuilder();
        
        sb.append(name);
        sb.append('\n');
        sb.append("-".repeat(name.length()));
        sb.append('\n');
        sb.append("Date of birth: \t");
        sb.append(dateOfBirth.getYYYYMMDD());
        sb.append('\n');
        sb.append("Age: ");
        sb.append(getAgeYears());
        sb.append('\n');
        sb.append("Health: ");
        sb.append(health);

        string = sb.toString();
        
        return string;
    }
    
    /**
     * Checks if the creature is alive.
     *
     * @return {@code true} if the creature's health is greater than 0.
     */
    public boolean isAlive() {
        
        boolean alive;
        
        alive = health > ZERO_HP;
        
        return alive;
    }
    
    /**
     * Reduces the creature's health by a specified damage amount.
     * Health cannot fall below 0.
     *
     * @param damage the amount of damage. Must be a non-negative value.
     *
     * @throws DamageException if the damage value is negative.
     */
    public void takeDamage(final int damage) throws DamageException {
        
        if (damage < ZERO) {
            System.out.println("Damage cannot be negative");
        } else {

            health -= damage;

            if (health <= ZERO_HP) {
                health = ZERO_HP;
            }
        }
    }

    /**
     * Returns true if a creature has enough of a resource to perform a certain action, else false.
     * @param curResource The current resource value.
     * @param reqResource The amount of resource required to perform an action.
     */
    boolean resourceChecker(final int curResource, final int reqResource){
        return curResource < reqResource;
    }

    /**
     * Increases the creature's health by a specified healing amount.
     * Health cannot exceed {@value MAX_HP}.
     *
     * @param healAmount the amount to heal. Must be a non-negative value.
     *
     * @throws HealingException if the heal amount is negative.
     */
    public void heal(final int healAmount) throws HealingException {
        
        if (healAmount < ZERO) {
//            throw new HealingException("Heal amount cannot be negative");
            System.out.println("Heal amount cannot be negative");
        } else {

            health += healAmount;

            if (health > MAX_HP) {
                health = MAX_HP;
            }
        }
    }
    
    /**
     * This method validates the creature's name.
     * Name cannot be null, empty, or blank.
     *
     * @param name the name of the creature.
     *
     * @throws IllegalArgumentException if the name is null, empty, or blank.
     */
    private void validateName(final String name) {

        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
    }
    
    /**
     * This method validates the date of birth.
     * dateOfBirth cannot be in the future.
     *
     * @param dateOfBirth the date of birth of the creature.
     *
     * @throws IllegalArgumentException if the date of birth is in the future.
     */
    private void validateDateOfBirth(final Date dateOfBirth) {
        LocalDate today;
        String currentDate;
        
        today = LocalDate.now();
        currentDate = today.toString();

        if (dateOfBirth.getYYYYMMDD().compareTo(currentDate) > ZERO) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
    }
    
}
