package cc.dreamcode.utilities;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class ParseUtil {
    public static Optional<Integer> parseInteger(@NonNull String arg) {
        try {
            int i = Integer.parseInt(arg);
            return Optional.of(i);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Double> parseDouble(@NonNull String arg) {
        try {
            double d = Double.parseDouble(arg);
            return Optional.of(d);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Float> parseFloat(@NonNull String arg) {
        try {
            float f = Float.parseFloat(arg);
            return Optional.of(f);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Boolean> parseBoolean(@NonNull String arg) {
        try {
            boolean b = Boolean.parseBoolean(arg);
            return Optional.of(b);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
