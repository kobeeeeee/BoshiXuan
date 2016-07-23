package www.chendanfeng.com.boishixuan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.bean.UserInfoBean;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.model.BankAddResponse;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class BankCardAddActivity extends BaseActivity{
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.userName)
    TextView mUserName;
    @Bind(R.id.bankNo)
    EditText mBankNo;
    @Bind(R.id.bankName)
    EditText mBankName;
    private NetWorkCallBack mNetWorkCallBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_add);
        ButterKnife.bind(this);
        initHeader();
        initLayout();
        getData();
    }
    private void initHeader() {
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("添加银行卡");
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initLayout() {
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String userName = userInfoBean.getUserName();
        this.mUserName.setText(userName);
    }
    private void getData() {
        this.mNetWorkCallBack = new NetWorkCallBack();
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String userId = userInfoBean.getCustId();
        String bankNo = this.mBankNo.getText().toString();
        String bankName = this.mBankName.getText().toString();
        String userName = this.mUserName.getText().toString();
        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("card_number",bankNo);
        map.put("bank_name",bankName);
        map.put("user_name",userName);
        map.put("user_id",userId);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_BANDING_BANK,map,BankCardAddActivity.this.mNetWorkCallBack, BankAddResponse.class);


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
            if(object instanceof BankAddResponse) {
                BankAddResponse bankAddResponse = (BankAddResponse)object;
                LogUtil.i(this,"bankListResponse = " + bankAddResponse);
            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }
}
