package com.archery.regulation;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.Validate;

/** An {@link Organization} represents institutions like a Club or a
 * Federation.
 *
 * {@link Organization Organizations} are responsible to define tournament
 * regulations.
 */
class Organization {
  private String name;
  private Set<TournamentDefinition> tournaments;

  /** Creates a new {@link Organization} instance.
   *
   * @param theName the {@link Organization} name, cannot be null nor empty.
   */
  Organization(final String theName) {
    Validate.notEmpty(theName, "Organization name cannot be null nor empty");

    name = theName;
    tournaments = new HashSet<>();
  }

  /** Add a new {@link TournamentDefinition}.
   *
   * @param tournament a {@link TournamentDefinition} instance, cannot be null.
   */
  void addTournament(final TournamentDefinition tournament) {
    tournaments.add(tournament);
  }
}
