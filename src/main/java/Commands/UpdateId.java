package Commands;

// тут тоже переделать
import City.*;
import Exceptions.*;
import Managers.CollectionManager;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда updateId - обновить значение элемента коллекции, id которого равен заданному
 */

public class UpdateId extends Command {
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса UpdateId
     * @param console консоль
     * @param collectionManager менеджер коллекци
     */
    public UpdateId(Console console, CollectionManager collectionManager){
        super("UpdateId","");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Обновляет элемент, у которого id равен заданному
     */
    @Override
    public Response execute(Request request){ // здесь быть аккуратнее
        boolean statusCommand;
        try{
            if (request.getArgs().isEmpty()) throw new WrongArgumentsException();
            int id = Integer.parseInt(request.getArgs());
            if (id<0) {
                Console.printError("Значение id должно быть больше 0! ");
                throw new IncorrectValueEntryException();
            }
            if (collectionManager.getCityCollection().isEmpty()){
                throw new CollectionMustBeNotEmptyException();
            }
            if (!collectionManager.getIdCities().contains(id)){
                throw new ClientDeleteObjectException();
            }
            City city = (City) request.getCommandCity();
            statusCommand=collectionManager.updateByIdFromCollection(city,id);
            if (statusCommand){
                return new Response("Complete","Команда успешно выполнилась!");
            }
            else{
                return new Response("Error","Возникли ошибки! Команда не выполнилась. Элемента с таким id нет в коллекции!");
            }
    }catch(IncorrectValueEntryException | WrongArgumentsException | NumberFormatException  e){
            return new Response("Error","Возникли ошибки! Команда не выполнилась");
        }catch (CollectionMustBeNotEmptyException e){
            return new Response("Error","Возникли ошибки! Команда не выполнилась"+e.getMessage());
        }catch (ClientDeleteObjectException e){
            return new Response("Error", "Объекта с данным id нет в коллекции! Вероятно его удалил другой пользователь.");
        }
    }

    @Override
    public String describe(){
        return this +": обновить значение элемента коллекции по id.";
    }
    @Override
    public String toString(){
        return "updateId";
    }
}
