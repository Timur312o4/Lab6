import Managers.Client;
import Managers.Console;
import Managers.Executor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main { 
    public static void main(String[] args){
        if (args.length != 2){
            System.out.println("Передайте хост в аргументы командной строки!");
            return;
        }
        Console console = new Console();
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        Client client = new Client(host,port,5000,5);
        ArrayList<String> commands = new ArrayList<>();
        commands.add("add");
        commands.add("groupcountingbymetersabovesealevel");
        commands.add("sumofmetersabovesealevel");
        commands.add("insertat");
        commands.add("execute_script");
        commands.add("clear");
        commands.add("show");
        commands.add("removeLower");
        commands.add("history");
        commands.add("exit");
        commands.add("help");
        commands.add("printascending");
        commands.add("removebyid");
        commands.add("info");
        Executor executor = new Executor(console,new Scanner(System.in),client,commands);
        executor.userMode();
    }
}
