package example.com.douying.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import douying.example.com.mylibrary.view.utils.EncodeUtils;
import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.R;
import example.com.douying.model.GetPostReplyM;

/**
 * Created by admin on 2018/12/10.
 */

public class SubAdapter extends BaseQuickAdapter<GetPostReplyM.DataBean.SubBean,BaseViewHolder> {
    public SubAdapter( @Nullable List<GetPostReplyM.DataBean.SubBean> data) {
        super(R.layout.item_circleinfo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetPostReplyM.DataBean.SubBean item) {
        helper.setText(R.id.item_circleinfo_name,item.getTruename());

        helper.setText(R.id.item_circleinfo_time,item.getAdd_time());

        helper.setText(R.id.item_circleinfo_tv, EncodeUtils.urlDecode(Utils.Base64toStr(item.getContent())));

        ImgLoadUtils.loadCirthumbnail(mContext,item.getPic(),helper.getView(R.id.item_circleinfo_avatar));

    }
}
