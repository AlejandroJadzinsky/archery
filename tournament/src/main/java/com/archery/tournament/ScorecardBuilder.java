package com.archery.tournament;

import com.archery.regulation.TournamentDefinition;

/** Creates multiple {@link Scorecard} instances for a configured
 *  {@link TournamentDefinition}.
 */
public class ScorecardBuilder {
  private TournamentDefinition tournamentDefinition;

  public ScorecardBuilder(final TournamentDefinition definition) {
    tournamentDefinition = definition;
  }

  public Scorecard forArcher(final ArcherRegistration archerRegistration) {
    return new Scorecard(tournamentDefinition, archerRegistration);
  }
}
