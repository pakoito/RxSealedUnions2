package com.pacoworks.rxsealedunions2.tennis;

public class PlayerTwo {
    public boolean equals(Object o) {
        return o == this || o instanceof PlayerTwo;
    }
}
