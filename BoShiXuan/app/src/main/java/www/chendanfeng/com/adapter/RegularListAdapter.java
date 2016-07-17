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
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.boishixuan.RegularBuyActivity;
import www.chendanfeng.com.network.model.RegularResponse;
import www.chendanfeng.com.network.model.WithdrawRecordResponse;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public class RegularListAdapter extends RecyclerView.Adapter<RegularListAdapter.RegularView>{
    private List<RegularResponse> mRegularResponseList;
    private Context mContext;
    public RegularListAdapter(Context context, List<RegularResponse> responseList) {
        this.mRegularResponseList = responseList;
        this.mContext = context;
    }
    @Override
    public RegularView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_regular, parent, false);
        return new RegularView(view);
    }
    @Override
    public void onBindViewHolder(RegularView holder, int position) {
        holder.mRegularProductName.setText(this.mRegularResponseList.get(position).regularProductName);
        holder.mRegularProductIncome.setText(this.mRegularResponseList.get(position).regularProductIncome);
        holder.mRegularProductDay.setText(this.mRegularResponseList.get(position).regularProductDay);
        holder.mRegularProductMin.setText(this.mRegularResponseList.get(position).regularProductMin);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.mRegularResponseList.size();
    }

    public class RegularView extends RecyclerView.ViewHolder {
        @Bind(R.id.regularProductName)
        TextView mRegularProductName;
        @Bind(R.id.regularProductIncome)
        TextView mRegularProductIncome;
        @Bind(R.id.regularProductDay)
        TextView mRegularProductDay;
        @Bind(R.id.regularProductMin)
        TextView mRegularProductMin;
        @OnClick(R.id.regularLayout)
        public void OnClick(View view) {
            Intent intent = new Intent(RegularListAdapter.this.mContext, RegularBuyActivity.class);
            RegularListAdapter.this.mContext.startActivity(intent);
        }
        public RegularView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
