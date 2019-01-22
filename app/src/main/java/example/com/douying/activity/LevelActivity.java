package example.com.douying.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import douying.example.com.mylibrary.view.utils.SpanUtils;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.adapter.LevelAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetPrivilegeM;
import example.com.douying.utils.ToastUtil;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2019/1/9.
 */

public class LevelActivity extends BaseActivity {
    @BindView(R.id.level_rv)
    RecyclerView level_rv;
    @BindView(R.id.level_progressBar)
    ProgressBar level_progressBar;
    @BindView(R.id.level_level)
    TextView level_level;
    @BindView(R.id.level_active)
    TextView level_active;
    @BindView(R.id.level_need)
    TextView level_need;
    @BindView(R.id.level_progressBar_tv)
    TextView level_progressBar_tv;
    LevelAdapter levelAdapter;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        inview();
        getPrivilege();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_level;
    }

    private void inview() {
        levelAdapter = new LevelAdapter();
        level_rv.setAdapter(levelAdapter);
        level_rv.setLayoutManager(new GridLayoutManager(this, 3));

        level_active.setText(BaseData.Active + "");
        level_level.setText("Lv." + BaseData.Level);
        int need = BaseData.Level * (BaseData.Level + 5) * 100;//需要多少活跃值
       // level_need.setText("距离下一等级还需" + (need-BaseData.Active) + "活跃值" );
        level_need.setText(SpanUtils.getForegroundColorSpan(this,"距离下一等级还需" + (need-BaseData.Active) + "活跃值" ,(need-BaseData.Active)+"" ));
        level_progressBar.setMax(need);
        level_progressBar.setProgress(BaseData.Active);
    }

    private void getPrivilege() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("class", BaseData.Level);
        mainHttp.HttpGet(this, "Person/getPrivilege", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        GetPrivilegeM getPrivilegeM = new Gson().fromJson(response.body(), GetPrivilegeM.class);
                        levelAdapter.addData(getPrivilegeM.getData().getPrivilege());
                        break;
                    default:
                        ToastUtil.show(LevelActivity.this, baseM.getInfo());
                        break;
                }
            }
        });
    }
}
