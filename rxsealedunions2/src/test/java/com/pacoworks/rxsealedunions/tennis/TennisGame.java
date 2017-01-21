/*
 * Copyright (c) pakoito 2016
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

package com.pacoworks.rxsealedunions.tennis;

import io.reactivex.functions.Function;

public class TennisGame {
    public static Score scorePoint(Score score, Player player) {
        return score.getScore().join(scorePoints(player), scoreAdvantage(player),
                scoreDeuce(player), scoreGame(player));
    }

    private static Function<Points, Score> scorePoints(final Player player) {
        return new Function<Points, Score>() {
            @Override
            public Score apply(final Points points) {
                if (isPlayerForty(points.getKey())) {
                    return player.getPlayer().join(new Function<PlayerOne, Score>() {
                        @Override
                        public Score apply(PlayerOne one) {
                            return Score.game(Game.one());
                        }
                    }, new Function<PlayerTwo, Score>() {
                        @Override
                        public Score apply(PlayerTwo two) {
                            return isPlayerThirty(points.getValue()) ? Score.deuce()
                                    : scorePlayer(points.getKey(), score(points.getValue()));
                        }
                    });
                } else if (isPlayerForty(points.getValue())) {
                    return player.getPlayer().join(new Function<PlayerOne, Score>() {
                        @Override
                        public Score apply(PlayerOne one) {
                            return isPlayerThirty(points.getKey()) ? Score.deuce()
                                    : scorePlayer(score(points.getKey()), points.getValue());
                        }
                    }, new Function<PlayerTwo, Score>() {
                        @Override
                        public Score apply(PlayerTwo two) {
                            return Score.game(Game.two());
                        }
                    });
                } else {
                    return player.getPlayer().join(new Function<PlayerOne, Score>() {
                        @Override
                        public Score apply(PlayerOne one) {
                            return scorePlayer(score(points.getKey()), points.getValue());
                        }
                    }, new Function<PlayerTwo, Score>() {
                        @Override
                        public Score apply(PlayerTwo two) {
                            return scorePlayer(points.getKey(), score(points.getValue()));
                        }
                    });
                }
            }
        };
    }

    private static Score scorePlayer(PlayerPoints playerOnePoints, PlayerPoints playerTwoPoints) {
        return Score.points(playerOnePoints, playerTwoPoints);
    }

    private static boolean isPlayerThirty(PlayerPoints playerPoints) {
        return playerPoints.getPlayerPoints().join(new Function<Zero, Boolean>() {
            @Override
            public Boolean apply(Zero zero) {
                return false;
            }
        }, new Function<Fifteen, Boolean>() {
            @Override
            public Boolean apply(Fifteen fifteen) {
                return false;
            }
        }, new Function<Thirty, Boolean>() {
            @Override
            public Boolean apply(Thirty thirty) {
                return true;
            }
        }, new Function<Forty, Boolean>() {
            @Override
            public Boolean apply(Forty forty) {
                return false;
            }
        });
    }

    private static boolean isPlayerForty(PlayerPoints playerPoints) {
        return playerPoints.getPlayerPoints().join(new Function<Zero, Boolean>() {
            @Override
            public Boolean apply(Zero zero) {
                return false;
            }
        }, new Function<Fifteen, Boolean>() {
            @Override
            public Boolean apply(Fifteen fifteen) {
                return false;
            }
        }, new Function<Thirty, Boolean>() {
            @Override
            public Boolean apply(Thirty thirty) {
                return false;
            }
        }, new Function<Forty, Boolean>() {
            @Override
            public Boolean apply(Forty forty) {
                return true;
            }
        });
    }

    private static PlayerPoints score(PlayerPoints playerPoints) {
        return playerPoints.getPlayerPoints().join(new Function<Zero, PlayerPoints>() {
            @Override
            public PlayerPoints apply(Zero zero) {
                return PlayerPoints.fifteen();
            }
        }, new Function<Fifteen, PlayerPoints>() {
            @Override
            public PlayerPoints apply(Fifteen fifteen) {
                return PlayerPoints.thirty();
            }
        }, new Function<Thirty, PlayerPoints>() {
            @Override
            public PlayerPoints apply(Thirty thirty) {
                return PlayerPoints.forty();
            }
        }, new Function<Forty, PlayerPoints>() {
            @Override
            public PlayerPoints apply(Forty forty) {
                throw new IllegalStateException();
            }
        });
    }

    private static Function<Advantage, Score> scoreAdvantage(final Player player) {
        return new Function<Advantage, Score>() {
            @Override
            public Score apply(Advantage advantage) {
                return advantage.getPlayer().join(new Function<PlayerOne, Score>() {
                    @Override
                    public Score apply(PlayerOne playerOne) {
                        return player.getPlayer().join(new Function<PlayerOne, Score>() {
                            @Override
                            public Score apply(PlayerOne one) {
                                return Score.game(Game.one());
                            }
                        }, new Function<PlayerTwo, Score>() {
                            @Override
                            public Score apply(PlayerTwo two) {
                                return Score.deuce();
                            }
                        });
                    }
                }, new Function<PlayerTwo, Score>() {
                    @Override
                    public Score apply(PlayerTwo playerTwo) {
                        return player.getPlayer().join(new Function<PlayerOne, Score>() {
                            @Override
                            public Score apply(PlayerOne one) {
                                return Score.deuce();
                            }
                        }, new Function<PlayerTwo, Score>() {
                            @Override
                            public Score apply(PlayerTwo two) {
                                return Score.game(Game.two());
                            }
                        });
                    }
                });
            }
        };
    }

    private static Function<Deuce, Score> scoreDeuce(final Player player) {
        return new Function<Deuce, Score>() {
            @Override
            public Score apply(Deuce deuce) {
                return player.getPlayer().join(new Function<PlayerOne, Score>() {
                    @Override
                    public Score apply(PlayerOne first) {
                        return Score.advantage(Advantage.one());
                    }
                }, new Function<PlayerTwo, Score>() {
                    @Override
                    public Score apply(PlayerTwo second) {
                        return Score.advantage(Advantage.two());
                    }
                });
            }
        };
    }

    private static Function<Game, Score> scoreGame(final Player player) {
        return new Function<Game, Score>() {
            @Override
            public Score apply(Game game) {
                return player.getPlayer().join(new Function<PlayerOne, Score>() {
                    @Override
                    public Score apply(PlayerOne one) {
                        return scorePlayer(PlayerPoints.fifteen(), PlayerPoints.zero());
                    }
                }, new Function<PlayerTwo, Score>() {
                    @Override
                    public Score apply(PlayerTwo second) {
                        return scorePlayer(PlayerPoints.zero(), PlayerPoints.fifteen());
                    }
                });
            }
        };
    }
}
