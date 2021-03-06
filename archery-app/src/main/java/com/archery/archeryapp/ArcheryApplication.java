package com.archery.archeryapp;

import org.springframework.context.annotation.Configuration;

import com.k2.core.Application;
import com.k2.swagger.Swagger;
import com.k2.hibernate.Hibernate;

import com.archery.community.CommunityRegistrator;
import com.archery.Regulation;
import com.archery.tournament.TournamentRegistrator;

/** The Archery application.
 *
 * This application includes the Archery module.
 */
@Configuration
public class ArcheryApplication extends Application {

  /** Constructor, creates a ArcheryApplication and registers the
   * Archery module.
   */
  public ArcheryApplication() {
    super(new Hibernate(), new CommunityRegistrator(), new Regulation(),
        new TournamentRegistrator(), new Swagger());
  }
}

