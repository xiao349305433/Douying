package example.com.douying.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.adapter.TicketAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetTicketM;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2018/12/25.
 */

public class TicketActivity extends BaseActivity {
    List<GetTicketM.DataBean.BaseBean> list = new ArrayList<>();
    @BindView(R.id.ticket_rv)
    RecyclerView ticket_rv;
    TicketAdapter ticketAdapter=new TicketAdapter();
    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
    }


    @Override
    public int provideContentViewId() {
        return R.layout.activity_ticket;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getdata();
    }


    private void getdata() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpGet(this, "Person/getTicket", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        GetTicketM getTicketM = new Gson().fromJson(response.body(), GetTicketM.class);
                        list.addAll(getTicketM.getData().getOff());
                        list.addAll(getTicketM.getData().getOn());
                        ticketAdapter.addData(list);
                        ticket_rv.setLayoutManager(new LinearLayoutManager(TicketActivity.this));
                        ticket_rv.setAdapter(ticketAdapter);
                        break;
                    default:
                        break;
                }
            }
        });


    }
}
