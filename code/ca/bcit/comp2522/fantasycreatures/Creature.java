package ca.bcit.comp2522.fantasycreatures;

import java.time.LocalDate;

public class Creature {

    private static final int ZERO = 0;

    private static final int ZERO_HP = 0;
    private static final int DEFAULT_HP = 100;

    private final String name;
    private final Date dateOfBirth;

    private int health;

    public Creature(final String name, final Date dateOfBirth) throws IllegalArgumentException {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = DEFAULT_HP;

        validateName(name);
        validateDateOfBirth(dateOfBirth);
    }

    public int getAgeYears() {

        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int birthYear = dateOfBirth.getYear();

        return birthYear - currentYear;
    }

    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append(name);
        for (int i = 0; i <= name.length(); i++) {
            details.append('-');
        }
        details.append("Date of birth: \t").append(dateOfBirth)
                .append('\n')
                .append("Date of Birth: ").append(dateOfBirth.toString())
                .append('\n')
                .append("Age: ").append(this.getAgeYears())
                .append('\n')
                .append("Health: ").append(health);

        return details.toString();
    }


    public boolean isAlive() {
        return health > ZERO_HP;
    }

    private void takeDamage(int damage) {

    }

    private void heal(int healAmount) {

    }

    private void validateName(String name) {

        if (name != null && !name.isEmpty()) {
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
