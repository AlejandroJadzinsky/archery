package com.archery.tournament;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.Validate;

import com.archery.regulation.TargetZone;

/** Anything that is score-able. It may keep a list of {@link ScoringBlock}
 *  elements and return its aggregated {@link Score}.
 *
 * @param <T> anything that extends Scoring.
 */
abstract class ScoringBlock<T extends Scoring> implements Scoring {
  private List<T> scorings;

  /** Default constructor.
   */
  protected ScoringBlock() {
    scorings = new LinkedList<>();
  }

  /** Adds a new {@link Scoring} instance.
   *
   * @param scoring a {@link Scoring} instance, cannot be null.
   */
  protected void addScoring(final T scoring) {
    Validate.notNull(scoring, "cannot add null Scoring");

    scorings.add(scoring);
  }

  /** Exposes the list of added {@link Scoring} instances.
   *
   * @return a list of {@link Scoring} instances, never null.
   */
  protected List<T> getScorings() {
    return scorings;
  }

  /** Returns this instance aggregated score.
   *
   * @return a {@link Score} instance, never null.
   */
  public Score getScore() {
    Score result = new Score();
    scorings.forEach(s -> result.add(s.getScore()));
    return result;
  }

  /** Scores the given {@link TargetZone} instances.
   *
   * @param hits a list of {@link TargetZone} instances, cannot be null nor
   * empty.
   */
  abstract void score(final List<TargetZone> hits);

  /** Indicates if this instance is competed or not.
   *
   * @return true if this instance is completed, otherwise false.
   */
  abstract boolean isCompleted();
}
