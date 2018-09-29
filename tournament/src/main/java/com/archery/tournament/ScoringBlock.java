package com.archery.tournament;

import java.util.LinkedList;
import java.util.List;

import com.archery.regulation.TargetZone;

/** Anything that is score-able. It may keep a list of {@link ScoringBlock}
 *  elements and return its aggregated {@link Score}.
 */
public abstract class ScoringBlock<T extends Scoring> implements Scoring {
  private List<T> scorings;

  protected ScoringBlock() {
    scorings = new LinkedList<>();
  }

  protected void addScoring(final T scoring) {
    scorings.add(scoring);
  }

  protected List<T> getScorings() {
    return scorings;
  }

  public Score getScore() {
    Score result = new Score();
    scorings.forEach(s -> result.add(s.getScore()));
    return result;
  }

  abstract void score(final List<TargetZone> hits);

  public abstract boolean isCompleted();
}
