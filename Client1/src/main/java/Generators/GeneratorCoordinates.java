package Generators;

import City.Coordinates;
import Exceptions.BuildObjectException;
import Exceptions.IncorrectValueEntryException;
import Exceptions.MustBeNotEmptyException;
import City.ValidatorGenerates;

import java.util.Scanner;

/**
 * Класс GeneratorCoordinates
 * @author Timur
 */
public class GeneratorCoordinates {
    /**
     * Генерация координат
     * @return coordinates
     * @throws BuildObjectException выбрасывается исключение при ошибке создания объекта
     */
    public Coordinates generate(Scanner scanner) throws  BuildObjectException{
        Coordinates coor = new Coordinates(this.reqX(scanner),reqY(scanner));
        if (!ValidatorGenerates.validateCoordinates(coor)) throw new BuildObjectException();
        return coor;
    }

    /**
     * Запрашивает у пользователя координату X
     * @return x
     */
    public int reqX(Scanner scanner){
        Scanner sc = scanner;
        while (true) {
            System.out.println("Введите значение X: (для Coordinates, тип int)");
            try {
                String userInput = sc.nextLine().trim();
                if (userInput.isEmpty()) throw new MustBeNotEmptyException();
                int x = Integer.parseInt(userInput.trim());
                if (x > -579) {
                    return x;
                } else throw new IncorrectValueEntryException();
            } catch (IncorrectValueEntryException e) {
                System.err.println("не верно введено значение для переменной X, ожидается значение координаты > -579, повторите попытку,");
            } catch (NumberFormatException e){
                System.err.println("Неверный формат данных, ожидается тип int, Повторите попытку:");
            }catch (MustBeNotEmptyException e){
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Запрашивает у пользователя координату y
     * @return y
     */
    public double reqY(Scanner scanner){
        Scanner sc = scanner;
        while(true){
            System.out.println("Введите значение Y: (для Coordinates, тип double)");
            try{
                String userInput=sc.nextLine().trim();
                if (!userInput.isEmpty()){
                    double y = Double.parseDouble(userInput.trim());
                    return y;
                }else throw new MustBeNotEmptyException();
            }catch(MustBeNotEmptyException e){
                System.err.println(e.getMessage());
            }catch(NumberFormatException e){
                System.err.println("Неверный формат данных, ожидается тип double (число с плавающей точкой без суффикса f и F), Повторите попытку:");
            }
        }

        }
}
