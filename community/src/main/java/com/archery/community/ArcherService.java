package com.archery.community;

import com.archery.community.api.model.ArcherDto;

/** The Archer contract implementation.
 *  This service is meant to let CommunityService deal with archers. */
public class ArcherService {

  /** Creates and persists a new {@link Archer} instance from a given
   * {@link ArcherDto}.
   *
   * @param archer an {@link ArcherDto} instance, cannot be null.
   */
  void createArcher(final ArcherDto archer) {
    Archer newArcher = new Archer(archer.getArcherName(),
        archer.getArcherEmail(), archer.getArcherPass());
  }
}
