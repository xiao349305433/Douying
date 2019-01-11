package example.com.douying.activitys;

import android.os.Bundle;
import android.view.View;


import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.MyJZVideoPlayerStandard;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import example.com.douying.http.JsonCallback;
import example.com.douying.http.MainHttp;
import example.com.douying.model.GetListM;
import example.com.douying.model.GetSingleM;
import example.com.douying.view.CustomDialog;
import qiu.niorgai.StatusBarCompat;

public class LookVideoActivityS extends BaseActivity {
    CustomDialog dialog;
    @BindView(R.id.lookvideos_vd)
    MyJZVideoPlayerStandard lookvideos_vd;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
        setdata();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_lookvideos;
    }


    @OnClick({R.id.lookvideos_share})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.lookvideos_share:
                setdialog();
                break;
        }
    }

    private void setdata() {
        GetListM.DataBean dataBean = getIntent().getParcelableExtra("data");
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", dataBean.getId());
        mainHttp.HttpGet(this, "Index/getSingle", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {

                GetSingleM getSingleM = new Gson().fromJson(response.body(), GetSingleM.class);
                switch (getSingleM.getCode()) {
                    case 200:
                       lookvideos_vd.setUp(MainHttp.RES_STRING+getSingleM.getData().getPoster(),lookvideos_vd.SCREEN_WINDOW_NORMAL,"");
                        ImgLoadUtils.loadImage(LookVideoActivityS.this,MainHttp.RES_STRING+getSingleM.getData().getPoster(),lookvideos_vd.thumbImageView);
                        break;
                    default:
                        break;
                }
            }
        });

    }


    private void setdialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.dialog_share)
                        .heightDimenRes(R.dimen.dialog_share_height)
                        .widthDimenRes(R.dimen.dialog_share_width)
                        .style(R.style.Dialog)
                        .addViewOnclick(R.id.share_close, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        })
                        .build();
        dialog.show();

    }

}
