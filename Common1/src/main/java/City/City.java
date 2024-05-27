package City;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Класс City
 *
 * @author Timur
 */
public class City  implements Comparable<City>, Serializable {
    private Integer id;//Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; // Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final long area;//Значение поля должно быть больше 0, Поле не может быть null
    private final Long population;//Значение поля должно быть больше 0, Поле не может быть null
    private final Long metersAboveSeaLevel;
    private final long telephoneCode;//Значение поля должно быть больше 0, Максимальное значение поля: 100000
    private final Goverment goverment;//Поле может быть null
    private final StandartOfLiving standartOfLiving;//Поле может быть null
    private final Human governor;//Поле может быть null

    /**
     * Конструктор класса City
     * @param id айди города
     * @param name название города
     * @param coordinates координаты города
     * @param creationDate дата создания
     * @param area площадь города
     * @param population популяция
     * @param metersAboveSeaLevel уровень моря
     * @param telephoneCode телефонный код
     * @param goverment политический строй
     * @param standartOfLiving уровень жизни
     * @param governor мэр
     */
    public City(Integer id, String name, Coordinates coordinates, Date creationDate, long area, Long population, Long metersAboveSeaLevel, long telephoneCode, Goverment goverment, StandartOfLiving standartOfLiving, Human governor){
        this.id = id;
        this.name=name;
        this.coordinates=coordinates;
        this.creationDate=creationDate;
        this.area=area;
        this.population=population;
        this.metersAboveSeaLevel=metersAboveSeaLevel;
        this.telephoneCode=telephoneCode;
        this.goverment=goverment;
        this.standartOfLiving=standartOfLiving;
        this.governor=governor;
    }

    /**
     * Получить id
     * @return вернуть id
     */
    public Integer getId(){
        return id;
    }

    /**
     * изменить id
     * @param id айди
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * получить название города
     * @return название города
     */
    public String getName() {
        return name;
    }

    /**
     * получить координаты
     * @return координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * получить время создания
     * @return время создания
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * получить площадь
     * @return площадь
     */
    public long getArea() {
        return area;
    }

    /**
     * получить популяцию
     * @return популяцию
     */
    public Long getPopulation() {
        return population;
    }

    /**
     * получить уровень моря
     * @return уровень моря
     */
    public Long getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    /**
     * получить телефонный код
     * @return телефонный код
     */
    public long getTelephoneCode() {
        return telephoneCode;
    }

    /**
     * Получить политический строй
     * @return политический строй
     */
    public Goverment getGoverment() {
        return goverment;
    }

    /**
     * получить уровень жизни
     * @return уровень жизни
     */
    public StandartOfLiving getStandartOfLiving() {
        return standartOfLiving;
    }

    /**
     * Получить мэра города
     * @return мэра
     */
    public Human getGovernor() {
        return governor;
    }

    @Override
    public String toString(){
        return "name: " + name +", " + "id: " + id +","+ "coordinates: " + coordinates + "," +
                "creationDate: " + creationDate +", "+ "area: " + area + ", "+ "population: " + population + ",\n"+
                "MetersAboveSeaLevel: " + metersAboveSeaLevel + ", "+ "telephonecode: " + telephoneCode + ", "+ "goverment: " + goverment + ","+
                "standartOfLiving: " + standartOfLiving + ", "+ "governor: " + governor + ".";
    }
    @Override
    public int compareTo(City city){
        if (this.population > city.getPopulation()){
            return 1;
        }else if(this.population< city.getPopulation()){
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, telephoneCode, goverment, standartOfLiving, governor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) && Objects.equals(name, city.name) && Objects.equals(coordinates, city.coordinates) && Objects.equals(creationDate, city.creationDate) && Objects.equals(population, city.population) && Objects.equals(metersAboveSeaLevel, city.metersAboveSeaLevel) && goverment == city.goverment && standartOfLiving == city.standartOfLiving && Objects.equals(governor, city.governor);
    }
}
