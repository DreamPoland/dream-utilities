package cc.dreamcode.utilities.adventure;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@UtilityClass
public final class ComponentUtil {

    public static Component join(@NonNull List<Component> components, @NonNull Component separator) {
        if (components.isEmpty()) {
            return Component.empty();
        }
        net.kyori.adventure.text.TextComponent.Builder builder = Component.text();
        for (int i = 0; i < components.size(); i++) {
            if (i > 0) {
                builder.append(separator);
            }
            builder.append(components.get(i));
        }
        return builder.build();
    }

    public static Component join(@NonNull Component[] components, @NonNull Component separator) {
        return join(Arrays.asList(components), separator);
    }

    public static Component replace(@NonNull Component component, @NonNull Pattern pattern, @NonNull Component replacement) {
        return component.replaceText(TextReplacementConfig.builder()
                .match(pattern)
                .replacement(x -> replacement)
                .build());
    }

    public static Component replace(@NonNull Component component, @NonNull String literalText, @NonNull Component replacement) {
        return replace(component, Pattern.compile(Pattern.quote(literalText)), replacement);
    }
}
