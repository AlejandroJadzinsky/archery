package com.archery.tournament;

import java.util.Collections;
import java.util.Set;
import org.apache.commons.lang3.Validate;

/** A group of {@link Shooter shooters} that shoot together and one of them is
 * designed as the scorer.
 */
class Patrol {
  private Set<Shooter> group;

  /** Creates a new {@link Patrol} instance.
   *
   * @param shooters the Set of {@link Shooter} instances that compose this
   * instance, cannot be null nor empty.
   */
  Patrol(final Set<Shooter> shooters) {
    Validate.notEmpty(shooters, "The shooters set is null or empty.");

    group = shooters;
  }

  /** Removes an {@link Shooter} from this instance.
   *
   * @param shooter the {@link Shooter} instance to be removed.
   *
   * @return the same instance of the {@link Shooter} removed, otherwise null.
   */
  Shooter removeShooter(final Shooter shooter) {
    if (group.remove(shooter)) {
      return shooter;
    }

    return null;
  }

  /** Adds an {@link Shooter} to this instance.
   *
   * @param shooter an {@link Shooter} instance, cannot be null.
   */
  void addShooter(final Shooter shooter) {
    Validate.notNull(shooter, "Shooter is null");
    group.add(shooter);
  }

  /** Exposes the group af {@link Shooter} that compose this instance.
   *
   * @return a Set of {@link Shooter} instances, never null nor empty.
   */
  Set<Shooter> getGroup() {
    return Collections.unmodifiableSet(group);
  }
}
