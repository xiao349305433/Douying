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
import example.com.douying.adapter.Fg4_LlikeAdapter;
import example.com.douying.adapter.Fg4_NoticeAdapter;
import example.com.douying.model.GetNewM;

public class Fg4_NoticeFragment extends BaseFragment {
    @BindView(R.id.fg4_notice_rv)
    RecyclerView fg4_notice_rv;
    Fg4_NoticeAdapter fg4_noticeAdapter;
    List<String> list = new ArrayList();
    GetNewM.DataBean dataBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fg4_notice;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setdata();
    }

    private void setdata() {
        dataBean = getArguments().getParcelable("DataBean");
        fg4_noticeAdapter = new Fg4_NoticeAdapter(dataBean.getSystem());
        setNodata(fg4_notice_rv);
        fg4_noticeAdapter.setEmptyView(Nodata);
        fg4_notice_rv.setAdapter(fg4_noticeAdapter);
        fg4_notice_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (dataBean.getSystem().size() == 0) {
            fg4_noticeAdapter.getEmptyView().setVisibility(View.VISIBLE);
        } else {
            fg4_noticeAdapter.getEmptyView().setVisibility(View.GONE);
        }

    }
}
