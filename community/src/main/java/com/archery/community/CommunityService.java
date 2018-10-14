package com.archery.community;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.archery.community.api.CommunityApiDelegate;
import com.archery.community.api.model.ArcherDto;

/** The Community module contract implementation.
 */
public class CommunityService implements CommunityApiDelegate {

  private ArcherService archerService;

  /** Creates a new {@link CommunityService} instance with mandatory parameters.
   *
   * @param theArcherService the {@link ArcherService} instance, cannot be null.
   */
  public CommunityService(final ArcherService theArcherService) {
    archerService = theArcherService;
  }

  @Override
  @Transactional
  public ResponseEntity<Void> registerArcher(final ArcherDto newArcher) {
    archerService.createArcher(newArcher);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
