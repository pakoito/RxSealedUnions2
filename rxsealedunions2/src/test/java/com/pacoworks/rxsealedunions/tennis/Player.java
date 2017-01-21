
package com.pacoworks.rxsealedunions.tennis;

import com.pacoworks.rxsealedunions.Union2;
import com.pacoworks.rxsealedunions.generic.UnionFactories;

import io.reactivex.functions.Function;

public abstract class Player {
    private static final Union2.Factory<PlayerOne, PlayerTwo> FACTORY = UnionFactories
            .<PlayerOne, PlayerTwo> doubletFactory();

    private static final Union2<PlayerOne, PlayerTwo> PLAYER_TWO = FACTORY.second(new PlayerTwo());

    private static final Union2<PlayerOne, PlayerTwo> PLAYER_ONE = FACTORY.first(new PlayerOne());

    Player() {
    }

    public static Player one() {
        return new Player() {
            @Override
            public Union2<PlayerOne, PlayerTwo> getPlayer() {
                return PLAYER_ONE;
            }
        };
    }

    public static Player two() {
        return new Player() {
            @Override
            public Union2<PlayerOne, PlayerTwo> getPlayer() {
                return PLAYER_TWO;
            }
        };
    }

    public static String getString(Player player) {
        return player.getPlayer().join(new Function<PlayerOne, String>() {
            @Override
            public String apply(PlayerOne playerOne) {
                return "Player 1";
            }
        }, new Function<PlayerTwo, String>() {
            @Override
            public String apply(PlayerTwo playerTwo) {
                return "Player 2";
            }
        });
    }

    public abstract Union2<PlayerOne, PlayerTwo> getPlayer();

    @Override
    public String toString() {
        return getString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Player))
            return false;
        final Player other = (Player)o;
        final Object this$value = this.getPlayer();
        final Object other$value = other.getPlayer();
        return this$value == null ? other$value == null : this$value.equals(other$value);
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $value = this.getPlayer();
        result = result * PRIME + ($value == null ? 0 : $value.hashCode());
        return result;
    }
}
