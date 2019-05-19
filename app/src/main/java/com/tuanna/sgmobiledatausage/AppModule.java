package com.tuanna.sgmobiledatausage;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import com.tuanna.sgmobiledatausage.network.MobileUsageAPI;
import com.tuanna.sgmobiledatausage.network.RetrofitClient;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class AppModule {

    public static final String SETTINGS_PREFERENCES_SHARED_PREFS = "SettingsPreferencesSharedPrefs";

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application;
    }

    @Provides
    @Singleton
    Resources providesResources() {
        return application.getResources();
    }

    @Provides
    CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

//  @Provides
//  @Singleton
//  Retrofit providesRetrofit() {
//    return RetrofitClient.Companion.getClient();
//  }

    @Provides
    @Singleton
    MobileUsageAPI providesMobileUsageService() {
        return RetrofitClient.Companion.getClient().create(MobileUsageAPI.class);
    }

    @Provides
    @Named(SETTINGS_PREFERENCES_SHARED_PREFS)
    static SharedPreferences provideSettingsPreferencesSharedPreferences(Context context) {
        return context.getSharedPreferences(SETTINGS_PREFERENCES_SHARED_PREFS, Context.MODE_PRIVATE);
    }

}
