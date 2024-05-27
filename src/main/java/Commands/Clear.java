package Commands;

import Exceptions.WrongArgumentsException;
import Managers.CollectionManager;
import Managers.Console;
import City.City;
import Network.Request;
import Network.Response;

/**
 * Команда clear - очистить коллекцию
 *
 * @author Timur
 */
public class Clear extends Command {
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор команды Clear
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Очистить коллекцию
     */
    @Override
    public Response execute(Request request){
        try{
            if (!request.getArgs().isEmpty()) throw new WrongArgumentsException();
            boolean statusCommand=collectionManager.clearCollection();
            if (statusCommand){
                return new Response("Complete","Команда успешно выполнилась");
            }else {
                return new Response("Error","Возникли ошибки! Команда не выполнилась");
            }
    }catch(WrongArgumentsException e){
            return new Response("Error","Возникли ошибки! Команда не выполнилась "+e.getMessage());
        }
    }
    @Override
    public String describe(){
        return this+": очищает коллекцию";
    }
    @Override
    public String toString(){
        return "clear";
    }
}
