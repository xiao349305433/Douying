package example.com.douying.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseFragment;
import example.com.douying.R;
import example.com.douying.activity.HomeActivity;
//首页
public class Fragment1 extends BaseFragment {

    @BindView(R.id.fg1_img)
    ImageView fg1_img;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_1;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }

    public static Fragment newInstance() {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(NewsDetailActivity.KEY_NEWS, news);
        Fragment fragment = new Fragment1();
        //    fragment.setArguments(bundle);
        return fragment;
    }
    @OnClick({R.id.fg1_img})
    public void test(View view){
            switch (view.getId()){
                case R.id.fg1_img:
                    ((HomeActivity) getActivity()).showswtich();
                    break;

            }
    }


}
