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
import www.chendanfeng.com.adapter.NewsListAdapter;
import www.chendanfeng.com.bean.UserInfoBean;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.model.MessageListResponse;
import www.chendanfeng.com.network.model.NewsResponse;
import www.chendanfeng.com.util.ItemDivider;
import www.chendanfeng.com.util.LogUtil;
import www.chendanfeng.com.xrecyclerview.ProgressStyle;
import www.chendanfeng.com.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2016/7/16 0016.
 */
public class PersonNewsFragment extends BaseFragment{
    @Bind(R.id.personNewsRecyclerView)
    XRecyclerView mPersonNewsRecyclerView;
    private NewsListAdapter mNewsListAdapter;
    private View mView;
    private List<NewsResponse> mNewsResponseList;
    private NetWorkCallBack mNetWorkCallBack;
    private int mPageNum=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_person_news, container, false);
        ButterKnife.bind(this, this.mView);
        return this.mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }
    private void initRecyclerView() {
        this.mNewsResponseList = new ArrayList<>();
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.newsContent= "尊敬的张小三用户，您在2016-07-12  17:19:00的时候萨达的防晒霜的撒旦王琪琪";
        newsResponse.newsTitle = "购买理财消息";
        newsResponse.newsTime = "2016-07-12 17:30:00";
        this.mNewsResponseList.add(newsResponse);

        newsResponse = new NewsResponse();
        newsResponse.newsContent= "尊敬的张小三用户，您在2016-07-12  17:19:00的时候萨达的防晒霜的撒旦王琪琪";
        newsResponse.newsTitle = "购买理财消息";
        newsResponse.newsTime = "2016-07-12 17:30:00";
        this.mNewsResponseList.add(newsResponse);

        newsResponse = new NewsResponse();
        newsResponse.newsContent= "尊敬的张小三用户，您在2016-07-12  17:19:00的时候萨达的防晒霜的撒旦王琪琪";
        newsResponse.newsTitle = "购买理财消息";
        newsResponse.newsTime = "2016-07-12 17:30:00";
        this.mNewsResponseList.add(newsResponse);


        this.mNewsListAdapter = new NewsListAdapter(getActivity(),this.mNewsResponseList,2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mPersonNewsRecyclerView.setLayoutManager(linearLayoutManager);
        this.mPersonNewsRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        this.mPersonNewsRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        this.mPersonNewsRecyclerView.setArrowImageView(R.drawable.icon_font_down_grey);


        this.mPersonNewsRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        PersonNewsFragment.this.mPersonNewsRecyclerView.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        PersonNewsFragment.this.mPersonNewsRecyclerView.loadMoreComplete();
                    }
                },3000);

            }
        });
        this.mPersonNewsRecyclerView.setAdapter(this.mNewsListAdapter);
    }

    private void getData() {
        this.mNetWorkCallBack = new NetWorkCallBack();
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String userId = userInfoBean.getCustId();
        String userPhone = userInfoBean.getCustMobile();
        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("page_num",mPageNum);
        map.put("page_size",10);
        map.put("msg_type","0");
        map.put("user_id",userId);
        map.put("user_phone",userPhone);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_MESSAGE,map,PersonNewsFragment.this.mNetWorkCallBack, MessageListResponse.class);

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
            if(object instanceof MessageListResponse) {
                MessageListResponse messageListResponse = (MessageListResponse)object;
                LogUtil.i(this,"messageListResponse = " + messageListResponse);
            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }
}
