package Managers;

import Commands.*;

import java.util.*;

/**
 * Менеджер команд
 */
public class CommandManager {
    private final String[] commandHistory = new String[9];
    private final HashMap<String, Command> commandCollection = new HashMap<>();
    private final ArrayList<String> commands;
    private final static Console console = new Console();
    /**
     * Конструктор класса CommandManager
     */
    public CommandManager(CollectionManager collectionManager) {
        commandCollection.put("add",new Add(console,collectionManager));
        commandCollection.put("clear",new Clear(console,collectionManager));
        commandCollection.put("show",new Show(console,collectionManager));
        commandCollection.put("removebyid", new RemoveById(console,collectionManager));
        commandCollection.put("info", new Info(console,collectionManager));
        commandCollection.put("groupcountingbymetersabovesealevel", new GroupCountingByMetersAboveSeaLevel(console,collectionManager));
        commandCollection.put("insertat",new InsertAt(console,collectionManager));
        commandCollection.put("printascending", new PrintAscending(console,collectionManager));
        commandCollection.put("exit",new Exit(console));
        commandCollection.put("execute_script",new ExecuteScript(console));
        commandCollection.put("removelower", new RemoveLower(console,collectionManager));
        commandCollection.put("sumofmetersabovesealevel", new SumOfMetersAboveSeaLevel(console,collectionManager));
        commandCollection.put("updateid", new UpdateId(console,collectionManager));
        commands = new ArrayList<String>(commandCollection.keySet());
    }

    /**
     * Возвращает коллекцию команд
     * @return коллекцию commandCollection
     */
    public HashMap<String, Command> getCommandCollection() {
        return commandCollection;
    }

    /**
     * добавить команду в коллекцию команд
     * @param string1 название команды
     * @param command команда
     */
    public void addCommand(String string1,Command command){
        commandCollection.put(string1,command);
        commands.add(string1);
    }
    public Command getCommand(String string){
        return commandCollection.get(string);
    }

    /**
     * Добавить команду в историю
     * @param command1 команда
     */
    public void addToCommandHistory(String command1) {
        if (commands.contains(command1)) {
            for (int i = 8; i > 0; i--) {
                commandHistory[i] = commandHistory[i - 1]; // мы должны при применении команды history вывести последние 9 команд не включая его или же включая его?
            }
            commandHistory[0] = command1;
        }
    }

    /**
     * Вывести подробную справку по командам
     */
    public String help(){
        StringJoiner answer = new StringJoiner("\n");
        for (Command command: commandCollection.values()){
            answer.add(command.describe());
        }
        return answer.toString();
    }

    /**
     * Получить историю команд
     */
    public String getCommandHistory() {
        StringBuilder ans = new StringBuilder();
        for (String str: commandHistory){
            ans.append(str+" ");
        }
        ans.deleteCharAt(ans.length()-1);
        ans.append("\n");
        return ans.toString();
    }
}
