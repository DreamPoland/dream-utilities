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

package cc.dreamcode.utilities.adventure;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;

import java.util.Locale;
import java.util.Map;

@UtilityClass
public final class PlayerListUtil {

    public static void sendHeaderAndFooter(@NonNull Audience audience, @NonNull Component header, @NonNull Component footer) {
        audience.sendPlayerListHeaderAndFooter(header, footer);
    }

    public static void sendHeaderAndFooter(@NonNull Audience audience, @NonNull String headerMiniMessage, @NonNull String footerMiniMessage) {
        sendHeaderAndFooter(audience, AdventureUtil.component(headerMiniMessage), AdventureUtil.component(footerMiniMessage));
    }

    public static void sendHeaderAndFooter(@NonNull Audience audience, @NonNull String headerMiniMessage, @NonNull String footerMiniMessage, @NonNull Map<String, Object> placeholders) {
        sendHeaderAndFooter(audience, AdventureUtil.component(headerMiniMessage, placeholders), AdventureUtil.component(footerMiniMessage, placeholders));
    }

    public static void sendHeaderAndFooter(@NonNull Audience audience, @NonNull String headerMiniMessage, @NonNull String footerMiniMessage, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        sendHeaderAndFooter(audience, AdventureUtil.component(headerMiniMessage, locale, placeholders), AdventureUtil.component(footerMiniMessage, locale, placeholders));
    }

    public static void sendHeader(@NonNull Audience audience, @NonNull Component header) {
        audience.sendPlayerListHeader(header);
    }

    public static void sendHeader(@NonNull Audience audience, @NonNull String headerMiniMessage) {
        sendHeader(audience, AdventureUtil.component(headerMiniMessage));
    }

    public static void sendHeader(@NonNull Audience audience, @NonNull String headerMiniMessage, @NonNull Map<String, Object> placeholders) {
        sendHeader(audience, AdventureUtil.component(headerMiniMessage, placeholders));
    }

    public static void sendFooter(@NonNull Audience audience, @NonNull Component footer) {
        audience.sendPlayerListFooter(footer);
    }

    public static void sendFooter(@NonNull Audience audience, @NonNull String footerMiniMessage) {
        sendFooter(audience, AdventureUtil.component(footerMiniMessage));
    }

    public static void sendFooter(@NonNull Audience audience, @NonNull String footerMiniMessage, @NonNull Map<String, Object> placeholders) {
        sendFooter(audience, AdventureUtil.component(footerMiniMessage, placeholders));
    }
}
