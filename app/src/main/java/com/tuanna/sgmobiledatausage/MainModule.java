package com.tuanna.sgmobiledatausage;

import com.tuanna.sgmobiledatausage.main.Contract;
import com.tuanna.sgmobiledatausage.main.MainActivity;
import com.tuanna.sgmobiledatausage.main.MainPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {

    @Binds
    abstract Contract.Presenter providesMainPresenter(
            MainPresenter presenter);

    @ContributesAndroidInjector
    abstract MainActivity mainActivityInjector();
}
