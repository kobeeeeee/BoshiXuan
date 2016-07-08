package www.chendanfeng.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import www.chendanfeng.com.boishixuan.LeaseProductActivity;
import www.chendanfeng.com.boishixuan.R;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class LeaseProductAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> mProductNameList;
    public LeaseProductAdapter(Context context,List<String> productNameList){
        this.mContext = context;
        this.mProductNameList = productNameList;
    }
    @Override
    public int getCount() {
        return this.mProductNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mProductNameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_lease_product,null);
            holder = new ViewHolder();
            holder.productName = (TextView) convertView.findViewById(R.id.productName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.productName.setText(this.mProductNameList.get(position));
        return convertView;
    }
    class ViewHolder{
        public TextView productName;
    }
}
