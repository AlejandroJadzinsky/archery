package com.archery.regulation;

import org.apache.commons.lang3.Validate;

/** Tournaments have one or more Rounds, Each Round is divided into Ends. An
 * archer shoots N arrows per end.
 */
public class EndDefinition {
  /** The number of arrows shot in each end, greater than 0. */
  private int arrows;

  /** Creates a new {@link EndDefinition} instance.
   *
   * @param numberOfArrows the number of arrows shot in this End, must be
   * greater than 0.
   */
  public EndDefinition(final int numberOfArrows) {
    Validate.isTrue(numberOfArrows > 0, "The arrows in End must be positive");

    arrows = numberOfArrows;
  }

  /** Expose the configured number of arrows to shoot in this End.
   *
   * @return an int, greater than 0.
   */
  public int getArrows() {
    return arrows;
  }
}
