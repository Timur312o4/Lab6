package Commands;

import Exceptions.WrongArgumentsException;
import Managers.CommandManager;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда history - выводит 9 последних использованных команд
 * @author Timur
 */
public class History extends Command{
    /**
     * Менеджер команд
     */
    private final CommandManager commandManager;

    /**
     * Конструктор команды history
     * @param console консоль
     * @param commandManager менеджер команд
     */
    public History(Console console, CommandManager commandManager){
        super("history","показать последние 9 команд");
        this.console=console;
        this.commandManager=commandManager;
    }

    /**
     * Выводит последние 9 использованных команд
     */
    @Override
    public Response execute(Request request){
        try{
            if (!request.getArgs().isEmpty()) throw new WrongArgumentsException();
            return new Response("Complete",commandManager.getCommandHistory());
    }catch(WrongArgumentsException e){
            return new Response("Error","Возникли ошибки! Команда не выполнилась "+e.getMessage());
        }
    }
    @Override
    public String describe(){
        return this +": показать последние 9 использованных команд";
    }
    @Override
    public String toString(){
        return "history";
    }
}
