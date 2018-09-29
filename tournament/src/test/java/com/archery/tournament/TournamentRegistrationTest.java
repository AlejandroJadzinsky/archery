package com.archery.tournament;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.archery.community.Archer;
import com.archery.community.Seat;
import com.archery.regulation.ShootingDivision;
import com.archery.regulation.ShootingStyle;

class TournamentRegistrationTest {

  @Mock
  private Seat seat;

  private TournamentRegistration registrar;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    LocalDate date = LocalDate.now().plusMonths(1);
    LocalTime time = LocalTime.of(9, 30);
    registrar = new TournamentRegistration(2);
  }

  @Nested
  @DisplayName("Contest Archers Registrations")
  class RegisterArchers {
    @Test
    void registerArcher() {
      assertEquals(2, registrar.getRemainingPositions(),
          "Wrong open positions");

      Archer archer = new Archer("archer1");
      registrar.registerArcher(archer, ShootingStyle.BL, ShootingDivision.ADULT);

      assertEquals(1, registrar.getRemainingPositions(),
          "Wrong open positions");
      assertTrue(registrar.isArcherRegistered(archer),
          "Registered archer is missing.");
    }

    @Test
    void registerArcher_noMoreRoom() {
      registrar.registerArcher(new Archer("archer1"), ShootingStyle.BL,
          ShootingDivision.ADULT);
      registrar.registerArcher(new Archer("archer2"), ShootingStyle.BL,
          ShootingDivision.ADULT);

      assertThrows(Exception.class,
          () -> registrar.registerArcher(new Archer("archer3"),
              ShootingStyle.BL, ShootingDivision.ADULT),
          "Exception expected with no room");

      assertEquals(0, registrar.getRemainingPositions(),
          "Wrong open positions");
    }

    @Test
    void registerArcher_alreadyRegistered() {
      assertEquals(2, registrar.getRemainingPositions(),
          "Wrong open positions");
      Archer archer = new Archer("archer1");
      registrar.registerArcher(archer, ShootingStyle.BL, ShootingDivision.ADULT);
      registrar.registerArcher(archer, ShootingStyle.BU, ShootingDivision.CUB);

      assertEquals(1, registrar.getRemainingPositions(),
          "Wrong open positions");
      assertTrue(registrar.isArcherRegistered(archer),
          "Registered archer is missing.");
      assertEquals(registrar.getRegistration(archer).getStyle(),
          ShootingStyle.BU);
      assertEquals(registrar.getRegistration(archer).getDivision(),
          ShootingDivision.CUB);
    }

    @Test
    void unregisterArcher() {
      Archer archer = new Archer("archer1");
      registrar.registerArcher(archer, ShootingStyle.BL, ShootingDivision.ADULT);

      assertEquals(1, registrar.getRemainingPositions(),
          "Wrong open positions");
      assertTrue(registrar.isArcherRegistered(archer),
          "Registered archer is missing.");

      registrar.unregisterArcher(archer);
      assertEquals(2, registrar.getRemainingPositions(),
          "Wrong open positions");
      assertFalse(registrar.isArcherRegistered(archer),
          "Unregistered archer is still present.");
    }

    @Test
    void registerArcher_registrationClosedByTime() {
      TournamentRegistration closedRegistration = new TournamentRegistration(2);
      closedRegistration.closeRegistration();
      Executable register = () -> closedRegistration.registerArcher(
          new Archer("archer1"), ShootingStyle.BL, ShootingDivision.ADULT);
      assertThrows(Exception.class, register,
          "Exception expected when registration is closed");
    }
  }
}
