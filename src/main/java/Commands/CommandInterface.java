package Commands;
import City.City;
import Network.Request;
import Network.Response;
/**
 * интерфейс команда
 * @author Timur
 */
public interface CommandInterface {
    /**
     * Вызов команды
     * @param request аргумент команды
     */
    public Response execute(Request request);

    /**
     * Получить описание команды
     */
    public String describe();
}
