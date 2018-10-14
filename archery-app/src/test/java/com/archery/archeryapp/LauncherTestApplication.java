package com.archery.archeryapp;

import com.k2.core.Application;
import com.k2.hibernate.Hibernate;
import com.k2.swagger.Swagger;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.archery.Community;
import com.archery.Regulation;
import com.archery.Tournament;

/** App launcher for local test usage.
 */
@Configuration
public class LauncherTestApplication extends Application {

  public LauncherTestApplication() {
    super(new Hibernate(), new Community(), new Regulation(), new Tournament(),
        new Swagger());
  }

  /** Launches the sample application.
   *
   * @param args the command line arguments.
   */
  public static void main(final String[] args) {
    new LauncherTestApplication().run(args);
  }

  /** The h2 console, accessible from <base-path>/console.
   *
   * @return the console, never null.
   */
  @Bean
  public ServletRegistrationBean h2servletRegistration() {
    ServletRegistrationBean registrationBean;
    registrationBean = new ServletRegistrationBean(new WebServlet());
    registrationBean.addUrlMappings("/console/*");
    return registrationBean;
  }
}
