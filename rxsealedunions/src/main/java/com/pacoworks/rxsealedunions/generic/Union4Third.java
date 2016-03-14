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

import com.pacoworks.rxsealedunions.Union4;

final class Union4Third<A, B, C, D> implements Union4<A, B, C, D> {
    private final C value;

    public Union4Third(C value) {
        this.value = value;
    }

    @Override
    public void continued(Action1<A> continuationFirst, Action1<B> continuationSecond,
                          Action1<C> continuationThird, Action1<D> continuationFourth) {
        continuationThird.call(value);
    }

    @Override
    public <R> R join(Func1<A, R> mapFirst, Func1<B, R> mapSecond, Func1<C, R> mapThird,
                      Func1<D, R> mapFourth) {
        return mapThird.call(value);
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Union4Third))
            return false;
        final Union4Third other = (Union4Third)o;
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
