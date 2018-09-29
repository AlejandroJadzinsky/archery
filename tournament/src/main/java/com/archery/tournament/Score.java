package com.archery.tournament;

/**
 *
 */
public class Score implements Comparable<Score>{
  private Integer points;
  private Integer bonuses;
  private Integer misses;

  public Score(final int thePoints, final int theBonuses, final int theMisses) {
    points = thePoints;
    misses = theMisses;
    bonuses = theBonuses;
  }

  public Score() {
    this(0, 0, 0);
  }

  public int getPoints() {
    return points;
  }

  public int getBonuses() {
    return bonuses;
  }

  public int getMisses() {
    return misses;
  }

  public void add(final Score other) {
    points += other.points;
    misses += other.misses;
    bonuses += other.bonuses;
  }

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

  public String print() {
    StringBuilder builder = new StringBuilder();
    builder.append("T: ").append(points)
        .append(" B: ").append(bonuses)
        .append(" M: ").append(misses);

    return builder.toString();
  }
}
