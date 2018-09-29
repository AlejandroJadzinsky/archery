package com.archery.tournament;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.Validate;

import com.archery.regulation.RoundDefinition;
import com.archery.regulation.ShootingDivision;
import com.archery.regulation.ShootingStyle;
import com.archery.regulation.TargetZone;
import com.archery.regulation.TournamentDefinition;

/** Register the performance of an Archer in a tournament.
 */
public class Scorecard extends ScoringBlock<RoundScore> {
  private ArcherRegistration archerRegistration;

  public Scorecard(final TournamentDefinition theDefinition,
      final ArcherRegistration theRegistration) {
    super();

    Validate.notNull(theDefinition, "The TournamentDefinition cannot be null");
    Validate.notNull(theRegistration, "ArcherRegistration cannot be null");
    archerRegistration = theRegistration;
    initializeRounds(theDefinition.getRounds());
  }

  private void initializeRounds(final List<RoundDefinition> rounds) {
    rounds.forEach(r -> addScoring(new RoundScore(r)));
  }

  public ArcherRegistration getArcherRegistration() {
    return archerRegistration;
  }

  @Override
  public boolean isCompleted() {
    return getScorings().stream()
        .noneMatch(RoundScore::isOpen);
  }

  public void score(final TargetZone... hits) {
    score(Arrays.asList(hits));
  }

  @Override
  public void score(final List<TargetZone> hits) {
    RoundScore round = getOpenScorecard();
    round.score(hits);
  }

  private RoundScore getOpenScorecard() {
    List<RoundScore> rounds = getScorings();
    return rounds.stream()
        .filter(RoundScore::isOpen)
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Scorecard is closed."));
  }

  public boolean is(final ShootingStyle style) {
    return archerRegistration.isStyle(style);
  }

  public boolean is(final ShootingDivision division) {
    return archerRegistration.isDivision(division);
  }

  String print() {
    StringBuilder builder = new StringBuilder();
    builder.append(archerRegistration.print()).append("\n");
    List<RoundScore> rounds = getScorings();
    for (int i = 0; i < rounds.size(); i++)  {
      RoundScore round = rounds.get(i);
      String scoreType = round.isCompleted() ? "FINAL" : "PARTIAL";
      builder.append("ROUND: ").append(i + 1);
      builder.append("  ").append(scoreType).append(" SCORE: ")
          .append(round.getScore().print());
      builder.append("\n").append(round.print()).append("\n");
    }

    String scoreType = isCompleted() ? "FINAL" : "PARTIAL";
    builder.append("TOURNAMENT ")
        .append(scoreType)
        .append(" SCORE: ").append(getScore().print());
    return builder.toString();
  }
}
