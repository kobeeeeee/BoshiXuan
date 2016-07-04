package www.chendanfeng.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.adapter.WalletListAdapter;
import www.chendanfeng.com.boishixuan.R;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class WalletFragment extends BaseFragment {
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.walletListView)
    ListView mWalletListView;
    private View mView;
    private List<String> mWalletTextList;
    private List<Integer> mWalletImageList;
    private WalletListAdapter mWalletListAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_wallet, container, false);
        ButterKnife.bind(this, this.mView);
        return this.mView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initHeader();
        initData();
        initListView();
    }
    public void initHeader() {
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("钱包");
    }
    public void initData() {
        this.mWalletTextList = new ArrayList<>();
        this.mWalletTextList.add("提款到银行账户");
        this.mWalletTextList.add("修改密码");
        this.mWalletTextList.add("实名认证");
        this.mWalletTextList.add("银行卡列表");
        this.mWalletTextList.add("提现记录");
        this.mWalletTextList.add("理财记录");
        this.mWalletTextList.add("充值记录");


        this.mWalletImageList = new ArrayList<>();
        this.mWalletImageList.add(R.drawable.wallet_drawing);
        this.mWalletImageList.add(R.drawable.wallet_password);
        this.mWalletImageList.add(R.drawable.wallet_real_name);
        this.mWalletImageList.add(R.drawable.wallet_bank_card);
        this.mWalletImageList.add(R.drawable.wallet_drawing_record);
        this.mWalletImageList.add(R.drawable.wallet_financing_record);
        this.mWalletImageList.add(R.drawable.wallet_recharge_record);
    }
    public void initListView() {
        this.mWalletListAdapter = new WalletListAdapter(getActivity(),this.mWalletTextList,this.mWalletImageList);
        mWalletListView.setAdapter(this.mWalletListAdapter);
    }
}
