package cc.dreamcode.utilities.bungee.adventure;

import net.kyori.adventure.text.Component;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class AdventureLegacyColor implements UnaryOperator<Component> {

    @Override
    public Component apply(Component component) {
        return component.replaceText(builder -> builder.match(Pattern.compile(".*"))
                .replacement((matchResult, builder1) -> AdventureLegacy.component(matchResult.group())));
    }
}
