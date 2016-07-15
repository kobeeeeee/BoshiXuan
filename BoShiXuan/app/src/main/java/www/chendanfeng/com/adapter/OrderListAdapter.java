package www.chendanfeng.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.network.model.OrderResponse;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderView>{
    private List<OrderResponse> mOrderResponseList;
    private Context mContext;
    public OrderListAdapter(Context context, List<OrderResponse> responseList) {
        this.mOrderResponseList = responseList;
        this.mContext = context;
    }
    @Override
    public OrderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderView(view);
    }

    @Override
    public void onBindViewHolder(OrderView holder, int position) {
        holder.mProductBrand.setText(this.mOrderResponseList.get(position).productBrand);
        holder.mProductDayRent.setText(this.mOrderResponseList.get(position).productDayRent);
        holder.mProductDeposit.setText(this.mOrderResponseList.get(position).productDeposit);
        holder.mOrderNo.setText(this.mOrderResponseList.get(position).orderNo);
        holder.mOrderPayTime.setText(this.mOrderResponseList.get(position).orderPayTime);
        holder.morderCreateTime.setText(this.mOrderResponseList.get(position).orderCreateTime);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.mOrderResponseList.size();
    }


    public class OrderView extends RecyclerView.ViewHolder {
        @Bind(R.id.productBrand)
        TextView mProductBrand;
        @Bind(R.id.productDeposit)
        TextView mProductDeposit;
        @Bind(R.id.productDayRent)
        TextView mProductDayRent;
        @Bind(R.id.orderNo)
        TextView mOrderNo;
        @Bind(R.id.orderCreateTime)
        TextView morderCreateTime;
        @Bind(R.id.orderPayTime)
        TextView mOrderPayTime;
        public OrderView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
