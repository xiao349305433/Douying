package example.com.douying.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.GetMallM;

/**
 * Created by admin on 2018/12/5.
 */

public class PointsMallAdapter extends BaseQuickAdapter<GetMallM.DataBean,BaseViewHolder> {
    public PointsMallAdapter( ) {
        super(R.layout.item_pointsmall);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetMallM.DataBean item) {

        ImgLoadUtils.setBg(mContext, MainHttp.RES_STRING+item.getImg(),((ImageView)helper.getView(R.id.item_pointsmall_img)));
        helper.setText(R.id.item_pointsmall_tv1,item.getName());

        switch (item.getType()){ //1为现金购买，2为积分兑换，3为两者
            case 1:
                helper.setText(R.id.item_pointsmall_num,item.getCost()+"元");
                break;
            case 2:
                helper.setText(R.id.item_pointsmall_num,item.getCredits()+"积分");
                break;
            case 3:
                helper.setText(R.id.item_pointsmall_num,item.getCost()+"元/"+item.getCredits()+"积分");
                break;
        }
    }
}
