package ca.bcit.comp2522.fantasycreatures;

import java.time.LocalDate;

public class Creature {

    private static final int ZERO = 0;

    private static final int ZERO_HP = 0;
    private static final int MAX_HP = 100;

    private final String name;
    private final Date dateOfBirth;

    private int health;

    public Creature(final String name, final Date dateOfBirth) throws IllegalArgumentException {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = MAX_HP;

        validateName(name);
        validateDateOfBirth(dateOfBirth);
    }

    public int getAgeYears() {

        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int birthYear = dateOfBirth.getYear();

        return currentYear - birthYear;
    }

    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append(name)
                .append('\n')
                .append("-".repeat(name.length()))
                .append('\n')
                .append("Date of birth: \t").append(dateOfBirth.getYYYYMMDD())
                .append('\n')
                .append("Age: ").append(this.getAgeYears())
                .append('\n')
                .append("Health: ").append(health);

        return details.toString();
    }


    private boolean isAlive() {
        return health > ZERO_HP;
    }

    public void takeDamage(int damage) throws DamageException {
        if (damage < 0) {
            throw new DamageException("Damage cannot be negative");
        }
        health -= damage;
        if(health <= ZERO) {
            health = ZERO;
        }

    }

    private void heal(int healAmount) throws HealingException {
        if (healAmount < 0){
            throw new HealingException("Heal amount cannot be negative");
        }
        health += healAmount;
        if(health > MAX_HP) {
            health = MAX_HP;
        }

    }

    private void validateName(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }

    }

    private void validateDateOfBirth(final Date dateOfBirth) {
        LocalDate today = LocalDate.now();
        String currentDate = today.toString();

        if (dateOfBirth.getYYYYMMDD().compareTo(currentDate) > ZERO) {
            throw new IllegalArgumentException("Date of birth should be in the past");
        }

    }


}
