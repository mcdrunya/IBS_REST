package ru.ibs;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestTest extends SetupTests {

    @Test
    void restTest() throws SQLException, JsonProcessingException {
        APIHelper.getFoodList();
        assertTrue(foodDb.isTableExists(), "Таблицы FOOD не существует");

        APIHelper.addNewFood("Арбуз", "FRUIT", true);
        assertTrue(foodDb.isFoodExists("Арбуз", "FRUIT", true), "Продукт с тестовыми данными не найден в таблице");
    }
}
