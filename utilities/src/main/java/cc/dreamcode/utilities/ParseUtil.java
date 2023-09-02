package cc.dreamcode.utilities;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.time.Duration;
import java.time.format.DateTimeParseException;
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

    public static Optional<Long> parseLong(@NonNull String arg) {
        try {
            long l = Long.parseLong(arg);
            return Optional.of(l);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Short> parseShort(@NonNull String arg) {
        try {
            short s = Short.parseShort(arg);
            return Optional.of(s);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Byte> parseByte(@NonNull String arg) {
        try {
            byte b = Byte.parseByte(arg);
            return Optional.of(b);
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

    public static boolean parseBoolean(@NonNull String arg) {
        return Boolean.parseBoolean(arg);
    }

    public static Optional<Character> parseChar(@NonNull String arg) {
        try {
            char c = arg.charAt(0);
            return Optional.of(c);
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public static Optional<Duration> parsePeriod(@NonNull String period) {
        try {
            return Optional.ofNullable(Duration.parse("PT" + period.replace("d", "DT").toUpperCase()));
        }
        catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
