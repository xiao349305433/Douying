package example.com.douying.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.SectionM;

/**
 * Created by admin on 2019/1/11.
 */

public class ModelAdapter2 extends BaseSectionQuickAdapter<SectionM, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ModelAdapter2(List<SectionM> data) {
        super(R.layout.item_model_content, R.layout.item_model_head, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionM item) {
        helper.setText(R.id.item_model_title, item.getHeader());
        helper.addOnClickListener(R.id.item_model_edit);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionM item) {
        ImgLoadUtils.loadthumbnail(mContext, MainHttp.RES_STRING + item.getT().getUrl(), (ImageView) helper.getView(R.id.item_model_img));
       // helper.setText(R.id.item_model_tv_1, item.getT().getTitle());
    }
}
