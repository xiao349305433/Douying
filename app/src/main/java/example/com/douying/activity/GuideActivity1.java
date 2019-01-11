package example.com.douying.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.RippleBackground;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import qiu.niorgai.StatusBarCompat;

public class GuideActivity1 extends BaseActivity {
    @BindView(R.id.content)
    RippleBackground content;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.gg));
        content.startRippleAnimation();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_guide1;
    }
    @OnClick({R.id.guide1_img})
    public void test(View view){
        switch (view.getId()){
            case R.id.guide1_img:
                startActivity(new Intent(this,GuideActivity2.class));
                break;
        }

    }

}
