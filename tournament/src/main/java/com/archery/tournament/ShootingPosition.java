package com.archery.tournament;

/** Represents the target or group of targets sheared by an Archer's Patrol.
 *
 * In an indoor (and some outdoor) contest, each Patrol use the same
 * {@link ShootingPosition} for the whole Round, while in field contests the
 * {@link ShootingPosition} is just the starting point of the Round trip.
 *
 */
public class ShootingPosition {
  private int position;

  public ShootingPosition(final int thePosition) {
    position = thePosition;
  }

  public int getPosition() {
    return position;
  }

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof ShootingPosition)) {
      return false;
    }

    return position == ((ShootingPosition) other).position;
  }

  @Override
  public int hashCode() {
    return position;
  }
}
