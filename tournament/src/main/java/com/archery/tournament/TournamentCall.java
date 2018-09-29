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
  /** The start date, never null. */
  private LocalDate date;
  /** The start time, never null. */
  private LocalTime time;
  /** The location, never null. */
  private Seat seat;
  /** The type of {@link TournamentDefinition contest} organized, never null. */
  private TournamentDefinition tournamentDefinition;

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
