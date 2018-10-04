package com.archery.community;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.archery.community.api.CommunityApiDelegate;
import com.archery.community.api.model.ArcherDto;

/** The community module contract implementation.
 */
public class CommunityService implements CommunityApiDelegate {
  private static Logger log = LoggerFactory.getLogger(CommunityService.class);

  private ArcherService archerService;

  /** Creates a new {@link CommunityService} instance with mandatory parameters.
   *
   * @param theArcherService the {@link ArcherService} instance, cannot be null.
   */
  public CommunityService(final ArcherService theArcherService) {
    archerService = theArcherService;
  }

  @Override
  public ResponseEntity<Void> createArcher(final ArcherDto newArcher) {
    log.debug("Creating new archer {}", newArcher);

    archerService.createArcher(newArcher);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
