package www.chendanfeng.com.boishixuan;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangln10784 on 2016/7/9.
 */
public class AboutActivity extends BaseActivity {
    public static final int TYPE_AGREE = 1;
    @Bind(R.id.agree)
    TextView agreeText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        agreeText.setOnClickListener(new MyOnClickListener(TYPE_AGREE));
//        agreeText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
//        agreeText.setMovementMethod(LinkMovementMethod.getInstance());
    }
    class MyOnClickListener implements  View.OnClickListener{
        public int mType;
        public MyOnClickListener(int type) {
            this.mType = type;
        }
        @Override
        public void onClick(View v){
            Intent intent;
            switch (this.mType){
                case TYPE_AGREE:
                    intent = new Intent(AboutActivity.this,AgreementActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

}
