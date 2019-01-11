package example.com.douying.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.activitys.HomeActivityS;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.CodeM;
import example.com.douying.model.GetLoginM;
import example.com.douying.utils.ToastUtil;
import example.com.douying.view.CodeTextView;
import example.com.douying.wxapi.Constant;
import example.com.douying.wxapi.WeiXin;
import example.com.douying.wxapi.WeiXinInfo;
import example.com.douying.wxapi.WeiXinToken;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_et_username)
    EditText et_username;
    @BindView(R.id.login_et_code)
    EditText ed_code;
    @BindView(R.id.login_send)
    CodeTextView login_send;
    private SharedPreferences mPerferences;
    private IWXAPI wxAPI;
    WeiXinInfo weiXinInfo;
    private String mCode = "";

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);//注册
        mPerferences = PreferenceManager.getDefaultSharedPreferences(this);
        setWxAPI();
        boolean mIslogin = mPerferences.getBoolean("islogin", false);
        if (mIslogin) {
            tologin(true, 1);
        }
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.login_login, R.id.login_send, R.id.login_weixin})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.login_login:
                String login_code = ed_code.getText().toString();
                if (login_code.isEmpty()) {
                    ToastUtil.show(this, "请输入验证码");
                } else if (mCode.equals(login_code)) {
                    tologin(false, 1);
                }
                break;
            case R.id.login_send:
                String phone = et_username.getText().toString();
                if (phone.isEmpty()) {
                    ToastUtil.show(this, "请输入手机号");
                } else {
                    sendcode();
                }
                break;
            case R.id.login_weixin:
                login_wx();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WeiXin weiXin) {
        if (weiXin.getType() == 1) {//登录
            getAccessToken(weiXin.getCode());
        }
    }


    private void tologin(boolean isLogin, int type) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("type", type);
        httpParams.put("device","android");

        switch (type) {
            case 1:
                if (isLogin) {
                    httpParams.put("phone", mPerferences.getString("username", ""));
                } else {
                    httpParams.put("phone", et_username.getText().toString());
                }
                break;
            case 2:
                httpParams.put("weikey", "");
                httpParams.put("pic", weiXinInfo.getHeadimgurl());
                httpParams.put("truename", weiXinInfo.getNickname());
                break;

        }
        mainHttp.HttpPost(this, "Index/getLogin", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                GetLoginM getLoginM = new Gson().fromJson(response.body(), GetLoginM.class);
                switch (getLoginM.getCode()) {
                    case 206:
                        SharedPreferences.Editor editor = mPerferences.edit();
                        editor.putBoolean("islogin", true);
                        editor.putString("username", getLoginM.getData().getName());//保存用户名
                        editor.putString("uid", getLoginM.getData().getId());
                        editor.putString("turename", getLoginM.getData().getTruename());
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, HomeActivityS.class);
                        intent.putExtra("userdata", getLoginM.getData());
                        startActivity(intent);
                        finish();
                        break;
                    default:
                        ToastUtil.show(LoginActivity.this, getLoginM.getInfo());
                        break;
                }

            }
        });

    }


    private void sendcode() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("PhoneNumbers", et_username.getText().toString());
        httpParams.put("type", 1);
        mainHttp.HttpGet(this, "Index/sendMsg", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                String str = response.body();
                CodeM codeM = new Gson().fromJson(response.body(), CodeM.class);
                switch (codeM.getCode()) {
                    case 200:
                        login_send.start();
                        mCode = codeM.getData().getSendCode();
                        break;
                    default:
                        ToastUtil.show(LoginActivity.this, codeM.getInfo());
                        break;
                }
            }
        });
    }


    private void setWxAPI() {
        wxAPI = WXAPIFactory.createWXAPI(this, Constant.WECHAT_APPID, true);
        wxAPI.registerApp(Constant.WECHAT_APPID);
    }

    public void login_wx() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = String.valueOf(System.currentTimeMillis());
        wxAPI.sendReq(req);
    }

    public void getAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        HttpParams httpParams = new HttpParams();
        httpParams.put("appid", Constant.WECHAT_APPID);
        httpParams.put("secret", Constant.WECHAT_SECRET);
        httpParams.put("code", code);
        httpParams.put("grant_type", "authorization_code");
        mainHttp.HttpPost2(this, url, httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                WeiXinToken weiXinToken = new Gson().fromJson(response.body(), WeiXinToken.class);
                switch (weiXinToken.getErrcode()) {
                    case 0:
                        getWeiXinUserInfo(weiXinToken);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
    }

    public void getWeiXinUserInfo(WeiXinToken weiXinToken) {
        String url = "https://api.weixin.qq.com/sns/userinfo";
        HttpParams httpParams = new HttpParams();
        httpParams.put("access_token", weiXinToken.getAccess_token());
        httpParams.put("openid", weiXinToken.getOpenid());
        mainHttp.HttpPost2(this, url, httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e("Tag", response.body());
                weiXinInfo = new Gson().fromJson(response.body(), WeiXinInfo.class);
                tologin(false, 2);
            }
        });
    }
}
