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
package cc.dreamcode.utilities.countdown;

import cc.dreamcode.utilities.MathUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Countdown {

    private final UUID uuid;
    private final Instant start;
    private final Duration duration;
    private final Runnable runnable;

    public Countdown(UUID uuid, Instant start, Duration duration) {
        this.uuid = uuid;
        this.start = start;
        this.duration = duration;
        this.runnable = () -> {
            // complete
        };
    }

    public boolean isComplete() {
        return MathUtil.isNegative(MathUtil.difference(this.start, this.duration));
    }
}
