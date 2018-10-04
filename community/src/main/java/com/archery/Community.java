package com.archery;

import com.k2.core.Module;
import com.k2.core.ModuleContext;
import com.k2.core.Registrator;
import com.k2.swagger.SwaggerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.archery.community.ArcherService;
import com.archery.community.CommunityService;
import com.archery.community.api.CommunityApi;
import com.archery.community.api.CommunityApiController;
import com.archery.community.api.CommunityApiDelegate;

/** The {@link Community} module.
 */
@Component("community")
@Module(shortName = "c")
public class Community implements Registrator {
  @Override
  public void addRegistrations(final ModuleContext moduleContext) {
    SwaggerRegistry swagger = moduleContext.get(SwaggerRegistry.class);
    swagger.registerIdl("/community/static/community.yaml");
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
  @Bean
  public CommunityApiDelegate communityApiDelegate(
      final ArcherService archerService) {
    return new CommunityService(archerService);
  }

  /** Creates the {@link ArcherService} instance.
   *
   * @return an {@link ArcherService} instance, never null.
   */
  @Bean
  ArcherService archerService() {
    return new ArcherService();
  }
}

