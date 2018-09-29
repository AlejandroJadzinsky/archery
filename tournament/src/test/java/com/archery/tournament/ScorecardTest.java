package com.archery.tournament;

import static com.archery.regulation.TargetZone.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScorecardTest {
  private Scorecard score;

  @BeforeEach
  void setUp() {
    score = ArcheryMother.archer1ScorecardEmpty();
  }

  @Test
  void score_incomplete() {
    //round 1
    score.score(FOUR, TWO, MISS);
    score.score(FIVE, FOUR, ONE);
    score.score(FOUR, FOUR, TWO);
    //round 2
    score.score(CROSS, THREE, MISS);
    score.score(TWO, TWO, TWO);

    //validate contest
    assertFalse(score.isCompleted(), "Contest should be open");
    Score contestScore = score.getScore();
    assertEquals(contestScore.getPoints(), 40);
    assertEquals(contestScore.getBonuses(), 1);
    assertEquals(contestScore.getMisses(), 2);

    //validate first round
    assertEquals(score.getScorings().size(), 2, "Wrong number of rounds");
    RoundScore round1 = score.getScorings().get(0);
    assertTrue(round1.isCompleted(), "First round should be closed");
    Score round1Score = round1.getScore();
    assertEquals(round1Score.getPoints(), 26);
    assertEquals(round1Score.getBonuses(), 0);
    assertEquals(round1Score.getMisses(), 1);
    assertEquals(round1.getScorings().size(), 3, "Wrong number of ends in round 1");

    //validate second round
    RoundScore round2 = score.getScorings().get(1);
    assertFalse(round2.isCompleted(), "Second round should be open");
    Score round2Score = round2.getScore();
    assertEquals(round2Score.getPoints(), 14);
    assertEquals(round2Score.getBonuses(), 1);
    assertEquals(round2Score.getMisses(), 1);
    assertEquals(round2.getScorings().size(), 2, "Wrong number of ends in round 2");

    //validate ends in second round
    RoundScore.EndScore end1Round2 = round2.getScorings().get(0);
    assertEquals(end1Round2.getScorings().size(), 3);

    RoundScore.EndScore end2Round2 = round2.getScorings().get(1);
    assertEquals(end2Round2.getScorings().size(), 3);

//    System.out.println(score.print());
  }

  @Test
  void score_complete() {
    //round 1
    score.score(FOUR, TWO, MISS);
    score.score(FIVE, FOUR, ONE);
    score.score(FOUR, FOUR, TWO);
    //round 2
    score.score(CROSS, THREE, MISS);
    score.score(TWO, TWO, TWO);
    score.score(MISS, MISS, MISS);

    //validate contest
    Score contestScore = score.getScore();
    assertEquals(contestScore.getPoints(), 40);
    assertEquals(contestScore.getBonuses(), 1);
    assertEquals(contestScore.getMisses(), 5);

    //validate first round
    assertEquals(score.getScorings().size(), 2, "Wrong number of rounds");
    RoundScore round1 = score.getScorings().get(0);
    assertTrue(round1.isCompleted(), "First round should be closed");
    Score round1Score = round1.getScore();
    assertEquals(round1Score.getPoints(), 26);
    assertEquals(round1Score.getBonuses(), 0);
    assertEquals(round1Score.getMisses(), 1);
    assertEquals(round1.getScorings().size(), 3, "Wrong number of ends in round 1");

    //validate second round
    RoundScore round2 = score.getScorings().get(1);
    assertTrue(round2.isCompleted(), "Second round should be closed");
    Score round2Score = round2.getScore();
    assertEquals(round2Score.getPoints(), 14);
    assertEquals(round2Score.getBonuses(), 1);
    assertEquals(round2Score.getMisses(), 4);
    assertEquals(round2.getScorings().size(), 3, "Wrong number of ends in round 2");

//    System.out.println(card.print());
  }

  @Test
  void score_closed() {
    //round 1
    score.score(FOUR, TWO, MISS);
    score.score(FIVE, FOUR, ONE);
    score.score(FOUR, FOUR, TWO);
    //round 2
    score.score(CROSS, THREE, MISS);
    score.score(TWO, TWO, TWO);
    score.score(MISS, MISS, MISS);

    assertThrows(RuntimeException.class, () -> score.score(CROSS, MISS, MISS));
  }

  @Test
  void score_empty() {
    Score tournamentScore = score.getScore();
    assertEquals(tournamentScore.getPoints(), 0);
    assertEquals(tournamentScore.getBonuses(), 0);
    assertEquals(tournamentScore.getMisses(), 0);

    //validate first round
    assertEquals(score.getScorings().size(), 2, "Wrong number of rounds");
    RoundScore round1 = score.getScorings().get(0);
    assertFalse(round1.isCompleted(), "First round should be open");
    Score round1Score = round1.getScore();
    assertEquals(round1Score.getPoints(), 0);
    assertEquals(round1Score.getBonuses(), 0);
    assertEquals(round1Score.getMisses(), 0);
    assertEquals(round1.getScorings().size(), 0, "Wrong number of ends in round 1");

    //validate second round
    RoundScore round2 = score.getScorings().get(1);
    assertFalse(round2.isCompleted(), "Second round should be open");
    Score round2Score = round2.getScore();
    assertEquals(round2Score.getPoints(), 0);
    assertEquals(round2Score.getBonuses(), 0);
    assertEquals(round2Score.getMisses(), 0);
    assertEquals(round2.getScorings().size(), 0, "Wrong number of ends in round 2");

//    System.out.println(score.print());
  }
}
