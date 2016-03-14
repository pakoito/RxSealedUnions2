package com.pacoworks.sealedunions.tennis;

public class PlayerTwo {
    public boolean equals(Object o) {
        return o == this || o instanceof PlayerTwo;
    }
}
