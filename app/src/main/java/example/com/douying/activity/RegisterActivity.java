package example.com.douying.activity;

import android.os.Bundle;

import example.com.douying.BaseActivity;
import example.com.douying.R;

public class RegisterActivity extends BaseActivity {
    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_register;
    }
}
