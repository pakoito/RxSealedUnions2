package com.pacoworks.sealedunions.tennis;

public class Deuce {
    public boolean equals(Object o) {
        return o == this || o instanceof Deuce;
    }
}
