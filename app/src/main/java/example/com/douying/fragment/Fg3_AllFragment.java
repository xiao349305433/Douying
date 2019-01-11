package example.com.douying.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import example.com.douying.BaseFragment;
import example.com.douying.R;
import example.com.douying.adapter.Fg3_AllAdapter;
import example.com.douying.adapter.MyFollowAdapter;

public class Fg3_AllFragment extends BaseFragment {
    @BindView(R.id.fg3_all_rv)
    RecyclerView fg3_all_rv;
    Fg3_AllAdapter fg3_allAdapter;
    List<String> list = new ArrayList();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fg3_all;
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
        fg3_allAdapter = new Fg3_AllAdapter(list);
        fg3_all_rv.setAdapter(fg3_allAdapter);
        fg3_all_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
