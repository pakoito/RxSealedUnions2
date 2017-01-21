
package com.pacoworks.rxsealedunions2.tennis;

import com.pacoworks.rxsealedunions2.Union2;
import com.pacoworks.rxsealedunions2.generic.UnionFactories;

public abstract class Advantage extends Player {
    private static final Union2.Factory<PlayerOne, PlayerTwo> FACTORY = UnionFactories
            .doubletFactory();

    private static final Union2<PlayerOne, PlayerTwo> ADVANTAGE_SECOND = FACTORY
            .second(new PlayerTwo());

    private static final Union2<PlayerOne, PlayerTwo> ADVANTAGE_FIRST = FACTORY
            .first(new PlayerOne());

    private Advantage() {
    }

    public static Advantage one() {
        return new Advantage() {
            @Override
            public Union2<PlayerOne, PlayerTwo> getPlayer() {
                return ADVANTAGE_FIRST;
            }
        };
    }

    public static Advantage two() {
        return new Advantage() {
            @Override
            public Union2<PlayerOne, PlayerTwo> getPlayer() {
                return ADVANTAGE_SECOND;
            }
        };
    }
}
