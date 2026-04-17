package com.palestine.roots.viewmodel;

import com.palestine.roots.data.local.PreferencesManager;
import com.palestine.roots.domain.repository.SiteRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<SiteRepository> repositoryProvider;

  private final Provider<PreferencesManager> preferencesManagerProvider;

  public HomeViewModel_Factory(Provider<SiteRepository> repositoryProvider,
      Provider<PreferencesManager> preferencesManagerProvider) {
    this.repositoryProvider = repositoryProvider;
    this.preferencesManagerProvider = preferencesManagerProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(repositoryProvider.get(), preferencesManagerProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<SiteRepository> repositoryProvider,
      Provider<PreferencesManager> preferencesManagerProvider) {
    return new HomeViewModel_Factory(repositoryProvider, preferencesManagerProvider);
  }

  public static HomeViewModel newInstance(SiteRepository repository,
      PreferencesManager preferencesManager) {
    return new HomeViewModel(repository, preferencesManager);
  }
}
