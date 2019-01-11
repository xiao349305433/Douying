package example.com.douying.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.ArrayList;
import java.util.List;


import douying.example.com.mylibrary.view.utils.EncodeUtils;
import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.R;

import example.com.douying.model.GetPostReplyM;
import example.com.douying.model.ReplyDetailBean;

/**
 * Date:  18/12/6.
 * Desc: 评论与回复列表的适配器
 */

public class CommentExpandAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "CommentExpandAdapter";
    private List<GetPostReplyM.DataBean> commentBeanList;
    private List<GetPostReplyM.DataBean.SubBean> replyBeanList;
    private Context context;
    private int pageIndex = 1;

    public CommentExpandAdapter(Context context, List<GetPostReplyM.DataBean> commentBeanList) {
        this.context = context;
        this.commentBeanList = commentBeanList;
            inchildshow();
    }

    private void inchildshow(){
        for (int i = 0; i <commentBeanList.size() ; i++) {
            //判断评论的回复是否大于2，大于2的去掉后面的数据，在list的最后面加上 “共回复 commentBeanList.get(i).getSub().size() 条”
            if(commentBeanList.get(i).getSub().size()>2){
                GetPostReplyM.DataBean.SubBean subBean=new GetPostReplyM.DataBean.SubBean("",Utils.getBase64("共回复"+ commentBeanList.get(i).getSub().size()+"条"));
                  //  commentBeanList.get(i).getSub().subList(0,2);
                commentBeanList.get(i).getSub().add(2,subBean);
            }


        }

    }


    @Override
    public int getGroupCount() {
        return commentBeanList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if(commentBeanList.get(i).getSub() == null){
            return 0;
        }else {
            if(commentBeanList.get(i).getSub().size()>2){
                return 3;
            }else {
                return commentBeanList.get(i).getSub().size()>0 ? commentBeanList.get(i).getSub().size():0;
            }
        }

    }

    @Override
    public Object getGroup(int i) {
        return commentBeanList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return commentBeanList.get(i).getSub().get(i1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    boolean isLike = false;

    @Override
    public View getGroupView(final int groupPosition, boolean isExpand, View convertView, ViewGroup viewGroup) {
        final GroupHolder groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item_layout, viewGroup, false);
            groupHolder = new GroupHolder(convertView);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        ImgLoadUtils.loadCirthumbnail(context,commentBeanList.get(groupPosition).getPic(),groupHolder.logo);
        groupHolder.tv_name.setText(commentBeanList.get(groupPosition).getTruename());
        groupHolder.tv_time.setText(commentBeanList.get(groupPosition).getAdd_time());
        groupHolder.tv_content.setText(EncodeUtils.urlDecode(Utils.Base64toStr(commentBeanList.get(groupPosition).getContent())));
//        groupHolder.iv_like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(isLike){
//                    isLike = false;
//                    groupHolder.iv_like.setColorFilter(Color.parseColor("#aaaaaa"));
//                }else {
//                    isLike = true;
//                    groupHolder.iv_like.setColorFilter(Color.parseColor("#FF5C5C"));
//                }
//            }
//        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        final ChildHolder childHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_reply_item_layout,viewGroup, false);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        }
        else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        String replyUser = commentBeanList.get(groupPosition).getSub().get(childPosition).getTruename();
        if(!TextUtils.isEmpty(replyUser)){
            childHolder.tv_name.setText(replyUser + ":");
        } else if(replyUser.equals("")){
            childHolder.tv_name.setText("");
        }else {
            childHolder.tv_name.setText("无名"+":");
        }
        childHolder.tv_content.setText( EncodeUtils.urlDecode(Utils.Base64toStr(commentBeanList.get(groupPosition).getSub().get(childPosition).getContent())) );

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private class GroupHolder{
        private ImageView logo;
        private TextView tv_name, tv_content, tv_time;
//        private ImageView iv_like;
        public GroupHolder(View view) {
            logo = (ImageView) view.findViewById(R.id.comment_item_logo);
            tv_content = (TextView) view.findViewById(R.id.comment_item_content);
            tv_name = (TextView) view.findViewById(R.id.comment_item_userName);
            tv_time = (TextView) view.findViewById(R.id.comment_item_time);
//            iv_like = (ImageView) view.findViewById(R.id.comment_item_like);
        }
    }

    private class ChildHolder{
        private TextView tv_name, tv_content;
        public ChildHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.reply_item_user);
            tv_content = (TextView) view.findViewById(R.id.reply_item_content);
        }
    }







}
