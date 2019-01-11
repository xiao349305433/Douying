package example.com.douying.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.ItemImageLongClickListener;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.MyJZVideoPlayerStandard;

import douying.example.com.mylibrary.view.utils.EncodeUtils;
import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.adapter.CommentExpandAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.http.MainHttp;
import example.com.douying.model.BaseM;

import example.com.douying.model.GetArticlesListM;

import example.com.douying.model.GetPostReplyM;
import example.com.douying.utils.ToastUtil;
import example.com.douying.view.CommentExpandableListView;
import example.com.douying.view.ExpandableTextView;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2018/12/7.
 */

public class CircleInfoActivity extends BaseActivity {
    @BindView(R.id.detail_page_lv_comment)
     CommentExpandableListView expandableListView;
    @BindView(R.id.circleinfo_circle_praise)
    ImageView circleinfo_circle_praise;
    @BindView(R.id.circleinfo_title)
    TextView circleinfo_title;
    @BindView(R.id.detail_page_userLogo)
    ImageView detail_page_userLogo;
    private CommentExpandAdapter adapter;
    private BottomSheetDialog dialog;
    GetArticlesListM.DataBean dataBean;
    boolean IsPraise=false;
    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        initView();

    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_circleinfo2;
    }

     @OnClick({R.id.circleinfo_detail_page_do_comment,R.id.circleinfo_comment,R.id.circleinfo_circle_praise})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.circleinfo_detail_page_do_comment:
                showCommentDialog();
                break;
            case R.id.circleinfo_comment:
                showCommentDialog();
                break;
            case R.id.circleinfo_circle_praise:
                if(IsPraise){
                    IsPraise = false;
                    takeAction("off",dataBean.getId(),dataBean.getUid());
                }   else {
                    IsPraise = true;
                    takeAction("off",dataBean.getId(),dataBean.getUid());
                }


                break;

        }
     }

    private void initView() {
        dataBean = getIntent().getParcelableExtra("DataBean");
        circleinfo_title.setText(dataBean.getTruename());
        ImgLoadUtils.loadCirthumbnail(this,dataBean.getPic(),detail_page_userLogo);
        TextView name = findViewById(R.id.detail_page_userName);
        TextView time = findViewById(R.id.detail_page_time);
        ExpandableTextView content =findViewById(R.id.detail_page_expandabletv);
        NineGridImageView<String> nineGridImageView =findViewById(R.id.detail_page_images);
        MyJZVideoPlayerStandard actor_mjvpst = findViewById(R.id.detail_page_video);
        name.setText(dataBean.getTruename());
        time.setText(dataBean.getAdd_time());
        //文本
        if (dataBean.getContent() != null) {
            content.setText(Utils.Base64toStr(dataBean.getContent()));
        } else {
            content.setVisibility(View.GONE);
        }
        //图片九宫格
        if (dataBean.getImgs() != null) {
            nineGridImageView.setVisibility(View.VISIBLE);
            List<String> listimg = new ArrayList<>();
            for (int i = 0; i < dataBean.getImgs().split("@").length; i++) {
                listimg.add(MainHttp.RES_STRING+dataBean.getImgs().split("@")[i]);
            }

            nineGridImageView.setAdapter(mAdapter);
            nineGridImageView.setImagesData(listimg);
            nineGridImageView.setItemImageClickListener(new ItemImageClickListener<String>() {
                @Override
                public void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
                    Log.d("onItemImageClick", list.get(index));
                }
            });
            nineGridImageView.setItemImageLongClickListener(new ItemImageLongClickListener<String>() {
                @Override
                public boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<String> list) {
                    Log.d("onItemImageLongClick", list.get(index));
                    return true;
                }
            });
        } else {
            nineGridImageView.setVisibility(View.GONE);
        }

        //视频
        if (dataBean.getVideo() != null) {
            actor_mjvpst.setUp(MainHttp.RES_STRING + dataBean.getVideo(), actor_mjvpst.SCREEN_WINDOW_NORMAL, "");
            ImgLoadUtils.loadVideoScreenshot(this,MainHttp.RES_STRING+dataBean.getVideo(),actor_mjvpst.thumbImageView,1);
        } else {
            actor_mjvpst.setVisibility(View.GONE);
        }

        if(dataBean.getIs_praise().equals("on")){
            IsPraise = true;
            circleinfo_circle_praise.setColorFilter( Color.parseColor("#FF5C5C"));
        }   else {
            IsPraise = true;
            circleinfo_circle_praise.setColorFilter( Color.parseColor("#FFFFFF"));
        }

        getdata(0);
    }

    private void getdata(int page) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("aid", dataBean.getId());
        httpParams.put("area", 2);
        if (page == 0) {
        } else {
            httpParams.put("page", page);
        }
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpGet(this, "Articles/postReply", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 200:
                        GetPostReplyM getPostReplyM=new Gson().fromJson(response.body(),GetPostReplyM.class);
                        initExpandableListView(getPostReplyM.getData());
                        break;
                    default:
                        ToastUtil.show(CircleInfoActivity.this, baseM.getInfo());
                        break;
                }
            }
        });
    }

    private void takeAction(String action, int aid, int to_uid) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("area", 2);
        httpParams.put("action", action);//on为确认，off为取消
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("aid", aid); //帖子id
        httpParams.put("to_uid", to_uid);
        mainHttp.HttpGet(this, "Articles/takeAction", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 206:
                        if(action.equals("on")){
                            IsPraise = true;
                            circleinfo_circle_praise.setColorFilter( Color.parseColor("#FF5C5C"));
                        }else {
                            IsPraise = false;
                            circleinfo_circle_praise.setColorFilter( Color.parseColor("#FFFFFF"));
                        }
                        ToastUtil.show(CircleInfoActivity.this, baseM.getInfo());
                        break;
                    default:
                        ToastUtil.show(CircleInfoActivity.this, baseM.getInfo());
                        break;
                }
            }
        });
    }


    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(final List<GetPostReplyM.DataBean> commentList){
        expandableListView.setGroupIndicator(null);
        //默认展开所有回复
        adapter = new CommentExpandAdapter(this, commentList);
        expandableListView.setAdapter(adapter);
        for(int i = 0; i<commentList.size(); i++){
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
             showReplyDialog(commentList.get(groupPosition));
                return true;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Intent intent=new Intent(CircleInfoActivity.this,SubActivity.class);
                intent.putExtra("DataBean",commentList.get(groupPosition));
                startActivity(intent);
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //toast("展开第"+groupPosition+"个分组");
            }
        });
    }

    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){
                    addReply(commentContent,1,dataBean.getUid(),dataBean.getId());
                    //commentOnWork(commentContent);
                }else {
                    Toast.makeText(CircleInfoActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }



    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private void showReplyDialog(GetPostReplyM.DataBean dataBean){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint("回复 " + dataBean.getTruename() + " 的评论:");
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(replyContent)){
                    addReply(replyContent,2,dataBean.getUid(),dataBean.getId());
                }else {
                    Toast.makeText(CircleInfoActivity.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    //添加回复和评论
    private void addReply(String content,int send,int to_uid,int to_id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("aid", dataBean.getId());
        httpParams.put("content",EncodeUtils.urlEncode(Utils.getBase64(content),"UTF-8"));
        httpParams.put("to_uid", to_uid);
        httpParams.put("send",send);//1为作品,2为人
        httpParams.put("area", 2);//1 为作品 2为帖子
        httpParams.put("to_id",to_id);
        switch (send){
            case 1:
                httpParams.put("from_id",dataBean.getId());
                break;
            case 2:
                httpParams.put("from_id",to_id);
                break;
        }

        mainHttp.HttpPost(this, "Articles/addReply", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 206:
                        ToastUtil.show(CircleInfoActivity.this, baseM.getInfo());
                        dialog.dismiss();
                        getdata(0);
                        break;
                    default:
                        ToastUtil.show(CircleInfoActivity.this, baseM.getInfo());

                        break;

                }
            }
        });
    }



    NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String s) {
            ImgLoadUtils.loadImage(CircleInfoActivity.this, s, imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
            startPhotoActivity(CircleInfoActivity.this,imageView,list,index);
        }

        @Override
        protected boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<String> list) {
            Toast.makeText(context, "image long click position is " + index, Toast.LENGTH_SHORT).show();
            return true;
        }
    };


    public void startPhotoActivity(Context context, ImageView imageView,List<String> mlist,int index) {
        Intent intent = new Intent(context, DragPhotoActivity.class);
        int location[] = new int[2];

        imageView.getLocationOnScreen(location);
        intent.putExtra("left", location[0]);
        intent.putExtra("top", location[1]);
        intent.putExtra("height", imageView.getHeight());
        intent.putExtra("width", imageView.getWidth());
        intent.putExtra("mList",new Gson().toJson(mlist));
        intent.putExtra("index",index);
        context.startActivity(intent);
        overridePendingTransition(0,0);
    }

}
