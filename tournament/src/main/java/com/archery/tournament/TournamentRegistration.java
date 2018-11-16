package com.archery.tournament;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

import com.archery.regulation.ShootingDivision;
import com.archery.regulation.ShootingStyle;

/** A {@link TournamentRegistration} manges the archers registration to a
 * Tournament.
 *
 * Basically defines:
 *   - the quota available for archers
 *   - whether or not the registration is still open.
 *   - division where the archer competes.
 *   - style where the archer competes.
 */
class TournamentRegistration {
  private int quota;
  private Map<Shooter, ShooterRegistration> participants;
  //TODO registration may be closed just by the passing of time, may be with
  // a dueDate
  private boolean open;

  /** Creates a new {@link TournamentRegistration} instance.
   *
   * @param theQuota the number of participants allowed, must be greater than 0.
   */
  TournamentRegistration(final int theQuota) {
    Validate.isTrue(theQuota > 1,
        "The Tournament Archers limit must be grater than one");

    quota = theQuota;
    participants = new HashMap<>();
    open = true;
  }

  /** Register a {@link Shooter}.
   *
   * @param archer the archer to register, cannot be null.
   * @param style the style in which the archer wants to compete, cannot be
   * null.
   * @param division the division in which the archer wants to compete,
   * cannot be null.
   */
  void registerArcher(final Shooter archer, final ShootingStyle style,
      final ShootingDivision division) {
    validateRegistration();

    participants.put(archer, new ShooterRegistration(archer, style, division));
  }

  /** Checks if the given {@link Shooter} is already registered.
   *
   * @param archer an {@link Shooter} instance, cannot be null.
   *
   * @return true if the given {@link Shooter} is registered, otherwise false.
   */
  boolean isArcherRegistered(final Shooter archer) {
    return participants.get(archer) != null;
  }

  /** Unregister an archer.
   *
   * @param archer an {@link Shooter} instance, never null.
   */
  void unregisterArcher(final Shooter archer) {
    validateRegistration();

    participants.remove(archer);
  }

  /** Search for an archer registration in this instance.
   *
   * @param archer an {@link Shooter} instance.
   *
   * @return an {@link ShooterRegistration} instance or null if none is fund.
   */
  ShooterRegistration getRegistration(final Shooter archer) {
    return participants.get(archer);
  }

  /** Exposes the registered archers.
   *
   * @return a collection of {@link ShooterRegistration} instances, never null.
   */
  Collection<ShooterRegistration> getRegistrations() {
    return Collections.unmodifiableCollection(participants.values());
  }

  /** The contest registration is open until the first round is started.
   *
   * @return true if the registration is still open, otherwise false.
   */
  boolean isRegistrationOpen() {
    return open;
  }

  /** Close this instance, after this method invocation registration is no
   * longer possible.
   */
  void closeRegistration() {
    open = false;
  }

  private void validateRegistration() {
    if (!isRegistrationOpen()) {
      throw new RuntimeException("Registration is closed");
    }
    if (!isRoomAvailable()) {
      throw new RuntimeException("No more room for archers");
    }
  }

  private boolean isRoomAvailable() {
    return getRemainingPositions() > 0;
  }

  /** Returns the number of available positions.
   *
   * @return an int, always positive or zero.
   */
  int getRemainingPositions() {
    return quota - participants.size();
  }
}
