package example.com.douying.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import example.com.douying.fragment.Fragment1;
import example.com.douying.fragment.Fragment2;
import example.com.douying.fragment.Fragment3;
import example.com.douying.fragment.Fragment4;
import example.com.douying.fragment.Fragment5;
import example.com.douying.utils.PermissionChecker;
import qiu.niorgai.StatusBarCompat;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.home_menu)
    ImageView home_menu;
    @BindView(R.id.home_tv1)
    TextView tv1;
    @BindView(R.id.home_tv2)
    TextView tv2;
    @BindView(R.id.home_tv3)
    TextView tv3;
    @BindView(R.id.home_tv4)
    TextView tv4;
    @BindView(R.id.fl_container)
    FrameLayout fl_container;
    protected PermissionChecker permissionChecker;
    protected static final  String[] PERMISSIONS=new String[]{Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            Manifest.permission.ACCESS_NOTIFICATION_POLICY,
    };
    @Override
    public void afterCreate(Bundle savedInstanceState) {
            showFragment1();

        StatusBarCompat.translucentStatusBar(this);
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_home;
    }

    @OnClick({R.id.home_menu, R.id.home_tv1, R.id.home_tv2, R.id.home_tv3, R.id.home_tv4,R.id.home_tv5})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.home_menu:
                closeswticth();
                break;
            case R.id.home_tv1:
                closeswticth();
                showFragment1();
                break;
            case R.id.home_tv2:
                closeswticth();
                showFragment2();
                break;
            case R.id.home_tv3:
                closeswticth();
                showFragment3();
                break;
            case R.id.home_tv4:
                closeswticth();
                showFragment4();
                break;
            case R.id.home_tv5:
                closeswticth();
                showFragment5();
                break;
        }
    }
        //还原页面
    public void closeswticth(){
        fl_container.animate().translationY(0);
        fl_container.setPivotY(fl_container.getMeasuredHeight() / 2);
        fl_container.animate().scaleX(1f);
        fl_container.animate().scaleY(1f);
    }
        //缩小页面
    public void showswtich() {
        fl_container.animate().scaleX(0.7f);
        fl_container.animate().scaleY(0.7f);
        fl_container.setPivotY(fl_container.getMeasuredHeight() / 2);
        fl_container.animate().translationY(600);
    }

    private void showFragment1() {
        Fragment fragment = Fragment1.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_container, fragment, Fragment1.TAG);
        transaction.commit();
    }

    private void showFragment2() {
        Fragment fragment = Fragment2.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_container, fragment, Fragment2.TAG);
        transaction.commit();
    }

    private void showFragment3() {
        Fragment fragment = Fragment3.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_container, fragment, Fragment3.TAG);
        transaction.commit();
    }

    private void showFragment4() {
        Fragment fragment = Fragment4.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_container, fragment, Fragment4.TAG);
        transaction.commit();
    }

    private void showFragment5() {
        Fragment fragment = Fragment5.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_container, fragment, Fragment5.TAG);
        transaction.commit();
    }

    private void  setPermission(){
        permissionChecker = new PermissionChecker(this);
        permissionChecker.setTitle(getString(R.string.check_info_title));
        permissionChecker.setMessage(getString(R.string.check_info_message));
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            onPermission();
//            new MaterialDialog.Builder(this).title(R.string.require_acquisition)
//                    .content(R.string.default_always_message)
//                    .positiveText("下一步").onPositive((dialog, which) -> onPermission()).show();
        }
    }

    private void onPermission() {
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            permissionChecker.requestPermissions();
        }
    }
}
