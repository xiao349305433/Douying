package example.com.douying.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import example.com.douying.BaseData;
import example.com.douying.BaseFragment;
import example.com.douying.R;
import example.com.douying.adapter.PointRecordAdapter;
import example.com.douying.adapter.PointsMallAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;

/**
 * Created by admin on 2018/12/6.
 */

public class PointRecordOutFragment extends BaseFragment {
    @BindView(R.id.pointrecordout_rv)
    RecyclerView pointrecordout_rv;
    PointRecordAdapter pointsMallAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pointrecordout;
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
        pointsMallAdapter = new PointRecordAdapter(list);
        pointrecordout_rv.setAdapter(pointsMallAdapter);
        pointrecordout_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
