package www.chendanfeng.com.boishixuan;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.adapter.RechargeRecordAdapter;
import www.chendanfeng.com.adapter.WithdrawRecordAdapter;
import www.chendanfeng.com.network.model.RechargeRecordResponse;
import www.chendanfeng.com.network.model.WithdrawRecordResponse;
import www.chendanfeng.com.xrecyclerview.ProgressStyle;
import www.chendanfeng.com.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2016/7/10 0010.
 */
public class RechargeRecordActivity extends BaseActivity{
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.rechargeRecyclerView)
    XRecyclerView mRechargeRecyclerView;
    private RechargeRecordAdapter mRechargeRecordAdapter;
    private List<RechargeRecordResponse> mRechargeRecordResponseList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_record);
        ButterKnife.bind(this);
        initHeader();
        initRecyclerView();
    }
    private void initHeader(){
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("充值记录");
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initRecyclerView() {
        this.mRechargeRecordResponseList = new ArrayList<>();
        RechargeRecordResponse rechargeRecordResponse = new RechargeRecordResponse();
        rechargeRecordResponse.rechargeMode = "门店现金充值";
        rechargeRecordResponse.rechargeMoney = "1000元";
        rechargeRecordResponse.orderNo = "241246336548546256";
        rechargeRecordResponse.rechargeTime = "2016.06.07     11:55:09";
        this.mRechargeRecordResponseList.add(rechargeRecordResponse);

        rechargeRecordResponse = new RechargeRecordResponse();
        rechargeRecordResponse.rechargeMode = "门店现金充值";
        rechargeRecordResponse.rechargeMoney = "10000元";
        rechargeRecordResponse.orderNo = "241246336548546257";
        rechargeRecordResponse.rechargeTime = "2016.06.08     16:51:13";
        this.mRechargeRecordResponseList.add(rechargeRecordResponse);


        rechargeRecordResponse = new RechargeRecordResponse();
        rechargeRecordResponse.rechargeMode = "网上充值";
        rechargeRecordResponse.rechargeMoney = "200元";
        rechargeRecordResponse.orderNo = "241246336548546258";
        rechargeRecordResponse.rechargeTime = "2016.06.09     22:25:11";
        this.mRechargeRecordResponseList.add(rechargeRecordResponse);


        rechargeRecordResponse = new RechargeRecordResponse();
        rechargeRecordResponse.rechargeMode = "网上充值";
        rechargeRecordResponse.rechargeMoney = "500元";
        rechargeRecordResponse.orderNo = "241246336548546259";
        rechargeRecordResponse.rechargeTime = "2016.06.12     14:55:32";
        this.mRechargeRecordResponseList.add(rechargeRecordResponse);
        this.mRechargeRecordAdapter = new RechargeRecordAdapter(this,this.mRechargeRecordResponseList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.mRechargeRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRechargeRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        this.mRechargeRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        this.mRechargeRecyclerView.setArrowImageView(R.drawable.icon_font_down_grey);


        this.mRechargeRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        RechargeRecordActivity.this.mRechargeRecyclerView.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        RechargeRecordActivity.this.mRechargeRecyclerView.loadMoreComplete();
                    }
                },3000);

            }
        });
        this.mRechargeRecyclerView.setAdapter(this.mRechargeRecordAdapter);
    }
}
