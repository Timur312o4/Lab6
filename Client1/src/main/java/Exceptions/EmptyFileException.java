package Exceptions;

/**
 * Выбрасывается, если обращаются к пустому файлу
 *
 * @author Timur
 */
public class EmptyFileException extends Exception{
    @Override
    public String getMessage(){
        return "Файл не должен быть пустым!";
    }
}
