package example.com.douying.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.GetPrivilegeM;

/**
 * Created by admin on 2019/1/10.
 */

public class LevelAdapter extends BaseQuickAdapter<GetPrivilegeM.DataBean.PrivilegeBean,BaseViewHolder> {
    public LevelAdapter() {
        super(R.layout.item_level);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetPrivilegeM.DataBean.PrivilegeBean item) {
        ImgLoadUtils.setBg(mContext, MainHttp.RES_STRING+item.getIcon(),(ImageView) helper.getView(R.id.item_level_img));
        helper.setText(R.id.item_level_tv,item.getDetail());

    }
}
