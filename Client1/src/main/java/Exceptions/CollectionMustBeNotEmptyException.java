package Exceptions;
/**
 * Выбрасывается, если коллекция не должна быть пустой
 *
 * @author Timur
 */
public class CollectionMustBeNotEmptyException extends Exception{
    @Override
    public String getMessage(){
        return "коллекция не должна быть пустой!";
    }
}
