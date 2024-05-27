package Managers;

/**
 * Класс для работы с консолью
 */
public class Console {
    /**
     * Выводит в стандартный поток вывода без переноса
     * @param out переданные значения
     */
    public static void print(Object out){
        System.out.print(out);
    }

    /**
     * Выводит в стандартный поток вывода с переносом
     * @param out параметры
     */
    public static void println(Object out){
        System.out.println(out);
    }

    /**
     * Выводит в стандартный поток ошибок с переносом
     * @param obj переданные параметры
     */
    public static void printError(Object obj){
        System.err.println(obj);
    }
}
