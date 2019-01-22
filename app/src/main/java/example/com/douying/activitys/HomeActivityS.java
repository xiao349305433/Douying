package example.com.douying.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.activity.GuideActivity1;
import example.com.douying.activity.MineActivity;
import example.com.douying.adapter.HomesAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetUserInfoM;
import example.com.douying.model.HomesModel;
import example.com.douying.utils.PermissionChecker;
import example.com.douying.view.CustomDialog;
import example.com.douying.view.PopMore;
import qiu.niorgai.StatusBarCompat;

public class HomeActivityS extends BaseActivity {
    @BindView(R.id.homes_rv)
    RecyclerView homes_rv;
    @BindView(R.id.homes_more)
    ImageView homes_more;
    HomesAdapter homesAdapter;
    CustomDialog dialog;
    private SharedPreferences mPerferences;
    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mPerferences = PreferenceManager.getDefaultSharedPreferences(this);
        StatusBarCompat.translucentStatusBar(this);
        setdata();
        setUserInfo();
        if (!mPerferences.getBoolean("IsShowGuide", false)) {
            BaseData.IsShowGuide = true;
            setdialog();
        }
        setPermission();
        getBlind();
        getuserInfo();
    }


    @Override
    public int provideContentViewId() {
        return R.layout.activity_homes;
    }

    @OnClick({R.id.homes_mine, R.id.homes_more})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.homes_mine:
                startActivity(new Intent(this, MineActivity.class));
                break;
            case R.id.homes_more:
                PopMore popMore = new PopMore(this, homes_more);
                popMore.showPopupWindow();
                break;
        }
    }

    private void setPermission() {
        permissionChecker = new PermissionChecker(this);
        permissionChecker.setTitle(getString(R.string.check_info_title));
        permissionChecker.setMessage(getString(R.string.check_info_message));
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            new MaterialDialog.Builder(this).title(R.string.require_acquisition)
                    .content(R.string.default_always_message)
                    .positiveText("下一步").onPositive((dialog, which) -> onPermission()).show();
        }
    }

    private void onPermission() {
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            permissionChecker.requestPermissions();
        }
    }

    private void getuserInfo() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpGet(this, "Index/userInfo", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        GetUserInfoM getUserInfoM = new Gson().fromJson(response.body(), GetUserInfoM.class);
                        SharedPreferences.Editor editor = mPerferences.edit();
                        editor.putBoolean("islogin", true);
                        editor.putString("username", getUserInfoM.getData().getName());//保存用户名
                        editor.putString("uid", getUserInfoM.getData().getId());
                        editor.putString("turename", getUserInfoM.getData().getTruename());
                        editor.putString("pic", getUserInfoM.getData().getPic());
                        editor.putInt("Credits", getUserInfoM.getData().getCredits());
                        editor.putInt("ticket", getUserInfoM.getData().getTicket().split(",").length);
                        editor.putInt("Level",getUserInfoM.getData().getGrade());
                        editor.putInt("Active",getUserInfoM.getData().getActive());
                        editor.putString("Face",getUserInfoM.getData().getFace());
                        editor.putInt("Care",getUserInfoM.getData().getCare());
                        editor.putInt("Favorite",getUserInfoM.getData().getFavorite());
                        editor.apply();
                        BaseData.IsSign = getUserInfoM.getData().getStatus() == 2 ? true : false;
                        setUserInfo();
                        break;
                    default:
                        break;
                }
            }
        });
    }


    private void setUserInfo() {
        BaseData.Turename = mPerferences.getString("turename", "");
        BaseData.Uid = mPerferences.getString("uid", "");
        BaseData.IsLogin = mPerferences.getBoolean("isLogin", false);
        BaseData.Pic = mPerferences.getString("pic", "");
        BaseData.Credits = mPerferences.getInt("Credits", 0);
        BaseData.Ticket = mPerferences.getInt("ticket", 0);
        BaseData.Level=mPerferences.getInt("Level",0);
        BaseData.Active=mPerferences.getInt("Active",0);
        BaseData.Face=mPerferences.getString("Face","");
        BaseData.Care=mPerferences.getInt("Care",0);
        BaseData.Favorite=mPerferences.getInt("Favorite",0);
    }

    private void setdata() {
        HttpParams httpParams = new HttpParams();
        mainHttp.HttpGet(this, "Index/index", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                HomesModel homesModel = new Gson().fromJson(response.body(), HomesModel.class);
                switch (homesModel.getCode()) {
                    case 200:
                        homesAdapter = new HomesAdapter(homesModel.getData());
                        homes_rv.setAdapter(homesAdapter);
                        setNodata(homes_rv);
                        homesAdapter.setEmptyView(Nodata);
                        homes_rv.setLayoutManager(new GridLayoutManager(HomeActivityS.this, 2));
                        homesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(HomeActivityS.this, SwtichActorActivityS2.class);
                                intent.putExtra("part", homesModel.getData().get(position).getPart());
                                startActivity(intent);
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //绑定推送消息
    private void getBlind() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("deviceToken", JPushInterface.getRegistrationID(getApplicationContext()));
        mainHttp.HttpGet(this, "Index/getBlind", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 202:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setdialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.dialog_swtichface)
                        .heightDimenRes(R.dimen.dialog_loginerror_height)
                        .widthDimenRes(R.dimen.dialog_loginerror_width)
                        .style(R.style.Dialog)
                        .setText(R.id.dialog_tv, "开启新手教程，教你如何玩转逗影")
                        .setButtonText(R.id.tocamera, "跳过")
                        .setButtonText(R.id.todatabasse, "现在开始")
                        .addViewOnclick(R.id.close, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        })
                        .addViewOnclick(R.id.tocamera, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        })
                        .addViewOnclick(R.id.todatabasse, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(HomeActivityS.this, GuideActivity1.class));
                            }
                        })
                        .build();
        dialog.show();
    }
}
