package example.com.douying.adapter;

/**
 * Created by Administrator on 2016/10/10.
 * OA的fragment的adapter
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    public static String[] mTITLES ;


    public TabAdapter(FragmentManager fm, List<Fragment> fragments, String[] TITLES ) {
        super(fm);
        mFragments = fragments;
        mTITLES=TITLES;
    }

    @Override
    public Fragment getItem(int arg0) {
        return mFragments.get(arg0);
    }

    //传入tab的长度
    @Override
    public int getCount() {
        return mFragments.size();
    }

    //传入Tab的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return mTITLES[position];
    }

}