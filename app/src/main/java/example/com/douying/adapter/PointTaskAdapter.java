package example.com.douying.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.GetTaskM;

/**
 * Created by admin on 2018/12/5.
 */

public class PointTaskAdapter extends BaseQuickAdapter<GetTaskM.DataBean,BaseViewHolder> {
    public PointTaskAdapter() {
        super(R.layout.item_pointtask);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetTaskM.DataBean item) {
                helper.setText(R.id.item_pointtask_taskname,item.getName());
                helper.setText(R.id.item_pointtask_taskcontent,"积分值+"+item.getCredits());
                switch (item.getComplete()){
                    case 1:
                        helper.setText(R.id.item_pointtask_task,"未完成");
                        helper.getView(R.id.item_pointtask_task).setBackground(mContext.getResources().getDrawable(R.drawable.item_pointtask_shape1));
                        helper.setTextColor(R.id.item_pointtask_task,mContext.getResources().getColor(R.color.pointtask_Orange));
                        break;
                    case 2:
                        helper.setText(R.id.item_pointtask_task,"已完成");
                        helper.getView(R.id.item_pointtask_task).setBackground(mContext.getResources().getDrawable(R.drawable.item_pointtask_shape2));
                        helper.setTextColor(R.id.item_pointtask_task,mContext.getResources().getColor(R.color.white));
                        break;
                    case 3:
                        helper.setText(R.id.item_pointtask_task,"已领取");
                        helper.getView(R.id.item_pointtask_task).setBackground(mContext.getResources().getDrawable(R.drawable.item_pointtask_shape2));
                        helper.setTextColor(R.id.item_pointtask_task,mContext.getResources().getColor(R.color.white));
                        break;
                }
       //     ImgLoadUtils.loadImage(mContext, MainHttp.RES_STRING+item.getIcon(),(ImageView) helper.getView(R.id.item_pointtask_icon));
    }
}
