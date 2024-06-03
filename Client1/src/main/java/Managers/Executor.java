package Managers;

import Exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import Generators.GeneratorCity;
import Network.Request;
import Network.Response;
import City.City;
/**
 * Класс Executor
 * @author Timur
 */
public class Executor {
    private final String[] commandHistory = new String[9];
    private boolean statusScript = false;
    private Scanner usedScanner;
    private int deepRecursion = 0;
    private final ArrayList<String> fileNamesList = new ArrayList<>();
    private final Client client;
    private final ArrayList<String> commands = new ArrayList<>();
    /**
     * Получает текущий поток ввода
     * @return usedScanner
     */
    public Scanner getUsedScanner(){
        return this.usedScanner;
    }

    /**
     * Меняет поток ввода
     * @param scanner новый поток ввода
     */
    public void setUsedScanner(Scanner scanner){
        this.usedScanner=scanner;
    }

    /**
     * Конструктор класса Executor
     *
     */
    public Executor(Console console, Scanner usedScanner,Client client, ArrayList<String> commands) {
        this.usedScanner = usedScanner;
        this.client = client;
        this.commands.addAll(commands);
    }

    public void userMode(){
        System.out.println("Добро пожаловать!");
        while (true){
            System.out.println("Введите команду, для того чтобы увидеть справку о командах введите help");
            try {
                if (!usedScanner.hasNext()) throw new ExitObliged();
                String[] command = (usedScanner.nextLine().trim() + " ").split(" ", 2);
                if (command[0].equals("help")) {
                    if (command[1].isEmpty()){
                        addCommandHistory("help");
                        System.out.println(getListCommand());
                        continue;
                    }else{
                        throw new WrongArgumentsException();
                    }
                }
                if (command[0].equals("history")) {
                    if (command[1].isEmpty()){
                        addCommandHistory("history");
                        getHistoryCommand();
                        continue;
                    } else{
                        throw new WrongArgumentsException();
                    }
                }
                Request request = new Request(command[0].trim(), command[1].trim());
                Response response = client.sendAnsOnRequest(request);
                if (response.getTypeResponse().equalsIgnoreCase("GENERATE_CITY")) {
                    City city = GeneratorCity.generate(this.getUsedScanner());
                    request = new Request(command[0].trim(), command[1].trim(), city);
                    Response newResponse = client.sendAnsOnRequest(request); // получаем ответ на наш запрос
                    Console.println(newResponse.getResponse());
                } else if (response.getTypeResponse().equalsIgnoreCase("EXECUTE_SCRIPT")) {
                    this.scriptMode(response.getResponse());
                    this.setDeepRecursion(0);// после выполнения на всякий случай так полагаю
                } else if (response.getTypeResponse().equalsIgnoreCase("EXIT")) { // А точно ли так должно работать
                    throw new ExitObliged();
                } if (response.getTypeResponse().equalsIgnoreCase("Complete")){
                    addCommandHistory(request.getCommandName());
                    Console.println(response.getResponse());
                    Console.println("Команда успешно выполнилась.");
                } else if (response.getTypeResponse().equalsIgnoreCase("ERROR")){
                    Console.println(response.getResponse());
                }
            }catch (ExitObliged e){
                Console.println("Завершение работы, до свидания!");
                System.exit(0);

            }catch (WrongArgumentsException e){
                Console.println(e.getMessage());
            }
            catch (Exception e){
                Console.printError(e.getMessage());
            }
            // Продумать все исключения, которые могут вылететь
        }
    }

