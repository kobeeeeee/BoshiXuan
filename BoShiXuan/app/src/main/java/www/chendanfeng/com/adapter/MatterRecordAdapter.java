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
import www.chendanfeng.com.network.model.MatterRecordResponse;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class MatterRecordAdapter extends RecyclerView.Adapter<MatterRecordAdapter.MatterView> {
    private List<MatterRecordResponse> mMatterRecordResponseList;
    private Context mContext;
    public MatterRecordAdapter(Context context, List<MatterRecordResponse> responseList) {
        this.mMatterRecordResponseList = responseList;
        this.mContext = context;
    }

    @Override
    public MatterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matter_record, parent, false);
        return new MatterView(view);
    }

    @Override
    public void onBindViewHolder(MatterView holder, int position) {
        holder.mMatterName.setText(this.mMatterRecordResponseList.get(position).matterName);
        holder.mBuyMoney.setText(this.mMatterRecordResponseList.get(position).buyMoney);
        holder.mBuyTime.setText(this.mMatterRecordResponseList.get(position).buyTime);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.mMatterRecordResponseList.size();
    }

    public class MatterView extends RecyclerView.ViewHolder {
        @Bind(R.id.matterName)
        TextView mMatterName;
        @Bind(R.id.buyTime)
        TextView mBuyTime;
        @Bind(R.id.buyMoney)
        TextView mBuyMoney;
        public MatterView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
