package com.archery.tournament;

/** Anything that can be scored. */
public interface Scoring {
  /** Returns the implementing object {@link Score}.
   *
   * @return a {@link Score} instance, never null.
   */
  Score getScore();
}
