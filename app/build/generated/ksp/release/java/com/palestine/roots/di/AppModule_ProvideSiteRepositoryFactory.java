package com.palestine.roots.di;

import com.palestine.roots.data.local.dao.SiteDao;
import com.palestine.roots.domain.repository.SiteRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class AppModule_ProvideSiteRepositoryFactory implements Factory<SiteRepository> {
  private final Provider<SiteDao> siteDaoProvider;

  public AppModule_ProvideSiteRepositoryFactory(Provider<SiteDao> siteDaoProvider) {
    this.siteDaoProvider = siteDaoProvider;
  }

  @Override
  public SiteRepository get() {
    return provideSiteRepository(siteDaoProvider.get());
  }

  public static AppModule_ProvideSiteRepositoryFactory create(Provider<SiteDao> siteDaoProvider) {
    return new AppModule_ProvideSiteRepositoryFactory(siteDaoProvider);
  }

  public static SiteRepository provideSiteRepository(SiteDao siteDao) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSiteRepository(siteDao));
  }
}
