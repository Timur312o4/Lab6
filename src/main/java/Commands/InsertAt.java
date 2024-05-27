package Commands;

// тут переделать
import Exceptions.*;
import Managers.CollectionManager;
import Managers.Console;
import Network.Request;
import Network.Response;
import City.City;

/**
 * Команда insertAt - добавляет новый элемент в коллекции по указанному индексу
 */
public class InsertAt extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса InsertAt
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public InsertAt(Console console, CollectionManager collectionManager){
        super("insertId","");
        this.collectionManager = collectionManager;
    }

    /**
     * Добавляет в коллекцию в указанное место новый элемент

     */
    @Override
    public Response execute(Request request){ // здесь быть аккуратнее
        boolean statusCommand;
        try{
            if (request.getArgs().isEmpty()) throw new WrongArgumentsException();
            int position = Integer.parseInt(request.getArgs());
            if (collectionManager.getCityCollection().isEmpty()){
                throw new CollectionMustBeNotEmptyException();
            }
            if (collectionManager.getCityCollection().size()<=position){
                throw new IncorrectValueEntryException();
            }
            City city = (City) request.getCommandCity();
            statusCommand=collectionManager.insertAtCollection(position, city);
            if (statusCommand){
                return new Response("complete","Команда выполнилась успешно. Элемент добавлен в заданную позицию.");
            }else {
                return new Response("Error", "Возникли ошибки. Команда не выполнилась. Невозможно добавить элемент в пустую коллекцию!");
            }
        }catch(IncorrectValueEntryException | WrongArgumentsException e){
            return new Response("Error","Команда не выполнилась. Неверный аргумент.");
        } catch(NumberFormatException e){
            return new Response("Error","Команда не выполнилась. Не верный формат данных в аргументе!");
        }catch(CollectionMustBeNotEmptyException e){
            return new Response("Error","Команда не выполнилась. "+e.getMessage());
        }
    }
    @Override
    public String describe(){
        return this+": Добавить новый элемент в коллекцию в заданную позицию";
    }
    @Override
    public String toString(){
        return "insertAt index";
    }
}
