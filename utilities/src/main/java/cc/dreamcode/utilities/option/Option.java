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
package cc.dreamcode.utilities.option;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

@EqualsAndHashCode
@ToString
public final class Option<V> {

    private final V value;

    public Option() {
        this.value = null;
    }

    public Option(@NonNull V value) {
        this.value = value;
    }

    @SuppressWarnings({ "OptionalUsedAsFieldOrParameterType" })
    public Option(@NonNull Optional<V> optionalValue) {
        this.value = optionalValue.orElse(null);
    }

    public static <T> Option<T> empty() {
        return new Option<>();
    }

    public static <T> Option<T> of(@NonNull T value) {
        return new Option<>(value);
    }

    public static <T> Option<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    @SuppressWarnings({ "OptionalUsedAsFieldOrParameterType" })
    public static <T> Option<T> ofOptional(@NonNull Optional<T> optionalValue) {
        return new Option<>(optionalValue);
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public boolean isEmpty() {
        return this.value == null;
    }

    public V get() {
        return this.value;
    }

    public V getOrElse(V value) {
        return this.isPresent() ? this.get() : value;
    }

    public V getOrInvokeElse(@NonNull Supplier<V> value) {
        return this.isPresent() ? this.get() : value.get();
    }

    public V getOrNull() {
        return this.getOrElse(null);
    }

    public V getOrThrow() throws NoSuchElementException {
        if (this.isPresent()) {
            return this.get();
        }

        throw new NoSuchElementException("No optional result present");
    }

    public <X extends Throwable> V getOrThrow(@NonNull Supplier<X> supplier) throws X {
        if (this.isPresent()) {
            return this.get();
        }

        throw supplier.get();
    }

    public Option<V> ifPresent(@NonNull Consumer<? super V> consumer) {
        if (this.isEmpty()) {
            return this;
        }

        consumer.accept(this.value);
        return this;
    }

    public Option<V> ifPresentOrElse(@NonNull Consumer<? super V> consumer, @NonNull Runnable runnable) {
        if (this.isEmpty()) {
            runnable.run();
            return this;
        }

        consumer.accept(this.value);
        return this;
    }

    public Stream<V> stream() {
        return Stream.of(this.value);
    }

    public Optional<V> toOptional() {
        return Optional.ofNullable(this.value);
    }
}
