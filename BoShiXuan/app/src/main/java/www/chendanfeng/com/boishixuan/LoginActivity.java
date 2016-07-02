package www.chendanfeng.com.boishixuan;


import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.util.LogUtil;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.login_user)
    RadioGroup userTab;
    @Bind(R.id.login_password)
    RadioButton passwordTab;
    @Bind(R.id.login_press)
    RadioButton pressTab;
    @Bind(R.id.login_forget)
    RadioButton forgetTab;
    @Bind(R.id.login_register)
    RadioButton registerTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);;
    }
}
