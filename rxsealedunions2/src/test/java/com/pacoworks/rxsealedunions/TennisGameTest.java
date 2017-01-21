/*
 * Copyright (c) pakoito 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pacoworks.rxsealedunions;

import com.pacoworks.rxsealedunions.tennis.*;
import org.junit.Assert;
import org.junit.Test;

import static com.pacoworks.rxsealedunions.tennis.TennisGame.scorePoint;

public class TennisGameTest {
    @Test
    public void testTennis() throws Exception {
        Score start = Score.points(PlayerPoints.zero(), PlayerPoints.zero());
        Score fifteenZero = updateAndPrintScore(start, Player.one());
        Score thirtyZero = updateAndPrintScore(fifteenZero, Player.one());
        Score fortyZero = updateAndPrintScore(thirtyZero, Player.one());
        Assert.assertEquals(Score.points(PlayerPoints.forty(), PlayerPoints.zero()).getScore(),
                fortyZero.getScore());
        Score fortyThirty = updateAndPrintScore(scorePoint(fortyZero, Player.two()), Player.two());
        Assert.assertEquals(Score.points(PlayerPoints.forty(), PlayerPoints.thirty()).getScore(),
                fortyThirty.getScore());
        Score deuce1 = updateAndPrintScore(fortyThirty, Player.two());
        Assert.assertEquals(Score.deuce().getScore(), deuce1.getScore());
        Score advantageP1 = updateAndPrintScore(deuce1, Player.one());
        Assert.assertEquals(Score.advantage(Advantage.one()).getScore(), advantageP1.getScore());
        Score gameP1 = updateAndPrintScore(advantageP1, Player.one());
        Assert.assertEquals(Score.game(Game.one()).getScore(), gameP1.getScore());
        Score zeroFifteen = updateAndPrintScore(gameP1, Player.two());
        Assert.assertEquals(Score.points(PlayerPoints.zero(), PlayerPoints.fifteen()).getScore(),
                zeroFifteen.getScore());
        Score deuce2 = updateAndPrintScore(advantageP1, Player.two());
        Assert.assertEquals(Score.deuce().getScore(), deuce2.getScore());
    }

    private Score updateAndPrintScore(Score oldScore, Player player) {
        Score newScore = scorePoint(oldScore, player);
        System.out.println(Score.getString(oldScore) + " + " + Player.getString(player) + " -> "
                + Score.getString(newScore));
        return newScore;
    }
}
