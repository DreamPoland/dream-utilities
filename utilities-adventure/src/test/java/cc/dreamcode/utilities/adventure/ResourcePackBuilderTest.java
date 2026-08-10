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
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResourcePackBuilderTest {

    @Test
    void testBuildInfo() {
        UUID id = UUID.randomUUID();
        String url = "https://cdn.example.com/pack.zip";
        String hash = "1234567890abcdef1234567890abcdef12345678";

        ResourcePackInfo info = ResourcePackBuilder.of(url, hash)
                .id(id)
                .buildInfo();

        assertNotNull(info);
        assertEquals(id, info.id());
        assertEquals(URI.create(url), info.uri());
        assertEquals(hash, info.hash());
    }

    @Test
    void testBuildRequestAndSend() {
        Audience audience = mock(Audience.class);
        String url = "https://cdn.example.com/pack.zip";

        ResourcePackRequest request = ResourcePackBuilder.of()
                .uri(url)
                .hash("hash123")
                .prompt("Pobierz paczke dla {player}")
                .placeholder("player", "Jan")
                .required(true)
                .send(audience);

        assertNotNull(request);
        assertTrue(request.required());
        assertNotNull(request.prompt());
        assertEquals("Pobierz paczke dla Jan", ComponentUtil.toPlainText(request.prompt()));

        ArgumentCaptor<ResourcePackRequest> captor = ArgumentCaptor.forClass(ResourcePackRequest.class);
        verify(audience).sendResourcePacks(captor.capture());
        assertEquals(request, captor.getValue());
    }
}
