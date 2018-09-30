package com.archery.tournament;

import java.time.LocalDate;
import java.time.LocalTime;
import org.apache.commons.lang3.Validate;

import com.archery.community.Seat;
import com.archery.regulation.TournamentDefinition;

/** A {@link TournamentCall} communicates a new Tournament to the archers
 * community.
 *
 * Basically defines:
 *   - The type of contest
 *   - Who organizes it (TBD)
 *   - Where it take place
 *   - When it is scheduled
 */
public class TournamentCall {
  private LocalDate date;
  private LocalTime time;
  private Seat seat;
  private TournamentDefinition tournamentDefinition;

  /** Creates a new {@link TournamentCall} instance.
   *
   * @param theDate the start date, cannot be null.
   * @param theTime the start time, cannot null.
   * @param theSeat the location, cannot null.
   * @param theTournamentDefinition the type of {@link TournamentDefinition}
   * organized, cannot null.
   */
  public TournamentCall(final LocalDate theDate, final LocalTime theTime,
      final Seat theSeat, final TournamentDefinition theTournamentDefinition) {
    Validate.notNull(theSeat, "The Contest seat is null");
    Validate.notNull(theDate, "The Contest date is null");
    Validate.notNull(theTime, "The Contest start time is null");
    Validate.notNull(theTournamentDefinition, "The ContestDefinition is null");

    date = theDate;
    time = theTime;
    seat = theSeat;
    tournamentDefinition = theTournamentDefinition;
  }
}
