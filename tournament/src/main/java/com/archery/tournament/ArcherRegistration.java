package com.archery.tournament;

import org.apache.commons.lang3.Validate;

import com.archery.community.Archer;
import com.archery.regulation.ShootingDivision;
import com.archery.regulation.ShootingStyle;

/** Represents the {@link Archer archer's} {@link ShootingStyle} and
 * {@link ShootingDivision} in the tournament in which the archer will
 * compete or has competed.
 *
 * The {@link ArcherRegistration} is managed in the
 * {@link TournamentRegistration}.
 */
public class ArcherRegistration {
  private Archer archer;
  private ShootingStyle style;
  private ShootingDivision division;

  public ArcherRegistration(final Archer theArcher,
      final ShootingStyle theStyle, final ShootingDivision theDivision) {
    Validate.notNull(theArcher, "Archer cannot be null");
    Validate.notNull(theStyle, "Shooting style cannot be null");
    Validate.notNull(theDivision, "Shooting division cannot be null");

    archer = theArcher;
    style = theStyle;
    division = theDivision;
  }

  public Archer getArcher() {
    return archer;
  }

  public ShootingStyle getStyle() {
    return style;
  }

  public ShootingDivision getDivision() {
    return division;
  }

  public boolean isStyle(final ShootingStyle aStyle) {
    return style.equals(aStyle);
  }

  public boolean isDivision(final ShootingDivision aDivision) {
    return division.equals(aDivision);
  }

  String print() {
    StringBuilder builder = new StringBuilder();
    builder.append("ARCHER: ").append(archer.logInfo())
        .append("\tSTYLE: ").append(style.name())
        .append("\tDIVISION: ").append(division.name());

    return builder.toString();
  }
}
