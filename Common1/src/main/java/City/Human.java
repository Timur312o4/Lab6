package City;

import java.io.Serializable;

/**
 * Класс Человек
 *
 * @author Timur
 */
public class Human implements Serializable {
    private String name;//Поле не может быть null, Строка не может быть пустой
    private long age;//Значение поля должно быть больше 0
    private int height;

    /**
     * Конструктор класса Human
     * @param name имя
     * @param age возраст
     * @param height рост
     */
    public Human(String name, long age,int height){
        this.name=name;
        this.age=age;
        this.height=height;
    }

    @Override
    public String toString(){
        return "(name:" + this.getName()+ ", age: " + this.getAge() + ", "+ "height: "+ this.getHeight()+" )";
    }

    /**
     * Получить имя человека
     * @return имя
     */
    public String getName(){
        return name;
    }

    /**
     * Получить возраст человека
     * @return возраст
     */
    public long getAge(){
        return age;
    }

    /**
     * Получить рост человека
     * @return рост
     */
    public int getHeight(){
        return height;
    }

}
