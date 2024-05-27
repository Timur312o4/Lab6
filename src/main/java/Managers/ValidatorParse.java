package Managers;

import City.*;
import Exceptions.IncorrectValueEntryException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Класс Validator, для проверки всех полей города на валидность, а также для проверки валидного парсинга
 *
 * @author Timur
 */
public class ValidatorParse { //Ещё раз располовинить или же и так норм, надо спросить. Можно в принципе взять все валидаторы и закинуть в parse валидатор и по идее должно быть норм
    /**
     * Метод для проверки строки из файла на валидность при парсинге
     * @param line на вход подается строка из файла
     * @return true, если строка валидная и false в противном случае
     */
    public static boolean validatepars(String line){
        String ans="";
        String[] parts=line.split(",");
        if (line.contains(",") && line.contains(";")) {
            Console.printError("Строка содержит смешанные разделители ',' и ';', поэтому заменим все на один и тот же разделитель");
            line = line.replace(';',',');

        }
        if (line.split(",").length!=15 && line.split(";").length!=15){              // второй способ, if((parts[11] == null && parts.length()!=12) || (parts[11] != null && parts.length()!=15)) return false
            Console.printError("в файле не заполенены все поля!");
            return false;
        }else if(line.split(",").length==15){
            parts = line.split(",");
        }else if(line.split(";").length==15){
            parts = line.split(";");
        }else{
            return false;
        }
        try{
            int id = Integer.parseInt(parts[0].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: на месте id ожидается тип int;";
            }else{ans += "на месте id ожидается тип int; ";}
        }
        String name = parts[1].trim();
        if (!name.matches(".*\\D.*")){
            ans += "В файле введены не корректные типы данных: на Name ожидается тип String содержащий только буквы; ";
        }
        try{
            int x = Integer.parseInt(parts[2].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+= "на месте X ожидается тип int; ";
        }
        try{
            double Y = Double.parseDouble(parts[3].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте Y ожидается тип double; ";
        }
        try{
            Date creationDate = ParseCSV.parseDatefromFile(parts[4].trim());
        }catch(ParseException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте creationDate ожидается правильная расстановка даты; ";
        }
        try{
            String t=parts[5].trim();
            if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                t=t.substring(0,t.length()-1);
            }
            Long area = Long.parseLong(t); //если парсинг неправильной строки, то вывод будет нормальным, для правильной строки не будет исключения так, что тоже все должно быть отлично
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте area ожидается тип long; ";
        }
        try{
            //написать нормальную проверку
            String t=parts[6].trim();
            if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                t=t.substring(0,t.length()-1);
            }
            Long population = Long.parseLong(t);
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте population ожидается тип Long; ";
        }
        try{
            //напииисать нормальную проверку
            String t=parts[7].trim();
            if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                t=t.substring(0,t.length()-1);
            }
            Long metersAboveSeaLevel = Long.parseLong(t);
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте metersAboveSeaLevel ожидается тип Long; ";
        }
        try{
            // написать нормальную проверку
            String t=parts[8].trim();
            if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                t=t.substring(0,t.length()-1);
            }
            long telephonecode = Long.parseLong(t);
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте telephonecode ожидается тип Long; ";
        }
        if (parts[9].trim().isEmpty() || parts[9].trim().equalsIgnoreCase("null")){
            Goverment goverment = null;
        }else{
            try{
                Goverment goverment = Goverment.valueOf(parts[9].trim());
            }catch(IllegalArgumentException e){
                if (ans.isEmpty()){
                    ans = "В файле введены не правильные типы данных: ";
                }
                ans+="на месте goverment ожидаются константы типа Goverment (DESPOTISM,CORPORATOCRACY,TOTALITARIANISM) или null (чтобы получить null, оставьте данное поле пустым) ; ";
            }}
        if (parts[10].trim().isEmpty() || parts[10].trim().equalsIgnoreCase("null")){
            StandartOfLiving standartOfLiving = null;
        }
        else{
            try{
                StandartOfLiving standartOfLiving= StandartOfLiving.valueOf(parts[10].trim());
            }catch(IllegalArgumentException e){
                if (ans.isEmpty()){
                    ans = "В файле введены не правильные типы данных: ";
                }
                ans+="на месте standartOfLiving ожидаются константы типа StandartOfLiving (ULTRA_HIGH, VERY_HIGH, LOW, ULTRA_LOW, NIGHTMARE) или null (чтобы получить null, оставьте данное поле пустым); ";
            }}
        try{
            String isThereMayorInTheCity = parts[11].trim();
            if ((!isThereMayorInTheCity.isEmpty() && !isThereMayorInTheCity.equalsIgnoreCase("null") && !isThereMayorInTheCity.equals("yes"))){
                throw new IncorrectValueEntryException();}
            if (isThereMayorInTheCity.equalsIgnoreCase("yes")){
                String humanName = parts[12].trim();
                if (!humanName.matches("[ a-zA-ZА-Яа-яёЁ-]*")){
                    ans += "В файле введены не корректные типы данных: на Name ожидается тип String содержащий только буквы; ";
                }
                try{
                    String t=parts[13].trim();
                    if (t.toLowerCase().charAt(t.length()-1)=='l' && (t.toLowerCase().split("l")).length==1){
                        t=t.substring(0,t.length()-1);
                    }
                    long age = Long.parseLong(t);
                }catch(NumberFormatException e){
                    if (ans.isEmpty()){
                        ans = "В файле введены не правильные типы данных: ";
                    }
                    ans+=" на месте age ожидается тип long; ";
                }
                try{
                    int height = Integer.parseInt(parts[14].trim());
                }catch(NumberFormatException e){
                    if (ans.isEmpty()){
                        ans = "В файле введены не правильные типы данных: ";
                    }
                    ans+="на месте height ожидается тип int; ";
                }}
        }catch(IncorrectValueEntryException e){
                if (ans.isEmpty()){
                    ans = "В файле введены не правильные типы данных: ";
                }
                ans+="на месте isThereMayorInTheCity ожидаются строки ('yes','null','')";
            }
        if (ans.isEmpty()){ return true;}
        else {
            ans = ans.substring(0,ans.length()-2)+"!";
            Console.printError(ans);
            return false;}
    }
    /**
     *  Проверка на валидность названия города
     * @param name подается на вход строка
     * @return true - если название удовлетворяет условиям, иначе false
     */
    public static boolean validateName(String name){
        if (name == null ||name.isEmpty() || !name.matches(".*\\D.*")) return false;
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
