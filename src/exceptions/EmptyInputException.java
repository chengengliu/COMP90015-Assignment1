package exceptions;

public class EmptyInputException extends Exception{
    public EmptyInputException(){
        super("You have entered nothing!");
    }
}
