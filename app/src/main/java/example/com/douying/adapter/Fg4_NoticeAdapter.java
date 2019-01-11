package example.com.douying.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.com.douying.R;
import example.com.douying.model.GetNewM;

public class Fg4_NoticeAdapter extends BaseQuickAdapter<GetNewM.DataBean.SystemBean,BaseViewHolder> {
    public Fg4_NoticeAdapter(@Nullable List data) {
        super(R.layout.item_fg4_notice, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetNewM.DataBean.SystemBean item) {
        helper.setText(R.id.item_fg4_notice_name,item.getTitle());
        helper.setText(R.id.item_fg4_notice_content,item.getContent());
        helper.setText(R.id.item_fg4_notice_time,item.getTime());

    }


}
