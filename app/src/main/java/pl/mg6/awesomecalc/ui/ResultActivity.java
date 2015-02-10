package pl.mg6.awesomecalc.ui;

import android.content.Context;
import android.content.Intent;

public final class ResultActivity extends BaseActivity {

    private static final String LEFT_VALUE = "left_value";
    private static final String RIGHT_VALUE = "right_value";

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
