package pl.mg6.awesomecalc;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import pl.mg6.awesomecalc.dagger.Injector;

public final class AwesomeCalcApp extends Application {

    private Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();
        Crashlytics.start(this);
        injector = new Injector(this);
    }

    public static void inject(Context context, Object root) {
        getInjector(context).inject(root);
    }

    public static void setTestModules(Context context, Object... testModules) {
        getInjector(context).setTestModules(testModules);
    }

    private static Injector getInjector(Context context) {
        AwesomeCalcApp app = (AwesomeCalcApp) context.getApplicationContext();
        return app.injector;
    }
}
