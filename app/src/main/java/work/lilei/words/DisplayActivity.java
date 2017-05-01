package work.lilei.words;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import work.lilei.words.base.BaseActivity;

/**
 * 展示
 */
public class DisplayActivity extends BaseActivity {

    // 字体展示
//    @BindView(R.id.tv_writing)
    private TextView mTvWriting;

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
        mTvWriting = (TextView) findViewById(R.id.tv_writing);
        mTvWriting.setText(getIntent().getStringExtra("text"));
        mBtnTakeScreenshot = (FloatingActionButton) findViewById(R.id.btn_take_screenshot);
        mBtnTakeScreenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    // 设置字体
    private void setFont(TextView tv, String fontFilePath) {
        Typeface tf = Typeface.createFromAsset(getAssets(), fontFilePath);
        tv.setTypeface(tf);
    }

    // 截图
    private void takeScreenshot() {
        // TODO
    }
}
