package com.archery.community;

import org.apache.commons.lang3.Validate;

import com.archery.infranstructure.BusinessException;

/** The Archer contract implementation.
 *  This service is meant to let CommunityService deal with archers. */
class ArcherService {

  private final ArcherRepository archerRepository;

  /** Creates a new {@link ArcherService} instance with mandatory parameters.
   *
   * @param theArcherRepository an {@link ArcherRepository} instance, cannot
   * be null.
   */
  ArcherService(final ArcherRepository theArcherRepository) {
    archerRepository = theArcherRepository;
  }

  /** Creates and persists the given {@link Archer} instance.
   *
   * @param archer an {@link Archer} instance, cannot be null.
   */
  void createArcher(final Archer archer) {
    Validate.notNull(archer, "The ArcherDto is null");

    try {
      archerRepository.add(archer);
    } catch (Exception e) {
      throw new BusinessException("Cannot create Archer", e, archer,
          "ArcherService.createArcher");
    }
  }
}
