package Generators;
import City.ValidatorGenerates;

import java.util.ArrayList;
import java.util.Random;

/**
 * Класс GeneratorId
 * @author Timur
 */
public class GeneratorId {
    private final ArrayList<Integer> idList= new ArrayList<>();
    /**
     * метод генерирует уникальный id
     * @return id
     */
    public int generate() {
            int maxint = 1000000;
            int minint = 1000;
            while (true) {
                Random rand = new Random();
                int id = rand.nextInt(minint, maxint) * minint - minint * rand.nextInt(1, 30);
                if (ValidatorGenerates.validateSameId(idList, id)) {
                    idList.add(id);
                    return id;
                }
            }
    }
}
