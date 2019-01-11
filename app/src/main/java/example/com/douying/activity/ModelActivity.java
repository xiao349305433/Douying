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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.BaseActivity;

import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.activitys.AddModelActivity;
import example.com.douying.adapter.ModelAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetModelM2;
import example.com.douying.model.TakeSingleM;

import example.com.douying.utils.ToastUtil;
import qiu.niorgai.StatusBarCompat;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class ModelActivity extends BaseActivity {
    @BindView(R.id.model_rv)
    RecyclerView model_rv;
    @BindView(R.id.model_sure)
    TextView model_sure;
    @BindView(R.id.model_edit)
    TextView model_edit;
    @BindView(R.id.model_img_1)
    ImageView model_img_1;
    @BindView(R.id.model_img_2)
    ImageView model_img_2;
    @BindView(R.id.model_img_3)
    ImageView model_img_3;
    @BindView(R.id.model_close_1)
    ImageView model_close_1;
    @BindView(R.id.model_close_2)
    ImageView model_close_2;
    @BindView(R.id.model_close_3)
    ImageView model_close_3;
    ModelAdapter modelAdapter;

    boolean IsSave1 = false;
    boolean IsSave2 = false;
    boolean IsSave3 = false;
    boolean IsClose1 = false;
    boolean IsClose2 = false;
    boolean IsClose3 = false;
    HttpParams httpParamsUp = new HttpParams();
    View nodata;
    private BottomSheetDialog dialog;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
        inview();
        setdata();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_model;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String path = data.getStringExtra("ImgPath");
            Bitmap bit = BitmapFactory.decodeFile(path);
            switch (requestCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
                case 1:
                    httpParamsUp.put("preview1", new File(path));
                    IsSave1 = true;
                    break;
                case 2:
                    httpParamsUp.put("preview2", new File(path));
                    IsSave2 = true;
                    break;
                case 3:
                    httpParamsUp.put("preview3", new File(path));
                    IsSave3 = true;
                    break;
                default:
                    break;
            }

        }

    }

    private void inview() {
        modelAdapter = new ModelAdapter();
        model_rv.setAdapter(modelAdapter);
        model_rv.setLayoutManager(new LinearLayoutManager(ModelActivity.this));
        nodata = getLayoutInflater().inflate(R.layout.empty_view_model, (ViewGroup) model_rv.getParent(), false);
        modelAdapter.setEmptyView(nodata);
        nodata.findViewById(R.id.empty_tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ModelActivity.this, AddModelActivity.class));
            }
        });
    }

