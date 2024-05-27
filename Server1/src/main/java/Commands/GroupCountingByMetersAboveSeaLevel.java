package Commands;

import Exceptions.CollectionMustBeNotEmptyException;
import Exceptions.WrongArgumentsException;
import Managers.CollectionManager;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 *  Команда groupCountingByMetersAboveSeaLevel - группирует элементы коллекции по значению поля metersAboveSeaLevel и выводит количество элементов в каждой группе
 * @author Timur
 */

public class GroupCountingByMetersAboveSeaLevel extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса GroupCountingByMetersAboveSeaLevel
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public GroupCountingByMetersAboveSeaLevel(Console console, CollectionManager collectionManager){
        super("groupCountingByMetersAboveSeaLevel","");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Группирует элементы коллекции по полю metersAboveSeaLevel
     * @param request аргумент команды
     */
    @Override
    public Response execute(Request request){
        String statusCommand;
        try{
            if (!request.getArgs().isEmpty()) throw new WrongArgumentsException();
            if(collectionManager.getCityCollection().isEmpty()){
                throw new CollectionMustBeNotEmptyException();
            }
            statusCommand=collectionManager.groupCountingCollectionByMetersAboveSeaLevel();
            if(!statusCommand.isEmpty()){
                return new Response("Complete",statusCommand);
            }else{
                return new Response("Error","Коллекция не должна быть пустой.Возникли ошибки! Команда не выполнилась");
            }
        }catch(WrongArgumentsException| CollectionMustBeNotEmptyException e){
            return new Response("Error","Возникли ошибки! Команда не выполнилась "+e.getMessage());
        }}

    @Override
    public String toString(){
        return "groupCountingByMetersAboveSeaLevel";
    }
    @Override
    public String describe(){
        return this+": сгруппирует элементы коллекции по значению данного поля и выведет кол-во элементов в каждой группе";
    }
}
