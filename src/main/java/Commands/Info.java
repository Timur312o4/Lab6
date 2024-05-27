package Commands;

import Exceptions.WrongArgumentsException;
import Managers.CollectionManager;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда info - выводит основную информацию о коллекции
 */
public class Info extends Command{
    /**
     * Менеджер коллекций
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Info
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public Info(Console console, CollectionManager collectionManager){
        super("info","");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит основную информацию о коллекции
     */
    @Override
    public Response execute(Request request){
        String statusCommand;
        try{
            if (!request.getArgs().isEmpty()) throw new WrongArgumentsException();
            statusCommand = collectionManager.infoAboutCollection(); // Вероятнее лучше всего вот этот вывод транслировать в response, в качестве строки, а по статусу уже выводить выполнилась ли команда
            if (!statusCommand.isEmpty()){
                return new Response("Complete",statusCommand);
            }else{
                return new Response("Error","Команда не выполнилась, возникли ошибки!");
            }
        }catch(WrongArgumentsException e){
            return new Response("Error","Возникли ошибки! Команда не выполнилась"+e.getMessage());
        }
    }

    @Override
    public String describe(){
        return this+": показать информацию о коллекции";
    }
    @Override
    public String toString(){
        return "info";
    }
}
