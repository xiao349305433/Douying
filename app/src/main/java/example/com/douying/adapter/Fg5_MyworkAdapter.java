package example.com.douying.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import example.com.douying.R;

public class Fg5_MyworkAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public Fg5_MyworkAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_fg5_mywork_expan1);
        addItemType(TYPE_LEVEL_1, R.layout.item_fg5_mywork_expan2);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
            switch (helper.getItemViewType()){
                case TYPE_LEVEL_0:
//                    helper.setText(R.id.item_fg5_mywork_expan1_vdtv,"");
//                    helper.setText(R.id.item_fg5_mywork_expan1_scannum,"");
//                    helper.setText(R.id.item_fg5_mywork_expan1_commentnum,"");
//                    helper.setText(R.id.item_fg5_mywork_expan1_zannum,"");
                //    helper.getView(R.id.item_fg5_mywork_expan1_vd)
                    break;
                case TYPE_LEVEL_1:
//                    helper.setText(R.id.item_fg5_mywork_expan2_name,"");
//                    helper.setText(R.id.item_fg5_mywork_expan2_content,"");
//                    helper.setText(R.id.item_fg5_mywork_expan2_time,"");
                   // helper.getView(R.id.item_fg5_mywork_expan2_avatar)
                    break;
            }
    }
}
