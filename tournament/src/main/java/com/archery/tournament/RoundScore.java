package com.archery.tournament;

import java.util.List;
import org.apache.commons.lang3.Validate;

import com.archery.regulation.RoundDefinition;
import com.archery.regulation.Target;
import com.archery.regulation.TargetZone;

/** Register the performance of an Archer in a given round.
 */
class RoundScore extends ScoringBlock<RoundScore.EndScore> {
  private RoundDefinition definition;

  /** Creates a new {@link RoundScore} instance.
   *
   * @param theDefinition a {@link RoundDefinition} instance, cannot be null.
   */
  RoundScore(final RoundDefinition theDefinition) {
    super();

    definition = theDefinition;
  }

  @Override
  void score(final List<TargetZone> hits) {
    if (isCompleted()) {
      throw new RuntimeException("Round is completed");
    }
    EndScore end = new EndScore(definition.getEndDefinition().getArrows(),
        definition.getTarget());
    end.score(hits);
    addScoring(end);
  }

  @Override
  public boolean isCompleted() {
    return definition.getNumberOfEnds() == getScorings().size();
  }

  /** Indicates if this instance is completed or not.
   *
   * @return true if the round is incomplete, otherwise false.
   */
  public boolean isOpen() {
    return !isCompleted();
  }

  /** Just for debugging. Delete asap.
   * @return a String, never null nor empty.
   */
  String print() {
    List<EndScore> ends = getScorings();
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < ends.size(); i++) {
      Score endScore = ends.get(i).getScore();
      builder.append(i + 1)
          .append(": ")
          .append(ends.get(i).print())
          .append(endScore.print())
          .append("\n");
    }

    return builder.toString();
  }

  /** Represents the total points obtained by all the arrows in an End.
   */
  static class EndScore extends ScoringBlock<TargetZoneScore> {
    private int totalHits;
    private Target target;

    /** Creates a new {@link EndScore} instance.
     *
     * @param arrows the number of arrows to score, must be greater than 0.
     * @param theTarget the {@link Target} used to score the arrows hit, cannot
     * be null.
     */
    EndScore(final int arrows, final Target theTarget) {
      super();

      totalHits = arrows;
      target = theTarget;
    }

    @Override
    void score(final List<TargetZone> hits) {
      Validate.isTrue(totalHits == hits.size(), "Invalid number of hits");

      hits.forEach(h -> addScoring(new TargetZoneScore(h, target.score(h))));
    }

    @Override
    public boolean isCompleted() {
      return getScorings().size() == totalHits;
    }

    /** Just for debugging. Delete asap.
     * @return a String, never null nor empty.
     */
    String print() {
      List<TargetZoneScore> targetScores = getScorings();
      StringBuilder builder = new StringBuilder();
      targetScores.forEach(ts -> builder.append(ts.print()).append("  "));
      return builder.toString();
    }
  }

  /** Represents the points obtained by a single arrow when hitting a
   * {@link TargetZone}.
   */
  static class TargetZoneScore implements Scoring {
    private TargetZone zone;
    private int points;

    /** Creates a new {@link TargetZoneScore} instance.
     *
     * @param theZone the {@link TargetZone} to be scored, cannot be null.
     * @param thePoints the points earned, cannot be null.
     */
    TargetZoneScore(final TargetZone theZone, final Integer thePoints) {
      zone = theZone;
      points = thePoints;
    }

    @Override
    public Score getScore() {
      return new Score(points, bonusPoint(), missPoint());
    }

    private int bonusPoint() {
      return zone.isBonus() ? 1 : 0;
    }

    private int missPoint() {
      return zone.isMiss() ? 1 : 0;
    }

    /** Just for debugging. Delete asap.
     * @return a String, never null nor empty.
     */
    String print() {
      return zone.toString();
    }
  }
}
