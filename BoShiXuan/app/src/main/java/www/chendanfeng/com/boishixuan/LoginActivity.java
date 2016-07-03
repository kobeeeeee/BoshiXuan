package www.chendanfeng.com.boishixuan;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.util.LogUtil;

public class LoginActivity extends BaseActivity {
    public static final int TYPE_LOGIN = 1;
    public static final int TYPE_FORGET = 2;
    public static final int TYPE_REGISTER = 3;
    @Bind(R.id.login_user)
    EditText userEditText;
    @Bind(R.id.login_password)
    EditText passwordEditText;
    @Bind(R.id.login_press)
    ImageView loginButton;
    @Bind(R.id.login_forget)
    TextView forgetTextView;
    @Bind(R.id.login_register)
    TextView registerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        registerTextView.setOnClickListener(new MyOnClickListener(TYPE_REGISTER));
        loginButton.setOnClickListener(new MyOnClickListener(TYPE_LOGIN));
        forgetTextView.setOnClickListener(new MyOnClickListener(TYPE_FORGET));
    }
    class MyOnClickListener implements  View.OnClickListener {
        public int mType;
        public MyOnClickListener(int type) {
            this.mType = type;
        }
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (this.mType) {
                case TYPE_LOGIN:
                    //TODO 测试跳转主页面代码
                    intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                    break;
                case TYPE_FORGET:
                    break;
                case TYPE_REGISTER:
                    intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
