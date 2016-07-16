package www.chendanfeng.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.chendanfeng.com.boishixuan.NewsDetailActivity;
import www.chendanfeng.com.boishixuan.ProductDetailActivity;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.network.BaseResponse;
import www.chendanfeng.com.network.model.NewsResponse;
import www.chendanfeng.com.network.model.WithdrawRecordResponse;

/**
 * Created by Administrator on 2016/7/16 0016.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsView> {
    public static final int TYPE_SYSTEM = 1;
    public static final int TYPE_PERSON = 2;
    private int mType;
    private List<NewsResponse> mResponseList;
    private Context mContext;
    public NewsListAdapter(Context context, List<NewsResponse> responseList,int type) {
        this.mResponseList = responseList;
        this.mContext = context;
        this.mType = type;
    }
    @Override
    public NewsView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsView(view);
    }

    @Override
    public void onBindViewHolder(NewsView holder, int position) {
        holder.mNewsContent.setText(this.mResponseList.get(position).newsContent);
        holder.mNewsTitle.setText(this.mResponseList.get(position).newsTitle);
        holder.mNewsTime.setText(this.mResponseList.get(position).newsTime);
    }

    @Override
    public int getItemCount() {
        return this.mResponseList.size();
    }

    public class NewsView extends RecyclerView.ViewHolder {
        @Bind(R.id.newsContent)
        TextView mNewsContent;
        @Bind(R.id.newsTitle)
        TextView mNewsTitle;
        @Bind(R.id.newsTime)
        TextView mNewsTime;
        @OnClick(R.id.newsLayout)
        public void OnClick(View view) {
            Intent intent = new Intent(NewsListAdapter.this.mContext, NewsDetailActivity.class);
            intent.putExtra("type",NewsListAdapter.this.mType);
            NewsListAdapter.this.mContext.startActivity(intent);
        }
        public NewsView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
