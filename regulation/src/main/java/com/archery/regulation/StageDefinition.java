package com.archery.regulation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/** A Tournament can be divided in stages, each subdivided in one or more
 * Rounds.
 * Stages are typed and ordered using {@link StageType}.
 */
class StageDefinition {
  /** The type and number of {@link RoundDefinition rounds}. */
  private Map<Integer, RoundDefinition> rounds;

  /** Creates a new {@link StageDefinition} instance.
   *
   * @param theRounds the map or ordered {@link RoundDefinition} instances,
   * cannot be null nor empty.
   */
  StageDefinition(final Map<Integer, RoundDefinition> theRounds) {
    Validate.notEmpty(theRounds, "The RoundDefinitions cannot be null nor "
        + "empty");

    rounds = theRounds;
  }

  /** Expose the configured {@link RoundDefinition}.
   *
   * @return a List of {@link RoundDefinition} instances in this tournament.
   */
  List<RoundDefinition> getRounds() {
    return new LinkedList<>(rounds.values());
  }
}
