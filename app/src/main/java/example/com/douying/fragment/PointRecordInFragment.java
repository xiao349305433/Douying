package example.com.douying.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import example.com.douying.BaseFragment;
import example.com.douying.R;
import example.com.douying.adapter.PointRecordAdapter;

/**
 * Created by admin on 2018/12/6.
 */

public class PointRecordInFragment extends BaseFragment {
    @BindView(R.id.pointrecordin_rv)
    RecyclerView pointrecordin_rv;
    PointRecordAdapter pointRecordAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pointrecordin;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        getdata();
    }

    private void getdata() {
        List<Object> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        pointRecordAdapter = new PointRecordAdapter(list);
        pointrecordin_rv.setAdapter(pointRecordAdapter);
        pointrecordin_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
