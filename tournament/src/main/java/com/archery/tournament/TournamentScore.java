package com.archery.tournament;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.archery.regulation.ShootingDivision;
import com.archery.regulation.ShootingStyle;

/** The {@link TournamentScore}
 */
public class TournamentScore {

  private List<Scorecard> scorecards;

  public TournamentScore(final ScorecardBuilder scorecardBuilder,
      final Collection<ArcherRegistration> registrations) {
    scorecards = new LinkedList<>();

    registrations.forEach(r -> scorecards.add(scorecardBuilder.forArcher(r)));
  }

  public List<Scorecard> getScorecards() {
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
  public List<Scorecard> listScorecards(final boolean sortByArcher,
      final boolean sortByScore, final ShootingStyle filterStyle,
      final ShootingDivision filterDivision) {
    List<Scorecard> result;
    result = filterScorecards(filterStyle, filterDivision);

    result.sort(getComparator(sortByArcher, sortByScore));
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

  private Comparator<Scorecard> getComparator(final boolean sortByArcher,
      final boolean sortByScore) {
    return (o1, o2) -> {
      //score comparison is in reverse order, we want the higher scores first.
      int scoreComparision = o2.getScore().compareTo(o1.getScore());
      int archerComparision = o1.getArcherRegistration().getArcher().compareTo
          (o2.getArcherRegistration().getArcher());

      if (sortByArcher && sortByScore) {
        if (scoreComparision == 0) {
          return archerComparision;
        }
        return scoreComparision;
      }
      if (sortByArcher) {
        return archerComparision;
      }

      return scoreComparision;
    };
  }
}
