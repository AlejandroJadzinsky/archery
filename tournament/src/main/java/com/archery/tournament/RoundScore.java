package com.archery.tournament;

import java.util.List;
import org.apache.commons.lang3.Validate;

import com.archery.regulation.EndDefinition;
import com.archery.regulation.RoundDefinition;
import com.archery.regulation.Target;
import com.archery.regulation.TargetZone;

/**
 * Register the performance of an Archer in a given round.
 */
public class RoundScore extends ScoringBlock<RoundScore.EndScore> {
  private RoundDefinition definition;

  public RoundScore(final RoundDefinition theDefinition) {
    super();

    definition = theDefinition;
  }

  void score(final List<TargetZone> hits) {
    if (isCompleted()) {
      throw new RuntimeException("Round is completed");
    }
    EndScore end = new EndScore(definition.getEndDefinition(),
        definition.getTarget());
    end.score(hits);
    addScoring(end);
  }

  public boolean isCompleted() {
    return definition.getNumberOfEnds() == getScorings().size();
  }

  public boolean isOpen() {
    return !isCompleted();
  }

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

  static class EndScore extends ScoringBlock<TargetZoneScore> {
    private int totalHits;
    private Target target;

    EndScore(final EndDefinition definition, final Target theTarget) {
      super();

      totalHits = definition.getArrows();
      target = theTarget;
    }

    void score(final List<TargetZone> hits) {
      Validate.isTrue(totalHits == hits.size(), "Invalid number of hits");

      hits.forEach(h -> addScoring(new TargetZoneScore(h, target)));
    }

    @Override
    public boolean isCompleted() {
      return getScorings().size() == totalHits;
    }

    String print() {
      List<TargetZoneScore> targetScores = getScorings();
      StringBuilder builder = new StringBuilder();
      targetScores.forEach(ts -> builder.append(ts.print()).append("  "));
      return builder.toString();
    }
  }

  static class TargetZoneScore implements Scoring {
    private TargetZone zone;
    private Score score;

    TargetZoneScore(final TargetZone theZone, final Target target) {
      zone = theZone;
      score = getScore(target.score(zone));
    }

    private Score getScore(final int points) {
      return new Score(points, bonusPoint(), missPoint());
    }

    private int bonusPoint() {
      return zone.isBonus() ? 1 : 0;
    }

    private int missPoint() {
      return zone.isMiss() ? 1 : 0;
    }

    public Score getScore() {
      return score;
    }

    String print() {
      return zone.toString();
    }
  }
}
