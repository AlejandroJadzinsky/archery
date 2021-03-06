package com.archery.tournament;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.Validate;

import com.archery.regulation.RoundDefinition;
import com.archery.regulation.ShootingDivision;
import com.archery.regulation.ShootingStyle;
import com.archery.regulation.TargetZone;
import com.archery.regulation.TournamentDefinition;

/** Register the performance of a {@link ShooterRegistration registered archer}
 * in a tournament.
 */
class Scorecard extends ScoringBlock<RoundScore> {
  private ShooterRegistration shooterRegistration;

  /** Creates a new {@link Scorecard} instance.
   *
   * @param theDefinition a {@link TournamentDefinition} instance, cannot be
   * null.
   * @param theRegistration a {@link ShooterRegistration} instance, cannot be
   * null.
   */
  Scorecard(final TournamentDefinition theDefinition,
      final ShooterRegistration theRegistration) {
    super();

    Validate.notNull(theDefinition, "The TournamentDefinition cannot be null");
    Validate.notNull(theRegistration, "ShooterRegistration cannot be null");
    shooterRegistration = theRegistration;
    initializeRounds(theDefinition.getRounds());
  }

  private void initializeRounds(final List<RoundDefinition> rounds) {
    rounds.forEach(r -> addScoring(new RoundScore(r)));
  }

  @Override
  public boolean isCompleted() {
    return getScorings().stream()
        .noneMatch(RoundScore::isOpen);
  }

  /** This is syntactic-sugar to simplify client usage. This method provides
   * exactly the same functionality as {@link #score(List)}.
   *
   * @param hits the {@link TargetZone} instances, cannot be null nor
   * empty.
   */
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

  /** Checks if this instance belongs to an archer registered in the given
   * {@link ShootingStyle}.
   *
   * @param style a {@link ShootingStyle} instance, cannot be null.
   *
   * @return true if this instance style is equal to the parameter,
   * otherwise false.
   */
  boolean is(final ShootingStyle style) {
    return shooterRegistration.isStyle(style);
  }

  /** Checks if this instance belongs to an archer registered in the given
   * {@link ShootingDivision}.
   *
   * @param division a {@link ShootingDivision} instance, cannot be null.
   *
   * @return true if this instance division is equal to the parameter,
   * otherwise false.
   */
  boolean is(final ShootingDivision division) {
    return shooterRegistration.isDivision(division);
  }

  /** Creates a Comparator instance based on the given flags.
   *
   * When both are true score has precedence over archer.
   * When both are false score is used by default.
   *
   * @param archer when true objects are compared by
   * {@link Shooter#compareTo(Shooter)} comparison.
   * @param score when true objects are compared by
   * {@link Score#compareTo(Score)} comparison
   *
   * @return a {@link Comparator} instance, never null.
   */
  static Comparator<Scorecard> getComparatorBy(final boolean archer,
      final boolean score) {
    return (o1, o2) -> {
      //score comparison is in reverse order, we want the higher scores first.
      int scoreComparision = o2.getScore().compareTo(o1.getScore());

      Shooter o1Shooter = o1.shooterRegistration.getShooter();
      Shooter o2Shooter = o2.shooterRegistration.getShooter();
      int archerComparision = o1Shooter.compareTo(o2Shooter);

      if (archer && score) {
        if (scoreComparision == 0) {
          return archerComparision;
        }
        return scoreComparision;
      }
      if (archer) {
        return archerComparision;
      }

      return scoreComparision;
    };
  }

  /** Just for debugging. Delete asap.
   * @return a String, never null nor empty.
   */
  String print() {
    StringBuilder builder = new StringBuilder();
    builder.append(shooterRegistration.print()).append("\n");
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
