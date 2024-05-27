package Commands;
import Managers.Console;

/**
 * Абстрактный класс команд
 *
 * @author Timur
 */
public abstract class Command implements CommandInterface {
    /**
     * Консоль
     */
    protected Console console;
    private final String name;
    private final String description;

    /**
     * Конструктор класса Command
     * @param name название
     * @param description описание
     */
    public Command(String name, String description){
        this.name=name;
        this.description=description;
    }
}
