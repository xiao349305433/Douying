package example.com.douying.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.activitys.AddModelActivity;
import example.com.douying.adapter.ModelAdapter2;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetModelM;
import example.com.douying.model.SectionM;
import example.com.douying.utils.ToastUtil;
import qiu.niorgai.StatusBarCompat;


public class ModelActivity extends BaseActivity {
    @BindView(R.id.model_rv)
    RecyclerView model_rv;
  //  ModelAdapter modelAdapter;
    ModelAdapter2 modelAdapter2;

    View nodata;
    private BottomSheetDialog dialog;
    GetModelM getModelM;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
        setdata();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_model;
    }

    @Override
    protected void onStart() {
        super.onStart();
        setdata();
    }

    @OnClick({R.id.model_ADD})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.model_ADD:
                startActivity(new Intent(this, AddModelActivity.class));
                break;
        }
    }

    private void setdata() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpGet(this, "Index/getModel", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        getModelM = new Gson().fromJson(response.body(), GetModelM.class);
                        List<SectionM> sectionMList = new ArrayList<>();
                        for (int i = 0; i < getModelM.getData().getList().size(); i++) {
                            sectionMList.add(new SectionM(true, getModelM.getData().getList().get(i).getTitle(),getModelM.getData().getList().get(i).getPid(),i));
                            for (int j = 0; j < getModelM.getData().getList().get(i).getList().size(); j++) {
                                sectionMList.add(new SectionM(getModelM.getData().getList().get(i).getList().get(j)));
                            }
                        }
                        modelAdapter2 = new ModelAdapter2(sectionMList);
                        model_rv.setLayoutManager(new GridLayoutManager(ModelActivity.this,3));
                        nodata = getLayoutInflater().inflate(R.layout.empty_view_model, (ViewGroup) model_rv.getParent(), false);
                        modelAdapter2.setEmptyView(nodata);
                        nodata.findViewById(R.id.empty_tv_add).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(ModelActivity.this, AddModelActivity.class));
                            }
                        });
                        if (getModelM.getData().getList().size() ==0) {
                            modelAdapter2.getEmptyView().setVisibility(View.VISIBLE);
                        }
                        modelAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                showdialog(sectionMList.get(position).getArg());
                            }
                        });
                        model_rv.setAdapter(modelAdapter2);
                        break;
                    case 206:
                        ToastUtil.show(ModelActivity.this, baseM.getInfo());
                        break;
                    default:
                        ToastUtil.show(ModelActivity.this, baseM.getInfo());
                        break;
                }
            }
        });
    }


    private void showdialog(int arg) {
        dialog = new BottomSheetDialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.model_dialog_layout, null);
        dialog.setContentView(dialogView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) dialogView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        dialogView.measure(0, 0);
        behavior.setPeekHeight(dialogView.getMeasuredHeight());
        dialogView.findViewById(R.id.model_dialog_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(arg);
            }
        });
        dialogView.findViewById(R.id.model_dialog_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModelActivity.this, AddModelActivity.class);
                intent.putExtra("ListBeanX", getModelM.getData().getList().get(arg));
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.model_dialog_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void delete(int arg) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("id", modelAdapter2.getData().get(arg).getPid());
        httpParams.put("type", 5);
        mainHttp.HttpGet(this, "Person/get_del", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 203:
                        ToastUtil.show(ModelActivity.this, baseM.getInfo());
                        modelAdapter2.getData().remove(arg);
                        modelAdapter2.notifyDataSetChanged();
                        break;
                    default:
                        ToastUtil.show(ModelActivity.this, baseM.getInfo());
                        break;
                }
                dialog.dismiss();
            }
        });

    }


}
