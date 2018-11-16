package com.archery.tournament;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static com.archery.tournament.TournamentFactory.newShooter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.archery.regulation.ShootingDivision;
import com.archery.regulation.ShootingStyle;

class TournamentRegistrationTest {

  private TournamentRegistration registrar;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    registrar = new TournamentRegistration(2);
  }

  @Nested
  @DisplayName("Contest Archers Registrations")
  class RegisterArchers {
    @Test
    void registerArcher() {
      assertEquals(2, registrar.getRemainingPositions(),
          "Wrong open positions");

      Shooter shooter = newShooter("archer1");
      registrar.registerArcher(shooter, ShootingStyle.BL,
          ShootingDivision.ADULT);

      assertEquals(1, registrar.getRemainingPositions(),
          "Wrong open positions");
      assertTrue(registrar.isArcherRegistered(shooter),
          "Registered shooter is missing.");
    }

    @Test
    void registerArcher_noMoreRoom() {
      registrar.registerArcher(newShooter("archer1"), ShootingStyle.BL,
          ShootingDivision.ADULT);
      registrar.registerArcher(newShooter("archer2"), ShootingStyle.BL,
          ShootingDivision.ADULT);

      assertThrows(Exception.class,
          () -> registrar.registerArcher(newShooter("archer3"),
              ShootingStyle.BL, ShootingDivision.ADULT),
          "Exception expected with no room");

      assertEquals(0, registrar.getRemainingPositions(),
          "Wrong open positions");
    }

    @Test
    void registerArcher_alreadyRegistered() {
      assertEquals(2, registrar.getRemainingPositions(),
          "Wrong open positions");
      Shooter shooter = newShooter("archer1");
      registrar.registerArcher(shooter, ShootingStyle.BL,
          ShootingDivision.ADULT);
      registrar.registerArcher(shooter, ShootingStyle.BU, ShootingDivision.CUB);

      assertEquals(1, registrar.getRemainingPositions(),
          "Wrong open positions");
      assertTrue(registrar.isArcherRegistered(shooter),
          "Registered shooter is missing.");

      ShooterRegistration registered = registrar.getRegistration(shooter);
      ShootingStyle style = (ShootingStyle) ReflectionTestUtils.getField(
          registered, "style");
      ShootingDivision division = (ShootingDivision) ReflectionTestUtils
          .getField(registered, "division");

      assertEquals(style, ShootingStyle.BU);
      assertEquals(division, ShootingDivision.CUB);
    }

    @Test
    void unregisterArcher() {
      Shooter shooter = newShooter("archer1");
      registrar.registerArcher(shooter, ShootingStyle.BL,
          ShootingDivision.ADULT);

      assertEquals(1, registrar.getRemainingPositions(),
          "Wrong open positions");
      assertTrue(registrar.isArcherRegistered(shooter),
          "Registered shooter is missing.");

      registrar.unregisterArcher(shooter);
      assertEquals(2, registrar.getRemainingPositions(),
          "Wrong open positions");
      assertFalse(registrar.isArcherRegistered(shooter),
          "Unregistered shooter is still present.");
    }

    @Test
    void registerArcher_registrationClosedByTime() {
      TournamentRegistration closedRegistration = new TournamentRegistration(2);
      closedRegistration.closeRegistration();
      Executable register = () -> closedRegistration.registerArcher(
          newShooter("archer1"), ShootingStyle.BL, ShootingDivision.ADULT);
      assertThrows(Exception.class, register,
          "Exception expected when registration is closed");
    }
  }
}
