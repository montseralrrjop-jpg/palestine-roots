package com.palestine.roots.di;

import com.palestine.roots.data.local.dao.SiteDao;
import com.palestine.roots.data.local.db.PalestineDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AppModule_ProvideSiteDaoFactory implements Factory<SiteDao> {
  private final Provider<PalestineDatabase> databaseProvider;

  public AppModule_ProvideSiteDaoFactory(Provider<PalestineDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SiteDao get() {
    return provideSiteDao(databaseProvider.get());
  }

  public static AppModule_ProvideSiteDaoFactory create(
      Provider<PalestineDatabase> databaseProvider) {
    return new AppModule_ProvideSiteDaoFactory(databaseProvider);
  }

  public static SiteDao provideSiteDao(PalestineDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSiteDao(database));
  }
}
