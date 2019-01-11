package example.com.douying.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.luck.indicator.NormalEasyIndicator;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseFragment;
import example.com.douying.R;
import example.com.douying.activity.HomeActivity;
import example.com.douying.activity.MyFollowActivity;
import example.com.douying.activity.SettingActivity;
import example.com.douying.adapter.FragmentAdapter;
//我的
public class Fragment5 extends BaseFragment {
    @BindView(R.id.fg5_easy_indicator)
    NormalEasyIndicator normalEasyIndicator;
    @BindView(R.id.fg5_vp)
    ViewPager mviewPager;
    @BindView(R.id.fg5_fans)
    LinearLayout fg5_fans;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_5;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        settab();
    }

    public static Fragment newInstance() {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(NewsDetailActivity.KEY_NEWS, news);
        Fragment fragment = new Fragment5();
        //    fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.fg5_img,R.id.fg5_setting,R.id.fg5_follow})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.fg5_img:
                ((HomeActivity) getActivity()).showswtich();
                break;
            case R.id.fg5_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.fg5_follow:
                startActivity(new Intent(getActivity(),MyFollowActivity.class));
                break;
        }
    }


    private void settab() {
        normalEasyIndicator.setTabTitles(new String[]{"我的作品 9", "我赞过的 370"});
        Fg5_MyLikeFragment fg5_myLikeFragment = new Fg5_MyLikeFragment();
        Fg5_MyWorkFragment fg5_myWorkFragment = new Fg5_MyWorkFragment();
        Fragment[] fragments = new Fragment[]{fg5_myLikeFragment, fg5_myWorkFragment};
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments);
        normalEasyIndicator.setViewPage(mviewPager, fragmentAdapter);
    }
}
