
package com.pacoworks.sealedunions.tennis;

import rx.functions.Func1;

import com.pacoworks.rxsealedunions.Union4;
import com.pacoworks.rxsealedunions.generic.GenericUnions;

public abstract class PlayerPoints {
    private static final Union4.Factory<Zero, Fifteen, Thirty, Forty> FACTORY = GenericUnions
            .quartetFactory();

    private static final Union4<Zero, Fifteen, Thirty, Forty> FORTY = FACTORY.fourth(new Forty());

    private static final Union4<Zero, Fifteen, Thirty, Forty> THIRTY = FACTORY.third(new Thirty());

    private static final Union4<Zero, Fifteen, Thirty, Forty> FIFTEEN = FACTORY
            .second(new Fifteen());

    private static final Union4<Zero, Fifteen, Thirty, Forty> ZERO = FACTORY.first(new Zero());

    private PlayerPoints() {
    }

    public static PlayerPoints zero() {
        return createPlayerPointsFromGeneric(ZERO);
    }

    public static PlayerPoints fifteen() {
        return createPlayerPointsFromGeneric(FIFTEEN);
    }

    public static PlayerPoints thirty() {
        return createPlayerPointsFromGeneric(THIRTY);
    }

    public static PlayerPoints forty() {
        return createPlayerPointsFromGeneric(FORTY);
    }

    private static PlayerPoints createPlayerPointsFromGeneric(
            final Union4<Zero, Fifteen, Thirty, Forty> union4) {
        return new PlayerPoints() {
            @Override
            public Union4<Zero, Fifteen, Thirty, Forty> getPlayerPoints() {
                return union4;
            }
        };
    }

    public static String getString(PlayerPoints playerPoints) {
        return playerPoints.getPlayerPoints().join(new Func1<Zero, String>() {
            @Override
            public String call(Zero zero) {
                return "0";
            }
        }, new Func1<Fifteen, String>() {
            @Override
            public String call(Fifteen fifteen) {
                return "15";
            }
        }, new Func1<Thirty, String>() {
            @Override
            public String call(Thirty thirty) {
                return "30";
            }
        }, new Func1<Forty, String>() {
            @Override
            public String call(Forty forty) {
                return "40";
            }
        });
    }

    public abstract Union4<Zero, Fifteen, Thirty, Forty> getPlayerPoints();

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PlayerPoints))
            return false;
        final PlayerPoints other = (PlayerPoints)o;
        final Object this$value = this.getPlayerPoints();
        final Object other$value = other.getPlayerPoints();
        return this$value == null ? other$value == null : this$value.equals(other$value);
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $value = this.getPlayerPoints();
        result = result * PRIME + ($value == null ? 0 : $value.hashCode());
        return result;
    }
}
