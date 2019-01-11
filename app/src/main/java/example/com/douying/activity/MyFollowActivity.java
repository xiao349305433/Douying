package example.com.douying.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import example.com.douying.adapter.MyFollowAdapter;
import qiu.niorgai.StatusBarCompat;

public class MyFollowActivity extends BaseActivity {
    @BindView(R.id.myfollow_rv)
    RecyclerView myfollow_rv;
    MyFollowAdapter myFollowAdapter;
    List<String> list = new ArrayList();

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.gg));
        setdata();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_myfollow;
    }

    private void setdata() {
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        myFollowAdapter = new MyFollowAdapter(list);
        myfollow_rv.setAdapter(myFollowAdapter);
        myfollow_rv.setLayoutManager(new LinearLayoutManager(this));
    }

}
