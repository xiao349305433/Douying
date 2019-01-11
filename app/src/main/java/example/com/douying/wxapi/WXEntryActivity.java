package example.com.douying.wxapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by admin on 2018/3/22.
 */

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI wxApi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wxApi = WXAPIFactory.createWXAPI(this,Constant.WECHAT_APPID,true);
        wxApi.registerApp(Constant.WECHAT_APPID);
        wxApi.handleIntent(getIntent(),this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.i("ansen","WXEntryActivity onReq:"+baseReq);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if(baseResp.getType()== ConstantsAPI.COMMAND_SENDAUTH){//登陆
            Log.i("ansen", "微信登录操作.....");
            SendAuth.Resp authResp = (SendAuth.Resp) baseResp;
            WeiXin weiXin=new WeiXin(1,baseResp.errCode,authResp.code);
            EventBus.getDefault().post(weiXin);
        }
        finish();
    }
}
