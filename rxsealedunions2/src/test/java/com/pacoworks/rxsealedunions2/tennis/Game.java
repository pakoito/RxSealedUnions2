
package com.pacoworks.rxsealedunions2.tennis;

import com.pacoworks.rxsealedunions2.Union2;
import com.pacoworks.rxsealedunions2.generic.UnionFactories;

public abstract class Game extends Player {
    private Game() {
    }

    private static final Union2.Factory<PlayerOne, PlayerTwo> FACTORY = UnionFactories
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
