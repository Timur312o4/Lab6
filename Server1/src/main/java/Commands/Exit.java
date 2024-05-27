package Commands;
import Managers.Console;
import Network.Request;
import Network.Response;

/**
 * Команда exit - выход из программы
 *
 * @author Timur
 */
public class Exit extends Command{
    /**
     * Конструктор класса Exit
     * @param console консоль
     */
    public Exit(Console console){
        super("exit","выход");
        this.console = console;
    }

    /**
     * Завершение программы
     */
    @Override
    public Response execute(Request request){// доделать
        return new Response("EXIT","Завершение программы");
    }
    @Override
    public String describe(){

        return this +": выходит из программы";
    }
    @Override
    public String toString(){
        return "exit";
    }
}
