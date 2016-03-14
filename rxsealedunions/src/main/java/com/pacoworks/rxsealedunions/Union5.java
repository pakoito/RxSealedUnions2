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

package com.pacoworks.rxsealedunions;

import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Union5 represents a union containing an element of 5 possible types
 *
 * @param <First> first possible type
 * @param <Second> second possible type
 * @param <Third> third possible type
 * @param <Fourth> fourth possible type
 * @param <Fifth> fifth possible type
 */
public interface Union5<First, Second, Third, Fourth, Fifth> {
    /**
     * Executes one of the continuations depending on the element type
     */
    void continued(Action1<First> continuationFirst, Action1<Second> continuationSecond,
                   Action1<Third> continuationThird, Action1<Fourth> continuationFourth,
                   Action1<Fifth> continuationFifth);

    /**
     * Transforms the element in the union to a new type
     *
     * @param <R> result type
     * @return an object of the result type
     */
    <R> R join(Func1<First, R> mapFirst, Func1<Second, R> mapSecond,
               Func1<Third, R> mapThird, Func1<Fourth, R> mapFourth,
               Func1<Fifth, R> mapFifth);

    /**
     * Creator class for Union5
     */
    interface Factory<First, Second, Third, Fourth, Fifth> {
        /**
         * Creates a Union5 wrapping a value of the first type
         *
         * @param value the value
         * @return a Union5 object wrapping the value
         */
        Union5<First, Second, Third, Fourth, Fifth> first(First value);

        /**
         * Creates a Union5 wrapping a value of the second type
         *
         * @param value the value
         * @return a Union5 object wrapping the value
         */
        Union5<First, Second, Third, Fourth, Fifth> second(Second value);

        /**
         * Creates a Union5 wrapping a value of the third type
         *
         * @param value the value
         * @return a Union5 object wrapping the value
         */
        Union5<First, Second, Third, Fourth, Fifth> third(Third value);

        /**
         * Creates a Union5 wrapping a value of the fourth type
         *
         * @param value the value
         * @return a Union5 object wrapping the value
         */
        Union5<First, Second, Third, Fourth, Fifth> fourth(Fourth value);

        /**
         * Creates a Union5 wrapping a value of the fifth type
         *
         * @param value the value
         * @return a Union5 object wrapping the value
         */
        Union5<First, Second, Third, Fourth, Fifth> fifth(Fifth value);
    }
}
