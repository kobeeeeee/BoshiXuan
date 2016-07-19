package www.chendanfeng.com.boishixuan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.bean.UserInfoBean;
import www.chendanfeng.com.util.CommonUtil;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class ModifyLoginPswActivity extends BaseActivity{
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.modifyLoginPsw)
    ImageView mModifyBtn;
    @Bind(R.id.oldPswText)
    EditText mOldPswText;
    @Bind(R.id.newPswText)
    EditText mNewPswText;
    @Bind(R.id.confirmPswText)
    EditText mConfirmPswText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_login_psw);
        ButterKnife.bind(this);
        initHeader();
    }
    private void initHeader() {
        this.mHeader.setText("修改登录密码");
        this.mHeader.setVisibility(View.VISIBLE);
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initOnClickListener() {
        this.mModifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPsw = mOldPswText.getText().toString();
                String newPsw = mNewPswText.getText().toString();
                String confirmPassword = mConfirmPswText.getText().toString();
                UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
                String password = userInfoBean.getPassword();
                if(!password.equals(oldPsw)) {
                    Toast toast = Toast.makeText(ModifyLoginPswActivity.this,"原密码输入有误",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                if(!newPsw.equals(confirmPassword)){
                    Toast toast = Toast.makeText(ModifyLoginPswActivity.this,"两次输入密码不一致",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                if(!CommonUtil.checkPassword(confirmPassword)){
                    Toast toast = Toast.makeText(ModifyLoginPswActivity.this,"密码长度为6-20位字母或有效数字组成",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
            }
        });
    }
}
