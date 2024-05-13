package cc.dreamcode.utilities;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.time.Duration;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class ParseUtil {

    private static final Pattern SIMPLE_DURATION_PATTERN = Pattern.compile("(?<value>-?[0-9]+)(?<unit>ms|ns|d|h|m|s)");
    private static final Pattern JBOD_FULL_DURATION_PATTERN = Pattern.compile("((-?[0-9]+)(ms|ns|d|h|m|s))+");

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
        return ParseUtil.readJbodPattern(period);
    }

    private static Duration timeToDuration(long longValue, String unit) {
        switch (unit) {
            case "d":
                return Duration.ofDays(longValue);
            case "h":
                return Duration.ofHours(longValue);
            case "m":
                return Duration.ofMinutes(longValue);
            case "s":
                return Duration.ofSeconds(longValue);
            case "ms":
                return Duration.ofMillis(longValue);
            case "ns":
                return Duration.ofNanos(longValue);
            default:
                throw new IllegalArgumentException("Really, this one should not be possible: " + unit);
        }
    }

    private static Optional<Duration> readJbodPattern(String text) {

        text = text.toLowerCase(Locale.ROOT);
        text = text.replace(" ", "");

        Matcher fullMatcher = JBOD_FULL_DURATION_PATTERN.matcher(text);
        if (!fullMatcher.matches()) {
            return Optional.empty();
        }

        Matcher matcher = SIMPLE_DURATION_PATTERN.matcher(text);
        boolean matched = false;
        BigInteger currentValue = BigInteger.valueOf(0);

        while (matcher.find()) {
            matched = true;
            long longValue = Long.parseLong(matcher.group("value"));
            String unit = matcher.group("unit");
            currentValue = currentValue.add(BigInteger.valueOf(timeToDuration(longValue, unit).toNanos()));
        }

        return matched ? Optional.of(Duration.ofNanos(currentValue.longValueExact())) : Optional.empty();
    }
}
