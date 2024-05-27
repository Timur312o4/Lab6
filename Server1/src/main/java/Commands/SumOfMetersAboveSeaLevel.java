package Commands;

import Exceptions.CollectionMustBeNotEmptyException;
import Exceptions.WrongArgumentsException;
import Managers.CollectionManager;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда sumOfMetersAboveSeaLevel - складывает все поля metersAboveSeaLevel элементов из коллекции
 */
public class SumOfMetersAboveSeaLevel extends Command{
    /**
     * менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса SumOfMetersAboveSeaLevel
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public SumOfMetersAboveSeaLevel(Console console, CollectionManager collectionManager){
        super("SumOfMetersAboveSeaLevel","");
        this.console=console;
        this.collectionManager = collectionManager;
    }

    /**
     * складывает все поля metersAboveSeaLevel элементов из коллекции
     */
    @Override
    public Response execute(Request request){
        String statusCommand;
        try{
            if (!request.getArgs().isEmpty()) throw new WrongArgumentsException();
            if (collectionManager.getCityCollection().isEmpty()){
                throw new CollectionMustBeNotEmptyException();
            }
            statusCommand=collectionManager.sumOfMetersAboveSeaLevelCollection();
            if (!statusCommand.equals("0")){
                return new Response("Complete",statusCommand);
            }else{
                return new Response("Error","Возникли ошибки! Команда не выполнилась");

            }
    }catch(WrongArgumentsException|CollectionMustBeNotEmptyException e){
            Console.printError(e.getMessage());
            return new Response("Error","Возникли ошибки! Команда не выполнилась");
        }
    }
    @Override
    public String describe(){
        return this + ": вывести сумму всех значений поля " + this;
    }
    @Override
    public String toString(){
        return "sumOfMetersAboveSeaLevel";
    }
}
