package example.com.douying.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.List;

import cn.jzvd.MyJZVideoPlayerStandard;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.R;
import example.com.douying.activitys.SwtichActorActivityS;
import example.com.douying.http.MainHttp;
import example.com.douying.model.PartBean;

/**
 * Created by admin on 2018/10/17.
 */

public class ActorViewPagerAdapter extends PagerAdapter {
    Context mContext;
    List<PartBean> mListPart;
    public ActorViewPagerAdapter(Context context, List<PartBean> ListPart){
        this.mContext=context;
        this.mListPart=ListPart;
    }
    @Override
    public int getCount() {
        return mListPart.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_actorvideo, null);
        MyJZVideoPlayerStandard actor_mjvpst=view.findViewById(R.id.item_mjvpst);
        ImgLoadUtils.loadImage(mContext,MainHttp.RES_STRING+mListPart.get(position).getPlayposter(),actor_mjvpst.thumbImageView);
        //ImageLoader.with(mContext,MainHttp.RES_STRING+mListPart.get(position).getPlayposter(),actor_mjvpst.thumbImageView);
         actor_mjvpst.setUp(MainHttp.RES_STRING+mListPart.get(position).getPlay(), actor_mjvpst.SCREEN_WINDOW_NORMAL,"");
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        container.removeView(container.getChildAt(position));
    }
}
