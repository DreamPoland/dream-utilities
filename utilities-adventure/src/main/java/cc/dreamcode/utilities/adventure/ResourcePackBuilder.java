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

package cc.dreamcode.utilities.adventure.builder;

import cc.dreamcode.utilities.adventure.AdventureUtil;
import cc.dreamcode.utilities.adventure.ComponentUtil;
import lombok.NonNull;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.text.Component;

import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class ResourcePackBuilder {

    private UUID id;
    private URI uri;
    private String hash = "";
    private Component prompt;
    private boolean required = false;
    private final Map<String, Object> placeholders = new HashMap<>();
    private Locale locale;

    public static ResourcePackBuilder of() {
        return new ResourcePackBuilder();
    }

    public static ResourcePackBuilder of(@NonNull String uriString, @NonNull String hash) {
        return new ResourcePackBuilder().uri(uriString).hash(hash);
    }

    public static ResourcePackBuilder of(@NonNull URI uri, @NonNull String hash) {
        return new ResourcePackBuilder().uri(uri).hash(hash);
    }

    public ResourcePackBuilder id(@NonNull UUID id) {
        this.id = id;
        return this;
    }

    public ResourcePackBuilder uri(@NonNull URI uri) {
        this.uri = uri;
        return this;
    }

    public ResourcePackBuilder uri(@NonNull String uriString) {
        this.uri = URI.create(uriString);
        return this;
    }

    public ResourcePackBuilder hash(@NonNull String hash) {
        this.hash = hash;
        return this;
    }

    public ResourcePackBuilder prompt(@NonNull Component prompt) {
        this.prompt = prompt;
        return this;
    }

    public ResourcePackBuilder prompt(@NonNull String promptMiniMessage) {
        this.prompt = AdventureUtil.component(promptMiniMessage);
        return this;
    }

    public ResourcePackBuilder prompt(@NonNull String promptMiniMessage, @NonNull Map<String, Object> placeholders) {
        this.prompt = AdventureUtil.component(promptMiniMessage, placeholders);
        return this;
    }

    public ResourcePackBuilder prompt(@NonNull String promptMiniMessage, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        this.prompt = AdventureUtil.component(promptMiniMessage, locale, placeholders);
        return this;
    }

    public ResourcePackBuilder required(boolean required) {
        this.required = required;
        return this;
    }

    public ResourcePackBuilder placeholder(@NonNull String key, Object value) {
        this.placeholders.put(key, value);
        return this;
    }

    public ResourcePackBuilder placeholders(@NonNull Map<String, Object> placeholders) {
        this.placeholders.putAll(placeholders);
        return this;
    }

    public ResourcePackBuilder locale(@NonNull Locale locale) {
        this.locale = locale;
        return this;
    }

    public ResourcePackInfo buildInfo() {
        if (this.uri == null) {
            throw new IllegalStateException("URI cannot be null when building ResourcePackInfo");
        }
        UUID finalId = this.id != null ? this.id : UUID.nameUUIDFromBytes(this.uri.toString().getBytes());
        return ResourcePackInfo.resourcePackInfo(finalId, this.uri, this.hash != null ? this.hash : "");
    }

    public ResourcePackRequest buildRequest() {
        ResourcePackInfo packInfo = buildInfo();
        Component finalPrompt = this.prompt;
        if (finalPrompt != null && !this.placeholders.isEmpty()) {
            finalPrompt = ComponentUtil.replace(finalPrompt, this.placeholders);
        }
        ResourcePackRequest.Builder builder = ResourcePackRequest.resourcePackRequest()
                .packs(packInfo)
                .required(this.required);
        if (finalPrompt != null) {
            builder.prompt(finalPrompt);
        }
        return builder.build();
    }

    public ResourcePackRequest send(@NonNull Audience audience) {
        ResourcePackRequest request = buildRequest();
        audience.sendResourcePacks(request);
        return request;
    }
}
