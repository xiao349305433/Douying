package example.com.douying.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.MyJZVideoPlayerStandard;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import example.com.douying.activity.CameraActivity;
import example.com.douying.activity.ModelActivity;
import example.com.douying.adapter.SwtichActorAdapter;
import example.com.douying.http.MainHttp;
import example.com.douying.model.PartBean;
import example.com.douying.view.CustomDialog;
import qiu.niorgai.StatusBarCompat;

public class SwtichActorActivityS extends BaseActivity {
    @BindView(R.id.swtichactors_rv)
    RecyclerView swtichactor_rv;
    @BindView(R.id.actor_mjvpst)
    MyJZVideoPlayerStandard actor_mjvpst;
    SwtichActorAdapter swtichActorAdapter;
    List<Object> list = new ArrayList();
    CustomDialog dialog;
    PartBean partBean;
    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        setdata();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_swtichactors;
    }

    @OnClick({R.id.swtichactors_tocamera})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.swtichactors_tocamera:
                setdialog();
                break;
        }
    }


    private void setdata() {
        partBean = getIntent().getParcelableExtra("PartBean");
        ImgLoadUtils.loadImage(SwtichActorActivityS.this,MainHttp.RES_STRING+partBean.getPlayposter(),actor_mjvpst.thumbImageView);
        actor_mjvpst.setUp(MainHttp.RES_STRING+partBean.getPlay(), actor_mjvpst.SCREEN_WINDOW_NORMAL,"");
        swtichActorAdapter = new SwtichActorAdapter(partBean.getRole());
        swtichactor_rv.setAdapter(swtichActorAdapter);
        swtichactor_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        swtichActorAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.item_swtichactor_img:
                        for (int i = 0; i <  partBean.getRole().size() ; i++) {
                            if(i==position){
                                partBean.getRole().get(position).setIschoose(true);
                            }else {
                                partBean.getRole().get(i).setIschoose(false);
                            }
                        }
                        swtichActorAdapter.notifyDataSetChanged();
                        break;
                }


            }
        });
    }

    private void takePhoto() {
        if (ActivityCompat.checkSelfPermission(SwtichActorActivityS.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SwtichActorActivityS.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0x12);
            return;
        }

    }

    private void setdialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.dialog_swtichface)
                        .heightDimenRes(R.dimen.dialog_loginerror_height)
                        .widthDimenRes(R.dimen.dialog_loginerror_width)
                        .style(R.style.Dialog)
                        .setButtonText(R.id.tocamera, "拍摄")
                        .setButtonText(R.id.todatabasse, "调取")
                        .addViewOnclick(R.id.close, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        })
                        .addViewOnclick(R.id.tocamera, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(SwtichActorActivityS.this, CameraActivity.class));
                            }
                        })
                        .addViewOnclick(R.id.todatabasse, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(SwtichActorActivityS.this, ModelActivity.class));
                            }
                        })
                        .build();
        dialog.show();

    }
}
