package com.archery.regulation;

import org.apache.commons.lang3.Validate;

/** Tournaments have one or more Rounds. A round is defined by its distance
 * to the target (when known) and a target used for this distance.
 * TODO (A.J 2018-09-22) add distance to the model
 *
 * Rounds are divided into a number of {@link EndDefinition Ends}.
 */
public class RoundDefinition {
  /** The {@link Target} used in this Round, never null. */
  private Target target;
  /** The number of {@link EndDefinition Ends} in this Round. */
  private int numberOfEnds;
  /** The {@link EndDefinition} used in this Round, never null. */
  private EndDefinition endDefinition;

  /** Creates a new {@link RoundDefinition} instance.
   *
   * @param theTarget a {@link Target} instance, cannot be null.
   * @param theNumberOfEnds an int for the number of ends, greater than 0.
   * @param theEndDefinition an {@link EndDefinition} instance, cannot be null.
   */
  public RoundDefinition(final Target theTarget, final int theNumberOfEnds,
      final EndDefinition theEndDefinition) {
    Validate.notNull(theTarget, "The Target cannot be null");
    Validate.isTrue(theNumberOfEnds > 0, "The number of Ends must be positive");
    Validate.notNull(theEndDefinition, "The End cannot be null");

    target = theTarget;
    numberOfEnds = theNumberOfEnds;
    endDefinition = theEndDefinition;
  }

  /** Exposes the {@link Target} used in this Round.
   *
   * @return a {@link Target} instance, never null.
   */
  public Target getTarget() {
    return target;
  }

  /** Exposes the number of Ends in this Round.
   *
   * @return an int, greater than 0.
   */
  public int getNumberOfEnds() {
    return numberOfEnds;
  }

  /** Exposes the {@link EndDefinition} for this Round.
   *
   * @return an {@link EndDefinition} instance, never null.
   */
  public EndDefinition getEndDefinition() {
    return endDefinition;
  }
}
