package work.lilei.words.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import work.lilei.words.R;
import work.lilei.words.base.BaseActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WriteActivity extends BaseActivity {

    // 字体展示
//    @BindView(R.id.tv_writing)
    private TextView mTvWriting;
    // 用户输入
//    @BindView(R.id.et_input)
    private EditText mEtInput;
    // 确认按钮
//    @BindView(R.id.btn_ok)
    private Button btnOK;

    // 字体
    private static final String FONT_CXD = "fonts/CXD_6763_Chinese.ttf";
    private static final String FONT_ELEPHANT = "fonts/Elephant_Handwritten_Chinese.ttf";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        initViews();
        setFont(mTvWriting, FONT_ELEPHANT);
    }

    private void initViews() {
        mTvWriting = (TextView) findViewById(R.id.tv_writing);
        mTvWriting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WriteActivity.this, DisplayActivity.class);
                intent.putExtra("text", mTvWriting.getText());
                startActivity(intent);
            }
        });
        mEtInput = (EditText) findViewById(R.id.et_input);
        btnOK = (Button) findViewById(R.id.btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayHandWriting();
            }
        });
    }

    // 设置字体
    private void setFont(TextView tv, String fontFilePath) {
        Typeface tf = Typeface.createFromAsset(getAssets(), fontFilePath);
        tv.setTypeface(tf);
    }

    // 点击后更新TV
//    @OnClick(R.id.btn_ok)
    private void displayHandWriting() {
        String inp = mEtInput.getText().toString();
        if (TextUtils.isEmpty(inp)) {
            toastShort("未输入文字");
            mTvWriting.setClickable(false);
        } else {
            mTvWriting.setText(inp);
            mTvWriting.setClickable(true);
        }
    }
}
