package com.archery.tournament;

import com.archery.regulation.TournamentDefinition;

/** Creates multiple {@link Scorecard} instances for a configured
 *  {@link TournamentDefinition}.
 */
public class ScorecardBuilder {
  private TournamentDefinition tournamentDefinition;

  /** Creates a new {@link ScorecardBuilder} instance.
   *
   * @param definition a {@link TournamentDefinition} instance, cannot be null.
   */
  public ScorecardBuilder(final TournamentDefinition definition) {
    tournamentDefinition = definition;
  }

  /** Creates a new {@link Scorecard} instance for the given
   * {@link ShooterRegistration}.
   *
   * @param shooterRegistration an {@link ShooterRegistration} instance,
   * cannot be null.
   *
   * @return a new {@link Scorecard} instance, never null.
   */
  public Scorecard forArcher(final ShooterRegistration shooterRegistration) {
    return new Scorecard(tournamentDefinition, shooterRegistration);
  }
}
