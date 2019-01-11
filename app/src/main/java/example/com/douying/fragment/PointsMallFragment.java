package example.com.douying.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.BaseData;
import example.com.douying.BaseFragment;
import example.com.douying.R;
import example.com.douying.activity.LevelActivity;
import example.com.douying.activity.PointRecordActivity;
import example.com.douying.activity.PointTaskActivity;
import example.com.douying.activity.TicketActivity;
import example.com.douying.adapter.PointsMallAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetBuyM;
import example.com.douying.model.GetMallM;
import example.com.douying.model.GetOrderM;
import example.com.douying.utils.ToastUtil;
import example.com.douying.view.CustomDialog;
import example.com.douying.wxapi.Constant;
import example.com.douying.wxapi.WeiXinPay;

/**
 * Created by admin on 2018/12/5.
 */

public class PointsMallFragment extends BaseFragment {

    @BindView(R.id.pointsmall_rv)
    RecyclerView pointsmall_rv;
    @BindView(R.id.totask)
    LinearLayout totask;
    @BindView(R.id.pointsmall_point)
    TextView pointsmall_point;
    @BindView(R.id.pointsmall_img)
    ImageView pointsmall_img;
    @BindView(R.id.pointsmall_Ticket)
    TextView pointsmall_Ticket;
    @BindView(R.id.pointsmall_level)
    TextView pointsmall_level;
    PointsMallAdapter pointsMallAdapter;
    CustomDialog dialog;
    private IWXAPI wxAPI;
    String ChoosePay = "";
    String MoneyOrPoint = ""; // Point为积分 Money为钱

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pointsmall;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        inview();
        getdata();
    }

    public static Fragment newInstance() {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(NewsDetailActivity.KEY_NEWS, news);
        Fragment fragment = new PointsMallFragment();
        //    fragment.setArguments(bundle);
        return fragment;
    }


    @OnClick({R.id.totask, R.id.pointsmall_point, R.id.pointsmall_Ticketlayout,R.id.pointsmall_level})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.totask:
                startActivity(new Intent(getActivity(), PointTaskActivity.class));
                break;
            case R.id.pointsmall_point:
                startActivity(new Intent(getActivity(), PointRecordActivity.class));
                break;
            case R.id.pointsmall_Ticketlayout:
                startActivity(new Intent(getActivity(), TicketActivity.class));
                break;
            case R.id.pointsmall_level:
                startActivity(new Intent(getActivity(), LevelActivity.class));
                break;

        }
    }


    private void inview() {
        wxAPI = WXAPIFactory.createWXAPI(getActivity(), Constant.WECHAT_APPID, true);
        wxAPI.registerApp(Constant.WECHAT_APPID);
        pointsmall_point.setText(BaseData.Credits + "");
        pointsmall_Ticket.setText(BaseData.Ticket + "");
        pointsmall_level.setText("Lv."+BaseData.Level);
    }


    /**
     * 获取数据
     */
    private void getdata() {
        ImgLoadUtils.loadCirthumbnail(getActivity(), BaseData.Pic, pointsmall_img);
        HttpParams httpParams = new HttpParams();
        mainHttp.HttpGet(getActivity(), "Person/getMall", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        GetMallM getMallM = new Gson().fromJson(response.body(), GetMallM.class);
                        pointsMallAdapter = new PointsMallAdapter();
                        pointsmall_rv.setAdapter(pointsMallAdapter);
                        pointsmall_rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                        pointsMallAdapter.addData(getMallM.getData());
                        pointsMallAdapter.loadMoreComplete();
                        pointsMallAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                setdialog(pointsMallAdapter.getData().get(position));
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 显示dialog
     *
     * @param dataBean
     */
    private void setdialog(GetMallM.DataBean dataBean) {

        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.dialog_pay)
                        .heightDimenRes(R.dimen.dialog_pay_height)
                        .widthDimenRes(R.dimen.dialog_pay_width)
                        .style(R.style.Dialog)
                        .setText(R.id.dialog_pay_name, dataBean.getName())
                        .setRadioText(R.id.dialog_pay_price, "¥ " + dataBean.getCost())
                        .setRadioText(R.id.dialog_pay_point, "积分 " + dataBean.getCredits())
                        .setViewGone(R.id.dialog_pay_price, dataBean.getCost() != 0 ? View.VISIBLE : View.GONE)     //判断是否显示现金购买
                        .setViewGone(R.id.dialog_pay_point, dataBean.getCredits() != 0 ? View.VISIBLE : View.GONE) //判断是否显示积分购买
                        .setViewGone(R.id.dialog_pay_paylayout, dataBean.getCost() != 0 ? View.VISIBLE : View.GONE) //判断是否需要支付
                        .addRadio(R.id.dialog_pay_alipay, new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    ToastUtil.show(getActivity(), "选择支付宝");
                                    ChoosePay = "AliPay";
                                }
                            }
                        })
                        .addRadio(R.id.dialog_pay_wechat, new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    ToastUtil.show(getActivity(), "选择微信支付");
                                    ChoosePay = "WeiPay";
                                }
                            }
                        })
                        .addRadio(R.id.dialog_pay_point, new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    MoneyOrPoint = "Point";
                                }
                            }
                        })
                        .addRadio(R.id.dialog_pay_price, new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    MoneyOrPoint = "Money";
                                }
                            }
                        })
                        .addViewOnclick(R.id.dialog_pay_close, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        })
                        .addViewOnclick(R.id.dialog_pay_topay, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                switch (MoneyOrPoint) {
                                    case "Money":
                                        switch (ChoosePay) {
                                            case "AliPay":
                                                ToastUtil.show(getActivity(), "暂未接入支付宝");
                                                break;
                                            case "WeiPay":
                                                prepare_order(dataBean);
                                                break;
                                            default:
                                                ToastUtil.show(getActivity(), "请选择支付方式");
                                                break;
                                        }
                                        break;
                                    case "Point":
                                        pointbuy(dataBean);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        })
                        .build();
        dialog.show();
    }

    /**
     * 积分换购
     *
     * @param dataBean
     */
    private void pointbuy(GetMallM.DataBean dataBean) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("cid", dataBean.getId());
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpGet(getActivity(), "Person/getBuy", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 206:
                        GetBuyM buyM = new Gson().fromJson(response.body(), GetBuyM.class);
                        pointsmall_point.setText(buyM.getData().getCredits() + "");
                        BaseData.Ticket = BaseData.Ticket + 1;
                        pointsmall_Ticket.setText(BaseData.Ticket + "");
                        ToastUtil.show(getActivity(), baseM.getInfo());
                        dialog.dismiss();
                        break;
                    default:
                        ToastUtil.show(getActivity(), baseM.getInfo());
                        break;
                }
            }
        });


    }

    /**
     * 获取支付订单
     *
     * @param dataBean
     */
    private void prepare_order(GetMallM.DataBean dataBean) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("body", dataBean.getName());
        httpParams.put("total_fee", dataBean.getOn_price());
        httpParams.put("trade_type", "APP");
        httpParams.put("sid", dataBean.getId());
        httpParams.put("area", 2);
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpPost(getActivity(), "Weipay/prepare_order", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        GetOrderM getOrderM = new Gson().fromJson(response.body(), GetOrderM.class);
                        WeiXinPay weiXinPay = new WeiXinPay();
                        weiXinPay.setNoncestr(getOrderM.getData().getNoncestr());
                        weiXinPay.setPartnerid(getOrderM.getData().getPartnerid());
                        weiXinPay.setPrepayid(getOrderM.getData().getPrepayid());
                        weiXinPay.setSign(getOrderM.getData().getSign());
                        weiXinPay.setTimestamp(getOrderM.getData().getTimestamp());
                        weiXinPay.setPackage_value(getOrderM.getData().getPackageX());
                        if (wxAPI.isWXAppInstalled()) {
                            pay(weiXinPay);
                        } else {
                            ToastUtil.show(getActivity(), "未安装微信");
                        }
                        break;
                    default:
                        ToastUtil.show(getActivity(), baseM.getInfo());
                        break;
                }
            }
        });
    }


    /**
     * 发起支付
     *
     * @param weiXinPay
     */
    public void pay(WeiXinPay weiXinPay) {
        PayReq req = new PayReq();
        req.appId = Constant.WECHAT_APPID;//appid
        req.nonceStr = weiXinPay.getNoncestr();//随机字符串，不长于32位。推荐随机数生成算法
        req.packageValue = weiXinPay.getPackage_value();//暂填写固定值Sign=WXPay
        req.sign = weiXinPay.getSign();//签名
        req.partnerId = weiXinPay.getPartnerid();//微信支付分配的商户号
        req.prepayId = weiXinPay.getPrepayid();//微信返回的支付交易会话ID
        req.timeStamp = weiXinPay.getTimestamp();//时间戳
        wxAPI.registerApp(Constant.WECHAT_APPID);
        wxAPI.sendReq(req);
    }


}
