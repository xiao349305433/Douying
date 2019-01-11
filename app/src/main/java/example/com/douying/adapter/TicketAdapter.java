package example.com.douying.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.GetTicketM;

/**
 * Created by admin on 2018/12/25.
 */

public class TicketAdapter extends BaseQuickAdapter<GetTicketM.DataBean.BaseBean,BaseViewHolder> {
    public TicketAdapter() {
        super(R.layout.item_ticket);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetTicketM.DataBean.BaseBean item) {
      //  helper.setText(R.id.ticket_type,item.getOffon()==1?"已使用":"未使用");
        ImgLoadUtils.setBg(mContext, MainHttp.RES_STRING+item.getImg(),helper.getView(R.id.ticket_bg));

    }
}
