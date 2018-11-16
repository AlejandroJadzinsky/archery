package com.archery.tournament;

import org.apache.commons.lang3.Validate;

import com.archery.regulation.ShootingDivision;
import com.archery.regulation.ShootingStyle;

/** Represents the {@link Shooter} {@link ShootingStyle} and
 * {@link ShootingDivision} in the tournament in which the shooter will
 * compete or has competed.
 *
 * The {@link ShooterRegistration} is managed in the
 * {@link TournamentRegistration}.
 */
public class ShooterRegistration {
  private Shooter shooter;
  private ShootingStyle style;
  private ShootingDivision division;

  /** Creates a new {@link ShooterRegistration} instance.
   *
   * @param theShooter an {@link Shooter} instance, cannot be null.
   * @param theStyle an {@link ShootingStyle} instance, cannot be null.
   * @param theDivision an {@link ShootingDivision} instance, cannot be null.
   */
  ShooterRegistration(final Shooter theShooter,
      final ShootingStyle theStyle, final ShootingDivision theDivision) {
    Validate.notNull(theShooter, "Archer cannot be null");
    Validate.notNull(theStyle, "Shooting style cannot be null");
    Validate.notNull(theDivision, "Shooting division cannot be null");

    shooter = theShooter;
    style = theStyle;
    division = theDivision;
  }

  /** Expose the registered {@link Shooter}.
   *
   * @return an {@link Shooter} instance, never null.
   */
  Shooter getShooter() {
    return shooter;
  }

  /** Checks if this registration instance is of a certain
   * {@link ShootingStyle style}.
   *
   * @param aStyle a {@link ShootingStyle} instance, cannot be null.
   *
   * @return true if this instance is for the given {@link ShootingStyle}
   * style, otherwise false.
   */
  public boolean isStyle(final ShootingStyle aStyle) {
    return style.equals(aStyle);
  }

  /** Checks if this registration instance is of a certain
   * {@link ShootingDivision division}.
   *
   * @param aDivision a {@link ShootingDivision} instance, cannot be null.
   *
   * @return true if this instance is for the given {@link ShootingDivision}
   * division, otherwise false.
   */
  public boolean isDivision(final ShootingDivision aDivision) {
    return division.equals(aDivision);
  }

  /** Just for debugging. Delete asap.
   * @return a String, never null nor empty.
   */
  String print() {
    StringBuilder builder = new StringBuilder();
    builder.append("ARCHER: ").append(shooter)
        .append("\tSTYLE: ").append(style.name())
        .append("\tDIVISION: ").append(division.name());

    return builder.toString();
  }
}
