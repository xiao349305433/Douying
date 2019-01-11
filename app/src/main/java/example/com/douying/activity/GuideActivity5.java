package example.com.douying.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import butterknife.OnClick;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import example.com.douying.activitys.HomeActivityS;
import qiu.niorgai.StatusBarCompat;

public class GuideActivity5 extends BaseActivity {
    private SharedPreferences mPerferences;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mPerferences = PreferenceManager.getDefaultSharedPreferences(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_guide5;
    }

    @OnClick({R.id.guide5_over})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.guide5_over:

                SharedPreferences.Editor editor = mPerferences.edit();
                editor.putBoolean("IsShowGuide", true);//是否有引导
                editor.apply();
                startActivity(new Intent(this, HomeActivityS.class));
                break;
        }

    }
}
