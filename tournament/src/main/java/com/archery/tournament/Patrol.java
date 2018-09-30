package com.archery.tournament;

import java.util.Collections;
import java.util.Set;
import org.apache.commons.lang3.Validate;

import com.archery.community.Archer;

/** A group of {@link Archer archers} that shoot together and one of them is
 * designed as the scorer.
 */
public class Patrol {
  private Set<Archer> group;

  /** Creates a new {@link Patrol} instance.
   *
   * @param archers the Set of {@link Archer} instances that compose this
   * instance, cannot be null nor empty.
   */
  public Patrol(final Set<Archer> archers) {
    Validate.notEmpty(archers, "The archers set is null or empty.");

    group = archers;
  }

  /** Removes an {@link Archer} from this instance.
   *
   * @param archer the {@link Archer} instance to be removed.
   *
   * @return the same instance of the {@link Archer} removed, otherwise null.
   */
  public Archer removeArcher(final Archer archer) {
    if (group.remove(archer)) {
      return archer;
    }

    return null;
  }

  /** Adds an {@link Archer} to this instance.
   *
   * @param archer an {@link Archer} instance, cannot be null.
   */
  public void addArcher(final Archer archer) {
    Validate.notNull(archer, "Archer is null");
    group.add(archer);
  }

  /** Exposes the group af {@link Archer} that compose this instance.
   *
   * @return a Set of {@link Archer} instances, never null nor empty.
   */
  public Set<Archer> getGroup() {
    return Collections.unmodifiableSet(group);
  }
}
