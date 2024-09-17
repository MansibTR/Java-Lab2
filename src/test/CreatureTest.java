import ca.bcit.comp2522.fantasycreatures.*;

import java.sql.SQLOutput;

public class CreatureTest {
    
    public static void main (final String[] args) {
        Date puffBirth = new Date (1955, 1, 14);
        Dragon puff = new Dragon("Puff", puffBirth, 100);
        
        Date onAShelfBirth = new Date (1895, 7, 3);
        Elf onAShelf = new Elf ("OnAShelf", onAShelfBirth, 50);
        
        Date shrekBirth = new Date (1975, 5, 12);
        Orc shrek = new Orc ("Shrek", shrekBirth, 30);
        
        Date healerBirth = new Date (2000, 1, 1);
        CreatureHealer healer = new CreatureHealer("Healer", healerBirth);
        
        System.out.println(puff.getDetails());
        System.out.println();
        System.out.println(onAShelf.getDetails());
        System.out.println();
        System.out.println(shrek.getDetails());
        System.out.println();
        System.out.println(healer.getDetails());
        System.out.println();
        
        
        System.out.println("Puff instance of Dragon?: " + (puff instanceof Dragon));
        System.out.println("Puff's class: " + puff.getClass());
        System.out.println("OnAShelf instance of Elf?: " + (onAShelf instanceof Elf));
        System.out.println("OnAShelf's class: " + onAShelf.getClass());
        System.out.println("Shrek instance of Orc?: " + (shrek instanceof Orc));
        System.out.println("Shrek's class: " + shrek.getClass());
        
        
        shrek.cleave(onAShelf);
        System.out.println("OnAShelf got cleaved by Shrek!");
        System.out.println("OnAShelf details after cleave: \n" + onAShelf.getDetails());
        
        try
        {
            onAShelf.castSpell(shrek);
            System.out.println("OnAShelf cast a spell on Shrek");
        }
        catch (LowManaException e)
        {
            throw new RuntimeException(e);
        }
        
        System.out.println("Shrek details after a spell: \n" + shrek.getDetails());
        
        try
        {
            shrek.berserk();
            System.out.println("Shrek used Berserk!");
        }
        catch (LowRageException e)
        {
            System.out.println(e.getMessage());
        }
        
        shrek.cleave(onAShelf);
        
        System.out.println("OnAShelf got cleaved by Shrek!");
        System.out.println("OnAShelf details after a berserk cleave: \n" + onAShelf.getDetails());
        
        for (int i = 0; i <= 9; i++)
        {
            try
            {
                puff.breatheFire(shrek);
                System.out.println("Puff breathed fire on Shrek");
            }
            catch (LowFirePowerException e)
            {
                System.out.println(e.getMessage());
            }
        }
        
        System.out.println(shrek.getDetails());
        
        try {
            shrek.berserk();
            System.out.println("Shrek used Berserk!");
        }
        catch (LowRageException e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Is Shrek alive?: " + shrek.isAlive());
        healer.heal(shrek);
        System.out.println("Shrek got healed by Healer!");
        System.out.println("Is Shrek alive now?: " + shrek.isAlive());
        System.out.println("Shrek details: " + shrek.getDetails());
        
    }
}
