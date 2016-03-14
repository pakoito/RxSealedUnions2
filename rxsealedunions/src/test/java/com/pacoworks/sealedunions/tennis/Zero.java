package com.pacoworks.sealedunions.tennis;

public class Zero {
    public boolean equals(Object o) {
        return o == this || o instanceof Zero;
    }
}
