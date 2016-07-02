package www.chendanfeng.com.boishixuan;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.util.LogUtil;

public class LoginActivity extends BaseActivity {
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
        ButterKnife.bind(this);;
    }
}
