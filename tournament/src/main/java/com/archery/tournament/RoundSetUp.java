package com.archery.tournament;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.Validate;

/** Prepares a Round execution.
 */
class RoundSetUp {
  private Map<ShootingPosition, Patrol> patrolsOrder;

  /** Creates a new {@link RoundSetUp} instance with a {@link Patrol} for each
   * {@link ShootingPosition}. The number of archers in each patrol is divided
   * evenly according to the number of shooting positions.
   *
   * @param positions the number of {@link ShootingPosition}s in this round,
   * must be greater than 0.
   * @param archers the set of registered {@link Shooter}s, cannot be null nor
   * empty.
   */
  RoundSetUp(final int positions, final Set<Shooter> archers) {
    Validate.isTrue(positions > 0, "No shooting positions");

    patrolsOrder = new HashMap<>(positions);

    int defaultSize = archers.size() / positions;
    int remainder = archers.size() % positions;

    int shootingPosition = 1;
    Iterator<Shooter> iterator = archers.iterator();
    while (iterator.hasNext()) {
      Set<Shooter> group = new HashSet<>();
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
  Map<ShootingPosition, Patrol> getPatrolsOrder() {
    return Collections.unmodifiableMap(patrolsOrder);
  }

  /** Moves an {@link Shooter} from one {@link Patrol} to other indicating the
   *  {@link Patrol patrol's} {@link ShootingPosition}.
   *
   * @param shooter the archer to move, cannot be null and must be in origin
   * {@link Patrol}.
   * @param from the origin {@link ShootingPosition}, cannot be null.
   * @param to the destination {@link ShootingPosition}, cannot be null.
   */
  void moveShooter(final Shooter shooter, final ShootingPosition from,
      final ShootingPosition to) {
    Validate.notNull(shooter, "Cannot move a null shooter");
    Validate.notNull(from, "Origin patrol cannot be null");
    Validate.notNull(to, "Destination patrol cannot be null");

    Patrol fromPatrol = patrolsOrder.get(from);
    Validate.notNull(fromPatrol, "Origin ShootingPosition " + from.logInfo()
        + " does not exist");
    Patrol toPatrol = patrolsOrder.get(to);
    Validate.notNull(fromPatrol, "Destination ShootingPosition "
        + to.logInfo() + " does not exist");

    if (fromPatrol.removeShooter(shooter) == null) {
      throw new RuntimeException(String.format("Shooter %s is not in patrol %s",
          shooter, from.logInfo())
      );
    }
    toPatrol.addShooter(shooter);
  }
}
