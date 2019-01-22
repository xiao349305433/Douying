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
import example.com.douying.model.GetCareM;

public class MyFollowAdapter extends BaseQuickAdapter<GetCareM.DataBean.BaseBean, BaseViewHolder> {
    public MyFollowAdapter() {
        super(R.layout.item_myfollow);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetCareM.DataBean.BaseBean item) {
        helper.setText(R.id.item_myfollow_name, item.getTruename());
        helper.setText(R.id.item_myfollow_num, "关注 "+item.getCare()+"  粉丝 "+item.getFavorite());
        ImgLoadUtils.loadCirthumbnail(mContext, item.getPic(), helper.getView(R.id.item_myfollow_avatar));
    }
}
