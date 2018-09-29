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

  public Patrol(final Set<Archer> archers) {
    Validate.notEmpty(archers, "The archers set is null or empty.");

    group = archers;
  }

  public Archer removeArcher(final Archer archer) {
    if (group.remove(archer)) {
      return archer;
    }

    return null;
  }

  public void addArcher(final Archer archer) {
    Validate.notNull(archer, "Archer is null");
    group.add(archer);
  }

  public Set<Archer> getGroup() {
    return Collections.unmodifiableSet(group);
  }
}
