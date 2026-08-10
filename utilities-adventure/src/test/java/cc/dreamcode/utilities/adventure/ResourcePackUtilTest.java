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

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResourcePackUtilTest {

    @Test
    void testCreateInfo() {
        UUID id = UUID.randomUUID();
        URI uri = URI.create("https://example.com/pack.zip");
        String hash = "e003185387405e3f43b3...hash";

        ResourcePackInfo info = ResourcePackUtil.createInfo(id, uri, hash);
        assertNotNull(info);
        assertEquals(id, info.id());
        assertEquals(uri, info.uri());
        assertEquals(hash, info.hash());
    }

    @Test
    void testCreateRequestWithPlaceholders() {
        ResourcePackInfo info = ResourcePackUtil.createInfo("https://example.com/pack.zip", "abc123hash");
        ResourcePackRequest request = ResourcePackUtil.createRequest(
                info,
                "Please download pack for server {server}",
                true,
                Map.of("server", "Survival")
        );

        assertNotNull(request);
        assertTrue(request.required());
        assertNotNull(request.prompt());
        assertEquals("Please download pack for server Survival", ComponentUtil.toPlainText(request.prompt()));
    }

    @Test
    void testSendAndRemoveResourcePack() {
        Audience audience = mock(Audience.class);
        UUID packId = UUID.randomUUID();

        ResourcePackUtil.sendResourcePack(audience, "https://example.com/pack.zip", "hash123", "Required pack", true);

        ArgumentCaptor<ResourcePackRequest> captor = ArgumentCaptor.forClass(ResourcePackRequest.class);
        verify(audience).sendResourcePacks(captor.capture());
        assertNotNull(captor.getValue());

        ResourcePackUtil.removeResourcePack(audience, packId);
        verify(audience).removeResourcePacks(packId);

        ResourcePackUtil.clearResourcePacks(audience);
        verify(audience).clearResourcePacks();
    }
}
