package cc.dreamcode.utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoundUtil {
    public static double round(double number, int places) {
        double factor = Math.pow(10, places);
        return Math.round(number * factor) / factor;
    }
}
