package example.com.douying.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.com.douying.R;

public class Fg3_FollowAdapter extends BaseQuickAdapter <Object,BaseViewHolder>{
    public Fg3_FollowAdapter( @Nullable List data) {
        super(R.layout.item_fg3_follow, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
//        helper.setText(R.id.item_fg3_follow_name,"");
//        helper.setText(R.id.item_fg3_follow_num,"");
       // helper.getView(R.id.item_fg3_follow_avatar)
    }


}
