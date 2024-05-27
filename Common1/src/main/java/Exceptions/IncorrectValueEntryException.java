package Exceptions;

/**
 * Выбрасывается, если пользователь ввёл не верное значение
 */
public class IncorrectValueEntryException extends Exception{
    @Override
    public String getMessage(){
        return "Неверный ввод значения!";
    }

}
