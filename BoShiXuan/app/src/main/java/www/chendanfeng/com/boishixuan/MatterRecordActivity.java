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
import www.chendanfeng.com.adapter.MatterRecordAdapter;
import www.chendanfeng.com.adapter.WithdrawRecordAdapter;
import www.chendanfeng.com.network.model.MatterRecordResponse;
import www.chendanfeng.com.network.model.WithdrawRecordResponse;
import www.chendanfeng.com.xrecyclerview.ProgressStyle;
import www.chendanfeng.com.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2016/7/10 0010.
 */
public class MatterRecordActivity extends BaseActivity{
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.matterRecyclerView)
    XRecyclerView mMatterRecyclerView;
    private MatterRecordAdapter mMatterRecordAdapter;
    private List<MatterRecordResponse> mMatterRecordResponseList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matter_record);
        ButterKnife.bind(this);
        initHeader();
        initRecyclerView();
    }
    private void initHeader(){
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("理财记录");
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initRecyclerView() {
        this.mMatterRecordResponseList = new ArrayList<>();
        MatterRecordResponse matterRecordResponse = new MatterRecordResponse();
        matterRecordResponse.matterName = "360理财产品";
        matterRecordResponse.buyMoney = "300元";
        matterRecordResponse.buyTime = "2016.06.07     11:55:09";
        this.mMatterRecordResponseList.add(matterRecordResponse);

        matterRecordResponse = new MatterRecordResponse();
        matterRecordResponse.matterName = "361理财产品";
        matterRecordResponse.buyMoney = "500元";
        matterRecordResponse.buyTime = "2016.06.08     16:51:13";
        this.mMatterRecordResponseList.add(matterRecordResponse);


        matterRecordResponse = new MatterRecordResponse();
        matterRecordResponse.matterName = "362理财产品";
        matterRecordResponse.buyMoney = "600元";
        matterRecordResponse.buyTime = "2016.06.09     22:25:11";
        this.mMatterRecordResponseList.add(matterRecordResponse);


//        matterRecordResponse = new MatterRecordResponse();
//        matterRecordResponse.matterName = "622************2445";
//        matterRecordResponse.buyMoney = "700元";
//        matterRecordResponse.buyTime = "2016.06.12     14:55:32";
//        this.mMatterRecordResponseList.add(matterRecordResponse);
        this.mMatterRecordAdapter = new MatterRecordAdapter(this,this.mMatterRecordResponseList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.mMatterRecyclerView.setLayoutManager(linearLayoutManager);
        this.mMatterRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        this.mMatterRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        this.mMatterRecyclerView.setArrowImageView(R.drawable.icon_font_down_grey);


        this.mMatterRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        MatterRecordActivity.this.mMatterRecyclerView.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        MatterRecordActivity.this.mMatterRecyclerView.loadMoreComplete();
                    }
                },3000);

            }
        });
        this.mMatterRecyclerView.setAdapter(this.mMatterRecordAdapter);
    }
}
