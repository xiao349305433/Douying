package example.com.douying.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gson.Gson;
import com.luck.indicator.BoxEasyIndicator;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseData;
import example.com.douying.BaseFragment;

import example.com.douying.R;
import example.com.douying.activity.HomeActivity;
import example.com.douying.adapter.FragmentAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetNewM;

//消息
public class Fragment4 extends BaseFragment {
    @BindView(R.id.fg4_easy_indicator)
    BoxEasyIndicator boxEasyIndicator;
    @BindView(R.id.fg4_vp)
    ViewPager mviewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_4;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        getdata();

    }

    public static Fragment newInstance() {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(NewsDetailActivity.KEY_NEWS, news);
        Fragment fragment = new Fragment4();
        //    fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.fg4_img})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.fg4_img:
                getActivity().finish();
                break;

        }
    }


    private void  getdata(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpGet(getActivity(),"Person/getNews",httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM=new Gson().fromJson(response.body(),BaseM.class);
                switch (baseM.getCode()){
                    case 200:
                        GetNewM getNewM=new Gson().fromJson(response.body(),GetNewM.class);
                        settab(getNewM.getData());
                        break;
                        default:
                            break;
                }
            }
        });
    }


    private void settab(GetNewM.DataBean dataBean) {
        boxEasyIndicator.setTabTitles(new String[]{"动态",  "通知"});
        Fg4_CommentFragment fg4_commentFragment = new Fg4_CommentFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("DataBean",dataBean);
        fg4_commentFragment.setArguments(bundle);
        Fg4_NoticeFragment fg4_noticeFragment = new Fg4_NoticeFragment();
        fg4_noticeFragment.setArguments(bundle);
        Fragment[] fragments = new Fragment[]{fg4_commentFragment, fg4_noticeFragment};
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments);
        boxEasyIndicator.setViewPage(mviewPager, fragmentAdapter);
    }
}
