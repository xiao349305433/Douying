package example.com.douying.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import example.com.douying.adapter.TabAdapter;
import example.com.douying.fragment.PointRecordInFragment;
import example.com.douying.fragment.PointRecordOutFragment;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2018/12/6.
 */

public class PointRecordActivity extends BaseActivity {
    @BindView(R.id.pointrecord_tab)
    TabLayout pointrecord_tab;
    @BindView(R.id.pointrecord_vp)
    ViewPager pointrecord_vp;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        inview();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_pointrecord;
    }

    private void inview() {
        List<Fragment> fragments = new ArrayList<>();
        String[] mTITLES = new String[]{};
        PointRecordInFragment pointRecordInFragment = new PointRecordInFragment();
        PointRecordOutFragment pointRecordOutFragment = new PointRecordOutFragment();
        mTITLES = new String[]{"积分来源", "消费记录"};
        fragments.add(pointRecordInFragment);
        fragments.add(pointRecordOutFragment);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragments, mTITLES);
        pointrecord_vp.setAdapter(adapter);
        pointrecord_tab.setupWithViewPager(pointrecord_vp);
        pointrecord_tab.setTabsFromPagerAdapter(adapter);
        pointrecord_vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(pointrecord_tab));
        pointrecord_tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.pointtask_Orange)); //设置线的颜色
        pointrecord_tab.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.pointtask_Orange)); //设置文本的颜色
        pointrecord_tab.setTabGravity(TabLayout.GRAVITY_CENTER);
    }
}
