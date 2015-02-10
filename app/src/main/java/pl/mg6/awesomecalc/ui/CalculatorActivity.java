package pl.mg6.awesomecalc.ui;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.mg6.awesomecalc.R;

public final class CalculatorActivity extends BaseActivity {

    @InjectView(R.id.calculator_current_state)
    TextView currentState;

    private String leftValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.calculator_zero)
    void onZeroClick() {
        currentState.append("0");
    }

    @OnClick(R.id.calculator_one)
    void onOneClick() {
        currentState.append("1");
    }

    @OnClick(R.id.calculator_add)
    void onAddClick() {
        leftValue = currentState.getText().toString();
        currentState.setText(null);
    }

    @OnClick(R.id.calculator_result)
    void onResultClick() {
        String rightValue = currentState.getText().toString();
        showResult(leftValue, rightValue);
        leftValue = null;
        currentState.setText(null);
    }

    private void showResult(String leftValue, String rightValue) {
        ResultActivity.intent(this)
                .leftValue(leftValue)
                .rightValue(rightValue)
                .start();
    }
}
