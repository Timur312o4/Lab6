package Commands;

import Exceptions.WrongArgumentsException;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда execute_script - запуск скрипта
 *
 * @author Timur
 */
public class ExecuteScript extends Command{
    private final Console console;
    /**
     * исполнитель - который работает с пользоавтелем
     */

    /**
     * Конструктор класса ExecuteScript
     * @param console консоль
     */
    public ExecuteScript(Console console){
        super("execute_script","запустить скрипт");
        this.console = console;
    }

    /**
     * Запускает скрипт
     */
    @Override
    public Response execute(Request request){
        try{
            if (request.getArgs().isEmpty()) throw new WrongArgumentsException();
            return new Response("Execute_script",request.getArgs());
        }catch(WrongArgumentsException e){
            return new Response("Error",e.getMessage());
        }}
    @Override
    public String describe(){
        return this +": запускает скрипт";
    }
    @Override
    public String toString(){
        return "execute_script";
    }
}
