package cc.dreamcode.utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ClassUtil {
    public static boolean hasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
