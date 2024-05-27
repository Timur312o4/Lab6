package Exceptions;

/**
 * Выбрасывается, если в команду были переданы не верные аргументы
 */
public class WrongArgumentsException extends Exception{
    @Override
    public String getMessage(){
        return "В команду переданы не верные значения аргументов!";
    }
}