    /**
     * Ввод команд с помощью скрипта
     * @param fileName название файла, где лежит скрипт\
     */
    public void scriptMode(String fileName) {
        Scanner newScanner;
        try {
            var filePath = new File(fileName.trim());
            if (!filePath.canRead()) throw new NotEnoughRightException();
            newScanner = new Scanner(filePath);
            setUsedScanner(newScanner);
            if (fileNamesList.isEmpty()) {
                this.statusScript = true;
            }
            fileNamesList.add(fileName);
            if (!newScanner.hasNext()) throw new EmptyFileException();
            while (checkCountFileinFilesList(fileNamesList,fileName)&&newScanner.hasNext()){
                System.out.printf(String.format("%s-> ~ ", fileName));
                String gettingString = newScanner.nextLine();
                String[] scriptCommand = (gettingString.trim()+" ").split(" ", 2);
                Request request = new Request(scriptCommand[0].trim(), scriptCommand[1].trim());
                if (scriptCommand[0].equals("help")) {
                    if (scriptCommand[1].isEmpty()){
                        addCommandHistory("help");
                        System.out.println(getListCommand());
                        continue;
                    }else{
                        throw new WrongArgumentsException();
                    }
                }
                if (scriptCommand[0].equals("history")) {
                    if (scriptCommand[1].isEmpty()){
                        addCommandHistory("history");
                        getHistoryCommand();
                        continue;
                    } else{
                        throw new WrongArgumentsException();
                    }
                }
                Response response = client.sendAnsOnRequest(request);
                if (response.getResponse().equalsIgnoreCase("execute_script")) {
                    deepRecursion += 1;
                    if (deepRecursion == 2) {
                        Console.printError("Обнаружена рекурсия!");
                        throw new ScriptRecursionException();
                    }
                }
                if (response.getTypeResponse().equalsIgnoreCase("GENERATE_CITY")){
                    City city = GeneratorCity.generate(this.getUsedScanner());// так не опасно ли делать
                    request = new Request(scriptCommand[0].trim(),scriptCommand[1].trim(),city);
                    Response newResponse = client.sendAnsOnRequest(request); // получаем ответ на наш запрос
                    Console.println(newResponse.getResponse());
                }else if(response.getTypeResponse().equalsIgnoreCase("EXIT")){
                    Console.println("Завершение работы, до свидания!");
                    System.exit(0);
                } else if (response.getTypeResponse().equalsIgnoreCase("complete")){
                    addCommandHistory(request.getCommandName());
                    Console.println(response.getResponse());
                    Console.println("Команда успешно выполнилась.");
                } else if (response.getTypeResponse().equalsIgnoreCase("ERROR")){
                    Console.println(response.getResponse());
                }
            }
            fileNamesList.remove(fileName);
            Console.println("Весь файл " + fileName + " прочитан!");
        } catch (ScriptRecursionException | EmptyFileException | NotEnoughRightException exception) {
            Console.printError(exception.getMessage());

        } catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
        }
        catch (FileNotFoundException exception) {
            Console.printError("Файл " + fileName + " со скриптом не найден!");
        } catch (IllegalStateException exception) {
            Console.println("");
            Console.printError("Непредвиденная ошибка.");
        }catch(IncorrectValueEntryException | BuildObjectException | MustBeNotEmptyException e){
            Console.printError(e.getMessage());
        } finally {
            if (fileNamesList.isEmpty()) {
                setUsedScanner(new Scanner(System.in));
                statusScript = false;
            }
        }
    }
    public void addCommandHistory(String command1) {
        command1 = command1.toLowerCase();
        if (commands.contains(command1)) {
            for (int i = 8; i > 0; i--) {
                commandHistory[i] = commandHistory[i - 1]; // мы должны при применении команды history вывести последние 9 команд не включая его или же включая его?
            }
            commandHistory[0] = command1;
        }
    }
    public void getHistoryCommand(){
        for (String command : commandHistory) {
            Console.print(command+" ");
        }
        Console.println("");
    }
    public static String getListCommand(){
        return "add: добавляет элемент в коллекцию\n" +
                "groupCountingByMetersAboveSeaLevel: сгруппирует элементы коллекции по значению данного поля и выведет кол-во элементов в каждой группе\n" +
                "sumOfMetersAboveSeaLevel: вывести сумму всех значений поля sumOfMetersAboveSeaLevel\n" +
                "insertAt index: Добавить новый элемент в коллекцию в заданную позицию\n" +
                "execute_script: запускает скрипт\n" +
                "clear: очищает коллекцию\n" +
                "show: показать информацию об элементах в коллекции\n" +
                "removeLower: Удалить из коллекции все элементы, меньше, чем заданный\n" +
                "history: показать последние 9 использованных команд\n" +
                "exit: выходит из программы\n" +
                "updateId: обновить значение элемента коллекции по id.\n" +
                "help: Вывести информацию по доступным командам\n" +
                "printAscending: вывести элементы в порядке возрастания\n" +
                "removeById: удалить элемент из коллекции по заданному id\n" +
                "info: показать информацию о коллекции";
    }

    /**
     * Меняет глубину рекурсии
     * @param deep глубина рекурсии
     */
    public void setDeepRecursion(int deep) {
        this.deepRecursion = deep;
    }

    /**
     * Проверяет насколько много раз один и тот же файл находится в стеке
     * @param list стек вызовов
     * @param name название файла
     * @return true - если меньше 3 раз, false - если больше 2 раз
     */
    public boolean checkCountFileinFilesList(List<String> list, String name) {
        int count = 0;
        for (String element : list) {
            if (name.equals(element)) {
                count++;
                if (count > 2){
                    return false;
                }
            }
        }
        return true;
    }
}
