package example.com.douying.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseFragment;
import example.com.douying.R;
import example.com.douying.activity.HomeActivity;
import example.com.douying.adapter.TabAdapter;
//圈子
public class Fragment3 extends BaseFragment {
    @BindView(R.id.fg3_tab)
    TabLayout fg3_tab;
    @BindView(R.id.fge3_vp)
    ViewPager fge3_vp;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_3;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        getTablayout();

    }
    public static Fragment newInstance() {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(NewsDetailActivity.KEY_NEWS, news);
        Fragment fragment = new Fragment3();
        //    fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.fg3_img})
    public void test(View view){
        switch (view.getId()){
            case R.id.fg3_img:
                 getActivity().finish();
                break;

        }
    }

    private void getTablayout() {
        List<Fragment> fragments = new ArrayList<>();
        String[] mTITLES = new String[]{};
        Bundle bundle1 = new Bundle();
        Fg3_AllFragment fg3_allFragment=new Fg3_AllFragment();
        fg3_allFragment.setArguments(bundle1);

        Bundle bundle2 = new Bundle();
        Fg3_FollowFragment fg3_followFragment=new Fg3_FollowFragment();
        fg3_followFragment.setArguments(bundle2);

        Bundle bundle3 = new Bundle();
        Fg3_NearbyFragment fg3_nearbyFragment=new Fg3_NearbyFragment();
        fg3_nearbyFragment.setArguments(bundle3);

        Bundle bundle4 = new Bundle();
        Fg3_RecommendFragment fg3_recommendFragment=new Fg3_RecommendFragment();
        fg3_recommendFragment.setArguments(bundle4);

        fragments.add(fg3_allFragment);
        fragments.add(fg3_followFragment);
        fragments.add(fg3_nearbyFragment);
        fragments.add(fg3_recommendFragment);
        mTITLES = new String[]{"全部", "关注", "附近", "推荐"};
        TabAdapter adapter = new TabAdapter(getActivity().getSupportFragmentManager(), fragments, mTITLES);
        fge3_vp.setAdapter(adapter);
        fg3_tab.setupWithViewPager(fge3_vp);
        fg3_tab.setTabsFromPagerAdapter(adapter);
        fge3_vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(fg3_tab));
        fg3_tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.white)); //设置线的颜色
        fg3_tab.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.white)); //设置文本的颜色
        fg3_tab.setTabGravity(TabLayout.GRAVITY_FILL);
    }

}
