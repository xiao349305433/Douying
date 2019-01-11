package example.com.douying.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.R;
import example.com.douying.http.MainHttp;
import example.com.douying.model.GetModelM2;


public class ModelAdapter extends BaseQuickAdapter<GetModelM2.DataBean, BaseViewHolder> {
    public ModelAdapter() {
        super(R.layout.item_model);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetModelM2.DataBean item) {
        helper.addOnClickListener(R.id.item_model_close_1);
        helper.addOnClickListener(R.id.item_model_close_2);
        helper.addOnClickListener(R.id.item_model_close_3);
        helper.addOnClickListener(R.id.item_model_img_1);
        helper.addOnClickListener(R.id.item_model_img_2);
        helper.addOnClickListener(R.id.item_model_img_3);
        helper.addOnClickListener(R.id.item_model_edit);
        //判断是否显示
        ImgLoadUtils.loadthumbnail(mContext, MainHttp.RES_STRING + item.getList().get(0).getUrl(), (ImageView) helper.getView(R.id.item_model_img_1));
        ImgLoadUtils.loadthumbnail(mContext, MainHttp.RES_STRING + item.getList().get(1).getUrl(), (ImageView) helper.getView(R.id.item_model_img_2));
        ImgLoadUtils.loadthumbnail(mContext, MainHttp.RES_STRING + item.getList().get(2).getUrl(), (ImageView) helper.getView(R.id.item_model_img_3));

        helper.setText(R.id.item_model_tv_1, item.getList().get(0).getTitle());
        helper.setText(R.id.item_model_tv_2, item.getList().get(1).getTitle());
        helper.setText(R.id.item_model_tv_3, item.getList().get(2).getTitle());

        helper.setText(R.id.item_model_title,item.getTitle());




        if(item.isEdit()){
            helper.getView(R.id.item_model_close_1).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_model_close_2).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_model_close_3).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_model_edit,"完成");
        }else {
            helper.getView(R.id.item_model_close_1).setVisibility(View.GONE);
            helper.getView(R.id.item_model_close_2).setVisibility(View.GONE);
            helper.getView(R.id.item_model_close_3).setVisibility(View.GONE);
            helper.setText(R.id.item_model_edit,"编辑");
        }



//        if(item.getList().get(0).isImgshow()){
//            helper.getView(R.id.item_model_img_1).setVisibility(View.VISIBLE);
//        }else {
//            helper.getView(R.id.item_model_img).setVisibility(View.GONE);
//        }

        //判断是否被删除
        if(item.getList().get(0).isDelete){
           ImgLoadUtils.loadImage(mContext,R.mipmap.tianjiada, helper.getView(R.id.item_model_img_1));
           helper.getView(R.id.item_model_close_1).setVisibility(View.GONE);
          //  Glide.with(mContext).load(R.mipmap.tianjiada).diskCacheStrategy(DiskCacheStrategy.SOURCE).into();
        }else {
            //判断是否有值
            if(item.getList().get(0)==null){
                ImgLoadUtils.loadImage(mContext,R.mipmap.tianjiada, helper.getView(R.id.item_model_img_1));
            }else {
                ImgLoadUtils.loadthumbnail(mContext, MainHttp.RES_STRING+item.getList().get(0).getUrl(), (ImageView) helper.getView(R.id.item_model_img_1));
            }
        }


        if(item.getList().get(1).isDelete){
            ImgLoadUtils.loadImage(mContext,R.mipmap.tianjiada, helper.getView(R.id.item_model_img_2));
            helper.getView(R.id.item_model_close_2).setVisibility(View.GONE);
            //  Glide.with(mContext).load(R.mipmap.tianjiada).diskCacheStrategy(DiskCacheStrategy.SOURCE).into();
        }else {
            //判断是否有值
            if(item.getList().get(1)==null){
                ImgLoadUtils.loadImage(mContext,R.mipmap.tianjiada, helper.getView(R.id.item_model_img_2));
            }else {
                ImgLoadUtils.loadthumbnail(mContext, MainHttp.RES_STRING+item.getList().get(0).getUrl(), (ImageView) helper.getView(R.id.item_model_img_2));
            }
        }

        if(item.getList().get(2).isDelete){
            ImgLoadUtils.loadImage(mContext,R.mipmap.tianjiada, helper.getView(R.id.item_model_img_3));
            helper.getView(R.id.item_model_close_3).setVisibility(View.GONE);
            //  Glide.with(mContext).load(R.mipmap.tianjiada).diskCacheStrategy(DiskCacheStrategy.SOURCE).into();
        }else {
            //判断是否有值
            if(item.getList().get(2)==null){
                ImgLoadUtils.loadImage(mContext,R.mipmap.tianjiada, helper.getView(R.id.item_model_img_3));
            }else {
                ImgLoadUtils.loadthumbnail(mContext, MainHttp.RES_STRING+item.getList().get(2).getUrl(), (ImageView) helper.getView(R.id.item_model_img_3));
            }
        }

    }
}
