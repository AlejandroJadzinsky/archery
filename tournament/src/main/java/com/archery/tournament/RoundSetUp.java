package com.archery.tournament;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.Validate;

import com.archery.community.Archer;

/** Prepares a Round execution.
 */
public class RoundSetUp {
  private Map<ShootingPosition, Patrol> patrolsOrder;

  /** Creates a new {@link RoundSetUp} instance with a {@link Patrol} for each
   * {@link ShootingPosition}. The number of archers in each patrol is divided
   * evenly according to the number of shooting positions.
   *
   * @param positions the number of {@link ShootingPosition}s in this round,
   * must be greater than 0.
   * @param archers the set of registered {@link Archer}s, cannot be null nor
   * empty.
   */
  public RoundSetUp(final int positions, final Set<Archer> archers) {
    Validate.isTrue(positions > 0, "No shooting positions");

    patrolsOrder = new HashMap<>(positions);

    int defaultSize = archers.size() / positions;
    int remainder = archers.size() % positions;

    int shootingPosition = 1;
    Iterator<Archer> iterator = archers.iterator();
    while (iterator.hasNext()) {
      Set<Archer> group = new HashSet<>();
      for (int i = 0; i < defaultSize; i++) {
        group.add(iterator.next());
      }
      if (remainder > 0) {
        group.add(iterator.next());
        remainder--;
      }
      patrolsOrder.put(new ShootingPosition(shootingPosition++),
          new Patrol(group));
    }
  }

  /** Expose the setup configuration so far.
   * TODO (2018-09-21) only used in test so far
   *
   * @return a Map with configured {@link Patrol patrols} by
   * {@link ShootingPosition}. Never null.
   */
  public Map<ShootingPosition, Patrol> getPatrolsOrder() {
    return Collections.unmodifiableMap(patrolsOrder);
  }

  /** Moves an {@link Archer} from one {@link Patrol} to other indicating the
   *  {@link Patrol patrol's} {@link ShootingPosition}.
   *
   * @param archer the archer to move, cannot be null and must be in origin
   * {@link Patrol}.
   * @param from the origin {@link ShootingPosition}, cannot be null.
   * @param to the destination {@link ShootingPosition}, cannot be null.
   */
  public void moveArcher(final Archer archer, final ShootingPosition from,
      final ShootingPosition to) {
    Validate.notNull(archer, "Cannot move a null archer");
    Validate.notNull(from, "Origin patrol cannot be null");
    Validate.notNull(to, "Destination patrol cannot be null");

    Patrol fromPatrol = patrolsOrder.get(from);
    Validate.notNull(fromPatrol, "Origin ShootingPosition " + from.logInfo()
        + " does not exist");
    Patrol toPatrol = patrolsOrder.get(to);
    Validate.notNull(fromPatrol, "Destination ShootingPosition "
        + to.logInfo() + " does not exist");

    if (fromPatrol.removeArcher(archer) == null) {
      throw new RuntimeException(String.format("Archer %s is not in patrol %s",
          archer.logInfo(), from.logInfo())
      );
    }
    toPatrol.addArcher(archer);
  }
}
