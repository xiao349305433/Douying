package example.com.douying.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import example.com.douying.BaseFragment;
import example.com.douying.R;
import example.com.douying.adapter.Fg4_LlikeAdapter;

public class Fg4_LikeFragment extends BaseFragment {
    @BindView(R.id.fg4_like_rv)
    RecyclerView fg4_like_rv;
    Fg4_LlikeAdapter fg4_llikeAdapter;
    List<String> list = new ArrayList();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fg4_like;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setdata();
    }

    private void setdata() {
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        fg4_llikeAdapter = new Fg4_LlikeAdapter(list);
        fg4_like_rv.setAdapter(fg4_llikeAdapter);
        fg4_like_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
