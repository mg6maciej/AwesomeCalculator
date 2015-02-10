package pl.mg6.awesomecalc.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import pl.mg6.awesomecalc.AwesomeCalcApp;

public abstract class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AwesomeCalcApp.inject(this, this);
    }
}
