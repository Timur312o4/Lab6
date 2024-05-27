package Managers;
import Exceptions.MustBeNotEmptyException;
import Network.Request;
import Network.Response;
import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client {
    private String host;
    private int port;
    private int reconnectionTimeout;
    private int reconnectionAttempt;
    private int maxReconnectionAttempts;
    private Socket socket;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;
    public Client(String host, int port, int reconnectionTimeout, int maxReconnectionAttempts) {
        this.host = host;
        this.port = port;
        this.reconnectionTimeout = reconnectionTimeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
    }
    public Response sendAnsOnRequest(Request request){
        while (true){
            try {
                if (Objects.isNull(serverWriter)) throw new IOException();
                if (request.isEmpty()) throw new MustBeNotEmptyException(); //; кинуть исключение, что запрос пустой
                serverWriter.writeObject(request);
                serverWriter.flush();
                this.serverReader = new ObjectInputStream(socket.getInputStream());
                Response response = (Response) serverReader.readObject();
                this.disconnectFromServer();
                reconnectionAttempt = 0;
                return response;
            }catch (MustBeNotEmptyException e){
                return new Response("Error","Запрос не должен быть пустым!");
            }catch(IOException e){
                if (reconnectionAttempt == 0){ // Заходим в первый раз на сервер
                    connectToServer();
                    reconnectionAttempt += 1;
                    continue;
                }else{
                    System.err.println("Соединение с сервером разорвано");
                }
                try{
                    reconnectionAttempt+=1;
                    if (reconnectionAttempt >= maxReconnectionAttempts){
                        Console.printError("Было произведено максимальное количество попыток для соединения с сервером!");
                        return new Response("exit","Выход");
                    }
                    System.out.println("Повторная попытка подключения. Через 5"+ " секунд.");
                    Thread.sleep(reconnectionTimeout);
                    connectToServer();
                }catch(Exception e1){
                    Console.printError("Попытка соединения с сервером не успешна!");
                }
            }catch(ClassNotFoundException e){
                throw new RuntimeException(e);
            }
        }
    }
    public void disconnectFromServer(){
        try{
            this.socket.close();
            serverWriter.close();
            serverReader.close();
        }catch(IOException e){
            System.err.println("Не подключен к серверу");
        }
    }
    public void connectToServer(){
        try{
            if (reconnectionAttempt > 0) {
                Console.println("Попытка повторного подключения");
            }
            this.socket = new Socket(host,port);
            this.serverWriter = new ObjectOutputStream(socket.getOutputStream());
             // доходит до этого места
        }catch(IOException e){
            Console.printError("Произошла ошибка при подключении к с серверу!");
        }catch(IllegalArgumentException e){
            Console.printError("Ошибка, неверно переданы аргументы!");
        }
    }
}
