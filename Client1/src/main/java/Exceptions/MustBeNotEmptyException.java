package Exceptions;

/**
 * Выбрасывается, если значение не должно быть пустым
 *
 * @author Timur
 */
public class MustBeNotEmptyException extends Exception{
    /**
     * Конструктор исключения MustBeNotEmptyException
     */
    public MustBeNotEmptyException(){
    }
    @Override
    public String getMessage(){
        return "Поле не может быть пустым!";
    }
}
