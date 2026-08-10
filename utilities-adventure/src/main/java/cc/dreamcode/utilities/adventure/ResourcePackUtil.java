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

import cc.dreamcode.utilities.adventure.builder.ResourcePackBuilder;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.text.Component;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@UtilityClass
public final class ResourcePackUtil {

    public static ResourcePackBuilder builder() {
        return ResourcePackBuilder.of();
    }

    public static ResourcePackBuilder builder(@NonNull String uriString, @NonNull String hash) {
        return ResourcePackBuilder.of(uriString, hash);
    }

    public static ResourcePackBuilder builder(@NonNull URI uri, @NonNull String hash) {
        return ResourcePackBuilder.of(uri, hash);
    }

    public static ResourcePackInfo createInfo(@NonNull UUID id, @NonNull URI uri, @NonNull String hash) {
        return ResourcePackInfo.resourcePackInfo(id, uri, hash);
    }

    public static ResourcePackInfo createInfo(@NonNull UUID id, @NonNull String uriString, @NonNull String hash) {
        return createInfo(id, URI.create(uriString), hash);
    }

    public static ResourcePackInfo createInfo(@NonNull URI uri, @NonNull String hash) {
        return createInfo(UUID.nameUUIDFromBytes(uri.toString().getBytes()), uri, hash);
    }

    public static ResourcePackInfo createInfo(@NonNull String uriString, @NonNull String hash) {
        return createInfo(URI.create(uriString), hash);
    }

    public static ResourcePackRequest createRequest(@NonNull Collection<ResourcePackInfo> packInfos, Component prompt, boolean required) {
        ResourcePackRequest.Builder builder = ResourcePackRequest.resourcePackRequest()
                .packs(packInfos)
                .required(required);
        if (prompt != null) {
            builder.prompt(prompt);
        }
        return builder.build();
    }

    public static ResourcePackRequest createRequest(@NonNull ResourcePackInfo packInfo, Component prompt, boolean required) {
        return createRequest(Collections.singletonList(packInfo), prompt, required);
    }

    public static ResourcePackRequest createRequest(@NonNull ResourcePackInfo packInfo, @NonNull String promptMiniMessage, boolean required) {
        return createRequest(packInfo, AdventureUtil.component(promptMiniMessage), required);
    }

    public static ResourcePackRequest createRequest(@NonNull ResourcePackInfo packInfo, @NonNull String promptMiniMessage, boolean required, @NonNull Map<String, Object> placeholders) {
        return createRequest(packInfo, AdventureUtil.component(promptMiniMessage, placeholders), required);
    }

    public static ResourcePackRequest createRequest(@NonNull ResourcePackInfo packInfo, @NonNull String promptMiniMessage, boolean required, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return createRequest(packInfo, AdventureUtil.component(promptMiniMessage, locale, placeholders), required);
    }

    public static void sendResourcePack(@NonNull Audience audience, @NonNull ResourcePackInfo packInfo) {
        audience.sendResourcePacks(ResourcePackRequest.resourcePackRequest().packs(packInfo).build());
    }

    public static void sendResourcePack(@NonNull Audience audience, @NonNull ResourcePackRequest request) {
        audience.sendResourcePacks(request);
    }

    public static void sendResourcePack(@NonNull Audience audience, @NonNull String uriString, @NonNull String hash) {
        sendResourcePack(audience, createInfo(uriString, hash));
    }

    public static void sendResourcePack(@NonNull Audience audience, @NonNull String uriString, @NonNull String hash, boolean required) {
        ResourcePackInfo info = createInfo(uriString, hash);
        sendResourcePack(audience, createRequest(info, (Component) null, required));
    }

    public static void sendResourcePack(@NonNull Audience audience, @NonNull String uriString, @NonNull String hash, @NonNull String promptMiniMessage, boolean required) {
        ResourcePackInfo info = createInfo(uriString, hash);
        sendResourcePack(audience, createRequest(info, promptMiniMessage, required));
    }

    public static void sendResourcePack(@NonNull Audience audience, @NonNull String uriString, @NonNull String hash, @NonNull String promptMiniMessage, boolean required, @NonNull Map<String, Object> placeholders) {
        ResourcePackInfo info = createInfo(uriString, hash);
        sendResourcePack(audience, createRequest(info, promptMiniMessage, required, placeholders));
    }

    public static void removeResourcePack(@NonNull Audience audience, @NonNull UUID packId) {
        audience.removeResourcePacks(packId);
    }

    public static void removeResourcePacks(@NonNull Audience audience, @NonNull UUID... packIds) {
        audience.removeResourcePacks(java.util.Arrays.asList(packIds));
    }

    public static void removeResourcePacks(@NonNull Audience audience, @NonNull Iterable<UUID> packIds) {
        audience.removeResourcePacks(packIds);
    }

    public static void removeResourcePack(@NonNull Audience audience, @NonNull ResourcePackInfo packInfo) {
        audience.removeResourcePacks(packInfo.id());
    }

    public static void clearResourcePacks(@NonNull Audience audience) {
        audience.clearResourcePacks();
    }
}
