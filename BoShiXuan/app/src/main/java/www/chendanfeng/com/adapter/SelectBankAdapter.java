package www.chendanfeng.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.chendanfeng.com.boishixuan.R;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class SelectBankAdapter extends BaseAdapter{
    private List<String> mBankNameList = new ArrayList<>();
    private List<String> mBankNoList = new ArrayList<>();
    private Context mContext;
    public SelectBankAdapter(Context context,List<String> bankNameList,List<String> bankNoList) {
        this.mContext = context;
        this.mBankNameList = bankNameList;
        this.mBankNoList = bankNoList;
    }
    @Override
    public int getCount() {
        return this.mBankNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mBankNameList.get(position);
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
            convertView = inflater.inflate(R.layout.item_select_bank,null);
            holder = new ViewHolder();
            holder.bankName = (TextView) convertView.findViewById(R.id.bankName);
            holder.bankNo = (TextView) convertView.findViewById(R.id.bankNo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.bankName.setText(this.mBankNameList.get(position));
        holder.bankNo.setText(this.mBankNoList.get(position));
        return convertView;
    }

    class ViewHolder{
        public TextView bankName;
        public TextView bankNo;
    }
}
