package cc.dreamcode.utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ChanceUtil {
    private static boolean reachChange(double chance) {
        return Math.random() * 100 < chance;
    }
}
