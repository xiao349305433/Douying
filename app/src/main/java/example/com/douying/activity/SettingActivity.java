package example.com.douying.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import qiu.niorgai.StatusBarCompat;

public class SettingActivity extends BaseActivity {
    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.gg));
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_setting;
    }


}
