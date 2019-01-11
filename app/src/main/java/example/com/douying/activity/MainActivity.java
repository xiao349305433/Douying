package example.com.douying.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseActivity;

import example.com.douying.R;
import qiu.niorgai.StatusBarCompat;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.btn_register)
    Button btn_register;
    private Boolean exit = false;
    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        if (!isTaskRoot()) {
            finish();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (exit)
//                    finish();
//                else
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, 1000);
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_register,R.id.btn_login})
    public void test(View view){
        switch (view.getId()){
            case R.id.btn_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.btn_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }
    @Override
    public void onBackPressed() {
        exit = true;
        super.onBackPressed();
    }
}
