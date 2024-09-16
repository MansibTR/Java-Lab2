package ca.bcit.comp2522.fantasycreatures;

public class Creature {
    private static final int ZERO_HP = 0;
    private final String name;
    private final Date dateOfBirth;
    private int health;

    public Creature(String name, Date dateOfBirth) throws IllegalArgumentException {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public int getAgeYears() {
        return 0;
    }

    public String getDetails() {
        return "test";
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void takeDamage(int damage) {
    }

    public void heal(int healAmount) {
    }

    public void validateName() {
    }

    private void validateDateOfBirth() {
    }
}