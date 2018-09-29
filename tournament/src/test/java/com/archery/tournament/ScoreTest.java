package com.archery.tournament;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreTest {

  @Test
  void add() {
    Score score = new Score();
    validateScore(score, 0, 0, 0);

    score.add(new Score(1, 1, 1));
    score.add(new Score(2, 1, 0));
    score.add(new Score(1, 0, 2));
    score.add(new Score(0, 2, 1));
    validateScore(score, 4, 4, 4);
  }

  @Test
  void compareTo() {
    Score p1_b0_m0 = new Score(1, 0, 0);
    Score p1_b1_m0 = new Score(1, 1, 0);
    Score p1_b0_m1 = new Score(1, 0, 1);
    Score p1_b1_m1 = new Score(1, 1, 1);

    assertEquals(p1_b0_m0.compareTo(p1_b0_m0), 0);
    assertEquals(p1_b0_m0.compareTo(p1_b1_m0), -1);
    assertEquals(p1_b0_m0.compareTo(p1_b0_m1), 1);
    assertEquals(p1_b0_m0.compareTo(p1_b1_m1), -1);

    assertEquals(p1_b1_m0.compareTo(p1_b0_m0), 1);
    assertEquals(p1_b1_m0.compareTo(p1_b1_m0), 0);
    assertEquals(p1_b1_m0.compareTo(p1_b0_m1), 1);
    assertEquals(p1_b1_m0.compareTo(p1_b1_m1), 1);

    assertEquals(p1_b0_m1.compareTo(p1_b0_m0), -1);
    assertEquals(p1_b0_m1.compareTo(p1_b1_m0), -1);
    assertEquals(p1_b0_m1.compareTo(p1_b0_m1), 0);
    assertEquals(p1_b0_m1.compareTo(p1_b1_m1), -1);

    assertEquals(p1_b1_m1.compareTo(p1_b0_m0), 1);
    assertEquals(p1_b1_m1.compareTo(p1_b1_m0), -1);
    assertEquals(p1_b1_m1.compareTo(p1_b0_m1), 1);
    assertEquals(p1_b1_m1.compareTo(p1_b1_m1), 0);
  }

  private void validateScore(final Score score, final int points,
      final int bonuses, final int misses) {
    assertEquals(score.getPoints(), points);
    assertEquals(score.getBonuses(), bonuses);
    assertEquals(score.getMisses(), misses);
  }
}
