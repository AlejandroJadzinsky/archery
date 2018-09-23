package com.archery.regulation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

/** A {@link TournamentDefinition} defines the structure of a tournament.
 */
public class TournamentDefinition {
  /** The name that identify the contest. */
  private String name;

  /** The type and number of {@link RoundDefinition rounds}.*/
  private Map<Integer, RoundDefinition> rounds;

  /** Creates a new {@link TournamentDefinition} instance.
   *
   * @param theRounds the map or ordered {@link RoundDefinition} instances,
   * cannot be null nor empty.
   */
  public TournamentDefinition(final Map<Integer, RoundDefinition> theRounds) {
    Validate.notEmpty(theRounds, "The RoundDefinitions cannot be null nor "
        + "empty");

    rounds = theRounds;
  }

  /** Expose the configured {@link RoundDefinition}.
   *
   * @return a List of {@link RoundDefinition} instances in this tournament.
   */
  public List<RoundDefinition> getRounds() {
    return new LinkedList<>(rounds.values());
  }
}

