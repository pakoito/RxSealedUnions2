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

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

import com.pacoworks.rxsealedunions.Union1;

final class Union1None<T> implements Union1<T> {
    @Override
    public void continued(Action1<T> continuationFirst, Action0 continuationNone) {
        continuationNone.call();
    }

    @Override
    public <R> R join(Func1<T, R> mapFirst, Func0<R> mapNone) {
        return mapNone.call();
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Union1None))
            return false;
        final Union1None other = (Union1None)o;
        return true;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }

    @Override
    public String toString() {
        return "None()";
    }
}
