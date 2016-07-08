package www.chendanfeng.com.boishixuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

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
                    break;
            }
        }
    }


}
