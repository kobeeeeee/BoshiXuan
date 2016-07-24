package www.chendanfeng.com.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.adapter.OrderListAdapter;
import www.chendanfeng.com.bean.UserInfoBean;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.model.OrderDetailModel;
import www.chendanfeng.com.network.model.OrderModel;
import www.chendanfeng.com.network.model.OrderResponse;
import www.chendanfeng.com.util.LogUtil;
import www.chendanfeng.com.xrecyclerview.ProgressStyle;
import www.chendanfeng.com.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class PayOrderFragment extends BaseFragment{
    private View mView;
    private List<OrderResponse> mOrderResponseList;
    private OrderListAdapter mOrderListAdapter;
    @Bind(R.id.orderRecyclerView)
    XRecyclerView mOrderRecyclerView;
    private NetWorkCallBack mNetWorkCallBack = new NetWorkCallBack();
    private List<OrderDetailModel> mOrderDetailModelList = new ArrayList<>();
    private int mPageNum = 1;
    private int mPageSize = 10;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_pay_order, container, false);
        ButterKnife.bind(this, this.mView);
        return this.mView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initRecycleView();
        getData();
    }
    private void initData() {
        this.mOrderResponseList = new ArrayList<>();
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.productBrand = "芬迪";
        orderResponse.productDayRent = "250";
        orderResponse.productDeposit = "12000";
        orderResponse.orderNo = "37320160712171900991596";
        orderResponse.orderCreateTime = "2016-07-12 17:19:00";
        orderResponse.orderPayTime = "2016-07-12 17:19:00";
        this.mOrderResponseList.add(orderResponse);


        orderResponse = new OrderResponse();
        orderResponse.productBrand = "芬迪1";
        orderResponse.productDayRent = "251";
        orderResponse.productDeposit = "120200";
        orderResponse.orderNo = "37320160712171900991597";
        orderResponse.orderCreateTime = "2016-07-13 17:19:00";
        orderResponse.orderPayTime = "2016-07-13 17:19:00";
        this.mOrderResponseList.add(orderResponse);

        orderResponse = new OrderResponse();
        orderResponse.productBrand = "芬迪2";
        orderResponse.productDayRent = "252";
        orderResponse.productDeposit = "1000";
        orderResponse.orderNo = "37320160712171900991598";
        orderResponse.orderCreateTime = "2016-07-13 17:19:00";
        orderResponse.orderPayTime = "2016-07-13 17:19:00";
        this.mOrderResponseList.add(orderResponse);

    }
    private void initRecycleView() {
        this.mOrderListAdapter = new OrderListAdapter(getActivity(),this.mOrderDetailModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mOrderRecyclerView.setLayoutManager(linearLayoutManager);
        this.mOrderRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        this.mOrderRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        this.mOrderRecyclerView.setArrowImageView(R.drawable.icon_font_down_grey);


        this.mOrderRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                getData();
            }

            @Override
            public void onLoadMore() {
                isLoadMore = true;
                PayOrderFragment.this.mPageSize = PayOrderFragment.this.mPageSize + 10;
                getData();
            }
        });
        this.mOrderRecyclerView.setAdapter(this.mOrderListAdapter);
    }
    private void getData() {
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String userId = userInfoBean.getCustId();
        String userPhone = userInfoBean.getCustMobile();
        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("is_Payment","2");
        map.put("page_size",String.valueOf(this.mPageSize));
        map.put("page_num",String.valueOf(this.mPageNum));
        map.put("user_id",userId);
        map.put("user_phone",userPhone);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_QUERY_ORDER,map,PayOrderFragment.this.mNetWorkCallBack, OrderResponse.class);

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
            if(object instanceof OrderResponse) {
                OrderResponse orderResponse = (OrderResponse)object;
                LogUtil.i(this,"orderResponse = " + orderResponse);
                OrderModel orderModel = orderResponse.order_list;
                List<OrderDetailModel> orderDetailModelList = orderModel.data_list;
                PayOrderFragment.this.mOrderListAdapter.setList(orderDetailModelList);
                PayOrderFragment.this.mOrderListAdapter.notifyDataSetChanged();
            }

            if(isRefresh) {
                PayOrderFragment.this.mOrderRecyclerView.refreshComplete();
                isRefresh = false;
            }
            if(isLoadMore) {
                PayOrderFragment.this.mOrderRecyclerView.loadMoreComplete();
                isLoadMore = false;
            }
        }

        @Override
        public void onFailure(Object message) {
            if(isRefresh) {
                PayOrderFragment.this.mOrderRecyclerView.refreshComplete();
                isRefresh = false;
            }
            if(isLoadMore) {
                PayOrderFragment.this.mOrderRecyclerView.loadMoreComplete();
                isLoadMore = false;
            }
        }
    }
}
