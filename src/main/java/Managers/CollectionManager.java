package Managers;

import City.*;
import Exceptions.WrongArgumentsException;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;import java.util.stream.Collectors;

/**
 * Класс для работы с коллекцией
 * Методы: добавление, сортировка, удаление, очищение, суммирование по id и т.д.
 */

public class CollectionManager {
    private Vector<City> cityCollection;
    private ZonedDateTime creationDate;

    /**
     * Конструктор класса CollectionManager
     */
    public CollectionManager(){
        cityCollection=new Vector<City>();
        creationDate= ZonedDateTime.now();
    }

    /**
     * Конструктор класса CollectionManager
     * @param collection коллекция городов
     */
    public CollectionManager(Vector<City> collection){
        this.cityCollection=collection;
    }

    /**
     * добавляет город в коллекцию
     * @param city добавляемый город
     * @return результат операции.
     */
    public boolean addToCollection(City city){
        cityCollection.add(city);
        return true;
    }

    /**
     * удаляет элементы из коллекции меньшие входного города
     * @param city входной город
     * @return true - если операция выполнилась, иначе false
     */
    //Done!
    public boolean removeFromCollectionByLower(City city){
        if (!cityCollection.isEmpty()){
            List removeCities = this.getCityCollection().stream()
                    .filter(city1 -> city1.compareTo(city)<0)
                    .toList();
            cityCollection.removeAll(removeCities);
            //cityCollection.removeIf(city1-> city1.compareTo(city)<0);
            return true;}
        else{
            return false;
        }
    }

    /**
     * удаляет элемент из коллекции по заданному id
     * @param id заданный айди
     * @return true - если операция выполнилась, иначе false
     */
    public boolean removeFromCollectionById(Integer id){
        if (!cityCollection.isEmpty()){
            try{
                int n = cityCollection.size();
                cityCollection = this.getCityCollection().stream()
                        .filter(city -> !city.getId().equals(id)).collect(Collectors.toCollection(Vector::new));
                if (n != cityCollection.size()){
                    System.out.println("Данный элемент успешно удален!");
                    return true;
                }
                else throw new WrongArgumentsException();
            }catch(WrongArgumentsException e){
                System.err.println("Элемента с данным id нет в коллекции, для того чтобы узнать какие элементы есть и какие у них id введите команду show");
                return false;
            }}
        else{
            System.err.println("Невозможно удалить элемент из пустой коллекции!");
            return false;
        }
    }

    /**
     *Выводит информацию о коллекции
     *@return возвращает результат операции
     */
    public String infoAboutCollection(){
        return "Тип данных: " +cityCollection.getClass().getName()+"\n"+
        "Дата инициализации: " + creationDate+"\n"+
        "Количество элементов: "+ cityCollection.size();
    }

    /**
     * Обнавляет элемент коллекции по заданному id
     * @param city новый элемент
     * @param id айди по которому обновляется элемент
     * @return true - если операция выполнилась, иначе false
     */
    public boolean updateByIdFromCollection(City city, int id){ //по логике, если элемента с таким id нет в коллекции, то мы просто должны выкинуть исключение
            int index = IntStream.range(0,getCityCollection().size())
                    .filter(i -> this.getCityCollection().get(i).getId().equals(id))
                    .findFirst()
                    .orElse(-1);
            if (index != -1){
                cityCollection.set(index,city);
                return true;
            }
            // такого id нет
            return false;

    }

    /**
     * сохраняет коллекцию в указанный файл
     * @param fileName название файла
     * @param city объекты какого класса хранит коллекция
     * @return true - если операция выполнилась, иначе false
     */
    public boolean saveCityCollection(String fileName,Vector<City> city){
        if (!cityCollection.isEmpty()){
        try{
            FileSaveManager fileSaveManager = new FileSaveManager(this);
            fileSaveManager.writeToFile(fileName);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }else{
        System.err.println("Не нужно сохранять пустую коллекцию!");
        return false;
        }
    }

    /**
     * возвращается коллекция
     * @return коллекцию городов
     */
    public Vector<City> getCityCollection(){
        return cityCollection;
    }

    /**
     * Получить id всех элементов коллекции
     * @return список id
     */
    //Done!
    public ArrayList<Integer> getIdCities(){
        ArrayList<Integer> idList = new ArrayList<>(this.getCityCollection().stream()
                .map(city -> city.getId())
                .collect(Collectors.toList()));
        return idList;
    }

    /**
     * сложить поле metersAboveSeaLevel каждого элемента
     * @return true - если операция выполнилась
     */
    //Подумай
    public String sumOfMetersAboveSeaLevelCollection(){
        String ans = Long.toString(this.getCityCollection().stream()
                .mapToLong(city -> city.getMetersAboveSeaLevel())
                .sum());
        return ans;
    }

    /**
     * Показать элементы коллекции
     * Возвращает результат выполнения операции
     * @return возвращает результат операции
     */
    //done!
    public String showCollection(){
        if (!cityCollection.isEmpty()){
            String ans = this.getCityCollection().stream()
                    .map(element -> element + "\n----------------------------\n")
                    .collect(Collectors.joining());
            return ans;
        }else{
            return "[]";
        }
    }

    /**
     * изменить коллекцию
     * @param collection коллекция городов
     */
    public void setCityCollection(Vector<City> collection){
        cityCollection = collection;
    }

    /**
     * Очистить коллекцию
     * @return возвращает результат операции
     */
    public boolean clearCollection(){
        cityCollection.clear();
        return true;
    }

    /**
     * Добавить новый элемент в указанную позицию
     * @param position позиция
     * @param city новый элемент
     * @return true - если операция выполнилась, иначе false
     */
    public boolean insertAtCollection(int position,City city){ // использовать само поле, тут вроде никак не поменять на streamAPI
        if (!cityCollection.isEmpty()) {
            cityCollection.add(position, city);
            return true;
        }
        return false;
    }

    /**
     * сгруппировать элементы коллекции по полю metersAboveSeaLevel
     * @return true - если операция выполнилась, иначе false
     */
    public String groupCountingCollectionByMetersAboveSeaLevel(){
        String ans="";
        if(!cityCollection.isEmpty()){
            ans = this.getCityCollection().stream()
                    .map(city -> city.getMetersAboveSeaLevel())
                    .collect(Collectors.groupingBy(meters -> meters, Collectors.counting()))
                    .entrySet().stream()
                    .map(entry -> entry.getKey() + ": " + entry.getValue())
                    .collect(Collectors.joining(", \n"));
        }
        return ans;
    }

    /**
     * Вывести элементы коллекции в порядке возрастания
     * @return true - если операция выполнилась, иначе false
     */
    public String printAscending(){
        if (!cityCollection.isEmpty()){
            String ans = this.getCityCollection().stream()
                    .sorted()
                    .map(Object :: toString)
                    .collect(Collectors.joining("\n"));;
            return ans;}
        return "Error";
    }

    /**
     * Отсортировать коллекцию в порядке возрастания
     */
    public void sortCityCollection(){
        Collections.sort(cityCollection);
    }
}
