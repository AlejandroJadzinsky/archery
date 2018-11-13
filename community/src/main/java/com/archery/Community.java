package com.archery;

import com.k2.core.Module;
import com.k2.core.ModuleContext;
import com.k2.core.Public;
import com.k2.core.Registrator;
import com.k2.hibernate.HibernateRegistry;
import com.k2.swagger.SwaggerRegistry;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.archery.community.Archer;
import com.archery.community.ArcherRepository;
import com.archery.community.ArcherService;
import com.archery.community.CommunityService;
import com.archery.community.api.CommunityApi;
import com.archery.community.api.CommunityApiController;
import com.archery.community.api.CommunityApiDelegate;
import com.archery.infranstructure.ErrorHandler;

/** The {@link Community} module.
 */
@Configuration
@Component("community")
@EnableTransactionManagement(proxyTargetClass = true)
@Module(shortName = "c")
public class Community implements Registrator {
  @Override
  public void addRegistrations(final ModuleContext moduleContext) {
    SwaggerRegistry swagger = moduleContext.get(SwaggerRegistry.class);
    swagger.registerIdl("/community/static/community.yaml");

    HibernateRegistry hibernate = moduleContext.get(HibernateRegistry.class);
    hibernate.registerPersistentClass(Archer.class);
  }

  /** Registers the {@link CommunityApi} controller.
   *
   * @param delegate a {@link CommunityApiDelegate} instance, cannot be null.
   *
   * @return a {@link CommunityApi} implementation, never null.
   */
  @Bean
  public CommunityApi communityApi(final CommunityApiDelegate delegate) {
    return new CommunityApiController(delegate);
  }

  /** Exposes the {@link CommunityApiDelegate} implementation.
   *
   * @param archerService an {@link ArcherService} instance, cannot be null.
   *
   * @return a {@link CommunityApiDelegate} implementation, never null.
   */
  @Bean @Public
  public CommunityService communityService(final ArcherService archerService) {
    return new CommunityService(archerService);
  }

  /** Creates the {@link ArcherService} instance.
   *
   * @param archerRepository the {@link ArcherRepository} instance, cannot be
   * null.
   *
   * @return an {@link ArcherService} instance, never null.
   */
  @Bean
  ArcherService archerService(final ArcherRepository archerRepository) {
    return new ArcherService(archerRepository);
  }

  /** Creates the {@link ArcherRepository} instance.
   *
   * @param sessionFactory a {@link SessionFactory} implementation, cannot be
   * null.
   *
   * @return an {@link ArcherRepository} instance, never null.
   */
  @Bean
  ArcherRepository archerRepository(final SessionFactory sessionFactory) {
    return new ArcherRepository(sessionFactory);
  }

  /** Creates the Application {@link ErrorHandler} instance.
   *
   * @return an {@link ErrorHandler} instance, never null.
   */
  @Bean
  ErrorHandler errorHandler() {
    return new ErrorHandler();
  }
}

