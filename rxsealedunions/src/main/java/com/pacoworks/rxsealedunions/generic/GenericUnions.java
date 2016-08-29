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

import com.pacoworks.rxsealedunions.*;

public final class GenericUnions {
    public static <Result> Union0.Factory<Result> nulletFactory() {
        return new Union0.Factory<Result>() {
            @Override
            public Union0<Result> first(Result result) {
                return new Union0First<>(result);
            }
        };
    }

    public static <Result> Union1.Factory<Result> singletFactory() {
        return new Union1.Factory<Result>() {
            @Override
            public Union1<Result> first(Result result) {
                return new Union1First<>(result);
            }

            @Override
            public Union1<Result> none() {
                return new Union1None<>();
            }
        };
    }

    public static <T, U> Union2.Factory<T, U> doubletFactory() {
        return new Union2.Factory<T, U>() {
            @Override
            public Union2<T, U> first(T t) {
                return new Union2First<>(t);
            }

            @Override
            public Union2<T, U> second(U u) {
                return new Union2Second<>(u);
            }
        };
    }

    public static <T, U, V> Union3.Factory<T, U, V> tripletFactory() {
        return new Union3.Factory<T, U, V>() {
            @Override
            public Union3<T, U, V> first(T t) {
                return new Union3First<>(t);
            }

            @Override
            public Union3<T, U, V> second(U u) {
                return new Union3Second<>(u);
            }

            @Override
            public Union3<T, U, V> third(V v) {
                return new Union3Third<>(v);
            }
        };
    }

    public static <A, B, C, D> Union4.Factory<A, B, C, D> quartetFactory() {
        return new Union4.Factory<A, B, C, D>() {
            @Override
            public Union4<A, B, C, D> first(A a) {
                return new Union4First<>(a);
            }

            @Override
            public Union4<A, B, C, D> second(B b) {
                return new Union4Second<>(b);
            }

            @Override
            public Union4<A, B, C, D> third(C c) {
                return new Union4Third<>(c);
            }

            @Override
            public Union4<A, B, C, D> fourth(D d) {
                return new Union4Fourth<>(d);
            }
        };
    }

    public static <A, B, C, D, E> Union5.Factory<A, B, C, D, E> quintetFactory() {
        return new Union5.Factory<A, B, C, D, E>() {
            @Override
            public Union5<A, B, C, D, E> first(A a) {
                return new Union5First<>(a);
            }

            @Override
            public Union5<A, B, C, D, E> second(B b) {
                return new Union5Second<>(b);
            }

            @Override
            public Union5<A, B, C, D, E> third(C c) {
                return new Union5Third<>(c);
            }

            @Override
            public Union5<A, B, C, D, E> fourth(D d) {
                return new Union5Fourth<>(d);
            }

            @Override
            public Union5<A, B, C, D, E> fifth(E e) {
                return new Union5Fifth<>(e);
            }
        };
    }

    public static <A, B, C, D, E, F> Union6.Factory<A, B, C, D, E, F> sextetFactory() {
        return new Union6.Factory<A, B, C, D, E, F>() {
            @Override
            public Union6<A, B, C, D, E, F> first(A a) {
                return new Union6First<>(a);
            }

            @Override
            public Union6<A, B, C, D, E, F> second(B b) {
                return new Union6Second<>(b);
            }

            @Override
            public Union6<A, B, C, D, E, F> third(C c) {
                return new Union6Third<>(c);
            }

            @Override
            public Union6<A, B, C, D, E, F> fourth(D d) {
                return new Union6Fourth<>(d);
            }

            @Override
            public Union6<A, B, C, D, E, F> fifth(E e) {
                return new Union6Fifth<>(e);
            }

            @Override
            public Union6<A, B, C, D, E, F> sixth(F f) {
                return new Union6Sixth<>(f);
            }
        };
    }

    public static <A, B, C, D, E, F, G> Union7.Factory<A, B, C, D, E, F, G> septetFactory() {
        return new Union7.Factory<A, B, C, D, E, F, G>() {
            @Override
            public Union7<A, B, C, D, E, F, G> first(A a) {
                return new Union7First<>(a);
            }

            @Override
            public Union7<A, B, C, D, E, F, G> second(B b) {
                return new Union7Second<>(b);
            }

            @Override
            public Union7<A, B, C, D, E, F, G> third(C c) {
                return new Union7Third<>(c);
            }

            @Override
            public Union7<A, B, C, D, E, F, G> fourth(D d) {
                return new Union7Fourth<>(d);
            }

            @Override
            public Union7<A, B, C, D, E, F, G> fifth(E e) {
                return new Union7Fifth<>(e);
            }

            @Override
            public Union7<A, B, C, D, E, F, G> sixth(F f) {
                return new Union7Sixth<>(f);
            }

            @Override
            public Union7<A, B, C, D, E, F, G> seventh(G g) {
                return new Union7Seventh<>(g);
            }
        };
    }

    public static <A, B, C, D, E, F, G, H> Union8.Factory<A, B, C, D, E, F, G, H> octetFactory() {
        return new Union8.Factory<A, B, C, D, E, F, G, H>() {
            @Override
            public Union8<A, B, C, D, E, F, G, H> first(A a) {
                return new Union8First<>(a);
            }

            @Override
            public Union8<A, B, C, D, E, F, G, H> second(B b) {
                return new Union8Second<>(b);
            }

            @Override
            public Union8<A, B, C, D, E, F, G, H> third(C c) {
                return new Union8Third<>(c);
            }

            @Override
            public Union8<A, B, C, D, E, F, G, H> fourth(D d) {
                return new Union8Fourth<>(d);
            }

            @Override
            public Union8<A, B, C, D, E, F, G, H> fifth(E e) {
                return new Union8Fifth<>(e);
            }

            @Override
            public Union8<A, B, C, D, E, F, G, H> sixth(F f) {
                return new Union8Sixth<>(f);
            }

            @Override
            public Union8<A, B, C, D, E, F, G, H> seventh(G g) {
                return new Union8Seventh<>(g);
            }

            @Override
            public Union8<A, B, C, D, E, F, G, H> eighth(H h) {
                return new Union8Eighth<>(h);
            }
        };
    }

    public static <A, B, C, D, E, F, G, H, I> Union9.Factory<A, B, C, D, E, F, G, H, I> nonetFactory() {
        return new Union9.Factory<A, B, C, D, E, F, G, H, I>() {
            @Override
            public Union9<A, B, C, D, E, F, G, H, I> first(A a) {
                return new Union9First<>(a);
            }

            @Override
            public Union9<A, B, C, D, E, F, G, H, I> second(B b) {
                return new Union9Second<>(b);
            }

            @Override
            public Union9<A, B, C, D, E, F, G, H, I> third(C c) {
                return new Union9Third<>(c);
            }

            @Override
            public Union9<A, B, C, D, E, F, G, H, I> fourth(D d) {
                return new Union9Fourth<>(d);
            }

            @Override
            public Union9<A, B, C, D, E, F, G, H, I> fifth(E e) {
                return new Union9Fifth<>(e);
            }

            @Override
            public Union9<A, B, C, D, E, F, G, H, I> sixth(F f) {
                return new Union9Sixth<>(f);
            }

            @Override
            public Union9<A, B, C, D, E, F, G, H, I> seventh(G g) {
                return new Union9Seventh<>(g);
            }

            @Override
            public Union9<A, B, C, D, E, F, G, H, I> eighth(H h) {
                return new Union9Eighth<>(h);
            }

            @Override
            public Union9<A, B, C, D, E, F, G, H, I> ninth(I i) {
                return new Union9Ninth<>(i);
            }
        };
    }
}
