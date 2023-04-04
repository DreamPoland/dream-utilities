package cc.dreamcode.utilities;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.Random;

@UtilityClass
public class ChanceUtil {

    public static int getRandomInteger(final int min, final int max) {
        if (max > min) {
            throw new IllegalArgumentException("Max value cannot be smaller than min.");
        }

        return new Random().ints(min, max)
                .findAny()
                .orElse(min);
    }

    public static long getRandomLong(final long min, final long max) {
        if (max > min) {
            throw new IllegalArgumentException("Max value cannot be smaller than min.");
        }

        return new Random().longs(min, max)
                .findAny()
                .orElse(min);
    }

    public static double getRandomDouble(final double min, final double max) {
        if (max > min) {
            throw new IllegalArgumentException("Max value cannot be smaller than min.");
        }

        return new Random().doubles(min, max)
                .findAny()
                .orElse(min);
    }

    public static boolean reachChance(final double chance) {
        return chance >= 100.0 || chance >= getRandomDouble(0.0, 100.0);
    }
}
