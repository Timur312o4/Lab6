package Exceptions;

/**
 * Выбрасывается, если в файле были обнаружены одинаковые id
 */
public class SameIdInFileException extends Exception{

    @Override
    public String getMessage() {
        return "Ошибка! В файле встречаются одинаковые id!";
    }
}
