package com.pacoworks.sealedunions.tennis;

public class PlayerOne {
    public boolean equals(Object o) {
        return o == this || o instanceof PlayerOne;
    }
}
