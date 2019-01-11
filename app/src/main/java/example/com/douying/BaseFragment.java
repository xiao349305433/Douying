package example.com.douying;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import example.com.douying.http.MainHttp;


/**
 * Created by laucherish on 16/3/15.
 */
public abstract class BaseFragment extends Fragment {
    public static final String TAG = BaseFragment.class.getSimpleName();
    public View mRootView;
    public MainHttp mainHttp=new MainHttp();
    public  View Nodata;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        afterCreate(savedInstanceState);
       // EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//取消注册
    }

    public void setNodata(RecyclerView recyclerView) {
        Nodata = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //绑定页面
    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);
}
