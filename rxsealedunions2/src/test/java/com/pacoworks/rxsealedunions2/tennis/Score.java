
package com.pacoworks.rxsealedunions2.tennis;

import com.pacoworks.rxsealedunions2.Union4;
import com.pacoworks.rxsealedunions2.generic.UnionFactories;

import io.reactivex.functions.Function;

public abstract class Score {
    private Score() {
    }

    public static Score points(final PlayerPoints playerOnePoints,
            final PlayerPoints playerTwoPoints) {
        return new Score() {
            @Override
            public Union4<Points, Advantage, Deuce, Game> getScore() {
                return UnionFactories.<Points, Advantage, Deuce, Game> quartetFactory()
                        .first(new Points(playerOnePoints, playerTwoPoints));
            }
        };
    }

    public static Score advantage(final Advantage advantage) {
        return new Score() {
            @Override
            public Union4<Points, Advantage, Deuce, Game> getScore() {
                return UnionFactories.<Points, Advantage, Deuce, Game> quartetFactory()
                        .second(advantage);
            }
        };
    }

    public static Score deuce() {
        return new Score() {
            @Override
            public Union4<Points, Advantage, Deuce, Game> getScore() {
                return UnionFactories.<Points, Advantage, Deuce, Game> quartetFactory()
                        .third(new Deuce());
            }
        };
    }

    public static Score game(final Game game) {
        return new Score() {
            @Override
            public Union4<Points, Advantage, Deuce, Game> getScore() {
                return UnionFactories.<Points, Advantage, Deuce, Game> quartetFactory().fourth(game);
            }
        };
    }

    public static String getString(Score score) {
        return score.getScore().join(mapScoreString(), mapAdvantageString(), mapDeuceString(),
                mapGameString());
    }

    @Override
    public String toString() {
        return getString(this);
    }

    private static Function<Points, String> mapScoreString() {
        return new Function<Points, String>() {
            @Override
            public String apply(Points points) {
                return points.toString();
            }
        };
    }

    private static Function<Advantage, String> mapAdvantageString() {
        return new Function<Advantage, String>() {
            @Override
            public String apply(Advantage advantage) {
                return advantage.getPlayer().join(new Function<PlayerOne, String>() {
                    @Override
                    public String apply(PlayerOne playerOne) {
                        return "Adv P1";
                    }
                }, new Function<PlayerTwo, String>() {
                    @Override
                    public String apply(PlayerTwo playerTwo) {
                        return "Adv P2";
                    }
                });
            }
        };
    }

    private static Function<Deuce, String> mapDeuceString() {
        return new Function<Deuce, String>() {
            @Override
            public String apply(Deuce deuce) {
                return "Deuce";
            }
        };
    }

    private static Function<Game, String> mapGameString() {
        return new Function<Game, String>() {
            @Override
            public String apply(Game game) {
                return game.getPlayer().join(new Function<PlayerOne, String>() {
                    @Override
                    public String apply(PlayerOne playerOne) {
                        return "Win P1";
                    }
                }, new Function<PlayerTwo, String>() {
                    @Override
                    public String apply(PlayerTwo playerTwo) {
                        return "Win P2";
                    }
                });
            }
        };
    }

    public abstract Union4<Points, Advantage, Deuce, Game> getScore();
}
