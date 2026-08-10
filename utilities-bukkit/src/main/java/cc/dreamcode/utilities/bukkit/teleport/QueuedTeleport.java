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
package cc.dreamcode.utilities.bukkit.teleport;

import cc.dreamcode.utilities.MathUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.HumanEntity;

import java.time.Duration;
import java.time.Instant;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Data
@RequiredArgsConstructor
public class QueuedTeleport {

    private final Instant instant;
    private final Duration duration;
    private final boolean cancelOnMove;

    private final BiConsumer<HumanEntity, Duration> countdownNotice;
    private final Consumer<HumanEntity> movedNotice;
    private final Consumer<HumanEntity> alreadyInAction;
    private final Consumer<HumanEntity> taskAfter;

    public Duration getCountdown() {
        return MathUtil.difference(this.instant, this.duration);
    }

}
