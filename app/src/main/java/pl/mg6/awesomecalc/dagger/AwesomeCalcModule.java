package pl.mg6.awesomecalc.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.mg6.awesomecalc.BuildConfig;
import pl.mg6.awesomecalc.api.BinaryCalculatorService;
import pl.mg6.awesomecalc.ui.CalculatorActivity;
import pl.mg6.awesomecalc.ui.ResultActivity;
import retrofit.RestAdapter;

@Module(
        injects = {
                CalculatorActivity.class,
                ResultActivity.class,
        },
        library = true
)
@SuppressWarnings("unused")
public final class AwesomeCalcModule {

    public static final String ENDPOINT = "http://private-b44f1-awesomecalculator.apiary-mock.com";

    private final Context context;

    public AwesomeCalcModule(Context context) {
        this.context = context;
    }

    private RestAdapter.LogLevel getLogLevel() {
        return BuildConfig.DEBUG
                ? RestAdapter.LogLevel.FULL
                : RestAdapter.LogLevel.NONE;
    }

    @Provides
    @Singleton
    public BinaryCalculatorService provideBinaryCalculatorService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(getLogLevel())
                .build();
        return restAdapter.create(BinaryCalculatorService.class);
    }
}
