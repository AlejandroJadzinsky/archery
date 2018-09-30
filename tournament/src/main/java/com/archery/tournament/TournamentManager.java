package com.archery.tournament;

import com.archery.regulation.TournamentDefinition;

/** Manages the steps in a Tournament execution.
 */
public class TournamentManager {
  private TournamentDefinition tournamentDefinition;
  private TournamentRegistration tournamentRegistration;
  private TournamentScore tournamentScore;

  /** Prepares the tournament for execution.
   */
  public void setupTournament() {
    ScorecardBuilder builder = new ScorecardBuilder(tournamentDefinition);
    tournamentScore = new TournamentScore(builder,
        tournamentRegistration.getRegistrations());
  }

}
