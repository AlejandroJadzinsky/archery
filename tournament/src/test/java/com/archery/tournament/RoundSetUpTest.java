package com.archery.tournament;

import static com.archery.tournament.TournamentFactory.newShooter;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoundSetUpTest {

  private Set<Shooter> archers;

  @BeforeEach
  void setUp() {
    archers = new HashSet<>();
    archers.add(newShooter("a1"));
    archers.add(newShooter("a2"));
    archers.add(newShooter("a3"));
    archers.add(newShooter("a4"));
    archers.add(newShooter("a5"));
    archers.add(newShooter("a6"));
    archers.add(newShooter("a7"));
    archers.add(newShooter("a8"));
    archers.add(newShooter("a9"));
  }

  @Test
  void newRoundSetUp_evenDistribution() {
    RoundSetUp newRound = new RoundSetUp(3, archers);
    Map<ShootingPosition, Patrol> patrols = newRound.getPatrolsOrder();

    assertEquals(patrols.size(), 3);
    assertEquals(patrols.get(new ShootingPosition(1)).getGroup().size(), 3);
    assertEquals(patrols.get(new ShootingPosition(2)).getGroup().size(), 3);
    assertEquals(patrols.get(new ShootingPosition(3)).getGroup().size(), 3);
  }

  @Test
  void newRoundSetUp_oddDistribution() {
    RoundSetUp newRound = new RoundSetUp(5, archers);
    Map<ShootingPosition, Patrol> patrols = newRound.getPatrolsOrder();

    assertEquals(patrols.size(), 5);
    assertEquals(patrols.get(new ShootingPosition(1)).getGroup().size(), 2);
    assertEquals(patrols.get(new ShootingPosition(2)).getGroup().size(), 2);
    assertEquals(patrols.get(new ShootingPosition(3)).getGroup().size(), 2);
    assertEquals(patrols.get(new ShootingPosition(4)).getGroup().size(), 2);
    assertEquals(patrols.get(new ShootingPosition(5)).getGroup().size(), 1);
  }

  @Test
  void moveArcher() {
    RoundSetUp newRound = new RoundSetUp(4, archers);
    Map<ShootingPosition, Patrol> patrols = newRound.getPatrolsOrder();

    ShootingPosition from = new ShootingPosition(1);
    ShootingPosition to = new ShootingPosition(4);

    Shooter archer = newRound.getPatrolsOrder().get(from).getGroup().stream()
        .findFirst().get();

    assertEquals(patrols.get(from).getGroup().size(), 3);
    assertTrue(patrols.get(from).getGroup().contains(archer));
    assertEquals(patrols.get(to).getGroup().size(), 2);
    assertFalse(patrols.get(to).getGroup().contains(archer));

    newRound.moveShooter(archer, from, to);

    assertEquals(patrols.get(from).getGroup().size(), 2);
    assertFalse(patrols.get(from).getGroup().contains(archer));
    assertEquals(patrols.get(to).getGroup().size(), 3);
    assertTrue(patrols.get(to).getGroup().contains(archer));
  }
}
