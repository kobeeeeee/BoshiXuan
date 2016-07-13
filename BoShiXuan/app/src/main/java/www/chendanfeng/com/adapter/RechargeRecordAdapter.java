package www.chendanfeng.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.network.model.RechargeRecordResponse;
import www.chendanfeng.com.network.model.WithdrawRecordResponse;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class RechargeRecordAdapter extends RecyclerView.Adapter<RechargeRecordAdapter.RechargeView>{
    private List<RechargeRecordResponse> mRechargeRecordResponseList;
    private Context mContext;
    public RechargeRecordAdapter(Context context, List<RechargeRecordResponse> responseList) {
        this.mRechargeRecordResponseList = responseList;
        this.mContext = context;
    }
    @Override
    public RechargeView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recharge_record, parent, false);
        return new RechargeView(view);
    }

    @Override
    public void onBindViewHolder(RechargeView holder, int position) {
        holder.mRechargeMode.setText(this.mRechargeRecordResponseList.get(position).rechargeMode);
        holder.mRechargeMoney.setText(this.mRechargeRecordResponseList.get(position).rechargeMoney);
        holder.mRechargeTime.setText(this.mRechargeRecordResponseList.get(position).rechargeTime);
        holder.mOrderNo.setText(this.mRechargeRecordResponseList.get(position).orderNo);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.mRechargeRecordResponseList.size();
    }


    public class RechargeView extends RecyclerView.ViewHolder {
        @Bind(R.id.rechargeMode)
        TextView mRechargeMode;
        @Bind(R.id.rechargeMoney)
        TextView mRechargeMoney;
        @Bind(R.id.rechargeTime)
        TextView mRechargeTime;
        @Bind(R.id.orderNo)
        TextView mOrderNo;
        public RechargeView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
