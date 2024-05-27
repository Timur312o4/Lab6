package Generators;

import City.Human;
import Exceptions.BuildObjectException;
import Exceptions.IncorrectValueEntryException;
import Exceptions.MustBeNotEmptyException;
import City.ValidatorGenerates;

import java.util.Scanner;

/**
 * Класс GeneratorHuman
 * @author Timur
 */
public class GeneratorHuman {


    /**
     * метод генерирует объект класса Human
     *
     * @return объект класса Human
     * @throws BuildObjectException при ошибках пользователя выбрасывается исключение
     */
    public Human generate(Scanner sc) throws BuildObjectException {
        System.out.println("Введите yes, если хотите, чтобы в городе был мэр, или введите null");
        while(true){
            String Input = sc.nextLine();
            if (Input.equals("null")) {
                return null;
            } else if (Input.equals("yes")) {
                Human human = new Human(requestName(sc), requestAge(sc), requestHeight(sc));
                if (!ValidatorGenerates.validateHuman(human)) throw new BuildObjectException();
                return human;
            }else{
            System.err.println("Ошибка.Пожалуйста введите yes, если хотите, чтобы в городе был мэр, иначе введите null!");
        }}

    }

    /**
     * Запрос имени человека
     * @return name
     */
    public String requestName(Scanner sc) {
        System.out.println("Введите значение переменной name (для Human, тип String):");
        while (true) {
            try {
                String name = sc.nextLine();
                if (name.isEmpty()) {
                    throw new MustBeNotEmptyException();
                }
                if (name.matches("[a-zA-ZА-Яа-яёЁ]*") && ValidatorGenerates.validateName(name)) {
                    return name;
                } else throw new IncorrectValueEntryException();

            } catch (IncorrectValueEntryException e) {
                System.err.println(e.getMessage());
                System.err.println("Ожидается, что в имени есть символы из русского или английского алфавита.");
            } catch (MustBeNotEmptyException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Запрос на возраст human
     * @return age
     */
    public Long requestAge(Scanner sc)  {
            System.out.println("Введите значение переменной Age (для Human, тип long):");
            while (true){
                try {
                    String t = sc.nextLine().trim();
                    if (t.isEmpty()){
                        throw new MustBeNotEmptyException();
                    }
                    if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                        t=t.substring(0,t.length()-1);
                    }
                    Long age = Long.parseLong(t);
                    if (ValidatorGenerates.validateHumanAge(age)){
                        return age;
                    }else throw new IncorrectValueEntryException();
                }catch (IncorrectValueEntryException e){
                    System.err.println(e.getMessage()+" ожидается, 0<age<100. Повторите попытку.");
                }catch(NumberFormatException e){
                    System.err.println("Не верно введен тип значения переменной age, ожидается тип long.");
                }catch (MustBeNotEmptyException e) {
                    System.err.println(e.getMessage());
                }
            }
    }

    /**
     * Запрос на рост Human
     * @return height
     */
    public int requestHeight(Scanner sc) {
        System.out.println("Введите значение переменной height (для Human, тип int):");
        while (true){
            try{
                String t = sc.nextLine();
                if (t.isEmpty()) throw new MustBeNotEmptyException();
                int height = Integer.parseInt(t.trim());
                if (ValidatorGenerates.validateHumanHeight(height)){
                    return height;
                }else throw new IncorrectValueEntryException();
            }catch (MustBeNotEmptyException e){
                System.err.println(e.getMessage());
            }catch (IncorrectValueEntryException e){
                System.err.println(e.getMessage() + " ожидается, что 50<=height<=250");
            }catch (NumberFormatException e){
                System.err.println("Не верно введен тип поля, ожидается тип int.");
            }
        }
    }
}



