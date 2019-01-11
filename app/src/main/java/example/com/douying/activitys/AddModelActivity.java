package example.com.douying.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.activity.CameraActivity;
import example.com.douying.activity.ModelActivity;
import example.com.douying.http.JsonCallback;
import example.com.douying.http.MainHttp;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetModelM2;
import example.com.douying.model.TakeSingleM;
import example.com.douying.utils.ToastUtil;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2019/1/4.
 */

public class AddModelActivity extends BaseActivity {
    //    @BindView(R.id.model_sure)
//    TextView model_sure;
    @BindView(R.id.addmodel_sure)
    TextView model_edit;
    @BindView(R.id.addmodel_img_1)
    ImageView model_img_1;
    @BindView(R.id.addmodel_img_2)
    ImageView model_img_2;
    @BindView(R.id.addmodel_img_3)
    ImageView model_img_3;
    @BindView(R.id.addmodel_close_1)
    ImageView model_close_1;
    @BindView(R.id.addmodel_close_2)
    ImageView model_close_2;
    @BindView(R.id.addmodel_close_3)
    ImageView model_close_3;
    @BindView(R.id.addmodel_edit)
    EditText addmodel_edit;

    boolean IsSave1 = false;
    boolean IsSave2 = false;
    boolean IsSave3 = false;
    boolean IsClose1 = false;
    boolean IsClose2 = false;
    boolean IsClose3 = false;
    HttpParams httpParamsUp = new HttpParams();
    GetModelM2.DataBean dataBean;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
        inview();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_addmodel;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String path = data.getStringExtra("ImgPath");
            Bitmap bit = BitmapFactory.decodeFile(path);
            switch (requestCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
                case 1:
                    model_img_1.setImageBitmap(bit);
                    httpParamsUp.put("preview1", new File(path));
                    model_close_1.setVisibility(View.VISIBLE);
                    model_img_1.setEnabled(false);
                    IsSave1 = true;
                    break;
                case 2:
                    model_img_2.setImageBitmap(bit);
                    httpParamsUp.put("preview2", new File(path));
                    model_close_2.setVisibility(View.VISIBLE);
                    model_img_2.setEnabled(false);
                    IsSave2 = true;
                    break;
                case 3:
                    model_img_3.setImageBitmap(bit);
                    httpParamsUp.put("preview3", new File(path));
                    model_close_3.setVisibility(View.VISIBLE);
                    model_img_3.setEnabled(false);
                    IsSave3 = true;
                    break;
                default:
                    break;
            }

        }

    }


    @OnClick({R.id.addmodel_sure, R.id.addmodel_img_1, R.id.addmodel_img_2, R.id.addmodel_img_3, R.id.addmodel_close_1, R.id.addmodel_close_2, R.id.addmodel_close_3})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.addmodel_sure:
                //   model_rv.setVisibility(View.VISIBLE);
                updata();
//                if (model_edit.getText().equals("完成")) {
//                    model_edit.setText("编辑");
//                    model_img_1.setEnabled(false);
//                    model_img_2.setEnabled(false);
//                    model_img_3.setEnabled(false);
//                    model_close_1.setVisibility(View.GONE);
//                    model_close_2.setVisibility(View.GONE);
//                    model_close_3.setVisibility(View.GONE);
//                } else {
//                    model_edit.setText("完成");
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
                break;
            case R.id.addmodel_close_1:
                IsSave1 = false;
                IsClose1 = true;
                model_img_1.setImageResource(R.mipmap.tianjiada);
                model_img_1.setEnabled(true);
                model_close_1.setVisibility(View.GONE);
                break;
            case R.id.addmodel_close_2:
                IsSave2 = false;
                IsClose2 = true;
                model_img_2.setImageResource(R.mipmap.tianjiada);
                model_img_2.setEnabled(true);
                model_close_2.setVisibility(View.GONE);
                break;
            case R.id.addmodel_close_3:
                IsSave3 = false;
                IsClose3 = true;
                model_img_3.setImageResource(R.mipmap.tianjiada);
                model_img_3.setEnabled(true);
                model_close_3.setVisibility(View.GONE);
                break;
            case R.id.addmodel_img_1:
                if (!IsSave1) {
                    Intent intent = new Intent(this, CameraActivity.class);
                    intent.putExtra("IsModel", true);
                    intent.putExtra("direction", 1);
                    startActivityForResult(intent, 1);
                }
                break;
            case R.id.addmodel_img_2:
                if (!IsSave2) {
                    Intent intent = new Intent(this, CameraActivity.class);
                    intent.putExtra("IsModel", true);
                    intent.putExtra("direction", 2);
                    startActivityForResult(intent, 2);
                }
                break;
            case R.id.addmodel_img_3:
                if (!IsSave3) {
                    Intent intent = new Intent(this, CameraActivity.class);
                    intent.putExtra("IsModel", true);
                    intent.putExtra("direction", 3);
                    startActivityForResult(intent, 3);
                }
                break;

        }
    }


    private void inview() {
        dataBean = getIntent().getParcelableExtra("DataBean");
        ImgLoadUtils.loadthumbnail(this, MainHttp.RES_STRING+dataBean.getList().get(0).getUrl(),model_img_1);
        ImgLoadUtils.loadthumbnail(this, MainHttp.RES_STRING+dataBean.getList().get(1).getUrl(),model_img_2);
        ImgLoadUtils.loadthumbnail(this, MainHttp.RES_STRING+dataBean.getList().get(2).getUrl(),model_img_3);
        addmodel_edit.setText(dataBean.getTitle());
        model_close_1.setVisibility(View.VISIBLE);
        model_close_2.setVisibility(View.VISIBLE);
        model_close_3.setVisibility(View.VISIBLE);

    }

    private void updata() {
        httpParamsUp.put("uid", BaseData.Uid);
        if (IsClose1 && !IsSave1) { //删除
            httpParamsUp.put("preview1", -1);
        } else if (IsClose1 && IsSave1) { //更新
            httpParamsUp.put("preview1", -2);
        } else {//不变

        }

        if (IsClose2 && !IsSave2) { //删除
            httpParamsUp.put("preview2", -1);
        } else if (IsClose2 && IsSave2) { //更新
            httpParamsUp.put("preview2", -2);
        } else {//不变

        }

        if (IsClose3 && !IsSave3) { //删除
            httpParamsUp.put("preview3", -1);
        } else if (IsClose3 && IsSave3) { //更新
            httpParamsUp.put("preview3", -2);
        } else {//不变

        }

        if (addmodel_edit.getText() != null) {
            httpParamsUp.put("title", addmodel_edit.getText().toString());
        }
        httpParamsUp.put("is_default", "1");
        mainHttp.HttpPost(this, "Index/takeSingle", httpParamsUp).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 202:
                        ToastUtil.show(AddModelActivity.this, baseM.getInfo());
                        finish();
                        break;
                    default:
                        ToastUtil.show(AddModelActivity.this, baseM.getInfo());
                        break;
                }
            }
        });
    }

}
