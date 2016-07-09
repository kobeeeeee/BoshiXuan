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

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class LeaseProductAdapter extends RecyclerView.Adapter<LeaseProductAdapter.ProductView> {
    private Context mContext;
    private List<String> mProductNameList;
    public LeaseProductAdapter(Context context,List<String> productNameList){
        this.mContext = context;
        this.mProductNameList = productNameList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.mProductNameList.size();
    }

    @Override
    public ProductView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lease_product, viewGroup, false);
        return new ProductView(view);
    }

    @Override
    public void onBindViewHolder(ProductView holder, int position) {
        holder.itemView.setTag(this.mProductNameList.get(position));
        holder.productName.setText(this.mProductNameList.get(position));
    }
    public class ProductView extends RecyclerView.ViewHolder {
        @Bind(R.id.productName)
        TextView productName;

        public ProductView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
