package example.com.douying.view;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import example.com.douying.utils.ToastUtil;

/**
 * Created by admin on 2018/9/14.
 */

public class CodeTextView extends android.support.v7.widget.AppCompatTextView {
    private CountDownTimer timer;
    private Context mcontext;

    public CodeTextView(Context context) {
        super(context);
    }

    public CodeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext=context;
        setCountDown();
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtil.show(context, "发送成功");
//                timer.start();
//                setEnabled(false);
//            }
//        });
    }

    public CodeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void  start(){
        ToastUtil.show(mcontext, "发送成功");
        timer.start();
        setEnabled(false);
    }


    private void setCountDown() {
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                setText("发送");
                setEnabled(true);
                timer.cancel();
            }
        };
    }

}
