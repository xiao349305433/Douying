package example.com.douying.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.adapter.MaterialAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.MaterialM;
import example.com.douying.utils.ToastUtil;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2018/9/28.
 */

public class MaterialActivity extends BaseActivity {
    @BindView(R.id.material_rv)
    RecyclerView material_rv;
    MaterialAdapter materialAdapter;
    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        inview();
        getdata();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_material;
    }

    @OnClick({R.id.material_add})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.material_add:
                startActivity(new Intent(this, AddMaterialActivity.class));
                break;

        }
    }

    private void inview(){
        materialAdapter=new MaterialAdapter();
        material_rv.setLayoutManager(new LinearLayoutManager(this));
        setNodata(material_rv);
        materialAdapter.setEmptyView(Nodata);
    }

    private void getdata() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("type", "1");
        mainHttp.HttpGet(this, "Index/getList", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        MaterialM materialM=new Gson().fromJson(response.body(),MaterialM.class);
                        if(materialM.getData().size()>0){
                            materialAdapter.addData(materialM.getData());
                        }else {
                            materialAdapter.getEmptyView().setVisibility(View.VISIBLE);
                        }
                        material_rv.setAdapter(materialAdapter);

                        break;
                    default:
                        ToastUtil.show(MaterialActivity.this,baseM.getInfo());
                        break;
                }

            }
        });
    }
}
