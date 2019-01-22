package example.com.douying.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.adapter.MyFollowAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetCareM;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2019/1/17.
 */

public class FansOrFollowActivity extends BaseActivity {
    @BindView(R.id.fansorfollow_title)
    TextView fansorfollow_title;
    @BindView(R.id.fansorfollow_rv)
    RecyclerView fansorfollow_rv;
    MyFollowAdapter followAdapter;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
        inview();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_fansorfollow;
    }

    private void inview() {
        String type = getIntent().getStringExtra("type");
        switch (type) {
            case "follow":
                fansorfollow_title.setText("我的关注");
                break;
            case "fans":
                fansorfollow_title.setText("我的粉丝");
                break;
        }
        fansorfollow_rv.setLayoutManager(new LinearLayoutManager(this));
        followAdapter=new MyFollowAdapter();
        setNodata(fansorfollow_rv);
        fansorfollow_rv.setAdapter(followAdapter);
        followAdapter.setEmptyView(Nodata);
        getdata(type);
    }


    private void getdata(String type){
        HttpParams httpParams=new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("page", 1);
        mainHttp.HttpGet(this,"Index/getCare",httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM=new Gson().fromJson(response.body(),BaseM.class);
                switch (baseM.getCode()){
                    case 200:
                        GetCareM getCareM=new Gson().fromJson(response.body(),GetCareM.class);
                        switch (type){
                            case "follow":
                                if(getCareM.getData().getCare().size()>0){
                                    followAdapter.addData(getCareM.getData().getCare());
                                }else {
                                    followAdapter.getEmptyView().setVisibility(View.VISIBLE);
                                }
                                break;
                            case "fans":
                                if(getCareM.getData().getOff().size()>0){
                                    followAdapter.addData(getCareM.getData().getFavor());
                                }else {
                                    followAdapter.getEmptyView().setVisibility(View.VISIBLE);
                                }
                                break;
                        }
                        break;
                }
            }
        });

    }

}
