package example.com.douying.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.jzvd.MyJZVideoPlayerStandard;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.MaterialM;

/**
 * Created by admin on 2019/1/9.
 */

public class MaterialAdapter extends BaseQuickAdapter<MaterialM.DataBean,BaseViewHolder> {


    public MaterialAdapter() {
        super(R.layout.item_material);
    }

    @Override
    protected void convert(BaseViewHolder helper, MaterialM.DataBean item) {
           MyJZVideoPlayerStandard myJZVideoPlayerStandard= helper.getView(R.id.item_material_video);
           myJZVideoPlayerStandard.setUp(MainHttp.RES_STRING+item.getPlayurl(),myJZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,"");
           helper.setText(R.id.item_material_tv,item.getIntroduce());
    }
}
