package Exceptions;

/**
 * Выбрасывается, если объект не создался
 *
 * @author Timur
 */
public class BuildObjectException extends Exception{
    @Override
    public String toString(){
        return "Ошибка, объект не создался";
    }
    @Override
    public String getMessage(){
        return "Произошла ошибка, связанная с созданием объекта";
    }
}
