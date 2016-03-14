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
 * Union8 represents a union containing an element of 8 possible types
 *
 * @param <First> first possible type
 * @param <Second> second possible type
 * @param <Third> third possible type
 * @param <Fourth> fourth possible type
 * @param <Fifth> fifth possible type
 * @param <Sixth> sixth possible type
 * @param <Seventh> seventh possible type
 * @param <Eighth> eighth possible type
 */
public interface Union8<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> {
    /**
     * Executes one of the continuations depending on the element type
     */
    void continued(Action1<First> continuationFirst, Action1<Second> continuationSecond,
                   Action1<Third> continuationThird, Action1<Fourth> continuationFourth,
                   Action1<Fifth> continuationFifth, Action1<Sixth> continuationSixth,
                   Action1<Seventh> continuationSeventh, Action1<Eighth> continuationEighth);

    /**
     * Transforms the element in the union to a new type
     *
     * @param <R> result type
     * @return an object of the result type
     */
    <R> R join(Func1<First, R> mapFirst, Func1<Second, R> mapSecond,
               Func1<Third, R> mapThird, Func1<Fourth, R> mapFourth, Func1<Fifth, R> mapFifth,
               Func1<Sixth, R> mapSixth, Func1<Seventh, R> mapSeventh,
               Func1<Eighth, R> mapEighth);

    /**
     * Creator class for Union8
     */
    interface Factory<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> {
        /**
         * Creates a Union8 wrapping a value of the first type
         *
         * @param value the value
         * @return a Union8 object wrapping the value
         */
        Union8<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> first(First value);

        /**
         * Creates a Union8 wrapping a value of the second type
         *
         * @param value the value
         * @return a Union8 object wrapping the value
         */
        Union8<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> second(Second value);

        /**
         * Creates a Union8 wrapping a value of the third type
         *
         * @param value the value
         * @return a Union8 object wrapping the value
         */
        Union8<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> third(Third value);

        /**
         * Creates a Union8 wrapping a value of the fourth type
         *
         * @param value the value
         * @return a Union8 object wrapping the value
         */
        Union8<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> fourth(Fourth value);

        /**
         * Creates a Union8 wrapping a value of the fifth type
         *
         * @param value the value
         * @return a Union8 object wrapping the value
         */
        Union8<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> fifth(Fifth value);

        /**
         * Creates a Union8 wrapping a value of the sixth type
         *
         * @param value the value
         * @return a Union8 object wrapping the value
         */
        Union8<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> sixth(Sixth value);

        /**
         * Creates a Union8 wrapping a value of the seventh type
         *
         * @param value the value
         * @return a Union8 object wrapping the value
         */
        Union8<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> seventh(Seventh value);

        /**
         * Creates a Union8 wrapping a value of the eighth type
         *
         * @param value the value
         * @return a Union8 object wrapping the value
         */
        Union8<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> eighth(Eighth value);
    }
}
