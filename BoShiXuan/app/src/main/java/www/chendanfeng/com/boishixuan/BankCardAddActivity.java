package www.chendanfeng.com.boishixuan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class BankCardAddActivity extends BaseActivity{
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_add);
        ButterKnife.bind(this);
        initHeader();
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
}
