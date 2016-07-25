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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.bean.UserInfoBean;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.model.CertificationResponse;
import www.chendanfeng.com.network.model.MessageListResponse;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by Administrator on 2016/7/10 0010.
 */
public class CertificationActivity extends BaseActivity{
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.realNameText)
    EditText mRealNameText;
    @Bind(R.id.identityText)
    EditText mIdentityText;
    @Bind(R.id.payPswText)
    EditText mPayPswText;
    @Bind(R.id.confirmBtn)
    ImageView mConfirmBtn;
    private NetWorkCallBack mNetWorkCallBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certication);
        ButterKnife.bind(this);
        initHeader();
        initClick();
    }
    private void initHeader(){
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("实名认证");
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initClick() {
        this.mNetWorkCallBack = new NetWorkCallBack();
        this.mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmClick();
            }
        });
    }
    private void confirmClick() {
        String realName = this.mRealNameText.getText().toString();
        String payPsw = this.mPayPswText.getText().toString();
        String identity = this.mIdentityText.getText().toString();
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String password = userInfoBean.getPayPsw();
        String name = userInfoBean.getUserName();
        if(TextUtils.isEmpty(realName)) {
            Toast toast = Toast.makeText(CertificationActivity.this,"请输入真实姓名",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(!name.equals(realName)) {
            Toast toast = Toast.makeText(CertificationActivity.this,"真实姓名输入有误",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(TextUtils.isEmpty(identity)) {
            Toast toast = Toast.makeText(CertificationActivity.this,"请输入身份证号码",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(identity.length() != 18) {
            Toast toast = Toast.makeText(CertificationActivity.this,"身份证号码输入不正确",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(TextUtils.isEmpty(payPsw)) {
            Toast toast = Toast.makeText(CertificationActivity.this,"请输入支付密码",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(!password.equals(payPsw)) {
            Toast toast = Toast.makeText(CertificationActivity.this,"支付密码不正确",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        String userId = userInfoBean.getCustId();
        String userPhone = userInfoBean.getCustMobile();

        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",userId);
        map.put("user_name",realName);
        map.put("idcard_num",identity);
        map.put("pay_passwd",password);
        map.put("user_phone",userPhone);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_REAL_NAME,map,CertificationActivity.this.mNetWorkCallBack, MessageListResponse.class);
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
            if(object instanceof CertificationResponse) {
                CertificationResponse certificationResponse = (CertificationResponse)object;
                LogUtil.i(this,"certificationResponse = " + certificationResponse);
            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }
}
