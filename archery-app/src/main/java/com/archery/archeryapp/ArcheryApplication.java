/* vim: set et ts=2 sw=2 cindent fo=qroca: */

package com.archery.archeryapp;

import org.springframework.context.annotation.Configuration;

import com.k2.core.Application;

import com.archery.Community;
import com.archery.Tournament;

/** The Archeryapplication.
 *
 * This application includes the Archery module.
 */
@Configuration
public class ArcheryApplication extends Application {

  /** Constructor, creates a ArcheryApplication and registers the
   * Archery module.
   */
  public ArcheryApplication() {
    super(new Community(), new Tournament());
  }
}

