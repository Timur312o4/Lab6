package Network;

import java.io.Serializable;
import City.City;
public class Request implements Serializable {
    private String commandName;
    private String args = ""; // аргументы, которые передаются в команде
    private City commandCity = null;
    private String status;
    public Request(String commandName,String args, City commandCity){
        this.commandName = commandName;
        this.args = args;
        this.commandCity = commandCity;
    }
    public Request(String commandName,String args){
        this.commandName=commandName;
        this.args=args;
    }
    public Request(String commandName, City commandCity){
        this.commandName=commandName;
        this.commandCity=commandCity;
    }
    /*public Request(String commandName, City commandCity, String status){
        this.commandName=commandName;
        this.commandCity=commandCity;
        this.status=status;
    }*/

    public String getCommandName() {
        return commandName;
    }
    public String getArgs() {
        return args;
    }
    public Serializable getCommandCity() {
        return commandCity;
    }
    public boolean isEmpty(){
        return commandName.isEmpty() && args.isEmpty() && commandCity == null;
    }
}
