package www.chendanfeng.com.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.adapter.MatterRecordAdapter;
import www.chendanfeng.com.adapter.OrderListAdapter;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.network.model.OrderResponse;
import www.chendanfeng.com.xrecyclerview.ProgressStyle;
import www.chendanfeng.com.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class UnPayOrderFragment extends BaseFragment{
    private View mView;
    private List<OrderResponse> mOrderResponseList;
    private OrderListAdapter mOrderListAdapter;
    @Bind(R.id.dispayOrderRecyclerView)
    XRecyclerView mDisPayOrderRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_dispay_order, container, false);
        ButterKnife.bind(this, this.mView);
        return this.mView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initRecycleView();
    }
    private void initData() {
        this.mOrderResponseList = new ArrayList<>();
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.productBrand = "芬迪";
        orderResponse.productDayRent = "250";
        orderResponse.productDeposit = "12000";
        orderResponse.orderNo = "37320160712171900991596";
        orderResponse.orderCreateTime = "2016-07-12 17:19:00";
        orderResponse.orderPayTime = "待付款";
        this.mOrderResponseList.add(orderResponse);


        orderResponse = new OrderResponse();
        orderResponse.productBrand = "芬迪1";
        orderResponse.productDayRent = "251";
        orderResponse.productDeposit = "120200";
        orderResponse.orderNo = "37320160712171900991597";
        orderResponse.orderCreateTime = "2016-07-13 17:19:00";
        orderResponse.orderPayTime = "待付款";
        this.mOrderResponseList.add(orderResponse);

        orderResponse = new OrderResponse();
        orderResponse.productBrand = "芬迪2";
        orderResponse.productDayRent = "252";
        orderResponse.productDeposit = "1000";
        orderResponse.orderNo = "37320160712171900991598";
        orderResponse.orderCreateTime = "2016-07-13 17:19:00";
        orderResponse.orderPayTime = "待付款";
        this.mOrderResponseList.add(orderResponse);

    }
    private void initRecycleView() {
        this.mOrderListAdapter = new OrderListAdapter(getActivity(),this.mOrderResponseList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mDisPayOrderRecyclerView.setLayoutManager(linearLayoutManager);
        this.mDisPayOrderRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        this.mDisPayOrderRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        this.mDisPayOrderRecyclerView.setArrowImageView(R.drawable.icon_font_down_grey);


        this.mDisPayOrderRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        UnPayOrderFragment.this.mDisPayOrderRecyclerView.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        UnPayOrderFragment.this.mDisPayOrderRecyclerView.loadMoreComplete();
                    }
                },3000);

            }
        });
        this.mDisPayOrderRecyclerView.setAdapter(this.mOrderListAdapter);
    }
}
