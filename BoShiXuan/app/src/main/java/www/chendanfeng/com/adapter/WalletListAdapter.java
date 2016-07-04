package www.chendanfeng.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import www.chendanfeng.com.boishixuan.R;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class WalletListAdapter extends BaseAdapter{
    public List<Integer> mWalletImageList;
    public List<String> mWalletTextList;
    public Context mContext;
    public WalletListAdapter(Context context,List<String> walletTextList,List<Integer> walletImageList) {
        this.mContext = context;
        this.mWalletTextList = walletTextList;
        this.mWalletImageList = walletImageList;
    }
    @Override
    public int getCount() {
        return this.mWalletTextList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mWalletTextList.get(position);
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
            convertView = inflater.inflate(R.layout.item_wallet,null);
            holder = new ViewHolder();
            holder.walletTextView = (TextView) convertView.findViewById(R.id.walletListText1);
            holder.walletImageView = (ImageView) convertView.findViewById(R.id.walletImageView);
            holder.walletTextView2 = (TextView) convertView.findViewById(R.id.walletListText2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.walletTextView.setText(this.mWalletTextList.get(position));
        holder.walletImageView.setImageResource(this.mWalletImageList.get(position));
        if(position == 2) {
            holder.walletTextView2.setVisibility(View.VISIBLE);
            holder.walletTextView2.setText("未认证");
        } else {
            holder.walletTextView2.setVisibility(View.GONE);
        }
        return convertView;
    }
    class ViewHolder{
        public TextView walletTextView;
        public ImageView walletImageView;
        public TextView walletTextView2;
    }
}
