package com.pacoworks.sealedunions.tennis;

import javafx.util.Pair;

public class Points extends Pair<PlayerPoints, PlayerPoints> {
    Points(PlayerPoints key, PlayerPoints value) {
        super(key, value);
    }

    @Override
    public String toString() {
        return "Points{" + PlayerPoints.getString(getKey()) + ", "
                + PlayerPoints.getString(getValue()) + "}";
    }
}
