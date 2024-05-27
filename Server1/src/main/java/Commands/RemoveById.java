package Commands;

import Exceptions.CollectionMustBeNotEmptyException;
import Exceptions.WrongArgumentsException;
import Managers.CollectionManager;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда removeById - удаляет элемент по айди
 */
public class RemoveById extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса RemoveById
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public RemoveById(Console console, CollectionManager collectionManager){
        super("removeById","");
        this.console=console;
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет элемент из коллекции по id
     */
    @Override
    public Response execute(Request request){ // с этой командой аккуратнее
        boolean statusCommand;
        try{
            if (request.getArgs().isEmpty()) throw new WrongArgumentsException();
            Integer id = Integer.parseInt(request.getArgs().trim());
            if (collectionManager.getCityCollection().isEmpty()) throw new CollectionMustBeNotEmptyException();
            statusCommand=collectionManager.removeFromCollectionById(id);
            if(statusCommand){
                return new Response("Complete","Команда успешно выполнилась!");
            }else{
                return new Response("Error","Возникли ошибки! Команда не выполнилась");
            }
        } catch(WrongArgumentsException e){
            return new Response("Error","Возникли ошибки! Команда не выполнилась "+e.getMessage());
        }catch(CollectionMustBeNotEmptyException e){
            return new Response("Error","Возникли ошибки! Команда не выполнилась "+e.getMessage());
        }
    }
    @Override
    public String describe(){
        return this+": удалить элемент из коллекции по заданному id";
    }
    @Override
    public String toString(){
        return "removeById";
    }
}
