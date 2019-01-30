package example.com.douying.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.activitys.LookVideoActivityS;
import example.com.douying.activitys.MaterialActivity;
import example.com.douying.activitys.SettingActivityS;
import example.com.douying.adapter.HomesAdapter;
import example.com.douying.adapter.MineAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetListM;
import example.com.douying.model.HomesModel;
import qiu.niorgai.StatusBarCompat;

public class MineActivity extends BaseActivity {
    @BindView(R.id.mine_rv)
    RecyclerView mine_rv;
    @BindView(R.id.mine_name)
    TextView mine_name;
    @BindView(R.id.mine_img)
    ImageView mine_img;
    @BindView(R.id.mine_follow_num)
    TextView mine_follow;
    @BindView(R.id.mine_fans_num)
    TextView mine_fans;
    MineAdapter mineAdapter;
    //   View nodata;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        //  setdata();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_mine;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setview();
    }

    @OnClick({R.id.mine_setting, R.id.mine_img, R.id.mine_fans, R.id.mine_follow, R.id.mine_works, R.id.mine_post, R.id.mine_circle, R.id.mine_model, R.id.mine_material, R.id.mine_pointmall, R.id.mine_saoyisao,R.id.mine_message})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.mine_setting:
                startActivity(new Intent(this, SettingActivityS.class));
                break;
            case R.id.mine_img:
                startActivity(new Intent(this, MineInfoActivity.class));
                break;
            case R.id.mine_follow:
                Intent intent1 = new Intent(this, FansOrFollowActivity.class);
                intent1.putExtra("type", "follow");
                startActivity(intent1);
                break;
            case R.id.mine_fans:
                Intent intent2 = new Intent(this, FansOrFollowActivity.class);
                intent2.putExtra("type", "fans");
                startActivity(intent2);
                break;
            case R.id.mine_post:

                break;
            case R.id.mine_works:

                break;
            case R.id.mine_circle:
                Intent intent3 = new Intent(this, MoreActivity.class);
                intent3.putExtra("Type", "circle");
                startActivity(intent3);
                break;
            case R.id.mine_model:
                startActivity(new Intent(this, ModelActivity.class));
                break;
            case R.id.mine_material:
                startActivity(new Intent(this, MaterialActivity.class));
                break;
            case R.id.mine_pointmall:
                Intent intent4 = new Intent(this, MoreActivity.class);
                intent4.putExtra("Type", "point");
                startActivity(intent4);
                break;
            case R.id.mine_message:
                Intent intent5 = new Intent(this, MoreActivity.class);
                intent5.putExtra("Type", "message");
                startActivity(intent5);
                break;
            case R.id.mine_saoyisao:
                startActivity(new Intent(this, CaptureActivity.class));
                break;

        }
    }


    private void setview() {
        mine_name.setText(BaseData.Turename);
        mine_fans.setText( BaseData.Favorite);
        mine_follow.setText(BaseData.Care);
//        setNodata(mine_rv);
//        mineAdapter = new MineAdapter();
//        mineAdapter.setEmptyView(Nodata);
//        mine_rv.setAdapter(mineAdapter);
//        mine_rv.setLayoutManager(new LinearLayoutManager(MineActivity.this));
        if (!BaseData.Pic.equals("")) {
            ImgLoadUtils.loadCirthumbnail(this, BaseData.Pic, mine_img);
        }
    }

    private void setdata() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpGet(this, "Index/getList", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        GetListM getListM = new Gson().fromJson(response.body(), GetListM.class);
                        mineAdapter.addData(getListM.getData());
                        mineAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(MineActivity.this, LookVideoActivityS.class);
                                intent.putExtra("data", getListM.getData().get(position));
                                startActivity(intent);
                            }
                        });

                        if (getListM.getData().size() == 0) {
                            mineAdapter.getEmptyView().setVisibility(View.VISIBLE);
                        } else {
                            mineAdapter.getEmptyView().setVisibility(View.GONE);
                        }
                        break;
                    default:
                        mineAdapter.getEmptyView().setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

}
