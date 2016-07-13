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
import www.chendanfeng.com.network.model.WithdrawRecordResponse;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class WithdrawRecordAdapter extends RecyclerView.Adapter<WithdrawRecordAdapter.WithdrawView> {
    private List<WithdrawRecordResponse> mWithdrawRecordResponse;
    private Context mContext;
    public WithdrawRecordAdapter(Context context, List<WithdrawRecordResponse> response) {
        this.mWithdrawRecordResponse = response;
        this.mContext = context;
    }
    @Override
    public WithdrawView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_withdraw_record, parent, false);
        return new WithdrawView(view);
    }

    @Override
    public void onBindViewHolder(WithdrawView holder, int position) {
        holder.mWithdrawMoney.setText(this.mWithdrawRecordResponse.get(position).withdrawMoney);
        holder.mWithdrawTime.setText(this.mWithdrawRecordResponse.get(position).withdrawTime);
        holder.mWithdrawBankCard.setText(this.mWithdrawRecordResponse.get(position).withdrawBankCard);
        holder.mWithdrawBankName.setText(this.mWithdrawRecordResponse.get(position).withdrawBankName);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.mWithdrawRecordResponse.size();
    }

    public class WithdrawView extends RecyclerView.ViewHolder {
        @Bind(R.id.withdrawMoney)
        TextView mWithdrawMoney;
        @Bind(R.id.withdrawTime)
        TextView mWithdrawTime;
        @Bind(R.id.withdrawBankNo)
        TextView mWithdrawBankCard;
        @Bind(R.id.withdrawBankName)
        TextView mWithdrawBankName;
        public WithdrawView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
