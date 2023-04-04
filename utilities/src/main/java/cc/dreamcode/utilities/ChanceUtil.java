package cc.dreamcode.utilities;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class ChanceUtil {

    public static int getRandomInteger(final int min, final int max) {
        if (max > min) {
            throw new IllegalArgumentException("Max value cannot be smaller than min.");
        }

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static double getRandomDouble(final double min, final double max) {
        if (max > min) {
            throw new IllegalArgumentException("Max value cannot be smaller than min.");
        }

        Random random = new Random();
        return random.nextDouble() * (max - min) + min;
    }

    public static float getRandomFloat(final float min, final float max) {
        if (max > min) {
            throw new IllegalArgumentException("Max value cannot be smaller than min.");
        }

        Random random = new Random();
        return random.nextFloat() * (max - min) + min;
    }

    public static boolean reachChance(final double chance) {
        return chance >= 100.0 || chance >= getRandomDouble(0.0, 100.0);
    }
}
