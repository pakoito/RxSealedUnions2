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

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Union9 represents a union containing an element of 9 possible types
 *
 * @param <First> first possible type
 * @param <Second> second possible type
 * @param <Third> third possible type
 * @param <Fourth> fourth possible type
 * @param <Fifth> fifth possible type
 * @param <Sixth> sixth possible type
 * @param <Seventh> seventh possible type
 * @param <Eighth> eighth possible type
 * @param <Ninth> ninth possible type
 */
public interface Union9<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> {
    /**
     * Executes one of the continuations depending on the element type
     */
    void continued(Consumer<First> continuationFirst, Consumer<Second> continuationSecond,
                   Consumer<Third> continuationThird, Consumer<Fourth> continuationFourth,
                   Consumer<Fifth> continuationFifth, Consumer<Sixth> continuationSixth,
                   Consumer<Seventh> continuationSeventh, Consumer<Eighth> continuationEighth,
                   Consumer<Ninth> continuationNinth);

    /**
     * Transforms the element in the union to a new type
     *
     * @param <R> result type
     * @return an object of the result type
     */
    <R> R join(Function<First, R> mapFirst, Function<Second, R> mapSecond,
               Function<Third, R> mapThird, Function<Fourth, R> mapFourth, Function<Fifth, R> mapFifth,
               Function<Sixth, R> mapSixth, Function<Seventh, R> mapSeventh,
               Function<Eighth, R> mapEighth, Function<Ninth, R> mapNinth);

    /**
     * Creator class for Union9
     */
    interface Factory<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> {
        /**
         * Creates a Union9 wrapping a value of the first type
         *
         * @param value the value
         * @return a Union9 object wrapping the value
         */
        Union9<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> first(
                First value);

        /**
         * Creates a Union9 wrapping a value of the second type
         *
         * @param value the value
         * @return a Union9 object wrapping the value
         */
        Union9<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> second(
                Second value);

        /**
         * Creates a Union9 wrapping a value of the third type
         *
         * @param value the value
         * @return a Union9 object wrapping the value
         */
        Union9<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> third(
                Third value);

        /**
         * Creates a Union9 wrapping a value of the fourth type
         *
         * @param value the value
         * @return a Union9 object wrapping the value
         */
        Union9<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> fourth(
                Fourth value);

        /**
         * Creates a Union9 wrapping a value of the fifth type
         *
         * @param value the value
         * @return a Union9 object wrapping the value
         */
        Union9<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> fifth(
                Fifth value);

        /**
         * Creates a Union9 wrapping a value of the sixth type
         *
         * @param value the value
         * @return a Union9 object wrapping the value
         */
        Union9<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> sixth(
                Sixth value);

        /**
         * Creates a Union9 wrapping a value of the seventh type
         *
         * @param value the value
         * @return a Union9 object wrapping the value
         */
        Union9<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> seventh(
                Seventh value);

        /**
         * Creates a Union9 wrapping a value of the eighth type
         *
         * @param value the value
         * @return a Union9 object wrapping the value
         */
        Union9<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> eighth(
                Eighth value);

        /**
         * Creates a Union9 wrapping a value of the ninth type
         *
         * @param value the value
         * @return a Union9 object wrapping the value
         */
        Union9<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> ninth(
                Ninth value);
    }
}
