package example.com.douying.http;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

/**
 * Created by admin on 2018/9/11.
 */

public class MainHttp {
    public static final String HOST_STRING = "https://xch.lxx673.shop/Home/";
    public static final String RES_STRING="https://xch.lxx673.shop/Uploads/";

    public MainHttp() {
        super();
    }

    public PostRequest<String> HttpPost(Context context, String function, HttpParams params) {
        Log.e("Tag",HOST_STRING+function+params);
        return OkGo.<String>post(HOST_STRING+function)
                .tag(context)
                .headers("Content-Type", "application/json;charset:utf-8")
                .params(params);
    }

    public PostRequest<String> HttpPost2(Context context, String function, HttpParams params) {
        Log.e("Tag",function+params);
        return OkGo.<String>post(function)
                .tag(context)
                .headers("Content-Type", "application/json;charset:utf-16")
                .params(params)
                ;
    }

    public GetRequest<String> HttpGet(Context context, String function, HttpParams params) {
        Log.e("Tag",HOST_STRING+function+params);
        return OkGo.<String>get(HOST_STRING+function)
                .tag(context)
                .params(params);
    }
}
