package example.com.douying.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import example.com.douying.BaseFragment;
import example.com.douying.R;
import example.com.douying.adapter.Fg3_AllAdapter;
import example.com.douying.adapter.Fg4_LlikeAdapter;
import example.com.douying.model.GetNewM;

public class Fg4_CommentFragment extends BaseFragment {
    @BindView(R.id.fg4_comment_rv)
    RecyclerView fg4_comment_rv;
    Fg4_LlikeAdapter fg4_llikeAdapter;
    List<String> list = new ArrayList();
    GetNewM.DataBean dataBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fg4_comment;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setdata();
    }

    private void setdata() {
        dataBean=getArguments().getParcelable("DataBean");
        fg4_llikeAdapter = new Fg4_LlikeAdapter(dataBean.getTrend());
        setNodata(fg4_comment_rv);
        fg4_llikeAdapter.setEmptyView(Nodata);
        fg4_comment_rv.setAdapter(fg4_llikeAdapter);
        fg4_comment_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(dataBean.getTrend().size()==0){
            fg4_llikeAdapter.getEmptyView().setVisibility(View.VISIBLE);
        }else {
            fg4_llikeAdapter.getEmptyView().setVisibility(View.GONE);
        }
    }
}
