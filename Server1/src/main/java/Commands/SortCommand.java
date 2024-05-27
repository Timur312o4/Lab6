package Commands;

import Exceptions.WrongArgumentsException;
import Managers.CollectionManager;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда sortCommand - сортирует коллекцию по возрастанию
 */
public class SortCommand extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса SortCommand
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public SortCommand(Console console, CollectionManager collectionManager){
        super("sort","");
        this.console=console;
        this.collectionManager=collectionManager;
    }

    /**
     * Сортирует коллекцию по возрастанию
     */
    @Override
    public Response execute(Request request){
        try{
            if (!request.getArgs().isEmpty()) throw new WrongArgumentsException();
            collectionManager.sortCityCollection();
            return new Response("Complete","Команда успешно выполнилась");
    }catch(WrongArgumentsException e ){
            Console.printError(e.getMessage());
            return new Response("Error","Возникли ошибки! Команда не выполнилась.");
        }}
    @Override
    public String describe(){
        return this + ": отсортировать коллекцию";
    }
    @Override
    public String toString(){
        return "sortCommand";
    }
}
