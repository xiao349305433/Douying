package example.com.douying.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.PartBean;



public class SwtichActorAdapter extends BaseQuickAdapter<PartBean.RoleBean, BaseViewHolder> {
    public SwtichActorAdapter(@Nullable List<PartBean.RoleBean> data) {
        super(R.layout.item_swtichactor, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PartBean.RoleBean item) {
        helper.addOnClickListener(R.id.item_swtichactor_img);
        if(item.isIschoose()){
            helper.getView(R.id.item_swtichactor_img).setBackground(mContext.getResources().getDrawable(R.drawable.item_swtichactor_img_shape_red));
        }else {
            helper.getView(R.id.item_swtichactor_img).setBackground(mContext.getResources().getDrawable(R.drawable.item_swtichactor_img_shape_wihte));
        }

        helper.setText(R.id.item_swtichactor_tv, item.getName());
        ImgLoadUtils.loadCircularBead(mContext, MainHttp.RES_STRING + item.getUpic(), (ImageView) (helper.getView(R.id.item_swtichactor_img)));

    }
}
