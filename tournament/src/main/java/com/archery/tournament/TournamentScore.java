package com.archery.tournament;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.Validate;

import com.archery.regulation.ShootingDivision;
import com.archery.regulation.ShootingStyle;

/** The {@link TournamentScore}
 */
class TournamentScore {

  private List<Scorecard> scorecards;

  /** Creates a new {@link TournamentScore} instance.
   *
   * @param scorecardBuilder a {@link ScorecardBuilder} instance, cannot be
   * null.
   * @param registrations a collection of {@link ShooterRegistration}
   * instances, cannot be null nor empty.
   */
  TournamentScore(final ScorecardBuilder scorecardBuilder,
      final Collection<ShooterRegistration> registrations) {
    Validate.notNull(scorecardBuilder, "ScorecardBuilder is null");
    Validate.notEmpty(registrations, "Archer registrations cannot be null nor"
        + " empty");

    scorecards = new LinkedList<>();
    registrations.forEach(r -> scorecards.add(scorecardBuilder.forArcher(r)));
  }

  /** Exposes the list of {@link Scorecard} in this instance.
   *
   * @return a list of {@link Scorecard} instances, never null.
   */
  List<Scorecard> getScorecards() {
    return Collections.unmodifiableList(scorecards);
  }

  /** Retrieves the list of {@link Scorecard} sorted and filtered according to
   * the given parameters.
   *
   * @param sortByArcher when true the list is sorted in ascendant order by the
   * archers public identifier, when false no sort is applied. If sortByScore is
   * true then it is applied first.
   * @param sortByScore when true the list is sorted in descendant order by the
   * {@link Scorecard scorecards} {@link Score}, when flase no sort is applied.
   * @param filterStyle when present only {@link Scorecard scorecards} that
   * match the given {@link ShootingStyle} are returned, otherwise no filter is
   * applied.
   * @param filterDivision when present only {@link Scorecard scorecards} that
   * match the given {@link ShootingDivision} are returned, otherwise no filter
   * is applied.
   *
   * @return a List of {@link Scorecard} instances, never null but can be empty.
   */
  List<Scorecard> listScorecards(final boolean sortByArcher,
      final boolean sortByScore, final ShootingStyle filterStyle,
      final ShootingDivision filterDivision) {
    List<Scorecard> result;
    result = filterScorecards(filterStyle, filterDivision);

    result.sort(Scorecard.getComparatorBy(sortByArcher, sortByScore));
    return result;
  }

  private List<Scorecard> filterScorecards(final ShootingStyle byStyle,
      final ShootingDivision byDivision) {
    Stream<Scorecard> stream = scorecards.stream();
    if (byStyle != null) {
      stream = stream.filter(s -> s.is(byStyle));
    }
    if (byDivision != null) {
      stream = stream.filter(s -> s.is(byDivision));
    }
    return stream.collect(Collectors.toList());
  }
}
