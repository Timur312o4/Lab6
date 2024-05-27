package Exceptions;

/**
 * Выбрасывается, если обращаться к файлу, к которому у нас нет прав
 */
public class NotEnoughRightException extends Exception{
    @Override
    public String getMessage(){
        return "У вас недостаточно прав для работы с файлом!";
    }
}
