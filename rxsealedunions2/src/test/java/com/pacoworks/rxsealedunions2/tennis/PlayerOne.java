package com.pacoworks.rxsealedunions2.tennis;

public class PlayerOne {
    public boolean equals(Object o) {
        return o == this || o instanceof PlayerOne;
    }
}
