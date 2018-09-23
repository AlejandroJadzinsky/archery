package com.archery.archeryapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.core.Application;
import com.k2.core.K2Environment;

public class ArcheryApplicationTest {

  /** The class logger. */
  private final Logger log = LoggerFactory.getLogger(
      ArcheryApplicationTest.class);

  private Application application;

  private String baseUrl;

  @BeforeEach
  public void setUp() {
    log.trace("Entering setUp");
    application = new ArcheryApplication();
    application.run(new String[] {"--server.port=0"});

    K2Environment environment;
    environment = application.getBean("environment", K2Environment.class);
    String port = environment.getProperty("local.server.port");
    baseUrl = "http://localhost:" + port;

    log.trace("Leaving setUp");
  }

  @AfterEach
  public void tearDown() {
    application.stop();
  }

}

