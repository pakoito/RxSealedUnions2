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
import org.junit.Assert;
import org.junit.Test;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

import java.util.Arrays;

public class GenericUnionsTest {
    private static final String VALID = "a";

    private static final String INVALID = "";

    private static final java.util.List<String> VALID_ARRAY = Arrays.asList(VALID, VALID, VALID,
            VALID, VALID, VALID, VALID, VALID, VALID);

    private static final Func1<Integer, String> VALUE = new Func1<Integer, String>() {
        @Override
        public String call(Integer integer) {
            return VALID;
        }
    };

    private static final Func1<Integer, String> EMPTY = new Func1<Integer, String>() {
        @Override
        public String call(Integer integer) {
            return INVALID;
        }
    };

    private static final Action1<Integer> SUCCESS = new Action1<Integer>() {
        @Override
        public void call(Integer integer) {
        }
    };

    private static final Action1<Integer> ERROR = new Action1<Integer>() {
        @Override
        public void call(Integer integer) {
            throw new IllegalStateException();
        }
    };

    @Test
    public void testJoin() throws Exception {
        Union1.Factory<Integer> singletFactory = GenericUnions.singletFactory();
        Union2.Factory<Integer, Integer> doubletFactory = GenericUnions.doubletFactory();
        Union3.Factory<Integer, Integer, Integer> tripletFactory = GenericUnions.tripletFactory();
        Union4.Factory<Integer, Integer, Integer, Integer> quartetFactory = GenericUnions
                .quartetFactory();
        Union5.Factory<Integer, Integer, Integer, Integer, Integer> quintetFactory = GenericUnions
                .quintetFactory();
        Union6.Factory<Integer, Integer, Integer, Integer, Integer, Integer> sextetFactory = GenericUnions
                .sextetFactory();
        Union7.Factory<Integer, Integer, Integer, Integer, Integer, Integer, Integer> septetFactory = GenericUnions
                .septetFactory();
        Union8.Factory<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> octetFactory = GenericUnions
                .octetFactory();
        Union9.Factory<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> nonetFactory = GenericUnions
                .nonetFactory();
        String join1 = singletFactory.first(0).join(VALUE, new Func0<String>() {
            @Override
            public String call() {
                return INVALID;
            }
        });
        String join2 = doubletFactory.first(0).join(VALUE, EMPTY);
        String join3 = tripletFactory.first(0).join(VALUE, EMPTY, EMPTY);
        String join4 = quartetFactory.first(0).join(VALUE, EMPTY, EMPTY, EMPTY);
        String join5 = quintetFactory.first(0).join(VALUE, EMPTY, EMPTY, EMPTY, EMPTY);
        String join6 = sextetFactory.first(0).join(VALUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY);
        String join7 = septetFactory.first(0).join(VALUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY);
        String join8 = octetFactory.first(0).join(VALUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY);
        String join9 = nonetFactory.first(0).join(VALUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY);
        Assert.assertEquals(VALID_ARRAY,
                Arrays.asList(join1, join2, join3, join4, join5, join6, join7, join8, join9));
        join1 = singletFactory.none().join(EMPTY, new Func0<String>() {
            @Override
            public String call() {
                return VALID;
            }
        });
        join2 = doubletFactory.second(0).join(EMPTY, VALUE);
        join3 = tripletFactory.second(0).join(EMPTY, VALUE, EMPTY);
        join4 = quartetFactory.second(0).join(EMPTY, VALUE, EMPTY, EMPTY);
        join5 = quintetFactory.second(0).join(EMPTY, VALUE, EMPTY, EMPTY, EMPTY);
        join6 = sextetFactory.second(0).join(EMPTY, VALUE, EMPTY, EMPTY, EMPTY, EMPTY);
        join7 = septetFactory.second(0).join(EMPTY, VALUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY);
        join8 = octetFactory.second(0).join(EMPTY, VALUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY);
        join9 = nonetFactory.second(0).join(EMPTY, VALUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY);
        Assert.assertEquals(VALID_ARRAY,
                Arrays.asList(join1, join2, join3, join4, join5, join6, join7, join8, join9));
        join3 = tripletFactory.third(0).join(EMPTY, EMPTY, VALUE);
        join4 = quartetFactory.third(0).join(EMPTY, EMPTY, VALUE, EMPTY);
        join5 = quintetFactory.third(0).join(EMPTY, EMPTY, VALUE, EMPTY, EMPTY);
        join6 = sextetFactory.third(0).join(EMPTY, EMPTY, VALUE, EMPTY, EMPTY, EMPTY);
        join7 = septetFactory.third(0).join(EMPTY, EMPTY, VALUE, EMPTY, EMPTY, EMPTY, EMPTY);
        join8 = octetFactory.third(0).join(EMPTY, EMPTY, VALUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY);
        join9 = nonetFactory.third(0).join(EMPTY, EMPTY, VALUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY);
        Assert.assertEquals(VALID_ARRAY,
                Arrays.asList(join1, join2, join3, join4, join5, join6, join7, join8, join9));
        join4 = quartetFactory.fourth(0).join(EMPTY, EMPTY, EMPTY, VALUE);
        join5 = quintetFactory.fourth(0).join(EMPTY, EMPTY, EMPTY, VALUE, EMPTY);
        join6 = sextetFactory.fourth(0).join(EMPTY, EMPTY, EMPTY, VALUE, EMPTY, EMPTY);
        join7 = septetFactory.fourth(0).join(EMPTY, EMPTY, EMPTY, VALUE, EMPTY, EMPTY, EMPTY);
        join8 = octetFactory.fourth(0).join(EMPTY, EMPTY, EMPTY, VALUE, EMPTY, EMPTY, EMPTY, EMPTY);
        join9 = nonetFactory.fourth(0).join(EMPTY, EMPTY, EMPTY, VALUE, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY);
        Assert.assertEquals(VALID_ARRAY,
                Arrays.asList(join1, join2, join3, join4, join5, join6, join7, join8, join9));
        join5 = quintetFactory.fifth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, VALUE);
        join6 = sextetFactory.fifth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, VALUE, EMPTY);
        join7 = septetFactory.fifth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, VALUE, EMPTY, EMPTY);
        join8 = octetFactory.fifth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, VALUE, EMPTY, EMPTY, EMPTY);
        join9 = nonetFactory.fifth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, VALUE, EMPTY, EMPTY, EMPTY,
                EMPTY);
        Assert.assertEquals(VALID_ARRAY,
                Arrays.asList(join1, join2, join3, join4, join5, join6, join7, join8, join9));
        join6 = sextetFactory.sixth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, VALUE);
        join7 = septetFactory.sixth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, VALUE, EMPTY);
        join8 = octetFactory.sixth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, VALUE, EMPTY, EMPTY);
        join9 = nonetFactory.sixth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, VALUE, EMPTY, EMPTY,
                EMPTY);
        Assert.assertEquals(VALID_ARRAY,
                Arrays.asList(join1, join2, join3, join4, join5, join6, join7, join8, join9));
        join7 = septetFactory.seventh(0).join(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, VALUE);
        join8 = octetFactory.seventh(0).join(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, VALUE,
                EMPTY);
        join9 = nonetFactory.seventh(0).join(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, VALUE, EMPTY,
                EMPTY);
        Assert.assertEquals(VALID_ARRAY,
                Arrays.asList(join1, join2, join3, join4, join5, join6, join7, join8, join9));
        join8 = octetFactory.eighth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, VALUE);
        join9 = nonetFactory.eighth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, VALUE,
                EMPTY);
        Assert.assertEquals(VALID_ARRAY,
                Arrays.asList(join1, join2, join3, join4, join5, join6, join7, join8, join9));
        join9 = nonetFactory.ninth(0).join(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                VALUE);
        Assert.assertEquals(VALID_ARRAY,
                Arrays.asList(join1, join2, join3, join4, join5, join6, join7, join8, join9));
    }

    @Test
    public void testContinued() throws Exception {
        Union1.Factory<Integer> singletFactory = GenericUnions.singletFactory();
        Union2.Factory<Integer, Integer> doubletFactory = GenericUnions.doubletFactory();
        Union3.Factory<Integer, Integer, Integer> tripletFactory = GenericUnions.tripletFactory();
        Union4.Factory<Integer, Integer, Integer, Integer> quartetFactory = GenericUnions
                .quartetFactory();
        Union5.Factory<Integer, Integer, Integer, Integer, Integer> quintetFactory = GenericUnions
                .quintetFactory();
        Union6.Factory<Integer, Integer, Integer, Integer, Integer, Integer> sextetFactory = GenericUnions
                .sextetFactory();
        Union7.Factory<Integer, Integer, Integer, Integer, Integer, Integer, Integer> septetFactory = GenericUnions
                .septetFactory();
        Union8.Factory<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> octetFactory = GenericUnions
                .octetFactory();
        Union9.Factory<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> nonetFactory = GenericUnions
                .nonetFactory();
        singletFactory.first(0).continued(SUCCESS, new Action0() {
            @Override
            public void call() {
                throw new IllegalStateException();
            }
        });
        doubletFactory.first(0).continued(SUCCESS, ERROR);
        tripletFactory.first(0).continued(SUCCESS, ERROR, ERROR);
        quartetFactory.first(0).continued(SUCCESS, ERROR, ERROR, ERROR);
        quintetFactory.first(0).continued(SUCCESS, ERROR, ERROR, ERROR, ERROR);
        sextetFactory.first(0).continued(SUCCESS, ERROR, ERROR, ERROR, ERROR, ERROR);
        septetFactory.first(0).continued(SUCCESS, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR);
        octetFactory.first(0).continued(SUCCESS, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR);
        nonetFactory.first(0).continued(SUCCESS, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
                ERROR);
        /* */
        singletFactory.none().continued(ERROR, new Action0() {
            @Override
            public void call() {
            }
        });
        doubletFactory.second(0).continued(ERROR, SUCCESS);
        tripletFactory.second(0).continued(ERROR, SUCCESS, ERROR);
        quartetFactory.second(0).continued(ERROR, SUCCESS, ERROR, ERROR);
        quintetFactory.second(0).continued(ERROR, SUCCESS, ERROR, ERROR, ERROR);
        sextetFactory.second(0).continued(ERROR, SUCCESS, ERROR, ERROR, ERROR, ERROR);
        septetFactory.second(0).continued(ERROR, SUCCESS, ERROR, ERROR, ERROR, ERROR, ERROR);
        octetFactory.second(0).continued(ERROR, SUCCESS, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR);
        nonetFactory.second(0).continued(ERROR, SUCCESS, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
                ERROR);
        /* */
        tripletFactory.third(0).continued(ERROR, ERROR, SUCCESS);
        quartetFactory.third(0).continued(ERROR, ERROR, SUCCESS, ERROR);
        quintetFactory.third(0).continued(ERROR, ERROR, SUCCESS, ERROR, ERROR);
        sextetFactory.third(0).continued(ERROR, ERROR, SUCCESS, ERROR, ERROR, ERROR);
        septetFactory.third(0).continued(ERROR, ERROR, SUCCESS, ERROR, ERROR, ERROR, ERROR);
        octetFactory.third(0).continued(ERROR, ERROR, SUCCESS, ERROR, ERROR, ERROR, ERROR, ERROR);
        nonetFactory.third(0).continued(ERROR, ERROR, SUCCESS, ERROR, ERROR, ERROR, ERROR, ERROR,
                ERROR);
        /* */
        quartetFactory.fourth(0).continued(ERROR, ERROR, ERROR, SUCCESS);
        quintetFactory.fourth(0).continued(ERROR, ERROR, ERROR, SUCCESS, ERROR);
        sextetFactory.fourth(0).continued(ERROR, ERROR, ERROR, SUCCESS, ERROR, ERROR);
        septetFactory.fourth(0).continued(ERROR, ERROR, ERROR, SUCCESS, ERROR, ERROR, ERROR);
        octetFactory.fourth(0).continued(ERROR, ERROR, ERROR, SUCCESS, ERROR, ERROR, ERROR, ERROR);
        nonetFactory.fourth(0).continued(ERROR, ERROR, ERROR, SUCCESS, ERROR, ERROR, ERROR, ERROR,
                ERROR);
        /* */
        quintetFactory.fifth(0).continued(ERROR, ERROR, ERROR, ERROR, SUCCESS);
        sextetFactory.fifth(0).continued(ERROR, ERROR, ERROR, ERROR, SUCCESS, ERROR);
        septetFactory.fifth(0).continued(ERROR, ERROR, ERROR, ERROR, SUCCESS, ERROR, ERROR);
        octetFactory.fifth(0).continued(ERROR, ERROR, ERROR, ERROR, SUCCESS, ERROR, ERROR, ERROR);
        nonetFactory.fifth(0).continued(ERROR, ERROR, ERROR, ERROR, SUCCESS, ERROR, ERROR, ERROR,
                ERROR);
        /* */
        sextetFactory.sixth(0).continued(ERROR, ERROR, ERROR, ERROR, ERROR, SUCCESS);
        septetFactory.sixth(0).continued(ERROR, ERROR, ERROR, ERROR, ERROR, SUCCESS, ERROR);
        octetFactory.sixth(0).continued(ERROR, ERROR, ERROR, ERROR, ERROR, SUCCESS, ERROR, ERROR);
        nonetFactory.sixth(0).continued(ERROR, ERROR, ERROR, ERROR, ERROR, SUCCESS, ERROR, ERROR,
                ERROR);
        /* */
        septetFactory.seventh(0).continued(ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, SUCCESS);
        octetFactory.seventh(0).continued(ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, SUCCESS, ERROR);
        nonetFactory.seventh(0).continued(ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, SUCCESS, ERROR,
                ERROR);
        /* */
        octetFactory.eighth(0).continued(ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, SUCCESS);
        nonetFactory.eighth(0).continued(ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, SUCCESS,
                ERROR);
        /* */
        nonetFactory.ninth(0).continued(ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,
                SUCCESS);
    }
}
