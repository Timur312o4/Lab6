package Managers;

import City.*;
import Exceptions.EmptyFileException;
import Exceptions.IncorrectValueEntryException;
import Exceptions.SameIdInFileException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Класс ParseCSV - который парсит данные из файла и на основе этих данных создает коллекцию
 * @author Timur
 */
public class ParseCSV {
    /**
     * Парсер, который принимает на вход данные из файла и каждую строку преобразует в элемент коллекции
     * @param file файл
     * @return коллекцию
     */
    public Vector<City> getVector(File file) {
        Vector<City> VectorList = new Vector<>();
        ArrayList<String> listCity = new ArrayList<>();
        try (FileReader reader = new FileReader(file)) {
            if (file.length() == 0) throw new EmptyFileException();
            StringBuilder stringBuilder = new StringBuilder();
            String line1;
            HashSet<Integer> idSet = new HashSet<>();
            int character;
            while ((character = reader.read()) != -1) {
                if ((char) character == '\n' || (char) character == '\r') {
                    if (!stringBuilder.isEmpty()){
                        line1 = stringBuilder.toString();
                        listCity.add(line1);
                        stringBuilder.setLength(0);
                    }
                } else {
                    stringBuilder.append((char) character);
                }
            }
            if (!stringBuilder.isEmpty()) {
                line1 = stringBuilder.toString();
                listCity.add(line1);
            }
            for (String line : listCity) {
                if (ValidatorParse.validatepars(line)) {
                    try {
                        line = line.replace(';',',');
                        String[] parts = line.split(",");
                        int id = Integer.parseInt(parts[0]);
                        if (!idSet.contains(id)) {
                            idSet.add(id);
                        } else throw new SameIdInFileException();
                        String name = parts[1].trim();
                        if(ValidatorParse.validateName(parts[1].trim())){
                            name = parts[1].trim();}
                        else throw new IncorrectValueEntryException();
                        int x = Integer.parseInt(parts[2].trim());
                        double y = Double.parseDouble(parts[3].trim());
                        Coordinates coordinates = new Coordinates(x,y);
                        if(ValidatorParse.validateCoordinates(new Coordinates(x,y))){
                            coordinates = new Coordinates(x,y);}
                        else throw new IncorrectValueEntryException();
                        Date creationDate = parseDatefromFile(parts[4]);
                        String t=parts[5].trim();
                        Long area = Long.parseLong(t.substring(0,t.length()-1));
                        if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                            t=t.substring(0,t.length()-1);
                            if (ValidatorParse.validateArea(Long.parseLong(t))) {
                                area = Long.parseLong(t);
                            }else throw new IncorrectValueEntryException();
                        }
                        t=parts[6].trim();
                        Long population = Long.parseLong(t.substring(0,t.length()-1));
                        if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                            t=t.substring(0,t.length()-1);
                            if (ValidatorParse.validatePopulation(Long.parseLong(t))) {
                                population = Long.parseLong(t);
                            }else throw new IncorrectValueEntryException();
                        }
                        t=parts[7].trim();
                        Long metersAboveSeaLevel = Long.parseLong(t.substring(0,t.length()-1));
                        if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                            t=t.substring(0,t.length()-1);
                            if (ValidatorParse.validateMetersAboveSeaLevel(Long.parseLong(t))) {
                                population = Long.parseLong(t);
                            }else throw new IncorrectValueEntryException();
                        }
                        t=parts[8].trim();
                        Long telephoneCode = Long.parseLong(t.substring(0,t.length()-1));
                        if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                            t=t.substring(0,t.length()-1);
                            if (ValidatorParse.validateTelephoneCode(Long.parseLong(t))){
                                telephoneCode = Long.parseLong(t);
                            }else throw new IncorrectValueEntryException();
                        }
                        Goverment goverment;
                        StandartOfLiving standartOfLiving;
                        try {
                            goverment = Goverment.valueOf(parts[9].trim());
                        } catch (IllegalArgumentException e) {
                            goverment = null;
                        }
                        try {
                            standartOfLiving = StandartOfLiving.valueOf(parts[10].trim());
                        } catch (IllegalArgumentException e) {
                            standartOfLiving = null;
                        }
                        Human governor;
                        if (parts[11].trim().equalsIgnoreCase("null" )||parts[11].trim().isEmpty()) {
                            governor = null;
                        } else if(parts[11].trim().equalsIgnoreCase("yes")) {
                            String namegovernor = parts[12].trim();
                            t=parts[13].trim();
                            if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                                t=t.substring(0,t.length()-1);
                            }
                            long age = Long.parseLong(t);
                            int height = Integer.parseInt(parts[14].trim());
                            governor = new Human(namegovernor, age, height);
                        }else{
                            System.err.println("Поле отвечающее за наличие мэра в городе, заполнено не верными данными, исправьте!");
                            governor = null;
                        }
                        City city = new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, telephoneCode, goverment, standartOfLiving, governor);
                        if (ValidatorParse.validateCity(city)) {
                            VectorList.add(city);
                        } else {
                            System.out.println(city);
                            Console.printError("Ошибка в создании объекта город, в файле введены значения полей не удовлетворяющие ограничениям");
                        }
                    } catch (ParseException e) {
                        System.err.println(e.getMessage());
                        Console.printError("Ошибка создании объекта город.");
                    } catch (SameIdInFileException e) {
                        Console.printError(e.getMessage());
                        Console.printError("Ошибка в создании объекта город, в файле встречаются строки с одинаковыми значениями id.");
                    }catch(IncorrectValueEntryException e){
                        Console.printError(e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (EmptyFileException e) {
            Console.printError(e.getMessage());
        }
        return VectorList;
    }
    /**
     * Парсер даты, для того чтобы можно принять различные форматы дат
     * @param s строка
     * @return дату
     * @throws ParseException вылетает, если дата написанна не типичным образом или же не правильно записана
     */
    public static Date parseDatefromFile(String s) throws ParseException {
        String[] mspat = {"EEE MMM dd HH:mm:ss zzz yyyy", "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "MM/dd/yyyy", "MM-dd-yyyy", "dd/MM/yyyy", "dd-MM-yyyy",
                "yyyy/MM/dd", "yyyy-MM-dd", "yyyy/dd/MM", "yyyy-dd-MM"};
        for (String pat : mspat) {
            SimpleDateFormat dateFormat1 = new SimpleDateFormat(pat, Locale.ENGLISH);
            try {
                Date creationDate1 = dateFormat1.parse(s);
                return creationDate1;
            } catch (ParseException e) {
                System.out.print("");
            }
        }
        throw new ParseException("Не удалось распознать дату",-1);
    }
}