//    @OnClick({R.id.model_edit, R.id.model_sure, R.id.model_img_1, R.id.model_img_2, R.id.model_img_3, R.id.model_close_1, R.id.model_close_2, R.id.model_close_3})
//    public void test(View view) {
//        switch (view.getId()) {
//            case R.id.model_edit:
//                //   model_rv.setVisibility(View.VISIBLE);
//                if (model_edit.getText().equals("完成")) {
//                    model_edit.setText("编辑");
//                    model_sure.setVisibility(View.GONE);
//                    model_img_1.setEnabled(false);
//                    model_img_2.setEnabled(false);
//                    model_img_3.setEnabled(false);
//
//                    model_close_1.setVisibility(View.GONE);
//                    model_close_2.setVisibility(View.GONE);
//                    model_close_3.setVisibility(View.GONE);
//                } else {
//                    model_edit.setText("完成");
//                    model_sure.setVisibility(View.VISIBLE);
//                    model_img_1.setEnabled(true);
//                    model_img_2.setEnabled(true);
//                    model_img_3.setEnabled(true);
//
//                    if (IsSave1) {
//                        model_close_1.setVisibility(View.VISIBLE);
//                    } else {
//                        model_close_1.setVisibility(View.GONE);
//                    }
//                    if (IsSave2) {
//                        model_close_2.setVisibility(View.VISIBLE);
//                    } else {
//                        model_close_2.setVisibility(View.GONE);
//                    }
//                    if (IsSave3) {
//                        model_close_3.setVisibility(View.VISIBLE);
//                    } else {
//                        model_close_3.setVisibility(View.GONE);
//                    }
//                }
//                break;
//            case R.id.model_sure:
//                updata();
//                break;
//            case R.id.model_close_1:
//                IsSave1 = false;
//                IsClose1 = true;
//                model_img_1.setImageResource(R.mipmap.tianjiada);
//                model_img_1.setEnabled(true);
//                model_close_1.setVisibility(View.GONE);
//                break;
//            case R.id.model_close_2:
//                IsSave2 = false;
//                IsClose2 = true;
//                model_img_2.setImageResource(R.mipmap.tianjiada);
//                model_img_2.setEnabled(true);
//                model_close_2.setVisibility(View.GONE);
//                break;
//            case R.id.model_close_3:
//                IsSave3 = false;
//                IsClose3 = true;
//                model_img_3.setImageResource(R.mipmap.tianjiada);
//                model_img_3.setEnabled(true);
//                model_close_3.setVisibility(View.GONE);
//                break;
//            case R.id.model_img_1:
//                if (!IsSave1) {
//                    Intent intent = new Intent(this, CameraActivity.class);
//                    intent.putExtra("IsModel", true);
//                    intent.putExtra("direction", 1);
//                    startActivityForResult(intent, 1);
//                }
//                break;
//            case R.id.model_img_2:
//                if (!IsSave2) {
//                    Intent intent = new Intent(this, CameraActivity.class);
//                    intent.putExtra("IsModel", true);
//                    intent.putExtra("direction", 2);
//                    startActivityForResult(intent, 2);
//                }
//                break;
//            case R.id.model_img_3:
//                if (!IsSave3) {
//                    Intent intent = new Intent(this, CameraActivity.class);
//                    intent.putExtra("IsModel", true);
//                    intent.putExtra("direction", 3);
//                    startActivityForResult(intent, 3);
//                }
//                break;
//
//        }
//    }

    private void setdata() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpGet(this, "Index/getModel", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        GetModelM2 getModelM2 = new Gson().fromJson(response.body(), GetModelM2.class);
                        if (getModelM2.getData().size() > 0) {
                            modelAdapter.addData(getModelM2.getData());
                        } else {
                            modelAdapter.getEmptyView().setVisibility(View.VISIBLE);
                        }
                        modelAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.item_model_close_1:
                                        if (!modelAdapter.getData().get(position).getList().get(0).isDelete) {
                                            modelAdapter.getData().get(position).getList().get(0).setDelete(true);
                                        } else {
                                            modelAdapter.getData().get(position).getList().get(0).setDelete(false);
                                        }
                                        break;
                                    case R.id.item_model_close_2:
                                        if (!modelAdapter.getData().get(position).getList().get(1).isDelete) {
                                            modelAdapter.getData().get(position).getList().get(1).setDelete(true);
                                        } else {
                                            modelAdapter.getData().get(position).getList().get(1).setDelete(false);
                                        }
                                        break;
                                    case R.id.item_model_close_3:
                                        if (!modelAdapter.getData().get(position).getList().get(2).isDelete) {
                                            modelAdapter.getData().get(position).getList().get(2).setDelete(true);
                                        } else {
                                            modelAdapter.getData().get(position).getList().get(2).setDelete(false);
                                        }
                                        break;
                                    case R.id.item_model_img_1:
                                        if (modelAdapter.getData().get(position).getList().get(0).isDelete && modelAdapter.getData().get(position).isEdit()) {
                                            ToastUtil.show(ModelActivity.this, getModelM2.getInfo());
                                        }
                                        break;
                                    case R.id.item_model_img_2:
                                        if (modelAdapter.getData().get(position).getList().get(1).isDelete && modelAdapter.getData().get(position).isEdit()) {
                                            ToastUtil.show(ModelActivity.this, getModelM2.getInfo());
                                        }
                                        break;
                                    case R.id.item_model_img_3:
                                        if (modelAdapter.getData().get(position).getList().get(2).isDelete && modelAdapter.getData().get(position).isEdit()) {
                                            ToastUtil.show(ModelActivity.this, getModelM2.getInfo());
                                        }
                                        break;
                                    case R.id.item_model_edit:
                                        showdialog(position);
                                        //显示这条可编辑
//                                        if (!modelAdapter.getData().get(position).isEdit()) {
//                                            modelAdapter.getData().get(position).setEdit(true);
//                                        } else {
//                                            modelAdapter.getData().get(position).setEdit(false);
//                                        }
//                                        //让其他不可编辑
//                                        for (int i = 0; i < modelAdapter.getData().size(); i++) {
//                                            if (i != position) {
//                                                modelAdapter.getData().get(i).setEdit(false);
//                                            }
//                                        }
                                        break;
                                }
                                modelAdapter.notifyDataSetChanged();
                            }
                        });
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
                intent.putExtra("DataBean", modelAdapter.getData().get(arg));
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
        httpParams.put("id", modelAdapter.getData().get(arg).getPid());
        httpParams.put("type", 5);
        mainHttp.HttpGet(this, "Person/get_del", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 203:
                        ToastUtil.show(ModelActivity.this, baseM.getInfo());
                        modelAdapter.getData().remove(arg);
                        modelAdapter.notifyDataSetChanged();

                        break;
                    default:
                        ToastUtil.show(ModelActivity.this, baseM.getInfo());
                        break;
                }
                dialog.dismiss();
            }
        });

    }


    private void updata() {
        httpParamsUp.put("uid", BaseData.Uid);
        if (IsClose1 && !IsSave1) { //删除
            httpParamsUp.put("preview1", -1);
        } else if (IsClose1 && IsSave1) { //更新

        } else {//不变
            httpParamsUp.put("preview1", -2);
        }

        if (IsClose2 && !IsSave2) { //删除
            httpParamsUp.put("preview2", -1);
        } else if (IsClose2 && IsSave2) { //更新

        } else {//不变
            httpParamsUp.put("preview2", -2);
        }

        if (IsClose3 && !IsSave3) { //删除
            httpParamsUp.put("preview3", -1);
        } else if (IsClose3 && IsSave3) { //更新

        } else {//不变
            httpParamsUp.put("preview3", -2);
        }
        httpParamsUp.put("pid", "");
        httpParamsUp.put("title", "");
        httpParamsUp.put("is_default", "1");
        mainHttp.HttpPost(this, "Index/takeSingle", httpParamsUp).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                TakeSingleM takeSingleM = new Gson().fromJson(response.body(), TakeSingleM.class);
                switch (takeSingleM.getCode()) {
                    case 202:
                        ToastUtil.show(ModelActivity.this, takeSingleM.getInfo());
                        model_edit.setText("编辑");
                        model_sure.setVisibility(View.GONE);
                        model_img_1.setEnabled(false);
                        model_img_2.setEnabled(false);
                        model_img_3.setEnabled(false);

                        model_close_1.setVisibility(View.GONE);
                        model_close_2.setVisibility(View.GONE);
                        model_close_3.setVisibility(View.GONE);
                        break;
                    default:
                        ToastUtil.show(ModelActivity.this, takeSingleM.getInfo());
                        break;
                }
            }
        });
    }


    private void luban(String photo) {
        Luban.with(this)
                .load(photo)
                .ignoreBy(100)
                .setTargetDir(outputDir)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {

                        Log.e("Tag", "onSuccess");
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();
    }

}
