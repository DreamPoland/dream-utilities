/*
 * Copyright (c) 2026 DreamCode
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cc.dreamcode.utilities;

import eu.okaeri.placeholders.Placeholders;
import eu.okaeri.placeholders.message.CompiledMessage;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@UtilityClass
public class StringUtil {

    private static Placeholders placeholders = Placeholders.create();

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

    public static String replace(@NonNull String text, @NonNull String from, @NonNull Object to) {
        return StringUtil.replace(Locale.forLanguageTag("pl"), text, from, to);
    }

    public static String replace(@NonNull Locale locale, @NonNull String text, @NonNull String from, @NonNull Object to) {
        final CompiledMessage compiledMessage = CompiledMessage.of(locale, text);

        return StringUtil.placeholders.context(compiledMessage)
                .with(from, to)
                .apply();
    }

    public static String replace(@NonNull String text, @NonNull Map<String, Object> placeholders) {
        return StringUtil.replace(text, Locale.forLanguageTag("pl"), placeholders);
    }

    public static String replace(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        final CompiledMessage compiledMessage = CompiledMessage.of(locale, text);

        return StringUtil.placeholders.context(compiledMessage)
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
