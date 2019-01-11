package example.com.douying.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.utils.Utils;
import douying.example.com.vpager.BezierViewPager;
import douying.example.com.vpager.CardPagerAdapter;
import example.com.douying.BaseActivity;
import example.com.douying.R;

import qiu.niorgai.StatusBarCompat;

public class SwtichVideoActivity extends BaseActivity {
    @BindView(R.id.view_page)
    BezierViewPager viewPager;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        initImgList();
        setswtich();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_swtichvideo;
    }

    @OnClick({R.id.swtichvideo_videolayout})
    public void test(View view){
        switch (view.getId()){
            case R.id.swtichvideo_videolayout:
                startActivity(new Intent(this,SwtichActorActivity.class));
                break;
        }

    }

    private void setswtich() {
        int mWidth = getWindowManager().getDefaultDisplay().getWidth();
        CardPagerAdapter cardAdapter = new CardPagerAdapter(this);
        cardAdapter.addImgUrlList(imgList);
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
    }

    private List<Object> imgList;

    public void initImgList() {
        imgList = new ArrayList<>();
        imgList.add(R.mipmap.swtich1002);
        imgList.add(R.mipmap.swtich1003);
        imgList.add(R.mipmap.swtich1701);

    }


}
