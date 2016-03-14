
package com.pacoworks.rxsealedunions.tennis;

import rx.functions.Func1;

import com.pacoworks.rxsealedunions.Union4;
import com.pacoworks.rxsealedunions.generic.GenericUnions;

public abstract class Score {
    private Score() {
    }

    public static Score points(final PlayerPoints playerOnePoints,
            final PlayerPoints playerTwoPoints) {
        return new Score() {
            @Override
            public Union4<Points, Advantage, Deuce, Game> getScore() {
                return GenericUnions.<Points, Advantage, Deuce, Game> quartetFactory()
                        .first(new Points(playerOnePoints, playerTwoPoints));
            }
        };
    }

    public static Score advantage(final Advantage advantage) {
        return new Score() {
            @Override
            public Union4<Points, Advantage, Deuce, Game> getScore() {
                return GenericUnions.<Points, Advantage, Deuce, Game> quartetFactory()
                        .second(advantage);
            }
        };
    }

    public static Score deuce() {
        return new Score() {
            @Override
            public Union4<Points, Advantage, Deuce, Game> getScore() {
                return GenericUnions.<Points, Advantage, Deuce, Game> quartetFactory()
                        .third(new Deuce());
            }
        };
    }

    public static Score game(final Game game) {
        return new Score() {
            @Override
            public Union4<Points, Advantage, Deuce, Game> getScore() {
                return GenericUnions.<Points, Advantage, Deuce, Game> quartetFactory().fourth(game);
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

    private static Func1<Points, String> mapScoreString() {
        return new Func1<Points, String>() {
            @Override
            public String call(Points points) {
                return points.toString();
            }
        };
    }

    private static Func1<Advantage, String> mapAdvantageString() {
        return new Func1<Advantage, String>() {
            @Override
            public String call(Advantage advantage) {
                return advantage.getPlayer().join(new Func1<PlayerOne, String>() {
                    @Override
                    public String call(PlayerOne playerOne) {
                        return "Adv P1";
                    }
                }, new Func1<PlayerTwo, String>() {
                    @Override
                    public String call(PlayerTwo playerTwo) {
                        return "Adv P2";
                    }
                });
            }
        };
    }

    private static Func1<Deuce, String> mapDeuceString() {
        return new Func1<Deuce, String>() {
            @Override
            public String call(Deuce deuce) {
                return "Deuce";
            }
        };
    }

    private static Func1<Game, String> mapGameString() {
        return new Func1<Game, String>() {
            @Override
            public String call(Game game) {
                return game.getPlayer().join(new Func1<PlayerOne, String>() {
                    @Override
                    public String call(PlayerOne playerOne) {
                        return "Win P1";
                    }
                }, new Func1<PlayerTwo, String>() {
                    @Override
                    public String call(PlayerTwo playerTwo) {
                        return "Win P2";
                    }
                });
            }
        };
    }

    public abstract Union4<Points, Advantage, Deuce, Game> getScore();
}
