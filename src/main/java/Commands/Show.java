package Commands;

import Exceptions.WrongArgumentsException;
import Managers.CollectionManager;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда show - выводит элементы коллекции в строковом представлении
 */
public class Show extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Show
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public Show(Console console, CollectionManager collectionManager){
        super("show","показать коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Показывает элементы коллекции в строковом представлении
     */
    @Override
    public Response execute(Request request){
        String statusCommand;
        try{
            if (!request.getArgs().isEmpty()) throw new WrongArgumentsException();
            statusCommand=collectionManager.showCollection();
        if (!statusCommand.isEmpty()){
            Console.println("Команда успешно выполнилась.");
            return new Response("Complete",statusCommand);
        }else{
            return new Response("Error","Возникли ошибки! Команда не выполнилась");
        }
    }catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
            return new Response("Error","Возникли ошибки! Команда не выполнилась");
        }}

    @Override
    public String describe(){
        return this+": показать информацию об элементах в коллекции";
    }
    @Override
    public String toString(){
        return "show";
    }
}
