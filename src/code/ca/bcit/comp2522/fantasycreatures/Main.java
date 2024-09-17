package ca.bcit.comp2522.fantasycreatures;

/**
 * This is the driver class for the application.
 *
 * @author Ben Henry
 * @author Mansib Talukder
 * @version 1.0
 */
public class Main {
    
    /**
     * Drives the program.
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {

        Creature test = new Creature("Bean", new Date(2022,9,16));

        System.out.println(test.getDetails());

    }
}


