package City;

import java.io.Serializable;

/**
 *Перечисление уровней жизни городов
 *
 * @author Timur
 */
public enum StandartOfLiving implements Serializable {
    /**
     * Сверхвысокий
     */
    ULTRA_HIGH,
    /**
     * Очень высокий
     */
    VERY_HIGH,
    /**
     * Низкий
     */
    LOW,
    /**
     * Сверхнизкий
     */
    ULTRA_LOW,
    /**
     * Кошмар
     */
    NIGHTMARE;
}
