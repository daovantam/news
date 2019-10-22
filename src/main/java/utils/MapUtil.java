package utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapUtil {
    public static <T> T mapRow(T object, ResultSet rs) {
        Field[] fields = object.getClass().getDeclaredFields(); // lay ra cac thuoc tinh cua class

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                field.set(object, rs.getObject(field.getName(), field.getType()));
            } catch (IllegalAccessException e) {
                continue;
            } catch (SQLException e) {
                continue;
            }
        }
        return object;
    }
}
