package cc.dreamcode.utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoundUtil {
    public static double round(double number, int places) {
        if (number < 0) {
            return number;
        }
        long pow = (long) Math.pow(10, places);
        number = number * pow;
        long l = Math.round(number);
        return (double) l / pow;
    }
}
