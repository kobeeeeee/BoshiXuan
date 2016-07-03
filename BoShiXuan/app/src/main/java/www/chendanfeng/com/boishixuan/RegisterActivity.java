package www.chendanfeng.com.boishixuan;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by yangln on 2016/7/3.
 */
public class RegisterActivity extends BaseActivity {
    @Bind(R.id.inputPhone)
    EditText phoneEditText;
    @Bind(R.id.inputPassword)
    EditText passwordEditText;
    @Bind(R.id.confirmPassword)
    EditText confirmPasswordEditText;
    @Bind(R.id.inputCode)
    EditText CodeEditText;
    @Bind(R.id.buttonCode)
    ImageView codeButton;
    @Bind(R.id.checkagree)
    CheckBox checkagreeBox;
    @Bind(R.id.register)
    ImageView registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }
}

