package pl.mg6.awesomecalc.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import pl.mg6.awesomecalc.R;
import pl.mg6.awesomecalc.api.BinaryCalculatorService;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public final class ResultActivity extends BaseActivity {

    private static final String LEFT_VALUE = "left_value";
    private static final String RIGHT_VALUE = "right_value";
    private static final String RESULT = "result";

    @Inject
    BinaryCalculatorService binaryCalculatorService;
    private Subscription subscription;

    private String leftValue;
    private String rightValue;
    private String result;

    @InjectView(R.id.result_loading_progress)
    ProgressBar loadingProgress;
    @InjectView(R.id.result_text)
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        leftValue = getIntent().getStringExtra(LEFT_VALUE);
        rightValue = getIntent().getStringExtra(RIGHT_VALUE);
        if (savedInstanceState != null) {
            result = savedInstanceState.getString(RESULT);
        }
        ButterKnife.inject(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(RESULT, result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (result == null) {
            Observable<String> observable = binaryCalculatorService.add(leftValue, rightValue).observeOn(AndroidSchedulers.mainThread());
            subscription = observable.subscribe(this::displayResult, this::displayError);
        } else {
            displayResult(result);
        }
    }

    private void displayResult(String result) {
        this.result = result;
        displayText("Result: " + result);
    }

    private void displayError(Throwable error) {
        displayText(error.getMessage());
    }

    private void displayText(String text) {
        textResult.setText(text);
        textResult.setVisibility(View.VISIBLE);
        loadingProgress.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    public static IntentBuilder intent(Context context) {
        return new IntentBuilder(context);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    @Setter
    @Accessors(chain = true, fluent = true)
    public static final class IntentBuilder {

        private final Context context;
        private String leftValue;
        private String rightValue;

        public void start() {
            Intent intent = new Intent(context, ResultActivity.class);
            intent.putExtra(LEFT_VALUE, leftValue);
            intent.putExtra(RIGHT_VALUE, rightValue);
            context.startActivity(intent);
        }
    }
}
