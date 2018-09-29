package com.archery.tournament;

import static org.junit.jupiter.api.Assertions.*;
import static com.archery.regulation.TargetZone.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.archery.regulation.ShootingDivision;
import com.archery.regulation.ShootingStyle;

class TournamentScoreTest {

  private TournamentScore tournamentScore;

  @BeforeEach
  void setUp() {
    tournamentScore = ArcheryMother.testTournamentScore();
  }

  @Test
  void sortScorecards_sortByArcher() {
    List<Scorecard> result = tournamentScore.listScorecards(true, false, null,
        null);

    assertEquals(result.size(), 2);
    assertEquals(result.get(0).getArcherRegistration().getArcher().logInfo()
        , "archer1");
    assertEquals(result.get(1).getArcherRegistration().getArcher().logInfo()
        , "archer2");
  }

  @Test
  void sortScorecards_sortByScore() {
    List<Scorecard> scorecards = tournamentScore.getScorecards();
    String looser = scorecards.get(0).getArcherRegistration().getArcher()
        .logInfo();
    scorecards.get(0).score(FIVE, FIVE, FIVE);
    String winner = scorecards.get(1).getArcherRegistration() .getArcher()
        .logInfo();
    scorecards.get(1).score(CROSS, FIVE, FIVE);

    List<Scorecard> result = tournamentScore.listScorecards(false, true, null,
        null);

    assertEquals(result.size(), 2);
    assertEquals(result.get(0).getArcherRegistration().getArcher().logInfo()
        , winner);
    assertEquals(result.get(1).getArcherRegistration().getArcher().logInfo()
        , looser);
  }

  @Test
  void sortScorecards_sortByScoreAndArcher() {
    List<Scorecard> scorecards = tournamentScore.getScorecards();
    boolean pos0isArcher1 = scorecards.get(0).getArcherRegistration()
        .getArcher().logInfo().equals("archer1");
    if (pos0isArcher1) {
      scorecards.get(0).score(FIVE, FIVE, FIVE);
      scorecards.get(1).score(CROSS, FIVE, FIVE);
    } else {
      scorecards.get(1).score(FIVE, FIVE, FIVE);
      scorecards.get(0).score(CROSS, FIVE, FIVE);
    }

    List<Scorecard> result = tournamentScore.listScorecards(true, true, null,
        null);

    assertEquals(result.size(), 2);
    assertEquals(result.get(0).getArcherRegistration().getArcher().logInfo()
        , "archer2");
    assertEquals(result.get(1).getArcherRegistration().getArcher().logInfo()
        , "archer1");
  }

  @Test
  void filterScorecards_byStyle_noResult() {
    List<Scorecard> result = tournamentScore.listScorecards(false, false,
        ShootingStyle.BB_R, null);

    assertEquals(result.size(), 0);
  }

  @Test
  void filterScorecards_byStyle() {
    List<Scorecard> result = tournamentScore.listScorecards(false, false,
        ShootingStyle.LB, null);

    assertEquals(result.size(), 1);
  }

  @Test
  void filterScorecards_byDivision_noResult() {
    List<Scorecard> result = tournamentScore.listScorecards(false, false,
        null, ShootingDivision.CUB);

    assertEquals(result.size(), 0);
  }

  @Test
  void filterScorecards_byDivision() {
    List<Scorecard> result = tournamentScore.listScorecards(false, false,
        null, ShootingDivision.VETERAN);

    assertEquals(result.size(), 1);
  }

  @Test
  void filterScorecards_byStyleAndDivision_noResult() {
    List<Scorecard> result = tournamentScore.listScorecards(false, false,
        ShootingStyle.BL, ShootingDivision.VETERAN);

    assertEquals(result.size(), 0);
  }

  @Test
  void filterScorecards_byStyleAndDivision() {
    List<Scorecard> result = tournamentScore.listScorecards(false, false,
        ShootingStyle.BL, ShootingDivision.ADULT);

    assertEquals(result.size(), 1);
  }
}