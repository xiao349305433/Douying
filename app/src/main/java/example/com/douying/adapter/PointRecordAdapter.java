package example.com.douying.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.com.douying.R;

/**
 * Created by admin on 2018/12/6.
 */

public class PointRecordAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {

    public PointRecordAdapter(@Nullable List<Object> data) {
        super(R.layout.item_pointrecord, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }


}
