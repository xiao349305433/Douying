package example.com.douying.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import example.com.douying.BaseActivity;
import example.com.douying.R;
import example.com.douying.fragment.CircleFragment;
import example.com.douying.fragment.Fragment3;
import example.com.douying.fragment.Fragment4;
import example.com.douying.fragment.PointsMallFragment;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2018/11/9.
 */

public class MoreActivity extends BaseActivity {
    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
           switch (getIntent().getStringExtra("Type")){
               case "quan":
                   showQuan();
                   break;
               case "message":
                   showMessage();
                   break;
               case "circle":
                   showCircle();
                   break;
               case "point":
                   showPoint();
                   break;
           }
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_more;
    }

    private void showMessage() {
        Fragment fragment = Fragment4.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.more_container, fragment, Fragment4.TAG);
        transaction.commit();
    }
    private void showQuan() {
        Fragment fragment = Fragment3.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.more_container, fragment, Fragment3.TAG);
        transaction.commit();
    }
    private void showCircle() {
        Fragment fragment = CircleFragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.more_container, fragment, CircleFragment.TAG);
        transaction.commit();
    }

    private void showPoint() {
        Fragment fragment = PointsMallFragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.more_container, fragment, PointsMallFragment.TAG);
        transaction.commit();
    }


}
