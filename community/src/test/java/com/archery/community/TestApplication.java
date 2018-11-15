package com.archery.community;

import org.apache.commons.lang3.Validate;
import org.springframework.context.annotation.Configuration;

import com.k2.core.Application;
import com.k2.core.K2Environment;
import com.k2.hibernate.Hibernate;
import com.k2.swagger.Swagger;

/** The Community module Test {@link Application}.
 */
@Configuration
public class TestApplication extends Application {

  private static TestApplication application;

  private boolean initialized = false;

  TestApplication() {
    super(new Hibernate(), new Swagger(), new CommunityRegistrator());
  }

  /** Runs the application, or return the already running one.
   *
   * It starts the application on a random port. To obtain the
   *
   * @return a running application, never returns null.
   */
  synchronized public static TestApplication start() {
    if (application == null) {
      application = new TestApplication();
      application.run(new String[]{"--server.port=0"});
    }
    return application;
  }

  /** Returns the base url to hit this application.
   *
   * This is necessary because the app starts in a random port.
   *
   * @return the base url, without the trailing '/' (http://localhost:<port>).
   * Never returns null.
   */
  public String getBaseUrl() {
    K2Environment environment;
    environment = application.getBean("environment", K2Environment.class);

    String port = environment.getProperty("local.server.port");
    return "http://localhost:" + port;
  }

  public synchronized void run(final String[] args) {
    Validate.isTrue(!initialized, "Cannot call run here");
    super.run(args);
    initialized = true;
  }

  @Override
  public synchronized void stop() {
    // Ignore all calls to stop, just in case.
  }

  public static void main(final String... args) {
    application = new TestApplication();
    application.run(args);
  }
}

