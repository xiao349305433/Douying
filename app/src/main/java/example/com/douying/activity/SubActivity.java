package example.com.douying.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.utils.EncodeUtils;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;

import example.com.douying.adapter.SubAdapter;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.BaseM;
import example.com.douying.model.GetPostReplyM;
import example.com.douying.utils.ToastUtil;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2018/12/10.
 * 评论展开页面
 */

public class SubActivity extends BaseActivity {
    @BindView(R.id.sub_rv)
    RecyclerView sub_rv;
    @BindView(R.id.sub_title)
    TextView sub_title;
    SubAdapter subAdapter;
    View headerview;
    private BottomSheetDialog dialog;
    GetPostReplyM.DataBean dataBean;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        getdata();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_sub;
    }

    @OnClick({R.id.detail_page_do_comment})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.detail_page_do_comment:
                showCommentDialog();
                break;
        }
    }

    private void getdata() {
        dataBean = getIntent().getParcelableExtra("DataBean");
        headerview = getLayoutInflater().inflate(R.layout.sub_headerview, (ViewGroup) sub_rv.getParent(), false);
        ImageView imageView = headerview.findViewById(R.id.sub_avatar);
        TextView name = headerview.findViewById(R.id.sub_name);
        TextView time=headerview.findViewById(R.id.sub_time);
        TextView content=headerview.findViewById(R.id.sub_tv);
        TextView back=headerview.findViewById(R.id.sub_back);
        name.setText(dataBean.getTruename());
        time.setText(dataBean.getAdd_time());
        content.setText(EncodeUtils.urlDecode(Utils.Base64toStr(dataBean.getContent())));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sub_title.setText(dataBean.getSub().size() + "条回复");
        subAdapter = new SubAdapter(dataBean.getSub());
        sub_rv.setLayoutManager(new LinearLayoutManager(this));
        sub_rv.setAdapter(subAdapter);
        subAdapter.addHeaderView(headerview);
        subAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showReplyDialog(dataBean.getSub().get(position));
            }
        });
    }


    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private void showReplyDialog(GetPostReplyM.DataBean.SubBean subBean){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint("回复 " + subBean.getTruename() + " 的评论:");
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(replyContent)){
                    addReply(replyContent,2,subBean.getUid(),subBean.getId());
                }else {
                    Toast.makeText(SubActivity.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
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


    private void addReply(String content,int send,int to_uid,int to_id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        httpParams.put("aid", dataBean.getAid());
        httpParams.put("content", EncodeUtils.urlEncode(Utils.getBase64(content),"UTF-8"));
        httpParams.put("to_uid", to_uid);
        httpParams.put("send",send);//1为作品,2为人
        httpParams.put("area", 2);//1 为作品 2为帖子
        httpParams.put("to_id",to_id);
        httpParams.put("from_id",to_id);
        mainHttp.HttpPost(this, "Articles/addReply", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseM baseM = new Gson().fromJson(response.body(), BaseM.class);
                switch (baseM.getCode()) {
                    case 206:
                        ToastUtil.show(SubActivity.this, baseM.getInfo());
                        dialog.dismiss();
                        break;
                    default:
                        ToastUtil.show(SubActivity.this, baseM.getInfo());

                        break;

                }
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
                    Toast.makeText(SubActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
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

}
