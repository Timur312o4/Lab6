package Commands;

// тут переделать
import Exceptions.BuildObjectException;
import Exceptions.IncorrectValueEntryException;
import Exceptions.MustBeNotEmptyException;
import Exceptions.WrongArgumentsException;
import Managers.CollectionManager;
import Managers.Console;
import Network.Request;
import Network.Response;
import City.City;
/**
 * Команда removeLower - удаляет элементы, которые меньше заданного элемента
 */
public class RemoveLower extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса RemoveLower
     * @param console консоль
     * @param collectionManager коллекции менеджер
     */
    public RemoveLower(Console console, CollectionManager collectionManager){
        super("RemoveLower","");
        this.console=console;
        this.collectionManager = collectionManager;
    }

    /**
     * удаляет элементы, которые меньше заданного элемента
     */
    @Override
    public Response execute(Request request) {
        boolean statusCommand;
        try {
            if (!request.getArgs().isEmpty()) throw new WrongArgumentsException();
            City cityreq = (City) request.getCommandCity();
            statusCommand=collectionManager.removeFromCollectionByLower(cityreq);
            if (statusCommand){
                return new Response("Complete","Команда успешно выполнилась");
            }else{
                return new Response("Error","Возникли ошибки! Команда не выполнилась. Не нужно удалять элементы из пустой коллекции.");
            }
        } catch (NumberFormatException| WrongArgumentsException e) {
            return new Response("Error","Возникли ошибки! Команда не выполнилась." +e.getMessage());

        }
    }

    @Override
    public String describe(){
        return this+": Удалить из коллекции все элементы, меньше, чем заданный";
    }
    @Override
    public String toString(){
        return "removeLower";
    }
}
