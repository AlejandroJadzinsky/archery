package com.archery.community;

import org.apache.commons.lang3.Validate;

import com.archery.community.api.model.ArcherDto;

/** The Archer contract implementation.
 *  This service is meant to let CommunityService deal with archers. */
public class ArcherService {

  private final ArcherRepository archerRepository;

  /** Creates a new {@link ArcherService} instance with mandatory parameters.
   *
   * @param theArcherRepository an {@link ArcherRepository} instance, cannot
   * be null.
   */
  public ArcherService(final ArcherRepository theArcherRepository) {
    archerRepository = theArcherRepository;
  }

  /** Creates and persists a new {@link Archer} instance from a given
   * {@link ArcherDto}.
   *
   * @param archer an {@link ArcherDto} instance, cannot be null.
   */
  void createArcher(final ArcherDto archer) {
    Validate.notNull(archer, "The ArcherDto is null");

    Archer newArcher = new Archer(archer.getName(), archer.getEmail(),
        archer.getPass());
    archerRepository.add(newArcher);
  }
}
