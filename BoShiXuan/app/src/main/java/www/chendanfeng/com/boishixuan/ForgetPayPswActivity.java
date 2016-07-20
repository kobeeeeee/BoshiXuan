package www.chendanfeng.com.boishixuan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.bean.UserInfoBean;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.network.RegisterResponse;
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.model.ModifyPswResponse;
import www.chendanfeng.com.util.CommonUtil;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class ForgetPayPswActivity extends BaseActivity{
    public static final int TYPE_SEND_VERIFY_CODE = 1;
    public static final int TYPE_MODIFY_PASSWORD = 2;
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.phoneNoText)
    EditText mPhoneNoText;
    @Bind(R.id.modifyPayPsw)
    ImageView mModifyBtn;
    @Bind(R.id.verifyCodeText)
    EditText verifyCodeText;
    @Bind(R.id.newPswText)
    EditText mNewPswText;
    @Bind(R.id.confirmPswText)
    EditText mConfirmPswText;
    @Bind(R.id.buttonCode)
    ImageView mCodeBtn;
    public NetWorkCallBack mNetWorkCallBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pay_psw);
        ButterKnife.bind(this);
        initHeader();
        this.mNetWorkCallBack = new NetWorkCallBack();
        this.mModifyBtn.setOnClickListener(new MyClickListener(TYPE_SEND_VERIFY_CODE))
        ;this.mCodeBtn.setOnClickListener(new MyClickListener(TYPE_MODIFY_PASSWORD));
    }
    private void initHeader() {
        this.mHeader.setText("忘记支付密码");
        this.mHeader.setVisibility(View.VISIBLE);
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void modifyClick() {
        String phoneNo = mPhoneNoText.getText().toString();
        String newPsw = mNewPswText.getText().toString();
        String confirmPassword = mConfirmPswText.getText().toString();
        String verifyCode = verifyCodeText.getText().toString();
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String phoneNumber = userInfoBean.getCustMobile();
        if(TextUtils.isEmpty(phoneNo)) {
            Toast toast = Toast.makeText(ForgetPayPswActivity.this,"请输入手机号",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(!phoneNo.equals(phoneNumber)) {
            Toast toast = Toast.makeText(ForgetPayPswActivity.this,"手机号码输入有误",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(TextUtils.isEmpty(newPsw)) {
            Toast toast = Toast.makeText(ForgetPayPswActivity.this,"请输入新密码",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(TextUtils.isEmpty(confirmPassword)) {
            Toast toast = Toast.makeText(ForgetPayPswActivity.this,"请输入确认密码",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(!newPsw.equals(confirmPassword)){
            Toast toast = Toast.makeText(ForgetPayPswActivity.this,"两次输入密码不一致",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(!CommonUtil.checkPassword(confirmPassword)){
            Toast toast = Toast.makeText(ForgetPayPswActivity.this,"密码长度为6-20位字母或有效数字组成",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(TextUtils.isEmpty(verifyCode)) {
            Toast toast = Toast.makeText(ForgetPayPswActivity.this,"请输入验证码",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("passwd_type","2");
        map.put("modify_type","2");
        map.put("old_passwd",verifyCode);
        map.put("new_passwd",newPsw);
        map.put("user_phone",phoneNo);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_MODIFY_PWD,map,ForgetPayPswActivity.this.mNetWorkCallBack, ModifyPswResponse.class);

    }
    private void sendVerifyCode(){
        String phoneNo = mPhoneNoText.getText().toString();
        Map<String,Object> map = new HashMap<>();
        map.put("user_phone",phoneNo);
        if(TextUtils.isEmpty(phoneNo)) {
            Toast toast = Toast.makeText(ForgetPayPswActivity.this,"请输入手机号",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_VERIFY_CODE,map,ForgetPayPswActivity.this.mNetWorkCallBack, RegisterResponse.class);
    }
    private class NetWorkCallBack implements RequestListener {

        @Override
        public void onBegin() {

        }

        @Override
        public void onResponse(Object object) {
            if(object == null) {
                return;
            }
            if (object instanceof RegisterResponse) {
                RegisterResponse registerResponse = (RegisterResponse)object;
                LogUtil.i(this,"registerResponse = " + registerResponse);
            }
            if(object instanceof ModifyPswResponse) {
                ModifyPswResponse modifyPswResponse = (ModifyPswResponse)object;
                LogUtil.i(this,"modifyPswResponse = " + modifyPswResponse);
                Toast toast = Toast.makeText(ForgetPayPswActivity.this,"密码修改成功",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                finish();
            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }
    class MyClickListener implements View.OnClickListener {
        private int mType;
        public MyClickListener(int type) {
            this.mType = type;
        }
        @Override
        public void onClick(View view) {
            switch (this.mType) {
                case TYPE_SEND_VERIFY_CODE:
                    sendVerifyCode();
                    break;
                case TYPE_MODIFY_PASSWORD:
                    modifyClick();
                    break;
            }
        }
    }
}
