package pl.mg6.awesomecalc.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.mg6.awesomecalc.R;

public final class CalculatorActivity extends BaseActivity {

    @InjectView(R.id.calculator_current_state)
    TextView currentState;
    String firstValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.calculator_result)
    void onResultClick() {
        String secondValue = getTextAndClear();
        ResultActivity.intent(this)
                .leftValue(firstValue)
                .rightValue(secondValue)
                .start();
    }

    @OnClick(R.id.calculator_add)
    void onAddClick() {
        firstValue = getTextAndClear();
    }

    private String getTextAndClear() {
        String text = currentState.getText().toString();
        currentState.setText("");
        return text;
    }

    @OnClick(R.id.calculator_zero)
    void onZeroClick() {
        currentState.append("0");
    }

    @OnClick(R.id.calculator_one)
    void onOneClick() {
        currentState.append("1");
    }
}
