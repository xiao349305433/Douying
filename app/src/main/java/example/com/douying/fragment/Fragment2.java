package example.com.douying.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.OnClick;
import example.com.douying.BaseFragment;

import example.com.douying.R;
import example.com.douying.activity.HomeActivity;
import example.com.douying.activity.SwtichVideoActivity;

public class Fragment2 extends BaseFragment {
    //录制页面
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_2;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }
    public static Fragment newInstance() {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(NewsDetailActivity.KEY_NEWS, news);
        Fragment fragment = new Fragment2();
        //    fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.fg2_img,R.id.fg2_tovideo1,R.id.fg2_tovideo2,R.id.fg2_tovideo3})
    public void test(View view){
        switch (view.getId()){
            case R.id.fg2_img:
                ((HomeActivity) getActivity()).showswtich();
                break;
            case R.id.fg2_tovideo1:
                startActivity(new Intent(getActivity(), SwtichVideoActivity.class));
                break;
            case R.id.fg2_tovideo2:
                startActivity(new Intent(getActivity(), SwtichVideoActivity.class));
                break;
            case R.id.fg2_tovideo3:
                startActivity(new Intent(getActivity(), SwtichVideoActivity.class));
                break;

        }
    }
}
