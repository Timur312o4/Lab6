package Commands;

import Exceptions.WrongArgumentsException;
import Managers.CollectionManager;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда printAscending - выводит элементы коллекции в порядке возрастания
 * @author Timur
 */
public class PrintAscending extends Command{
    /**
     * менеджер коллекций
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса PrintAscending
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public PrintAscending(Console console, CollectionManager collectionManager){
        super("printAscending","");
        this.collectionManager = collectionManager;
    }
    @Override
    public Response execute(Request request){
        String statusCommand;
        try{
            if (!request.getArgs().isEmpty()) throw new WrongArgumentsException();
            statusCommand=collectionManager.printAscending();
            if (!statusCommand.equals("Error")){
                return new Response("Complete",statusCommand);
            }else{
                return new Response("Error","Коллекция не должна быть пустой! []. Возникли ошибки! Команда не выполнилась");
            }
    }catch(WrongArgumentsException e){
            return new Response("Error","Возникли ошибки! Команда не выполнилась"+e.getMessage());
        }}
    @Override
    public String describe(){
        return this+": вывести элементы в порядке возрастания";
    }
    @Override
    public String toString(){
        return "printAscending";
    }
}
