package example.com.douying.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.com.douying.R;

public class Fg3_AllAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    public Fg3_AllAdapter( @Nullable List data) {
        super(R.layout.item_fg3_all, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
//        helper.setText(R.id.item_fg3_all_content,"");
//        helper.setText(R.id.item_fg3_all_name,"");
//        helper.setText(R.id.item_fg3_all_time,"");
//        helper.setText(R.id.item_fg3_all_v_name,"");
//        helper.setText(R.id.item_fg3_all_v_num,"");
      //  helper.getView(R.id.item_fg3_all_v_img)
      //  helper.getView(R.id.item_fg3_all_avatar);


    }


}
