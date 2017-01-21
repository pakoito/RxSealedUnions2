/*
 * Copyright (c) pakoito 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pacoworks.rxsealedunions2.generic;

import com.pacoworks.rxsealedunions2.Union5;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

final class Union5Fifth<A, B, C, D, E> implements Union5<A, B, C, D, E> {
    private final E value;

    public Union5Fifth(E value) {
        this.value = value;
    }

    @Override
    public void continued(Consumer<A> continuationFirst, Consumer<B> continuationSecond,
            Consumer<C> continuationThird, Consumer<D> continuationFourth,
            Consumer<E> continuationFifth) {
        try {
            continuationFifth.accept(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <R> R join(Function<A, R> mapFirst, Function<B, R> mapSecond, Function<C, R> mapThird,
            Function<D, R> mapFourth, Function<E, R> mapFifth) {
        try {
            return mapFifth.apply(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Union5Fifth))
            return false;
        final Union5Fifth other = (Union5Fifth)o;
        final Object this$value = this.value;
        final Object other$value = other.value;
        return this$value == null ? other$value == null : this$value.equals(other$value);
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $value = this.value;
        result = result * PRIME + ($value == null ? 0 : $value.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
