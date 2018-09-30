package com.archery.tournament;

import org.apache.commons.lang3.Validate;

/** Represents the result of a shooting.
 */
public class Score implements Comparable<Score> {
  private Integer points;
  private Integer bonuses;
  private Integer misses;

  /** Creates a new {@link Score} instance.
   *
   * @param thePoints the points scored.
   * @param theBonuses the bonus scored, must positive or zero.
   * @param theMisses the misses scored, must positive or zero.
   */
  public Score(final int thePoints, final int theBonuses, final int theMisses) {
    Validate.isTrue(theBonuses >= 0, "Bonuses cannot be negative");
    Validate.isTrue(theMisses >= 0, "Misses cannot be negative");

    points = thePoints;
    misses = theMisses;
    bonuses = theBonuses;
  }

  /** Default constructor, creates a new {@link Score} instance with points,
   * bonuses and misses set to zero.
   */
  Score() {
    this(0, 0, 0);
  }

  /** Returns the points in this instance.
   *
   * @return an int value.
   */
  public int getPoints() {
    return points;
  }

  /** Returns the number of bonus in this instance.
   *
   * @return an int value, always positive or zero.
   */
  public int getBonuses() {
    return bonuses;
  }

  /** Returns the number of misses in this instance.
   *
   * @return an int value, always positive or zero.
   */
  public int getMisses() {
    return misses;
  }

  /** Adds the given {@link Score} to this instance.
   *
   * @param other a {@link Score} instance, cannot be null.
   */
  public void add(final Score other) {
    Validate.notNull(other, "Adding score cannot be null");

    points += other.points;
    misses += other.misses;
    bonuses += other.bonuses;
  }

  /** Compare two {@link Score} instances.
   *
   * A {@link Score} greater than other if it has more points, when points are
   * equals if it has more bonuses and when bonuses are equals if it has less
   * misses.
   */
  @Override
  public int compareTo(final Score other) {
    int result = points.compareTo(other.points);
    if (result == 0) {
      result = bonuses.compareTo(other.bonuses);
      if (result == 0) {
        //misses comparison is all the way round because more misses is worse
        //than more points or more bonuses
        result = other.misses.compareTo(misses);
      }
    }
    return result;
  }

  /** Just for debugging. Delete asap.
   * @return a String, never null nor empty.
   */
  public String print() {
    StringBuilder builder = new StringBuilder();
    builder.append("T: ").append(points)
        .append(" B: ").append(bonuses)
        .append(" M: ").append(misses);

    return builder.toString();
  }
}
