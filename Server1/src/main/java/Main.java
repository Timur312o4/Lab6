import Commands.Command;
import Commands.Help;
import Commands.History;
import Managers.CollectionManager;
import Managers.CommandManager;
import Managers.*;
import Managers.Console;
import Managers.Server;
import City.City;
import Exceptions.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Неправильные аргументы!");
            return;
        }
        Logger logger = Logger.getLogger(Main.class.getName());
        ParseCSV parseCSV = new ParseCSV();
        Vector<City> cities = new Vector<>();
        String path = System.getenv("Lab6_data"); //
        if (path != null) {
            File file = new File(path);
            try {
                if (file.exists()) {
                    if (file.canRead()) {
                        cities = parseCSV.getVector(file);
                        logger.info("Коллекция загружена");
                    } else {
                        throw new NotEnoughRightException();
                    }
                } else throw new FileNotFoundException();
            } catch (NotEnoughRightException e) {
                System.err.println(e.getMessage());
                logger.log(Level.WARNING,"Недостаточно прав");
                System.exit(0);
            } catch (FileNotFoundException e) {
                System.err.println("Данного файла не существует!");
                logger.log(Level.WARNING,"Данного файла не существует!");
                System.exit(0);
            }
        } else {
            System.out.println("У вас отсутствует переменная окружения Lab6_data!");
            logger.log(Level.WARNING, "Отсутствует переменная окружения Lab6_data");
            System.exit(0);
        }
        int port = Integer.parseInt(args[0]);
        String host = args[1];
        CollectionManager collectionManager = new CollectionManager(cities);
        FileSaveManager fileSaveManager = new FileSaveManager(collectionManager);
        CommandManager commandManager = new CommandManager(collectionManager);
        Server serv1 = new Server(port, commandManager, fileSaveManager);
        serv1.start();
    }
}
