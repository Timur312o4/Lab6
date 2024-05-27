package City;

import java.util.ArrayList;

/**
 * Класс Validator, для проверки всех полей города на валидность, а также для проверки валидного парсинга
 *
 * @author Timur
 */
public class ValidatorGenerates {
    /**
     *  Проверка на валидность названия города
     * @param name подается на вход строка
     * @return true - если название удовлетворяет условиям, иначе false
     */
    public static boolean validateName(String name){
        if (name == null ||name.isEmpty() ||name.matches("^\\d+$")) return false;
        return true;
    }

    /**
     * Проверка на валидность площади
     * @param area подается на вход площадь
     * @return true - если удовлетворяет ограничениям, иначе false
     */
    public static boolean validateArea(long area){
        if (area<=0) return false;
        return true;
    }

    /**
     * Проверка на валидность население
     * @param population подается население
     * @return true - если значение удовлетворяет ограничениям, иначе false
     */
    public static boolean validatePopulation(Long population){
        if(population == null || population <= 0) return false;
        return true;
    }
    public static boolean validateMetersAboveSeaLevel(Long metersAboveSeaLevel){
        if(metersAboveSeaLevel == null || metersAboveSeaLevel>10000|| metersAboveSeaLevel<-10000) return false;
        return true;
    }

    /**
     * Проверка на валидность телефонного кода
     * @param telephoneCode подается код
     * @return true - если значение удовлетворяет ограничениям, иначе false
     */
    public static boolean validateTelephoneCode(long telephoneCode){
        if (telephoneCode<=0 || telephoneCode > 100000) return false;
        return true;
    }

    /**
     * Проверка на валидность человека
     * @param human подается human
     * @return true - если все его поля удовлетворяют ограничениям, иначе false
     */
    public static boolean validateHuman(Human human){
        if (human !=null){
            if (human.getName() == null || human.getName().isEmpty())
                return false;
            if (!validateHumanAge(human.getAge()))
                return false;
            if (!validateHumanHeight(human.getHeight()))
                return false;
            return true;}
        else return true;
    }

    /**
     * Проверка на валидность возраст человека
     * @param age подается возраст
     * @return true - если значение удовлетворяет ограничениям, иначе false
     */
    public static boolean validateHumanAge(long age){
        if (age<= 0 | age>=100){
            return false;
        }
        return true;
    }

    /**
     * Проверка на валидность роста человека
     * @param height подается рост
     * @return true - если значение удовлетворяет ограничениям, иначе false
     */
    public static boolean validateHumanHeight(int height){
        if (height<50 | height>250){
            return false;
        }
        return true;
    }

    /**
     * Проверка на валидность координат города
     * @param coordinates подаются координаты
     * @return true - если поля удовлетворяют ограничениям, иначе false
     */
    public static boolean validateCoordinates(Coordinates coordinates){
        if (coordinates.getX()<-579 || Double.valueOf(coordinates.getY()) == null) return false;
        return true;
    }

    /**
     * проверка на валидность города
     * @param city подается город
     * @return true - если все поля удовлетворяют ограничениям, иначе false
     */
    public static boolean validateCity(City city){
        if (validateId(city.getId()) && validateArea(city.getArea()) && validateName(city.getName()) && validateCoordinates(city.getCoordinates()) && validateArea(city.getArea()) && validatePopulation(city.getPopulation())
        &&validateTelephoneCode(city.getTelephoneCode()) && validateHuman(city.getGovernor())) return true;
        return false;
    }

    /**
     * Проверка на валидность id
     * @param id подается id
     * @return true - если значение поля удовлетворяет ограничениям, иначе false
     */
    public static boolean validateId(Integer id){
        if (id<=0) return false;
        return true;
    }

    /**
     * Проверка на повторяющиеся id
     * @param listId подается список всех id, полученных при парсинге
     * @param genId сам id
     * @return true - если данный id уникальный, иначе false
     */
    public static boolean validateSameId(ArrayList<Integer> listId,Integer genId) {
        if (listId.contains(genId)) {
            return false;
        } else return true;
    }

}
