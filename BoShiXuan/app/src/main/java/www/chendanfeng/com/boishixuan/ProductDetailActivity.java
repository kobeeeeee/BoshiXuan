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
import www.chendanfeng.com.network.model.CertificationResponse;
import www.chendanfeng.com.network.model.MessageListResponse;
import www.chendanfeng.com.network.model.PutInOrderResponse;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by Administrator on 2016/7/10 0010.
 */
public class ProductDetailActivity extends BaseActivity{
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.productImage)
    ImageView mProductImage;
    @Bind(R.id.productName)
    TextView mProductName;
    @Bind(R.id.productNo)
    TextView mProductNo;
    @Bind(R.id.depositText)
    EditText mDepositText;
    @Bind(R.id.dayRentText)
    EditText mDayRentText;
    @Bind(R.id.commitOrder)
    ImageView mCommitOrder;
    private NetWorkCallBack mNetWorkCallBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        initHeader();
        initClick();
    }
    private void initHeader() {
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("租赁信息");
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
        this.mCommitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commitClick();
            }
        });
    }
    private void commitClick() {
        Intent intent = getIntent();
        String productId = intent.getStringExtra("productId");
        String productNo = intent.getStringExtra("productNo");
        String productName = intent.getStringExtra("productName");
        this.mProductName.setText(productName);
        this.mProductNo.setText(productNo);
        String depositPrice = this.mDepositText.getText().toString();
        String rentPrice = this.mDayRentText.getText().toString();

        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String userId = userInfoBean.getCustId();
        String userPhone = userInfoBean.getCustMobile();
        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",userId);
        map.put("user_phone",userPhone);
        map.put("goods_number",productNo);
        map.put("deposit_price",depositPrice);
        map.put("rent_price",rentPrice);
        map.put("goods_Id",productId);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_PUTIN_ORDER,map,ProductDetailActivity.this.mNetWorkCallBack, MessageListResponse.class);

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
            if(object instanceof PutInOrderResponse) {
                PutInOrderResponse putInOrderResponse = (PutInOrderResponse)object;
                LogUtil.i(this,"putInOrderResponse = " + putInOrderResponse);
            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }
}
