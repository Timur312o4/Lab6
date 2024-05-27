package Generators;


import Exceptions.BuildObjectException;
import Exceptions.IncorrectValueEntryException;
import Exceptions.MustBeNotEmptyException;
import City.*;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;

/**
 * Класс GeneratorCity
 * @author Timur
 */
public class GeneratorCity {
    /**
     * Генирирует объект класса City
     * @return city
     * @throws IncorrectValueEntryException выбрасывается исключение при некорректном пользовательском вводе
     * @throws NumberFormatException выбрасывается исключение при неправильном вводе числовых данных
     * @throws BuildObjectException выбрасывается исключение при неправильном создании объекта
     * @throws MustBeNotEmptyException выбрасывается исключение при пустом значении
     */
    public static City generate(Scanner scanner) throws IncorrectValueEntryException, NumberFormatException, BuildObjectException, MustBeNotEmptyException {
        while (true) {
            try {
                GeneratorId genId = new GeneratorId();
                GeneratorCoordinates genCoordinates = new GeneratorCoordinates();
                GeneratorHuman genHuman = new GeneratorHuman();
                Integer id = genId.generate();
                String name = requestName(scanner);
                Coordinates coordinates = genCoordinates.generate(scanner);
                ZonedDateTime zonedDateTime = ZonedDateTime.now();
                long area = requestArea(scanner);
                Long population = requestPopulation(scanner);
                Long metersAboveSeaLevel = requestMetersAboveSeaLevel(scanner);
                long telephoneCode = requestTelephoneCode(scanner);
                Goverment goverment = requestGoverment(scanner);
                StandartOfLiving standartOfLiving = requestStandartOfLiving(scanner);
                Date creationDate = Date.from(zonedDateTime.toInstant());
                Human human = genHuman.generate(scanner);
                City city = new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, telephoneCode, goverment, standartOfLiving, human);
                if (ValidatorGenerates.validateCity(city)) {
                    return city;
                } else throw new BuildObjectException();
            } catch (BuildObjectException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Запрашивает у пользователя Name
     * @return name
     */
    public static String requestName(Scanner sc)  {
            while (true) {
                try {
                    System.out.println("Введите имя города");
                    String name = sc.nextLine();
                    if (name.isEmpty()) throw new MustBeNotEmptyException();
                    if (name.matches(".*[a-zA-ZА-Яа-яёЁ].*") && ValidatorGenerates.validateName(name)) {
                        return name;
                    } else throw new IncorrectValueEntryException();
                } catch (IncorrectValueEntryException e) {
                    System.err.println(e.getMessage()+" ожидается, что город содержит в себе какие-то буквы, Повторите попытку.");
                }catch (MustBeNotEmptyException e){
                    System.out.println(e.getMessage());
                }
            }
        }

    /**
     * Запрашивает у пользователя значение поля area
     * @return area
     */
    public static Long requestArea(Scanner sc)  {
        while (true) {
                try {
                    System.out.println("Введите значение area: (Для City, тип Long)");
                    String t = sc.nextLine();
                    if (t.isEmpty()) throw new MustBeNotEmptyException();
                    if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                        t=t.substring(0,t.length()-1);
                    }
                    Long area = Long.parseLong(t.trim());
                    if (area < 0 ) throw new IncorrectValueEntryException();
                    if (ValidatorGenerates.validateArea(area))
                        return area;
                } catch (MustBeNotEmptyException e) {
                    System.err.println(e.getMessage());
                } catch (IncorrectValueEntryException e){
                    System.err.println(e.getMessage()+ " ожидается, что area>0. Повторите попытку");
                }catch(NumberFormatException e){
                    System.err.println("Не верный формат введеных данных ожидается Long.");
                }
            }
        }

    /**
     * Запрашивает у пользователя значение населения
     * @return population
     */
    public static Long requestPopulation(Scanner sc) {
        while (true) {
                try {
                    System.out.println("Введите значение population: (Для City, тип Long)");
                    String t = sc.nextLine();
                    if (t.isEmpty()) throw new MustBeNotEmptyException(); // тут уже проверится, что t
                    if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                        t=t.substring(0,t.length()-1);
                    }
                    Long population = Long.parseLong(t.trim());
                    if (population<=0) throw new IncorrectValueEntryException();
                    if (ValidatorGenerates.validatePopulation(population)){
                        return population;
                    }
                } catch (MustBeNotEmptyException e) {
                    System.err.println(e.getMessage());
                } catch (IncorrectValueEntryException e){
                    System.err.println(e.getMessage()+ " ожидается, что population>0. Повторите попытку.");
                }catch(NumberFormatException e){
                    System.err.println("Не верный формат введеных данных, ожидается Long");
                }
            }
        }

