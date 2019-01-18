package com.archery.regulation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

class TournamentDefinitionTest {

  TournamentDefinition create_IFAA_indoor() {
    Map<TargetZone, Integer> targetZones = new HashMap<>();
    targetZones.put(TargetZone.CROSS, 5);
    targetZones.put(TargetZone.FIVE, 5);
    targetZones.put(TargetZone.FOUR, 4);
    targetZones.put(TargetZone.THREE, 3);
    targetZones.put(TargetZone.TWO, 2);
    targetZones.put(TargetZone.ONE, 1);
    targetZones.put(TargetZone.MISS, 0);
    Target ifaaIndorTarget = new Target(targetZones);

    EndDefinition indorEnd = new EndDefinition(5);
    RoundDefinition firstRound = new RoundDefinition(ifaaIndorTarget, 12,
        indorEnd);

    Map<Integer, RoundDefinition> rounds = new HashMap<>();
    rounds.put(1, firstRound);
    TournamentDefinition ifaaIndor = new TournamentDefinition("IFAA INDOOR",
        rounds);

    return ifaaIndor;
  }
}
