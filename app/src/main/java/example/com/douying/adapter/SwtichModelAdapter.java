package example.com.douying.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.FaceM;

/**
 * Created by admin on 2019/1/17.
 */

public class SwtichModelAdapter extends BaseQuickAdapter<FaceM.ModelBean, BaseViewHolder> {

    public SwtichModelAdapter(@Nullable List<FaceM.ModelBean> data) {
        super(R.layout.item_swtichmodel, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FaceM.ModelBean item) {
        ImgLoadUtils.loadCircularBead(mContext, MainHttp.RES_STRING + item.getList().get(0).getUrl(), (ImageView) (helper.getView(R.id.item_swtichmodel_img)));
        helper.setText(R.id.item_swtichmodel_title,item.getTitle());
    }
}
