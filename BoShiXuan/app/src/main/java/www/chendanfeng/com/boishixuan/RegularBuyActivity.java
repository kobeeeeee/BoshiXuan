package www.chendanfeng.com.boishixuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import www.chendanfeng.com.network.model.MessageListResponse;
import www.chendanfeng.com.network.model.OrderResponse;
import www.chendanfeng.com.network.model.RegularBuyResponse;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public class RegularBuyActivity extends BaseActivity{
    @Bind(R.id.checkProtocolBtn)
    ImageView mCheckProtocolBtn;
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.regularCheckbox)
    ImageView mRegularCheckbox;
    @Bind(R.id.regularBuyBtn)
    ImageView mRegularBuyBtn;
    @Bind(R.id.inputBuyMoney)
    EditText mInputBuyMoney;
    private boolean isChecked = false;
    private NetWorkCallBack mNetWorkCallBack;
    @Bind(R.id.regularBuyMin)
    TextView mRegularBuyMin;
    @Bind(R.id.regularYearIncome)
    TextView mRegularYearIncome;
    @Bind(R.id.regularBuyDay)
    TextView mRegularBuyDay;
    @Bind(R.id.accountBalance)
    TextView mAccountBalance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_buy);
        ButterKnife.bind(this);
        initHeader();
        initClick();
        initData();
    }
    private void initHeader() {
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("购买");
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initData() {
        Intent intent = getIntent();
        String interestRate = intent.getStringExtra("interestRate");
        String investDay = intent.getStringExtra("investDay");
        String investMoney = intent.getStringExtra("investMoney");
        this.mRegularBuyDay.setText(investDay);
        this.mRegularBuyMin.setText(investMoney);
        this.mRegularYearIncome.setText(interestRate);
    }
    private void initClick() {
        this.mNetWorkCallBack = new NetWorkCallBack();
        this.mCheckProtocolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegularBuyActivity.this,PayProtocolActivity.class);
                startActivity(intent);
            }
        });
        this.mRegularCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isChecked) {
                    isChecked = false;
                    RegularBuyActivity.this.mRegularCheckbox.setImageResource(R.drawable.regular_checkbox_normal);
                } else {
                    isChecked = true;
                    RegularBuyActivity.this.mRegularCheckbox.setImageResource(R.drawable.regular_checkbox_press);
                }
            }
        });
        this.mRegularBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmBuy();
            }
        });
    }
    private void confirmBuy() {
        Intent intent = getIntent();
        String productId = intent.getStringExtra("productId");
        String productName = intent.getStringExtra("productName");

        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String userId = userInfoBean.getCustId();
        String userPhone = userInfoBean.getCustMobile();
        String userName = userInfoBean.getUserName();
        String payPsw = userInfoBean.getPayPsw();
        String financeMoney = this.mInputBuyMoney.getText().toString();
        String investDay = this.mRegularBuyDay.getText().toString();
        String investMoney = this.mRegularBuyMin.getText().toString();
        String interestRate = this.mRegularYearIncome.getText().toString();

        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("product_id",productId);
        map.put("finance_money",financeMoney);
        map.put("pay_passwd",payPsw);
        map.put("product_name",productName);
        map.put("interest_rate",interestRate);
        map.put("user_name",userName);
        map.put("invest_days",investDay);
        map.put("user_id",userId);
        map.put("user_phone",userPhone);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_PURCHASE_FINANCE,map,RegularBuyActivity.this.mNetWorkCallBack, RegularBuyResponse.class);

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
            if(object instanceof RegularBuyResponse) {
                RegularBuyResponse regularBuyResponse = (RegularBuyResponse)object;
                LogUtil.i(this,"regularBuyResponse = " + regularBuyResponse);
            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }
}
