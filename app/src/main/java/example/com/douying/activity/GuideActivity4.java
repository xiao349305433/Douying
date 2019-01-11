package example.com.douying.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.douying.BaseActivity;
import example.com.douying.R;
import qiu.niorgai.StatusBarCompat;

public class GuideActivity4 extends BaseActivity {
    @BindView(R.id.guide4_img1)
    ImageView guide4_img1;
    @BindView(R.id.guide4_img2)
    ImageView guide4_img2;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_guide4;
    }


    @OnClick({R.id.guide4_img1, R.id.guide4_img2, R.id.guide4_next})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.guide4_img1:
                guide4_img1.setBackground(getResources().getDrawable(R.drawable.item_swtichactor_img_shape_red));
                guide4_img2.setBackground(null);
                break;
            case R.id.guide4_img2:
                guide4_img1.setBackground(null);
                guide4_img2.setBackground(getResources().getDrawable(R.drawable.item_swtichactor_img_shape_red));
                break;
            case R.id.guide4_next:
                startActivity(new Intent(this, GuideActivity5.class));
                break;
        }

    }
}
