package www.chendanfeng.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.chendanfeng.com.bean.UserInfoBean;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.event.BankSelectEvent;
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.model.BankDetailModel;
import www.chendanfeng.com.network.model.BankListResponse;
import www.chendanfeng.com.network.model.UnBindBankResponse;
import www.chendanfeng.com.util.CommonUtil;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class SelectBankAdapter extends BaseAdapter{
    private List<BankDetailModel> mBankModelList;
    private Activity mContext;
    private int mType;
    public SelectBankAdapter(Activity context, List<BankDetailModel> modelList,int type) {
        this.mContext = context;
        this.mBankModelList = modelList;
        this.mType = type;
    }
    public void setList(List<BankDetailModel> modelList) {
        this.mBankModelList = modelList;
    }
    @Override
    public int getCount() {
        return this.mBankModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mBankModelList.get(position);
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
        final BankDetailModel model = this.mBankModelList.get(position);
        if(model == null) {
            return convertView;
        }
        if(!TextUtils.isEmpty(model.card_number)) {
            model.card_number = model.card_number.substring(0,4) + "***********" + model.card_number.substring(15,19);
        }
        holder.bankName.setText(model.bank_name);
        holder.bankNo.setText(model.card_number);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (SelectBankAdapter.this.mType){
                    case 0:
                        selectBankCard(model);
                        break;
                    case 1:
                        BankSelectEvent bankSelectEvent = new BankSelectEvent(model);
                        EventBus.getDefault().post(bankSelectEvent);
                        break;
                }
            }
        });
        return convertView;
    }

    /**
     * 选择银行卡
     * @param model
     */
    public void selectBankCard(BankDetailModel model) {
        Intent intent = new Intent();
        intent.putExtra("bankName",model.bank_name);
        intent.putExtra("bankNo",model.card_number);
        intent.putExtra("bankId",model.bank_id);
        SelectBankAdapter.this.mContext.setResult(SelectBankAdapter.this.mContext.RESULT_OK,intent);
        SelectBankAdapter.this.mContext.finish();
    }


    class ViewHolder{
        public TextView bankName;
        public TextView bankNo;
    }
}
