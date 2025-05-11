package cc.dreamcode.utilities.adventure;

import cc.dreamcode.utilities.color.ColorProcessor;
import lombok.NonNull;

import java.util.Locale;
import java.util.Map;

public class AdventureProcessor implements ColorProcessor {
    @Override
    public String color(@NonNull String text) {
        return AdventureUtil.process(text);
    }

    @Override
    public String color(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return AdventureUtil.process(text, locale, placeholders, colorizePlaceholders);
    }

    @Override
    public String decolor(@NonNull String text) {
        return AdventureUtil.deprocess(text);
    }
}
