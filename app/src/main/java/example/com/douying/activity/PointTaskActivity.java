package example.com.douying.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.adapter.PointTaskAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetTaskM;
import example.com.douying.utils.ToastUtil;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2018/12/5.
 */

public class PointTaskActivity extends BaseActivity {
    @BindView(R.id.pointtask_rv)
    RecyclerView pointtask_rv;
    @BindView(R.id.pointtask_signIn_tv)
    TextView pointtask_signIn_tv;
    PointTaskAdapter pointTaskAdapter;
    boolean IsSignIn = false;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        getdata();

    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_pointtask;
    }

    @OnClick({R.id.pointtask_signIn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pointtask_signIn:
                if (!BaseData.IsSign) {
                    getSign();
                }
                break;
        }
    }

    private void getSign() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpGet(this, "Articles/getSign", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 206:
                        ToastUtil.show(PointTaskActivity.this, baseM.getInfo());
                        pointtask_signIn_tv.setText("已签到");
                        BaseData.IsSign=true;
                        break;
                    default:
                        ToastUtil.show(PointTaskActivity.this, baseM.getInfo());
                        break;
                }
            }
        });
    }

    private void getdata() {
        pointtask_signIn_tv.setText(BaseData.IsSign?"已签到":"签到");
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpGet(this, "Person/getTask", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        GetTaskM getTaskM = new Gson().fromJson(response.body(), GetTaskM.class);
                        pointTaskAdapter = new PointTaskAdapter();
                        pointTaskAdapter.addData(getTaskM.getData());
                        pointtask_rv.setAdapter(pointTaskAdapter);
                        pointtask_rv.setLayoutManager(new LinearLayoutManager(PointTaskActivity.this));
                        break;
                }
            }
        });
    }

}
