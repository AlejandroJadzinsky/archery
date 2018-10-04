package com.archery.tournament;

import static com.archery.community.CommunityMother.newArcher;
import static com.archery.regulation.TargetZone.*;
import static com.archery.regulation.TargetZone.CROSS;

import java.util.HashMap;
import java.util.Map;

import com.archery.community.Archer;
import com.archery.regulation.EndDefinition;
import com.archery.regulation.RoundDefinition;
import com.archery.regulation.ShootingDivision;
import com.archery.regulation.ShootingStyle;
import com.archery.regulation.Target;
import com.archery.regulation.TargetZone;
import com.archery.regulation.TournamentDefinition;

public class TournamentMother {

  private static Archer archer1 = newArcher("archer1");
  private static Archer archer2 = newArcher("archer2");

  public static TournamentDefinition testTournamentDefinition() {
    Map<TargetZone, Integer> targetZones = new HashMap<>();
    targetZones.put(MISS, 0);
    targetZones.put(ONE, 1);
    targetZones.put(TWO, 2);
    targetZones.put(THREE, 3);
    targetZones.put(FOUR, 4);
    targetZones.put(FIVE, 5);
    targetZones.put(CROSS, 5);
    Target target = new Target(targetZones);

    RoundDefinition roundDefinition1 = new RoundDefinition(
        target, 3, new EndDefinition(3));
    RoundDefinition roundDefinition2 = new RoundDefinition(
        target, 3, new EndDefinition(3));

    Map<Integer, RoundDefinition> roundDefinitions = new HashMap<>();
    roundDefinitions.put(1, roundDefinition1);
    roundDefinitions.put(2, roundDefinition2);

    return new TournamentDefinition(roundDefinitions);
  }

  public static ArcherRegistration archer1BlAdult() {
    return testTournamentRegistration().getRegistration(archer1);
  }

  public static ArcherRegistration archer2LbAdult() {
    return testTournamentRegistration().getRegistration(archer2);
  }

  public static TournamentRegistration testTournamentRegistration() {
    TournamentRegistration registration = new TournamentRegistration(4);
    registration.registerArcher(archer1, ShootingStyle.BL,
        ShootingDivision.ADULT);
    registration.registerArcher(archer2, ShootingStyle.LB,
        ShootingDivision.VETERAN);

    return registration;
  }

  public static ScorecardBuilder testScorecardBuilder() {
    return new ScorecardBuilder(testTournamentDefinition());
  }

  public static Scorecard archer1ScorecardEmpty() {
    return testScorecardBuilder().forArcher(archer1BlAdult());
  }

  public static TournamentScore testTournamentScore() {
    return new TournamentScore(testScorecardBuilder(),
        testTournamentRegistration().getRegistrations());
  }
}
