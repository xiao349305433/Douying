package example.com.douying.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.R;
import example.com.douying.model.GetNewM;

public class Fg4_LlikeAdapter extends BaseQuickAdapter<GetNewM.DataBean.TrendBean, BaseViewHolder> {

    public Fg4_LlikeAdapter(@Nullable List data) {
        super(R.layout.item_fg4_like, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetNewM.DataBean.TrendBean item) {

        helper.setText(R.id.item_fg4_like_content, Utils.Base64toStr(item.getContent()));
        helper.setText(R.id.item_fg4_like_name, item.getTruename());
        helper.setText(R.id.item_fg4_like_time, item.getTime());
        ImgLoadUtils.loadCirthumbnail(mContext, item.getPic(), helper.getView(R.id.item_fg4_like_avatar));
        // helper.getView(R.id.item_fg4_like_img)

    }


}
