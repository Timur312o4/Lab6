package Exceptions;

/**
 * Выбрасывается, если в файле обнаружена рекурсия
 */
// ну не знаю, мб это нужно наоборот закинуть в клиентскую часть
public class ScriptRecursionException extends Exception{
    @Override
    public String getMessage(){
        return "В файле есть рекурсия, её нельзя вызывать, иначе при выполнении скрипта вы снова обратитесь к этому же файлу!";
    }
}
