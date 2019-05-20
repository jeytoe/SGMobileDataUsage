package com.tuanna.sgmobiledatausage;

import com.tuanna.sgmobiledatausage.database.DatabaseLibraryModule;
import com.tuanna.sgmobiledatausage.main.MainActivity;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainModule.class,
        DatabaseLibraryModule.class
})
public interface AppComponent {
    void inject(MyApplication sqApplication);
    void inject(MainActivity mainActivity);
}
