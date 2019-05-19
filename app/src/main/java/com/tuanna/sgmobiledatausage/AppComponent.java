package com.tuanna.sgmobiledatausage;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
    AndroidInjectionModule.class,
    AppModule.class
})
public interface AppComponent {
  void inject(MyApplication sqApplication);
}
