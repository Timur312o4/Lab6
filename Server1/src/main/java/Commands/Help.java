package Commands;

import Exceptions.WrongArgumentsException;
import Managers.CommandManager;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда help - выводит информацию о всех командах
 */
public class Help extends Command{
    /**
     * Менеджер команд
     */
    private final CommandManager commandManger;

    /**
     * Конструктор класса Help
     * @param console консоль
     * @param commandManager менеджер команд
     */
    public Help(Console console, CommandManager commandManager){
        super("help","");
        this.console = console;
        this.commandManger=commandManager;
    }

    /**
     * Выводит информацию о всех командах
     */
    @Override
    public Response execute(Request request){
        try{
            if (!request.getArgs().isBlank()) throw new WrongArgumentsException();
            return new Response("Complete",commandManger.help());
        }catch(WrongArgumentsException e){
            return new Response("Error","Возникли ошибки! Команда не выполнилась");
        }
    }
    @Override
    public String describe(){
        return this+ ": Вывести информацию по доступным командам";
    }
    @Override
    public String toString(){
        return "help";
    }


}
