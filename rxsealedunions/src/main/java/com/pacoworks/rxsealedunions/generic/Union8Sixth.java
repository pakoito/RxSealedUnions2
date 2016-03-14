/*
 * Copyright (c) pakoito 2016
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

package com.pacoworks.rxsealedunions.generic;

import rx.functions.Action1;
import rx.functions.Func1;

import com.pacoworks.rxsealedunions.Union8;

final class Union8Sixth<A, B, C, D, E, F, G, H> implements Union8<A, B, C, D, E, F, G, H> {
    private final F value;

    public Union8Sixth(F value) {
        this.value = value;
    }

    @Override
    public void continued(Action1<A> continuationFirst, Action1<B> continuationSecond,
                          Action1<C> continuationThird, Action1<D> continuationFourth,
                          Action1<E> continuationFifth, Action1<F> continuationSixth,
                          Action1<G> continuationSeventh, Action1<H> continuationEighth) {
        continuationSixth.call(value);
    }

    @Override
    public <R> R join(Func1<A, R> mapFirst, Func1<B, R> mapSecond, Func1<C, R> mapThird,
                      Func1<D, R> mapFourth, Func1<E, R> mapFifth, Func1<F, R> mapSixth,
                      Func1<G, R> mapSeventh, Func1<H, R> mapEighth) {
        return mapSixth.call(value);
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Union8Sixth))
            return false;
        final Union8Sixth other = (Union8Sixth)o;
        final Object this$value = this.value;
        final Object other$value = other.value;
        return this$value == null ? other$value == null : this$value.equals(other$value);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $value = this.value;
        result = result * PRIME + ($value == null ? 0 : $value.hashCode());
        return result;
    }
}
