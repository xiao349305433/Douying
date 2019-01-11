package example.com.douying.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.HomesModel;
import example.com.douying.model.PartBean;


public class HomesAdapter extends BaseQuickAdapter<HomesModel.DataBean,BaseViewHolder> {
    public HomesAdapter( @Nullable List<HomesModel.DataBean> data) {
        super(R.layout.item_homes, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomesModel.DataBean item) {
        helper.setText(R.id.item_homes_name,item.getTitle());
//       ImageLoader.with(mContext,item.getPoster(),helper.getView(R.id.item_homes_img));
        ImgLoadUtils.setBg(mContext, MainHttp.RES_STRING+item.getPoster(),helper.getView(R.id.item_homes_img));
        List<PartBean> list=new Gson().fromJson(item.getPart(), new TypeToken<List<PartBean>>() {}.getType());
        helper.setText(R.id.item_homes_tv,list.size()+"");
        if (list.size()>1){
            helper.getView(R.id.item_homes_tv).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.item_homes_tv).setVisibility(View.GONE);
        }



    }
}
