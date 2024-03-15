package cc.dreamcode.utilities;

import eu.okaeri.placeholders.Placeholders;
import eu.okaeri.placeholders.message.CompiledMessage;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;

@UtilityClass
public class StringUtil {

    private static Placeholders placeholders = Placeholders.create(true);

    public static String join(List<String> stringList) {
        return join(stringList.toArray(new String[0]), "", 0, stringList.size());
    }

    public static String join(List<String> stringList, String separator) {
        return join(stringList.toArray(new String[0]), separator, 0, stringList.size());
    }

    public static String join(List<String> stringList, String separator, int from, int to) {
        return join(stringList.toArray(new String[0]), separator, from, to);
    }

    public static String join(String[] array) {
        return join(array, "", 0, array.length);
    }

    public static String join(String[] array, String separator) {
        return join(array, separator, 0, array.length);
    }

    public static String join(String[] array, String separator, int from, int to) {
        final StringBuilder stringBuilder = new StringBuilder();
        for(int i = from; i < to; i++) {
            if(i > from) stringBuilder.append(separator);
            if(array[i] != null) stringBuilder.append(array[i]);
        }
        return stringBuilder.toString();
    }

    public static String replace(@NonNull String text, @NonNull String from, @NonNull String to) {
        final CompiledMessage compiledMessage = CompiledMessage.of(text);

        return StringUtil.placeholders.contextOf(compiledMessage)
                .with(from, to)
                .apply();
    }

    public static String replace(@NonNull String text, @NonNull Map<String, Object> placeholders) {
        final CompiledMessage compiledMessage = CompiledMessage.of(text);

        return StringUtil.placeholders.contextOf(compiledMessage)
                .with(placeholders)
                .apply();
    }

    public static Placeholders getPlaceholders() {
        return StringUtil.placeholders;
    }

    public static void setPlaceholders(@NonNull Placeholders placeholders) {
        StringUtil.placeholders = placeholders;
    }
}
