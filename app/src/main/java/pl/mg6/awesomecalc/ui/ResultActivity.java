package pl.mg6.awesomecalc.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import pl.mg6.awesomecalc.R;
import pl.mg6.awesomecalc.api.BinaryCalculatorService;
import pl.mg6.awesomecalc.fakeapi.FakeBinaryCalculatorService;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public final class ResultActivity extends BaseActivity {

    private static final String LEFT_VALUE = "left_value";
    private static final String RIGHT_VALUE = "right_value";

    @Inject
    BinaryCalculatorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        String leftValue = getIntent().getStringExtra(LEFT_VALUE);
        String rightValue = getIntent().getStringExtra(RIGHT_VALUE);
        service.add(leftValue, rightValue)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ResultActivity.this::setResult);
    }

    private void setResult(String result) {
        TextView resultText = (TextView) findViewById(R.id.result_text);
        resultText.setText("Result: " + result);
        ProgressBar bar = (ProgressBar) findViewById(R.id.result_loading_progress);
        bar.setVisibility(View.GONE);
    }

    public static IntentBuilder intent(Context context) {
        return new IntentBuilder(context);
    }

    public static final class IntentBuilder {

        private final Context context;
        private String leftValue;
        private String rightValue;

        IntentBuilder(Context context) {
            this.context = context;
        }

        public IntentBuilder leftValue(String leftValue) {
            this.leftValue = leftValue;
            return this;
        }

        public IntentBuilder rightValue(String rightValue) {
            this.rightValue = rightValue;
            return this;
        }

        public void start() {
            Intent intent = new Intent(context, ResultActivity.class);
            intent.putExtra(LEFT_VALUE, leftValue);
            intent.putExtra(RIGHT_VALUE, rightValue);
            context.startActivity(intent);
        }
    }
}
