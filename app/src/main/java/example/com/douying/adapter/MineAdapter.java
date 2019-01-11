package example.com.douying.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.GetListM;


public class MineAdapter extends BaseQuickAdapter<GetListM.DataBean,BaseViewHolder> {
    public MineAdapter() {
        super(R.layout.item_mine);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetListM.DataBean item) {
        ImgLoadUtils.setBg(mContext, MainHttp.RES_STRING+item.getPoster(), helper.getView(R.id.item_mine_layout));
        helper.setText(R.id.item_mine_tv,item.getIntroduce());
//        ((MyJZVideoPlayerStandard)helper.getView(R.id.item_mine_vd)).setUp(MainHttp.RES_STRING+item.getPlayurl());
    }
}
