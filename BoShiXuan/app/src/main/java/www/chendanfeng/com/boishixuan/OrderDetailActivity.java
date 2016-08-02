package www.chendanfeng.com.boishixuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
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
import www.chendanfeng.com.network.model.OrderPayResponse;
import www.chendanfeng.com.util.CommonUtil;
import www.chendanfeng.com.util.LogUtil;
import www.chendanfeng.com.view.PaypswDialog;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class OrderDetailActivity extends BaseActivity{
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.order_no)
    TextView mOrderNo;
    @Bind(R.id.deposit_money)
    TextView mDepositMoney;
    @Bind(R.id.day_rent_money)
    TextView mDayRentMoney;
    @Bind(R.id.confirmBtn)
    ImageView mConfirmBtn;
    String mOrderId = "";
    private NetWorkCallBack mNetWorkCallBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        initHeader();
        getData();
        initClick();
    }
    private void initClick() {
        this.mNetWorkCallBack = new NetWorkCallBack();
        this.mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
                String isVerify = userInfoBean.getIsVerity();
                if(!isVerify.equals("2")) {
                    CommonUtil.showToast("请先实名认证",OrderDetailActivity.this);
                    return;
                }
                final PaypswDialog payDialog = new PaypswDialog(OrderDetailActivity.this);
                payDialog.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        payDialog.dismiss();
                        String userId = userInfoBean.getCustId();
                        String userPhone = userInfoBean.getCustMobile();
                        //传入参数
                        String payPsw = payDialog.getText();
                        LogUtil.i(this,"支付密码输入 = " + payPsw);
                        Map<String,Object> map = new HashMap<>();
                        map.put("goods_order_id",OrderDetailActivity.this.mOrderId);
                        map.put("rent_price",OrderDetailActivity.this.mDayRentMoney.getText().toString());
                        map.put("user_id",userId);
                        map.put("user_phone",userPhone);
                        map.put("pay_passwd",payPsw);
                        map.put("deposit_price",OrderDetailActivity.this.mDepositMoney.getText().toString());
                        map.put("order_number",OrderDetailActivity.this.mOrderNo.getText().toString());
                        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_FETCH_CASH,map,OrderDetailActivity.this.mNetWorkCallBack, OrderPayResponse.class);
                    }
                });
                payDialog.setOnNegativeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        payDialog.dismiss();
                    }
                });
                payDialog.show();
            }
        });
    }
    private void getData() {
        Intent intent = getIntent();
        this.mOrderId= intent.getStringExtra("orderId");
        String rentPrice = intent.getStringExtra("rentPrice");
        String depositPrice = intent.getStringExtra("depositPrice");
        String orderNumber = intent.getStringExtra("orderNumber");
        this.mDepositMoney.setText(depositPrice);
        this.mDayRentMoney.setText(rentPrice);
        this.mOrderNo.setText(orderNumber);
    }


    private void initHeader() {
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("订单支付");
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
            if(object instanceof OrderPayResponse) {
                OrderPayResponse orderPayResponse = (OrderPayResponse)object;
                LogUtil.i(this,"orderPayResponse = " + orderPayResponse);
                Toast toast = Toast.makeText(OrderDetailActivity.this,"支付成功",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                finish();
            }
        }

        @Override
        public void onFailure(Object message) {
            String msg = (String) message;
            CommonUtil.showToast(msg,OrderDetailActivity.this);
        }
    }
}
