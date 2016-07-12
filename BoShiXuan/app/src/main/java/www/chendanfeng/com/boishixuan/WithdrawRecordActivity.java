package www.chendanfeng.com.boishixuan;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.adapter.WithdrawRecordAdapter;
import www.chendanfeng.com.network.model.WithdrawRecordResponse;
import www.chendanfeng.com.xrecyclerview.ProgressStyle;
import www.chendanfeng.com.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2016/7/10 0010.
 */
public class WithdrawRecordActivity extends BaseActivity{
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.withdrawRecyclerView)
    XRecyclerView mWithdrawRecyclerView;
    private WithdrawRecordAdapter mWithdrawRecordAdapter;
    private List<WithdrawRecordResponse> mWithdrawRecordResponseList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_record);
        ButterKnife.bind(this);
        initHeader();
        initRecyclerView();
    }
    private void initHeader(){
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("提现记录");
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initRecyclerView() {
        this.mWithdrawRecordResponseList = new ArrayList<>();
        WithdrawRecordResponse withdrawRecordResponse = new WithdrawRecordResponse();
        withdrawRecordResponse.withdrawBankCard = "622************2115";
        withdrawRecordResponse.withdrawBankName = "工商银行";
        withdrawRecordResponse.withdrawMoney = "￥800.00元";
        withdrawRecordResponse.withdrawTime = "2016.06.07     11:55:09";
        this.mWithdrawRecordResponseList.add(withdrawRecordResponse);

        withdrawRecordResponse = new WithdrawRecordResponse();
        withdrawRecordResponse.withdrawBankCard = "622************2225";
        withdrawRecordResponse.withdrawBankName = "建设银行";
        withdrawRecordResponse.withdrawMoney = "￥8000.00元";
        withdrawRecordResponse.withdrawTime = "2016.06.08     16:51:13";
        this.mWithdrawRecordResponseList.add(withdrawRecordResponse);


        withdrawRecordResponse = new WithdrawRecordResponse();
        withdrawRecordResponse.withdrawBankCard = "622************2335";
        withdrawRecordResponse.withdrawBankName = "农业银行";
        withdrawRecordResponse.withdrawMoney = "￥1200.00元";
        withdrawRecordResponse.withdrawTime = "2016.06.09     22:25:11";
        this.mWithdrawRecordResponseList.add(withdrawRecordResponse);


        withdrawRecordResponse = new WithdrawRecordResponse();
        withdrawRecordResponse.withdrawBankCard = "622************2445";
        withdrawRecordResponse.withdrawBankName = "中国银行";
        withdrawRecordResponse.withdrawMoney = "￥3200.00元";
        withdrawRecordResponse.withdrawTime = "2016.06.12     14:55:32";
        this.mWithdrawRecordResponseList.add(withdrawRecordResponse);
        this.mWithdrawRecordAdapter = new WithdrawRecordAdapter(this,this.mWithdrawRecordResponseList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.mWithdrawRecyclerView.setLayoutManager(linearLayoutManager);
        this.mWithdrawRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        this.mWithdrawRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        this.mWithdrawRecyclerView.setArrowImageView(R.drawable.icon_font_down_grey);


        this.mWithdrawRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        WithdrawRecordActivity.this.mWithdrawRecyclerView.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        WithdrawRecordActivity.this.mWithdrawRecyclerView.loadMoreComplete();
                    }
                },3000);

            }
        });
        this.mWithdrawRecyclerView.setAdapter(this.mWithdrawRecordAdapter);
    }
}
