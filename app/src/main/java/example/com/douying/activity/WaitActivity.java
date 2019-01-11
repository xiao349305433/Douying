package example.com.douying.activity;

import android.os.Bundle;

import butterknife.BindView;
import douying.example.com.mylibrary.view.SineView;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import qiu.niorgai.StatusBarCompat;

public class WaitActivity extends BaseActivity {
    @BindView(R.id.wait_sineview1)
    SineView sineView1;
    @BindView(R.id.wait_sineview2)
    SineView sineView2;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
        sineView1.startWave();
        sineView2.startWave();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_wait;
    }
}
