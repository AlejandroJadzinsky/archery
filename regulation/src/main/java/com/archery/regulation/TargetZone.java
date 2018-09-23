package com.archery.regulation;

/** The available Zones that can be used to define a Target.
 *
 * These are names used to identify the zone but not necessarily represents its
 * value in points. Although it usually do,
 */
public enum TargetZone {
  /** A MISS represents a no zone in the target. */
  MISS,
  /** Zone ONE. */
  ONE,
  /** Zone TWO. */
  TWO,
  /** Zone THREE. */
  THREE,
  /** Zone FOUR. */
  FOUR,
  /** Zone FIVE. */
  FIVE,
  /** Zone SIX. */
  SIX,
  /** Zone SEVEN. */
  SEVEN,
  /** Zone EIGHT. */
  EIGHT,
  /** Zone NINE. */
  NINE,
  /** Zone TEN. */
  TEN,
  /** Zone CROSS. */
  CROSS,
  /** Zone FLY. */
  FLY;

  /** Expose a short representation to be used in a scorecard.
   *
   * @return a String of one character, never null nor empty.
   */
  public String toString() {
    switch(this) {
      case MISS: return "M";
      case ONE: return "1";
      case TWO: return "2";
      case THREE: return "3";
      case FOUR: return "4";
      case FIVE: return "5";
      case SIX: return "6";
      case SEVEN: return "7";
      case EIGHT: return "8";
      case NINE: return "9";
      case TEN: return "10";
      case CROSS: return "X";
      case FLY: return "F";
      default: throw new RuntimeException("This must never happen");
    }
  }

  /** Determines if this instance is considered a bonus or not.
   * Bonus are used to tiebreak scores when they have the same points.
   *
   * @return true if this instnace is a bonus, otherwise false.
   */
  public boolean isBonus() {
    switch(this) {
      case CROSS:
      case FLY: return true;
      default: return false;
    }
  }

  /** Determines if this instance is a MISS or not.
   * Misses are used to tiebreak scores when they have the same points.
   *
   * @return true if this instance is a MISS, otherwise false.
   */
  public boolean isMiss() {
    return this.equals(MISS);
  }
}
