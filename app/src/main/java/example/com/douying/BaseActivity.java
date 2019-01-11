package example.com.douying;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import example.com.douying.http.MainHttp;

//import com.example.admin.tutu.MyApplication;
//import com.example.admin.tutu.UI.SwtichLoginActivity;
//import com.example.admin.tutu.http.MainHttp;
//
//import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {
    //    public static final String TAG = BaseFragment.class.getSimpleName();
    public String outputDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath();
    public MyApplication myApplication;
    public MainHttp mainHttp = new MainHttp();

    public abstract void afterCreate(Bundle savedInstanceState);
    public  View Nodata;

    abstract public int provideContentViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        myApplication = MyApplication.getInstance();
        ButterKnife.bind(this);
        setCustomDensity(this, myApplication);
        afterCreate(savedInstanceState);
        //   EventBus.getDefault().register(this);
//        if (!isNetworkAvailable()) {
//            showText("无法连接到网络,请检查网络设置！");
//        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }

    private static float sRoncompatDennsity;
    private static float sRoncompatScaledDensity;

    /**
     * 屏幕适配
     */
    private void setCustomDensity(@NonNull Activity activity, final @NonNull Application application) {

        //application
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sRoncompatDennsity == 0) {
            sRoncompatDennsity = appDisplayMetrics.density;
            sRoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sRoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        //计算宽为360dp 同理可以设置高为640dp的根据实际情况
        final float targetDensity = appDisplayMetrics.widthPixels / 360;
        final float targetScaledDensity = targetDensity * (sRoncompatScaledDensity / sRoncompatDennsity);
        final int targetDensityDpi = (int) (targetDensity * 160);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;
        appDisplayMetrics.scaledDensity = targetScaledDensity;

        //activity
        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();

        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
    }


    public void setNodata(RecyclerView recyclerView) {
        Nodata = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);

    }


//    public void showText(String text) {
//        if (text.equals("未登录")) {
//            new AlertDialog.Builder(this).setTitle("提示").setMessage("登录过期，请重新登录。").setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(BaseActivity.this).edit();
//                    editor.putBoolean("islogin", false);
//                    editor.apply();
//                    startActivity(new Intent(getApplicationContext(), SwtichLoginActivity.class));
//                    finish();
//                }
//            }).setCancelable(false).create().show();
//        } else {
//            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//        }
//    }

//    public static boolean isWifiConnected(Context context) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        if (wifiNetworkInfo.isConnected()) {
//            return true;
//        }
//        return false;
//    }

//    /**
//     * 判断当前网络是否可用
//     */
//    @SuppressWarnings("deprecation")
//    public boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//        if (connectivityManager == null) {
//            return false;
//        } else {
//            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
//            if (networkInfo != null && networkInfo.length > 0) {
//                for (NetworkInfo aNetworkInfo : networkInfo) {
//                    if (aNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }

    protected void getSetting() {
        new AlertDialog.Builder(this).setTitle("提示").setMessage("权限被拒绝，清除数据重新进入应用，再点击允许").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        }).setNegativeButton("取消", null).setCancelable(false).create().show();
    }

    public String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        int versionCode = 0;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static void setStatusBgColor(Activity activity, @ColorInt int color) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.setStatusBarColor(color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}