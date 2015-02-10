package pl.mg6.awesomecalc.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import pl.mg6.awesomecalc.R;
import pl.mg6.awesomecalc.api.BinaryCalculatorService;
import pl.mg6.awesomecalc.fakeapi.FakeBinaryCalculatorService;
import rx.android.schedulers.AndroidSchedulers;

public final class ResultActivity extends BaseActivity {

    private static final String LEFT_VALUE = "left_value";
    private static final String RIGHT_VALUE = "right_value";

    @InjectView(R.id.result_text)
    TextView resultText;
    @InjectView(R.id.result_loading_progress)
    ProgressBar loadingProgress;

    @Inject
    BinaryCalculatorService binaryCalculatorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String firstNumber = getIntent().getStringExtra(LEFT_VALUE);
        String secondNumber = getIntent().getStringExtra(RIGHT_VALUE);
        binaryCalculatorService.add(firstNumber, secondNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showResult);
    }

    private void showResult(String result) {
        loadingProgress.setVisibility(View.GONE);
        resultText.setText("Result: " + result);
        resultText.setVisibility(View.VISIBLE);
    }

    public static IntentBuilder intent(Context context) {
        return new IntentBuilder(context);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    @Setter
    @Accessors(fluent = true, chain = true)
    public static final class IntentBuilder {

        private final Context context;
        private String leftValue;
        private String rightValue;

        public void start() {
            if (leftValue == null) {

            }
            Intent intent = new Intent(context, ResultActivity.class);
            intent.putExtra(LEFT_VALUE, leftValue);
            intent.putExtra(RIGHT_VALUE, rightValue);
            context.startActivity(intent);
        }
    }
}
