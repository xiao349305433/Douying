package example.com.douying;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;

import com.bumptech.glide.util.Util;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.Map;


import cn.jpush.android.api.JPushInterface;
import douying.example.com.mylibrary.view.utils.Utils;


/**
 * Created by admin on 2017/5/31.
 */

public class MyApplication extends SuperApplication {
    private static MyApplication instance;
//    private HttpProxyCacheServer proxy;
    private int appCount = 0;

    @Nullable
    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }

    @Override
    protected void onAppExit() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);
//        ZXingLibrary.initDisplayOpinion(this);
     //   inRouter();



        //极光
        setJPush();
        //音乐播放service
//        setMusic();//todo 临时注释
        //activity是否在前台的监听
        setStep();
        //添加emoji表情
        EmojiCompat.Config config = new BundledEmojiCompatConfig(this);
        EmojiCompat.init(config);
        setBugly();
    }

//    private void setMusic() {
//        if (!BaseUtil.getCurProcessName(this).contains(":musicLibrary")) {
//            MusicManager.get()
//                    .setContext(this)
//                    .init();
//        }
//    }

    private void setJPush() {
     //   JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());

    }


    private void setBugly(){
        Context context = getApplicationContext();
// 获取当前包名
        String packageName = context.getPackageName();
// 获取当前进程名
        String processName = Utils.getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
// 初始化Bugly
        CrashReport.initCrashReport(context, "b237a5c4f7", true, strategy);
    }

//    public static HttpProxyCacheServer getProxy(Context context) {
//        MyApplication app = (MyApplication) context.getApplicationContext();
//        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
//    }
//
//    private HttpProxyCacheServer newProxy() {
//        return new HttpProxyCacheServer(this);
//    }
//



    private void setStep() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                appCount++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                appCount--;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    /**
     * app是否在前台
     *
     * @return true前台，false后台
     */
    public boolean isForeground() {
        return appCount > 0;
    }


}
