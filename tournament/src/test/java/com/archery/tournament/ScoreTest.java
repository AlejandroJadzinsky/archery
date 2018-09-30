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
    Score p1B0M0 = new Score(1, 0, 0);
    Score p1B1M0 = new Score(1, 1, 0);
    Score p1M0M1 = new Score(1, 0, 1);
    Score p1B1M1 = new Score(1, 1, 1);

    assertEquals(p1B0M0.compareTo(p1B0M0), 0);
    assertEquals(p1B0M0.compareTo(p1B1M0), -1);
    assertEquals(p1B0M0.compareTo(p1M0M1), 1);
    assertEquals(p1B0M0.compareTo(p1B1M1), -1);

    assertEquals(p1B1M0.compareTo(p1B0M0), 1);
    assertEquals(p1B1M0.compareTo(p1B1M0), 0);
    assertEquals(p1B1M0.compareTo(p1M0M1), 1);
    assertEquals(p1B1M0.compareTo(p1B1M1), 1);

    assertEquals(p1M0M1.compareTo(p1B0M0), -1);
    assertEquals(p1M0M1.compareTo(p1B1M0), -1);
    assertEquals(p1M0M1.compareTo(p1M0M1), 0);
    assertEquals(p1M0M1.compareTo(p1B1M1), -1);

    assertEquals(p1B1M1.compareTo(p1B0M0), 1);
    assertEquals(p1B1M1.compareTo(p1B1M0), -1);
    assertEquals(p1B1M1.compareTo(p1M0M1), 1);
    assertEquals(p1B1M1.compareTo(p1B1M1), 0);
  }

  private void validateScore(final Score score, final int points,
      final int bonuses, final int misses) {
    assertEquals(score.getPoints(), points);
    assertEquals(score.getBonuses(), bonuses);
    assertEquals(score.getMisses(), misses);
  }
}
