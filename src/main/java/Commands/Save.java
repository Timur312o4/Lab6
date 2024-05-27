package Commands;

import Exceptions.WrongArgumentsException;
import Managers.CollectionManager;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда save - сохраняет коллекцию в выбранный файл
 */
public class Save extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Save
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public Save(Console console, CollectionManager collectionManager){
        super("save","сохранить");
        this.console=console;
        this.collectionManager = collectionManager;
    }

    /**
     * сохраняет коллекцию в указанный файл
     */
    @Override
    public Response execute(Request request){
        boolean statusCommand;
        try{
            if (request.getArgs().length() !=2 || !request.getArgs().isEmpty() ) throw new WrongArgumentsException();
            String fileName = request.getArgs().trim();
            statusCommand=collectionManager.saveCityCollection(fileName,collectionManager.getCityCollection());
            if (statusCommand){
                System.out.println("Коллекция сохранилась. Команда успешно выполнилась.");
                return new Response("Complete","Команда успешно выполнилась. Коллекция сохранилась в файл");
            }else{
                return new Response("Error","Возникли ошибки! Команда не выполнилась.");
            }
        }catch(WrongArgumentsException e){
            return new Response("Error",e.getMessage());
        }}
    @Override
    public String describe(){
        return this +": сохранить коллекцию в файл";
    }
    @Override
    public String toString(){
        return "save";
    }
}