    /**
     * Запрашивает у пользователя значение поля metersAboveSeaLevel
     * @return metersAboveSeaLevel
     */
    public static Long requestMetersAboveSeaLevel(Scanner sc) {
        while (true) {
                try {
                    System.out.println("Введите значение metersAboveSeaLevel: (Для City, тип Long)");
                    String t = sc.nextLine();
                    if (t.isEmpty()) throw new MustBeNotEmptyException();
                    if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                        t=t.substring(0,t.length()-1);
                    }
                    Long metersAboveSeaLevel = Long.parseLong(t.trim());
                    if (metersAboveSeaLevel<-10000 || metersAboveSeaLevel > 10000) throw new IncorrectValueEntryException();
                    return metersAboveSeaLevel;
                } catch (MustBeNotEmptyException e) {
                    System.err.println(e.getMessage());
                }catch (IncorrectValueEntryException e){
                    System.err.println(e.getMessage() + " Ожидается значение расположенное от -10000 до 10000 включительно.");
                }catch(NumberFormatException e){
                    System.err.println("Не верный формат введеных данных, ожидается Long");
                }
            }
        }

    /**
     * Запрашивает у пользователя поле telephonecode
     * @return telephonecode
     */
    public static long requestTelephoneCode(Scanner sc) {
        while (true) {
            try {
                System.out.println("Введите значение telephoneCode: (Для City, тип Long)");
                String t = sc.nextLine();
                if (t.isEmpty()) throw new MustBeNotEmptyException();
                if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                    t=t.substring(0,t.length()-1);
                }
                long telephoneCode = Long.parseLong(t.trim());
                if (ValidatorGenerates.validateTelephoneCode(telephoneCode)){
                    return telephoneCode;
                }else throw new IncorrectValueEntryException();
            } catch (MustBeNotEmptyException e) {
                System.err.println(e.getMessage());
            }catch (IncorrectValueEntryException e){
                System.err.println(e.getMessage() + " ожидается, что 0<=telephonecode<= 100000. Повторите попытку.");
            }catch (NumberFormatException e){
                System.err.println("Не верный формат введеных данных, ожидается тип Long");
            }

        }
    }

    /**
     * Запрашивает у пользователя константу из политического строя
     * @return null или константу
     */
    public static Goverment requestGoverment(Scanner sc) {
        while (true) {
            try {
                System.out.println("Введите значение Goverment: (Для City, тип Goverment)");
                for (Goverment goverment : Goverment.values()) {
                    System.out.print(goverment + " ");
                }
                System.out.println("если хотите, чтобы Goverment равнялся null нажмите просто Enter или пробел и Enter");
                String t = sc.nextLine().toUpperCase();
                if (t.isEmpty() || t.equalsIgnoreCase("null") || t.trim().isEmpty()){
                    return null;
                }
                else{
                    Goverment goverment = Goverment.valueOf(t);
                return goverment;}
            } catch(EnumConstantNotPresentException e){
                System.out.println("Введена не верная коснтанта, повторите попытку");
            } catch(IllegalArgumentException e){
                System.err.println("Введена константа не принадлежащая трем основным, повторите попытку");
            }
        }
    }

    /**
     * Запрашивает у пользователя константу из перечисления
     * @return констунту или null
     */
    public static StandartOfLiving requestStandartOfLiving(Scanner sc) {
        while (true) {
            try {
                System.out.println("Введите значение StandartOfLiving: (Для City, тип StandartOfLiving)");
                for (StandartOfLiving standartOfLiving : StandartOfLiving.values()) {
                    System.out.print(standartOfLiving + " ");
                }
                System.out.println("если хотите, чтобы StandartOfLiving равнялся null нажмите просто Enter или пробел и Enter");
                String t = sc.nextLine().toUpperCase();
                if (t.trim().isEmpty() || t.equalsIgnoreCase("null") ) {
                    return null;
                }
                else {
                    StandartOfLiving standartOfLiving = StandartOfLiving.valueOf(t);
                    return standartOfLiving;
                }
            }catch(EnumConstantNotPresentException e){
                System.out.println("Введена не верная константа");
            }catch(IllegalArgumentException e){
                System.err.println("Введена константа, которой нет в списке, повторите попытку");
            }
        }
    }
}



