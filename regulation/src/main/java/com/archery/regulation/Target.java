package com.archery.regulation;

import java.util.Map;
import org.apache.commons.lang3.Validate;

/** A basic {@link Target} defined only by its {@link TargetZone} and their
 * respective punctuations.
 */
public class Target {

  /** The score for each zone. */
  private Map<TargetZone, Integer> scoreZones;

  /** Creates a new {@link Target} instance.
   *
   * @param theScoreZones the {@link TargetZone} that define this target with
   * its configured points, cannot be null nor empty.
   */
  public Target(final Map<TargetZone, Integer> theScoreZones) {
    Validate.notEmpty(theScoreZones, "The score zones cannot be null or empty");

    scoreZones = theScoreZones;
  }

  /** Returns the number of points earned when the given {@link TargetZone}
   * is hit.
   *
   * @param hit the {@link TargetZone} to score, cannot be null.
   *
   * @return an Integer that represents the points earned, never null.
   */
  public Integer score(final TargetZone hit) {
    Validate.isTrue(scoreZones.keySet().contains(hit),
        "Not a valid zone in this target");

    return scoreZones.get(hit);
  }
}
