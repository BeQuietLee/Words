package work.lilei.words.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import work.lilei.words.R;
import work.lilei.words.base.BaseActivity;
import work.lilei.words.util.ViewUtil;

/**
 * 展示
 */
public class DisplayActivity extends BaseActivity {

    // 全屏
    private View mLayoutPage;

    // 字体展示
//    @BindView(R.id.tv_writing)
    private TextView mTvWriting;

    // anim_screenshot handler
    private Handler handler = new Handler();


    // 截图按钮
    private FloatingActionButton mBtnTakeScreenshot;

    // 字体
    private static final String FONT_CXD = "fonts/CXD_6763_Chinese.ttf";
    private static final String FONT_ELEPHANT = "fonts/Elephant_Handwritten_Chinese.ttf";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_display);
        initViews();
        setFont(mTvWriting, FONT_ELEPHANT);
    }

    private void initViews() {
        mLayoutPage = findViewById(R.id.layout_wholepage);

        mTvWriting = (TextView) findViewById(R.id.tv_writing);
        mTvWriting.setText(getIntent().getStringExtra("text"));
        mBtnTakeScreenshot = (FloatingActionButton) findViewById(R.id.btn_take_screenshot);
        mBtnTakeScreenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenshot();
            }
        });
    }

    // 设置字体
    private void setFont(TextView tv, String fontFilePath) {
        Typeface tf = Typeface.createFromAsset(getAssets(), fontFilePath);
        tv.setTypeface(tf);
    }

    // 截屏
    private void takeScreenshot() {
        mBtnTakeScreenshot.hide();
        ViewUtil.showAnimation(this, mLayoutPage, AnimationUtils.currentAnimationTimeMillis() + 300);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewUtil.takeScreenshot(DisplayActivity.this, mLayoutPage);
//                ViewUtil.takeScreenshot(DisplayActivity.this, getWindow().getDecorView().getRootView());
                mBtnTakeScreenshot.show();
            }
        }, 800);
    }
}
