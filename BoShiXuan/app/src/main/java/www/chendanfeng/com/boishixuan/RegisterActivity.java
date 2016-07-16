package www.chendanfeng.com.boishixuan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.network.RegisterResponse;
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.WebServiceApi;
import www.chendanfeng.com.util.CommonUtil;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by yangln on 2016/7/3.
 */
public class RegisterActivity extends BaseActivity {
    public static final int TYPE_SEND_VERIFY_CODE = 1;
    public static final int TYPE_REGISTER = 2;
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
    @Bind(R.id.checkAgree)
    CheckBox checkAgreeBox;
    @Bind(R.id.register)
    ImageView registerButton;
    public NetWorkCallBack mNetWorkCallBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        codeButton.setOnClickListener(new MyOnClickListener(TYPE_SEND_VERIFY_CODE));
        this.mNetWorkCallBack = new NetWorkCallBack();
        registerButton.setOnClickListener(new MyOnClickListener(TYPE_REGISTER));
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

    class MyOnClickListener implements  View.OnClickListener {
        public int mType;
        public MyOnClickListener(int type) {
            this.mType = type;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (this.mType) {
                case TYPE_SEND_VERIFY_CODE:
                    Map<String,Object> map = new HashMap<>();
                    map.put("user_phone","18757118127");
                    RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_VERIFY_CODE,map,RegisterActivity.this.mNetWorkCallBack, RegisterResponse.class);
                    break;
                case TYPE_REGISTER:
                    String phoneNumber = phoneEditText.getText().toString();
                    String password = passwordEditText.getText().toString();
                    String confirmPassword = confirmPasswordEditText.getText().toString();
                    String code = CodeEditText.getText().toString();
                    LogUtil.i(this,"register");
                    LogUtil.i(this,phoneNumber+"test");
                    LogUtil.i(this,password+"test1");
                    LogUtil.i(this,confirmPassword+"test2");
                    if(!checkPhoneNumber(phoneNumber)) {
                        Toast toast = Toast.makeText(RegisterActivity.this,"无效的手机号码",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        break;
                    }
                    if(!checkPassword(password)){
                        Toast toast = Toast.makeText(RegisterActivity.this,"密码长度为6-20位字母或有效数字组成",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        break;
                    }
                    if(!password.equals(confirmPassword)){
                        Toast toast = Toast.makeText(RegisterActivity.this,"两次输入密码不一致",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        break;
                    }
                    if(!checkAgreeBox.isChecked()){
                        Toast toast = Toast.makeText(RegisterActivity.this,"请先同意支付协议",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        break;
                    }
                    //TODO:获取验证码
                    //TODO:输入的验证码和获取到的验证码不一致，则报错“请输入正确的验证码”
                    Toast toast = Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    intent.setClass(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
    private class NetWorkCallBack implements RequestListener {

        @Override
        public void onBegin() {

        }

        @Override
        public void onResponse(Object object) {
            if (object != null && object instanceof RegisterResponse) {
                RegisterResponse registerResponse = (RegisterResponse)object;
                LogUtil.i(this,"registerResponse = " + registerResponse);
            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }
}

