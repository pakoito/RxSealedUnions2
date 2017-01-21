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

package com.pacoworks.rxsealedunions2;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Union4 represents a union containing an element of 4 possible types
 *
 * @param <First> first possible type
 * @param <Second> second possible type
 * @param <Third> third possible type
 * @param <Fourth> fourth possible type
 */
public interface Union4<First, Second, Third, Fourth> {
    /**
     * Executes one of the continuations depending on the element type
     */
    void continued(Consumer<First> continuationFirst, Consumer<Second> continuationSecond,
                   Consumer<Third> continuationThird, Consumer<Fourth> continuationFourth);

    /**
     * Transforms the element in the union to a new type
     *
     * @param <R> result type
     * @return an object of the result type
     */
    <R> R join(Function<First, R> mapFirst, Function<Second, R> mapSecond,
               Function<Third, R> mapThird, Function<Fourth, R> mapFourth);

    /**
     * Creator class for Union4
     */
    interface Factory<First, Second, Third, Fourth> {
        /**
         * Creates a Union4 wrapping a value of the first value
         *
         * @param value the value
         * @return a Union4 object wrapping the value
         */
        Union4<First, Second, Third, Fourth> first(First value);

        /**
         * Creates a Union4 wrapping a value of the second type
         *
         * @param value the value
         * @return a Union4 object wrapping the value
         */
        Union4<First, Second, Third, Fourth> second(Second value);

        /**
         * Creates a Union4 wrapping a value of the third type
         *
         * @param value the value
         * @return a Union4 object wrapping the value
         */
        Union4<First, Second, Third, Fourth> third(Third value);

        /**
         * Creates a Union4 wrapping a value of the fourth type
         *
         * @param value the value
         * @return a Union4 object wrapping the value
         */
        Union4<First, Second, Third, Fourth> fourth(Fourth value);
    }
}
