package www.chendanfeng.com.boishixuan;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangln10784 on 2016/7/9.
 */
public class AboutActivity extends BaseActivity {
    @Bind(R.id.agree)
    TextView agreeText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        agreeText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        agreeText.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
