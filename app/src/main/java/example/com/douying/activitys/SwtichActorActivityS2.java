package example.com.douying.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
import example.com.douying.adapter.ActorViewPagerAdapter;
import example.com.douying.adapter.SwtichActorAdapter;
import example.com.douying.http.MainHttp;
import example.com.douying.model.PartBean;
import example.com.douying.view.CustomDialog;
import qiu.niorgai.StatusBarCompat;

public class SwtichActorActivityS2 extends BaseActivity {
    @BindView(R.id.swtichactors_rv)
    RecyclerView swtichactor_rv;
    @BindView(R.id.actor_pointgroup)
    ViewGroup actor_pointgroup;
    @BindView(R.id.actor_viewpager)
    ViewPager actor_viewpager;
    SwtichActorAdapter swtichActorAdapter;
    List<Object> list = new ArrayList();
    List<PartBean> ListPart;
    CustomDialog dialog;
    ImageView[] points;

    ActorViewPagerAdapter actorViewPagerAdapter;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        setdata();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_swtichactors2;
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
        ListPart = new Gson().fromJson(getIntent().getStringExtra("part"), new TypeToken<List<PartBean>>() {
        }.getType());
        actorViewPagerAdapter = new ActorViewPagerAdapter(this, ListPart);
        actor_viewpager.setAdapter(actorViewPagerAdapter);
        actor_viewpager.setOffscreenPageLimit(2);
        //设置初始的
        swtichActorAdapter = new SwtichActorAdapter(ListPart.get(0).getRole());
        actor_viewpager.setOnPageChangeListener(new GuidePageChangeListener());

        setPointGroup();
        swtichactor_rv.setAdapter(swtichActorAdapter);
        swtichactor_rv.setLayoutManager(new LinearLayoutManager(SwtichActorActivityS2.this, LinearLayoutManager.HORIZONTAL, false));
        swtichActorAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int arg) {
                switch (view.getId()) {
                    case R.id.item_swtichactor_img:
                        for (int i = 0; i < ListPart.get(0).getRole().size(); i++) {
                            if (i == arg) {
                                ListPart.get(0).getRole().get(arg).setIschoose(true);
                            } else {
                                ListPart.get(0).getRole().get(i).setIschoose(false);
                            }
                        }
                        swtichActorAdapter.notifyDataSetChanged();
                        break;
                }

            }
        });

        actor_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                swtichActorAdapter = new SwtichActorAdapter(ListPart.get(position).getRole());
                swtichactor_rv.setAdapter(swtichActorAdapter);
                swtichactor_rv.setLayoutManager(new LinearLayoutManager(SwtichActorActivityS2.this, LinearLayoutManager.HORIZONTAL, false));
                swtichActorAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int arg) {
                        switch (view.getId()) {
                            case R.id.item_swtichactor_img:
                                for (int i = 0; i < ListPart.get(position).getRole().size(); i++) {
                                    if (i == arg) {
                                        ListPart.get(position).getRole().get(arg).setIschoose(true);
                                    } else {
                                        ListPart.get(position).getRole().get(i).setIschoose(false);
                                    }
                                }
                                swtichActorAdapter.notifyDataSetChanged();
                                break;
                        }

                    }
                });

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void setPointGroup() {
        // 清除
        actor_pointgroup.removeAllViews();
        actor_pointgroup.setVisibility(ListPart.size() > 1 ? View.VISIBLE : View.GONE);
        points = new ImageView[ListPart.size()];
        for (int i = 0; i < ListPart.size(); i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                ImgLoadUtils.setBg(this, R.mipmap.icon_point_white, imageView);
            } else {
                ImgLoadUtils.setBg(this, R.mipmap.icon_point_gray, imageView);
            }
            points[i] = imageView;
            actor_pointgroup.addView(points[i]);
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
                                startActivity(new Intent(SwtichActorActivityS2.this, CameraActivity.class));
                            }
                        })
                        .addViewOnclick(R.id.todatabasse, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(SwtichActorActivityS2.this, ModelActivity.class));
                            }
                        })
                        .build();
        dialog.show();

    }


    /**
     * 轮播图片监听
     */
    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int index) {
            ImgLoadUtils.setBg(SwtichActorActivityS2.this,R.mipmap.icon_point_white,points[index]);
            for (int i = 0; i < points.length; i++) {
                if (index != i) {
                    ImgLoadUtils.setBg(SwtichActorActivityS2.this,R.mipmap.icon_point_gray,points[i]);
                }
            }
        }
    }

}
