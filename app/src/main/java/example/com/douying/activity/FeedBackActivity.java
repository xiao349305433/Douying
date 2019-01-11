package example.com.douying.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.AppLoading;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.utils.ToastUtil;
import qiu.niorgai.StatusBarCompat;

public class FeedBackActivity extends BaseActivity {
    @BindView(R.id.feedback_edt)
    EditText feedback_edt;
    @BindView(R.id.feedback_sure)
    TextView feedback_sure;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_feedback;
    }

    @OnClick({R.id.feedback_sure})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.feedback_sure:
                send( feedback_edt.getText().toString());
                break;
            default:
                break;
        }
    }

    private void send(String str){
        AppLoading.show(this);
        HttpParams httpParams=new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("content",str);
        mainHttp.HttpPost(this,"Articles/getAdvice",httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM=new Gson().fromJson(response.body(),BaseM.class);
                switch (baseM.getCode()){
                    default:
                        ToastUtil.show(FeedBackActivity.this,baseM.getInfo());
                        break;
                }
                AppLoading.close();
            }
        });
    }


}
