package example.com.douying.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import butterknife.OnClick;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.activity.FeedBackActivity;
import example.com.douying.activity.LoginActivity;
import example.com.douying.activity.ModelActivity;
import qiu.niorgai.StatusBarCompat;

public class SettingActivityS extends BaseActivity {
    private SharedPreferences mPerferences;
    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
        mPerferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_settings;
    }

    @OnClick({R.id.settings_feedback, R.id.settings_model,R.id.settings_exit,R.id.settings_material})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.settings_feedback:
                startActivity(new Intent(this, FeedBackActivity.class));
                break;
            case R.id.settings_model:
                startActivity(new Intent(this, ModelActivity.class));
                break;
            case R.id.settings_exit:
                SharedPreferences.Editor editor = mPerferences.edit();
                editor.putBoolean("islogin", false);
                editor.apply();
                BaseData.IsLogin=false;
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.settings_material:
                startActivity(new Intent(this,MaterialActivity.class));
                break;
        }
    }
}
