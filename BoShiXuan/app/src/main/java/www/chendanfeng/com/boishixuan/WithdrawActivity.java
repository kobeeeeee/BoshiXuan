package www.chendanfeng.com.boishixuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.model.ModifyPswResponse;
import www.chendanfeng.com.network.model.RegularResponse;
import www.chendanfeng.com.network.model.WithDrawResponse;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class WithdrawActivity extends BaseActivity {
    public static final int TYPE_SELECT = 1;
    public static final int TYPE_WITHDRAW = 2;
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.arrowLayout)
    RelativeLayout mArrowLayout;
    @Bind(R.id.withdrawBtn)
    ImageView mWithdrawBtn;
    @Bind(R.id.bankNo)
    TextView mBankNo;
    @Bind(R.id.bankName)
    TextView mBankName;
    @Bind(R.id.balanceInput)
    EditText mBalanceInput;
    private NetWorkCallBack mNetWorkCallBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);
        initHeader();
        initOnClick();
    }
    private void initHeader() {
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("提现");

        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initOnClick() {
        this.mArrowLayout.setOnClickListener(new MyOnClickListener(TYPE_SELECT));
        this.mWithdrawBtn.setOnClickListener(new MyOnClickListener(TYPE_WITHDRAW));
    }
    class MyOnClickListener implements  View.OnClickListener{
        private int mType;
        public MyOnClickListener(int type) {
            this.mType = type;
        }
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (this.mType) {
                case TYPE_SELECT:
                    intent = new Intent(WithdrawActivity.this,BankCardSelectActivity.class);
                    startActivity(intent);
                    break;
                case TYPE_WITHDRAW:
                    confirmWithDraw();
                    break;
            }
        }
    }
    private void confirmWithDraw() {
        this.mNetWorkCallBack = new NetWorkCallBack();
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String userId = userInfoBean.getCustId();
        String userPhone = userInfoBean.getCustMobile();
        String payPsw = userInfoBean.getPayPsw();
        String fetchMoney = this.mBalanceInput.getText().toString();
        String cardNumber = this.mBankNo.getText().toString();
        String bankNumber = this.mBankName.getText().toString();
        String bankId = "";//TODO 银行卡ID

        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("fetch_money",fetchMoney);
        map.put("card_number",cardNumber);
        map.put("user_id",userId);
        map.put("user_phone",userPhone);
        map.put("pay_passwd",payPsw);
        map.put("bank_name",cardNumber);
        map.put("bank_name",bankNumber);
        map.put("bank_id",bankId);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_FETCH_CASH,map,WithdrawActivity.this.mNetWorkCallBack, WithDrawResponse.class);

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
            if(object instanceof WithDrawResponse) {
                WithDrawResponse withDrawResponse = (WithDrawResponse)object;
                LogUtil.i(this,"withDrawResponse = " + withDrawResponse);
                Toast toast = Toast.makeText(WithdrawActivity.this,"提现成功",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                finish();
            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }

}
