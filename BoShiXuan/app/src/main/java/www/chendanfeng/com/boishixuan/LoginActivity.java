package www.chendanfeng.com.boishixuan;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.bean.UserInfoBean;
import www.chendanfeng.com.util.LogUtil;

public class LoginActivity extends BaseActivity {
    public static final int TYPE_LOGIN = 1;
    public static final int TYPE_FORGET = 2;
    public static final int TYPE_REGISTER = 3;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Bind(R.id.input_user)
    EditText userEditText;
    @Bind(R.id.input_password)
    EditText passwordEditText;
    @Bind(R.id.login_press)
    ImageView loginButton;
    @Bind(R.id.login_forget)
    TextView forgetTextView;
    @Bind(R.id.login_register)
    TextView registerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initUserInfoBean();
        LogUtil.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        preferences = getSharedPreferences("firststart", Context.MODE_PRIVATE);
        if(preferences.getBoolean("firststart",true))
        {
            LogUtil.i(this,"logintest1");
            editor = preferences.edit();
            editor.putBoolean("firststart",false);
            editor.commit();
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),StartupActivity.class);
            startActivity(intent);
            this.finish();
        }
        registerTextView.setOnClickListener(new MyOnClickListener(TYPE_REGISTER));
        loginButton.setOnClickListener(new MyOnClickListener(TYPE_LOGIN));
        forgetTextView.setOnClickListener(new MyOnClickListener(TYPE_FORGET));
    }

    public static  boolean checkPhoneNumber(String phoneNumber){
        Pattern pattern = Pattern.compile("^1[0-9]{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static  boolean checkPassword(String password){
        Pattern pattern = Pattern.compile(".*[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
        //Pattern pattern = Pattern.compile("^[A-Za-z0-9]{6,20}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    /**
     * 当进入登录画面时，初始化userBean的信息
     */
    public void initUserInfoBean() {
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        userInfoBean.setSysType("1");

        String systemType = Build.VERSION.RELEASE;
        userInfoBean.setSysVersion(systemType);

        PackageManager manager;
        String applicationVersion = "";
        manager = getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getPackageName(),0);
            applicationVersion = String.valueOf(info.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        userInfoBean.setAppVersion(applicationVersion);

        String no = android.os.Build.VERSION.RELEASE;
        userInfoBean.setSysTerNo(no);
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
                    String phoneNumber = userEditText.getText().toString();
                    String password = passwordEditText.getText().toString();
                    if(!checkPhoneNumber(phoneNumber)) {
                        Toast toast = Toast.makeText(LoginActivity.this,"无效的手机号码",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        break;
                    }
                    //TODO:根据输入的用户名、密码调用接口校验用户名密码是否正确
                    //TODO 测试跳转主页面代码
                    intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                    break;
                case TYPE_FORGET:
                    intent = new Intent(LoginActivity.this,PasswordActivity.class);
                    startActivity(intent);
                    break;
                case TYPE_REGISTER:
                    intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
