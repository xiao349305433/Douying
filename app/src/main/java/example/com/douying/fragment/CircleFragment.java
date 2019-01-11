package example.com.douying.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.utils.ListItemDecorationUtil;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.BaseData;
import example.com.douying.BaseFragment;
import example.com.douying.R;
import example.com.douying.activity.CircleInfoActivity;
import example.com.douying.activity.CirclePublishActivity;
import example.com.douying.adapter.CircleAdapter;
import example.com.douying.event.PublishEvent;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetArticlesListM;
import example.com.douying.utils.ToastUtil;

/**
 * Created by admin on 2018/11/14.
 */

public class CircleFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    CircleAdapter circleAdapter;
    @BindView(R.id.circle_rv)
    RecyclerView circle_rv;
    @BindView(R.id.circle_publish)
    ImageView circle_publish;
    @BindView(R.id.circle_swipe)
    SwipeRefreshLayout circle_swipe;
    private boolean mLoadMoreEndGone = false;
    int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cirile;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        inview();
        EventBus.getDefault().register(this);
    }

    public static Fragment newInstance() {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(NewsDetailActivity.KEY_NEWS, news);
        Fragment fragment = new CircleFragment();
        //    fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.circle_publish})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.circle_publish:
                startActivity(new Intent(getActivity(), CirclePublishActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PublishEvent publishEvent) {
        if (publishEvent.isPublish()) {
            circle_swipe.setRefreshing(true);
            getdata(1, true);
        }

    }

    private void inview() {
        circleAdapter = new CircleAdapter();
        circleAdapter.setOnLoadMoreListener(this, circle_rv);
        circle_rv.setAdapter(circleAdapter);
        setNodata(circle_rv);
        circleAdapter.setEmptyView(Nodata);
        circle_swipe.setOnRefreshListener(this);
        circle_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        circle_rv.addItemDecoration(new ListItemDecorationUtil(getActivity(), Utils.dp2px(10), 0));
        circleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), CircleInfoActivity.class);
                intent.putExtra("DataBean", circleAdapter.getData().get(position));
                startActivity(intent);
            }
        });
//        circleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//            }
//        });
        circleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.item_circle_like:
                        if (circleAdapter.getData().get(position).getIs_praise().equals("on")) {
                            takeAction("off", circleAdapter.getData().get(position).getId(), circleAdapter.getData().get(position).getUid(), position);
                        } else {
                            takeAction("on", circleAdapter.getData().get(position).getId(), circleAdapter.getData().get(position).getUid(), position);
                        }
                        break;
                    case R.id.item_circle_liketv:
                        if (circleAdapter.getData().get(position).getIs_praise().equals("on")) {
                            takeAction("off", circleAdapter.getData().get(position).getId(), circleAdapter.getData().get(position).getUid(), position);
                        } else {
                            takeAction("on", circleAdapter.getData().get(position).getId(), circleAdapter.getData().get(position).getUid(), position);
                        }
                        break;
                    case R.id.item_circle_comment:
                        Intent intent = new Intent(getActivity(), CircleInfoActivity.class);
                        intent.putExtra("DataBean", circleAdapter.getData().get(position));
                        startActivity(intent);
                        break;
                }
            }
        });
        circle_swipe.setRefreshing(true);
        getdata(mPage, true);
    }


    private void takeAction(String action, int aid, int to_uid, int position) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("area", 2);
        httpParams.put("action", action);//on为确认，off为取消
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("aid", aid); //帖子id
        httpParams.put("to_uid", to_uid);
        mainHttp.HttpGet(getActivity(), "Articles/takeAction", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 206:
                        if (action.equals("on")) {
                            circleAdapter.getData().get(position).setIs_praise("on");
                        } else {
                            circleAdapter.getData().get(position).setIs_praise("off");
                        }
                        circleAdapter.notifyDataSetChanged();
                        ToastUtil.show(getActivity(), baseM.getInfo());

                        break;
                    default:
                        ToastUtil.show(getActivity(), baseM.getInfo());
                        break;
                }
            }
        });
    }


    private void getdata(int page, boolean RefreshOrAdd) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpGet(getActivity(), "/Articles/getList", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        GetArticlesListM getArticlesListM = new Gson().fromJson(response.body(), GetArticlesListM.class);
                        List<GetArticlesListM.DataBean> list=new ArrayList<>();
                        if (RefreshOrAdd) { //ture  刷新 false 加载更多
                            circleAdapter.setEnableLoadMore(true);
                            circleAdapter.getData().clear();
                            circleAdapter.addData(getArticlesListM.getData());
                            circleAdapter.loadMoreComplete();
                            mPage++;
                        } else {
                            if (getArticlesListM.getData().size() > 0) {
                                circleAdapter.addData(getArticlesListM.getData());
                                circleAdapter.loadMoreComplete();
                                circleAdapter.getEmptyView().setVisibility(View.GONE);
                                mPage++;
                            } else {
                                ToastUtil.show(getActivity(), "暂无更多内容");
                                circleAdapter.loadMoreEnd(mLoadMoreEndGone);
                            }
                        }
                        circle_swipe.setRefreshing(false);

                        break;
                    default:
                        ToastUtil.show(getActivity(), "暂无更多内容");
                        circleAdapter.getEmptyView().setVisibility(View.VISIBLE);
                        circleAdapter.loadMoreEnd();
                        circle_swipe.setRefreshing(false);
                        break;
                }
            }
        });
    }


    @Override
    public void onLoadMoreRequested() {
        //   circle_swipe.setEnabled(false);
        getdata(mPage, false); //ture  刷新 false 加载更多
    }

    @Override
    public void onRefresh() {
        circleAdapter.setEnableLoadMore(false);
        mPage = 1;
        getdata(mPage, true); //ture  刷新 false 加载更多
    }
}
