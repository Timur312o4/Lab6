package City;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс Сoordinates
 *
 * @author Timur
 */
public class    Coordinates implements Serializable {
    private int x;
    private double y;

    /**
     * Конструктор класса Coordinates
     * @param x координата по оси x
     * @param y координата по оси y
     */
    public Coordinates(Integer x,double y){
        this.x=x;//Значение поля должно быть больше -579
        this.y=y;//Поле не может быть null
    }
    @Override
    public String toString(){
        return "(x:"+ this.getX() + ", "+ "y: " + this.getY()+")";
    }

    /**
     * Получить координату по оси x
     * @return x
     */
    public int getX(){
        return x;
    }

    /** Получить координату по оси y
     * @return y
     */
    public double getY(){
        return y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
