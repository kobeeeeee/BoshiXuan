package www.chendanfeng.com.boishixuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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
import www.chendanfeng.com.network.model.AccountBalanceResponse;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public class RechargeActivity extends BaseActivity{
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.checkMoreBtn)
    ImageView mCheckMoreBtn;
    @Bind(R.id.accountBalanceText)
    TextView mAccountBalanceText;
    private NetWorkCallBack mNetWorkCallBack = new NetWorkCallBack();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        initHeader();
        getData();

        this.mCheckMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RechargeActivity.this,RechargeMoreActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initHeader(){
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("充值");
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getData() {
        Map<String,Object> map = new HashMap<>();
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String userId = userInfoBean.getCustId();
        String userPhone = userInfoBean.getCustMobile();
        map.put("user_id",userId);
        map.put("user_phone",userPhone);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_BALANCE_STATISTIC,map,RechargeActivity.this.mNetWorkCallBack, AccountBalanceResponse.class);
    }
    private class NetWorkCallBack implements RequestListener {

        @Override
        public void onBegin() {

        }

        @Override
        public void onResponse(Object object) {
            if (object != null && object instanceof AccountBalanceResponse) {
                AccountBalanceResponse accountBalanceResponse = (AccountBalanceResponse)object;
                RechargeActivity.this.mAccountBalanceText.setText(accountBalanceResponse.balance);
            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }
}
