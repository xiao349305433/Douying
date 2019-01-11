package example.com.douying.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.MyJZVideoPlayerStandard;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import douying.example.com.mylibrary.view.utils.Utils;
import douying.example.com.vpager.BezierViewPager;
import douying.example.com.vpager.CardPagerAdapter;
import douying.example.com.vpager.ImageLoadFactory;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.PartBean;
import example.com.douying.utils.GlideImageClient;

import qiu.niorgai.StatusBarCompat;

public class SwtichVideoActivityS extends BaseActivity {
    @BindView(R.id.view_pages)
    BezierViewPager viewPager;
    @BindView(R.id.swtich_video_tv1)
    TextView swtich_video_tv1;
    @BindView(R.id.swtich_video_tv2)
    TextView swtich_video_tv2;
    @BindView(R.id.swtichvideo_video)
    MyJZVideoPlayerStandard swtichvideo_video;
    private List<Object> viewpagerList;
    private List<String> videoimgList;
    private List<String> videourlList;
    private List<PartBean> list;
    private int mPosition;
    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        list = new Gson().fromJson(getIntent().getStringExtra("part"), new TypeToken<List<PartBean>>() {}.getType());
        setswtich();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_swtichvideos;
    }

    @OnClick({R.id.swtichvideo_videolayouts})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.swtichvideo_videolayouts:
                Intent intent=new Intent(this, SwtichActorActivityS.class);
                intent.putExtra("PartBean",list.get(mPosition));
                startActivity(intent);
                break;
        }

    }

    private void setswtich() {

        //viewpager的数据imgList
        viewpagerList = new ArrayList<>();
        //viedo的展示图list
        videoimgList=new ArrayList<>();
        //video的URL list
        videourlList=new ArrayList<>();

        for (int i = 0; i <list.size() ; i++) {
            viewpagerList.add(MainHttp.RES_STRING+list.get(i).getSubposter());
            videoimgList.add(MainHttp.RES_STRING+list.get(i).getPlayposter());
            videourlList.add(MainHttp.RES_STRING+list.get(i).getPlay());
        }
        //设置video
        swtichvideo_video.setUp(MainHttp.RES_STRING+list.get(0).getPlay(), swtichvideo_video.SCREEN_WINDOW_NORMAL,"");
        ImgLoadUtils.loadImage(SwtichVideoActivityS.this,MainHttp.RES_STRING+list.get(0).getPlayposter(),swtichvideo_video.thumbImageView);
        swtich_video_tv1.setText(list.get(0).getSubtitle());

        //设置viewpager
        ImageLoadFactory.getInstance().setImageClient(new GlideImageClient());
        int mWidth = getWindowManager().getDefaultDisplay().getWidth();
        CardPagerAdapter cardAdapter = new CardPagerAdapter(this);
        cardAdapter.addImgUrlList(viewpagerList);
        //设置阴影大小，即vPage  左右两个图片相距边框  maxFactor + 0.3*CornerRadius   *2
        //设置阴影大小，即vPage 上下图片相距边框  maxFactor*1.5f + 0.3*CornerRadius
        int maxFactor = mWidth / 25;
        cardAdapter.setMaxElevationFactor(maxFactor);
        int mWidthPading = mWidth / 4;
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(mWidth, (int) (mWidth * 0.6f)));
        viewPager.setPadding(mWidthPading + Utils.dp2px(10), mWidthPading / 4, mWidthPading + Utils.dp2px(10), mWidthPading / 4);
        viewPager.setClipToPadding(false);
        viewPager.setAdapter(cardAdapter);
        viewPager.showTransformer(0.5f);
        viewPager.setPageMargin(50);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPosition=position;
                swtich_video_tv2.setText("片段 " + (position+1));
                swtichvideo_video.setUp(videourlList.get(position),swtichvideo_video.SCREEN_WINDOW_NORMAL,"");
                ImgLoadUtils.loadImage(SwtichVideoActivityS.this,videoimgList.get(position),swtichvideo_video.thumbImageView);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
