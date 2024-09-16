package ca.bcit.comp2522.fantasycreatures;

public class Dragon extends Creature{

    final static int MIN_FIREPOWER = 0;
    final static int MAX_FIREPOWER = 100;

    final int firepower;


    public Dragon(final String name, final Date date_of_birth, int firepower) throws IllegalArgumentException {
        super(name, date_of_birth);
        this.firepower = firepower;
    }

    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append(super.getDetails());
        details.append("\nFire Power: ");
        details.append(firepower);

        return details.toString();

    }

    private void validateFirepower(){
        if(firepower < 0 || firepower > 100){
            throw new IllegalArgumentException("Firepower must be between" +
                    MIN_FIREPOWER + " and " + MAX_FIREPOWER);
        }
    }

    public void breatheFire(){

    }
}
