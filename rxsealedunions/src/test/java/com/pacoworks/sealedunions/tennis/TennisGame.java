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

package com.pacoworks.sealedunions.tennis;

import rx.functions.Func1;

public class TennisGame {
    public static Score scorePoint(Score score, Player player) {
        return score.getScore().join(scorePoints(player), scoreAdvantage(player),
                scoreDeuce(player), scoreGame(player));
    }

    private static Func1<Points, Score> scorePoints(final Player player) {
        return new Func1<Points, Score>() {
            @Override
            public Score call(final Points points) {
                if (isPlayerForty(points.getKey())) {
                    return player.getPlayer().join(new Func1<PlayerOne, Score>() {
                        @Override
                        public Score call(PlayerOne one) {
                            return Score.game(Game.one());
                        }
                    }, new Func1<PlayerTwo, Score>() {
                        @Override
                        public Score call(PlayerTwo two) {
                            return isPlayerThirty(points.getValue()) ? Score.deuce()
                                    : scorePlayer(points.getKey(), score(points.getValue()));
                        }
                    });
                } else if (isPlayerForty(points.getValue())) {
                    return player.getPlayer().join(new Func1<PlayerOne, Score>() {
                        @Override
                        public Score call(PlayerOne one) {
                            return isPlayerThirty(points.getKey()) ? Score.deuce()
                                    : scorePlayer(score(points.getKey()), points.getValue());
                        }
                    }, new Func1<PlayerTwo, Score>() {
                        @Override
                        public Score call(PlayerTwo two) {
                            return Score.game(Game.two());
                        }
                    });
                } else {
                    return player.getPlayer().join(new Func1<PlayerOne, Score>() {
                        @Override
                        public Score call(PlayerOne one) {
                            return scorePlayer(score(points.getKey()), points.getValue());
                        }
                    }, new Func1<PlayerTwo, Score>() {
                        @Override
                        public Score call(PlayerTwo two) {
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
        return playerPoints.getPlayerPoints().join(new Func1<Zero, Boolean>() {
            @Override
            public Boolean call(Zero zero) {
                return false;
            }
        }, new Func1<Fifteen, Boolean>() {
            @Override
            public Boolean call(Fifteen fifteen) {
                return false;
            }
        }, new Func1<Thirty, Boolean>() {
            @Override
            public Boolean call(Thirty thirty) {
                return true;
            }
        }, new Func1<Forty, Boolean>() {
            @Override
            public Boolean call(Forty forty) {
                return false;
            }
        });
    }

    private static boolean isPlayerForty(PlayerPoints playerPoints) {
        return playerPoints.getPlayerPoints().join(new Func1<Zero, Boolean>() {
            @Override
            public Boolean call(Zero zero) {
                return false;
            }
        }, new Func1<Fifteen, Boolean>() {
            @Override
            public Boolean call(Fifteen fifteen) {
                return false;
            }
        }, new Func1<Thirty, Boolean>() {
            @Override
            public Boolean call(Thirty thirty) {
                return false;
            }
        }, new Func1<Forty, Boolean>() {
            @Override
            public Boolean call(Forty forty) {
                return true;
            }
        });
    }

    private static PlayerPoints score(PlayerPoints playerPoints) {
        return playerPoints.getPlayerPoints().join(new Func1<Zero, PlayerPoints>() {
            @Override
            public PlayerPoints call(Zero zero) {
                return PlayerPoints.fifteen();
            }
        }, new Func1<Fifteen, PlayerPoints>() {
            @Override
            public PlayerPoints call(Fifteen fifteen) {
                return PlayerPoints.thirty();
            }
        }, new Func1<Thirty, PlayerPoints>() {
            @Override
            public PlayerPoints call(Thirty thirty) {
                return PlayerPoints.forty();
            }
        }, new Func1<Forty, PlayerPoints>() {
            @Override
            public PlayerPoints call(Forty forty) {
                throw new IllegalStateException();
            }
        });
    }

    private static Func1<Advantage, Score> scoreAdvantage(final Player player) {
        return new Func1<Advantage, Score>() {
            @Override
            public Score call(Advantage advantage) {
                return advantage.getPlayer().join(new Func1<PlayerOne, Score>() {
                    @Override
                    public Score call(PlayerOne playerOne) {
                        return player.getPlayer().join(new Func1<PlayerOne, Score>() {
                            @Override
                            public Score call(PlayerOne one) {
                                return Score.game(Game.one());
                            }
                        }, new Func1<PlayerTwo, Score>() {
                            @Override
                            public Score call(PlayerTwo two) {
                                return Score.deuce();
                            }
                        });
                    }
                }, new Func1<PlayerTwo, Score>() {
                    @Override
                    public Score call(PlayerTwo playerTwo) {
                        return player.getPlayer().join(new Func1<PlayerOne, Score>() {
                            @Override
                            public Score call(PlayerOne one) {
                                return Score.deuce();
                            }
                        }, new Func1<PlayerTwo, Score>() {
                            @Override
                            public Score call(PlayerTwo two) {
                                return Score.game(Game.two());
                            }
                        });
                    }
                });
            }
        };
    }

    private static Func1<Deuce, Score> scoreDeuce(final Player player) {
        return new Func1<Deuce, Score>() {
            @Override
            public Score call(Deuce deuce) {
                return player.getPlayer().join(new Func1<PlayerOne, Score>() {
                    @Override
                    public Score call(PlayerOne first) {
                        return Score.advantage(Advantage.one());
                    }
                }, new Func1<PlayerTwo, Score>() {
                    @Override
                    public Score call(PlayerTwo second) {
                        return Score.advantage(Advantage.two());
                    }
                });
            }
        };
    }

    private static Func1<Game, Score> scoreGame(final Player player) {
        return new Func1<Game, Score>() {
            @Override
            public Score call(Game game) {
                return player.getPlayer().join(new Func1<PlayerOne, Score>() {
                    @Override
                    public Score call(PlayerOne one) {
                        return scorePlayer(PlayerPoints.fifteen(), PlayerPoints.zero());
                    }
                }, new Func1<PlayerTwo, Score>() {
                    @Override
                    public Score call(PlayerTwo second) {
                        return scorePlayer(PlayerPoints.zero(), PlayerPoints.fifteen());
                    }
                });
            }
        };
    }
}
