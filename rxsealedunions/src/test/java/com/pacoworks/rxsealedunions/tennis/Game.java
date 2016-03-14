
package com.pacoworks.rxsealedunions.tennis;

import com.pacoworks.rxsealedunions.Union2;
import com.pacoworks.rxsealedunions.generic.GenericUnions;

public abstract class Game extends Player {
    private Game() {
    }

    private static final Union2.Factory<PlayerOne, PlayerTwo> FACTORY = GenericUnions
            .doubletFactory();

    private static final Union2<PlayerOne, PlayerTwo> GAME_PLAYER_TWO = FACTORY
            .second(new PlayerTwo());

    private static final Union2<PlayerOne, PlayerTwo> GAME_PLAYER_ONE = FACTORY
            .first(new PlayerOne());

    public static Game one() {
        return new Game() {
            @Override
            public Union2<PlayerOne, PlayerTwo> getPlayer() {
                return GAME_PLAYER_ONE;
            }
        };
    }

    public static Game two() {
        return new Game() {
            @Override
            public Union2<PlayerOne, PlayerTwo> getPlayer() {
                return GAME_PLAYER_TWO;
            }
        };
    }
}
