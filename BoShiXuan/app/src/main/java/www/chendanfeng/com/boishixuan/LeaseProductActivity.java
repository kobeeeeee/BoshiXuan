package www.chendanfeng.com.boishixuan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.adapter.LeaseProductAdapter;
import www.chendanfeng.com.bean.UserInfoBean;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.model.ModifyPswResponse;
import www.chendanfeng.com.network.model.ProductResponse;
import www.chendanfeng.com.util.LogUtil;
import www.chendanfeng.com.xrecyclerview.ProgressStyle;
import www.chendanfeng.com.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class LeaseProductActivity extends BaseActivity{
    public static final int TYPE_BAG = 1;
    public static final int TYPE_JEWELLERY = 3;
    public static final int TYPE_WATCH = 2;
    public static final int TYPE_OTHERS = 4;
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.leaseProductRecyclerView)
    XRecyclerView mRecyclerView;
    private List<String> mProductNameList;
    private LeaseProductAdapter mLeaseProductAdapter;
    public NetWorkCallBack mNetWorkCallBack;
    public int mPageNum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lease_product);
        ButterKnife.bind(this);
        initHeader();
        initRecyclerView();
    }
    private void initHeader() {
        Intent intent = getIntent();
        int type = intent.getIntExtra("type",0);
        String headerString = "";
        switch (type) {
            case TYPE_BAG:
                headerString = "包袋";
                break;
            case TYPE_JEWELLERY:
                headerString = "首饰";
                break;
            case TYPE_WATCH:
                headerString = "手表";
                break;
            case TYPE_OTHERS:
                headerString = "其他";
                break;
        }
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText(headerString);
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getData() {
        this.mNetWorkCallBack = new NetWorkCallBack();

        Intent intent = getIntent();
        int type = intent.getIntExtra("type",0);
        //传入参数
        Map<String,Object> map = new HashMap<>();
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String userId = userInfoBean.getCustId();
        String userPhone = userInfoBean.getCustMobile();
        map.put("goods_type",type);
        map.put("modify_type",10);
        map.put("page_num",mPageNum);
        map.put("user_phone",userId);
        map.put("user_phone",userPhone);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_RENT_GOODS,map,LeaseProductActivity.this.mNetWorkCallBack, ModifyPswResponse.class);

    }
    private void initRecyclerView() {
        this.mProductNameList = new ArrayList<>();
        this.mProductNameList.add("甲");
        this.mProductNameList.add("乙");
        this.mProductNameList.add("丙");
        this.mProductNameList.add("丁");
        this.mProductNameList.add("戊");
        this.mProductNameList.add("己");
        this.mProductNameList.add("庚");
        this.mProductNameList.add("辛");
        this.mProductNameList.add("壬");
        this.mProductNameList.add("葵");

        this.mLeaseProductAdapter = new LeaseProductAdapter(this,this.mProductNameList);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        this.mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        this.mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        this.mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setArrowImageView(R.drawable.icon_font_down_grey);


        this.mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        LeaseProductActivity.this.mRecyclerView.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        LeaseProductActivity.this.mRecyclerView.loadMoreComplete();
                    }
                },3000);

            }
        });
        this.mRecyclerView.setAdapter(this.mLeaseProductAdapter);
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
            if(object instanceof ProductResponse) {
                ProductResponse productResponse = (ProductResponse)object;
                LogUtil.i(this,"productResponse = " + productResponse);

            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }
}
