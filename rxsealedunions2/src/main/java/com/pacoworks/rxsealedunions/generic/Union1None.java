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

import com.pacoworks.rxsealedunions.Union1;

import java.util.concurrent.Callable;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

final class Union1None<T> implements Union1<T> {
    @Override
    public void continued(Consumer<T> continuationFirst, Action continuationNone) {
        try {
            continuationNone.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <R> R join(Function<T, R> mapFirst, Callable<R> mapNone) {
        try {
            return mapNone.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Union1None))
            return false;
        final Union1None other = (Union1None)o;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        return result;
    }

    @Override
    public String toString() {
        return "None()";
    }
}
