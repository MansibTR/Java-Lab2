package ca.bcit.comp2522.fantasycreatures;

public class DamageException extends IllegalArgumentException{
    DamageException(String message){
        super(message);
    }
}
