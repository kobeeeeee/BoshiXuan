package www.chendanfeng.com.boishixuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.adapter.LeaseProductAdapter;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class LeaseProductActivity extends BaseActivity{
    public static final int TYPE_BAG = 0;
    public static final int TYPE_JEWELLERY = 1;
    public static final int TYPE_WATCH = 2;
    public static final int TYPE_OTHERS = 3;
    @Bind(R.id.tv_head)
    TextView mHeader;
    @Bind(R.id.bar_left_btn)
    RelativeLayout mBackBtn;
    @Bind(R.id.leaseProductGridView)
    GridView mGridView;
    private List<String> mProductNameList;
    private LeaseProductAdapter mLeaseProductAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lease_product);
        ButterKnife.bind(this);
        initHeader();
        initGridView();
    }
    private void initHeader() {
        Intent intent = getIntent();
        int type = intent.getIntExtra("type",0);
        String headerString = "";
        switch (type) {
            case TYPE_BAG:
                headerString = "包袋";
                break;
            case TYPE_JEWELLERY:
                headerString = "首饰";
                break;
            case TYPE_WATCH:
                headerString = "手表";
                break;
            case TYPE_OTHERS:
                headerString = "其他";
                break;
        }
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText(headerString);
        this.mBackBtn.setVisibility(View.VISIBLE);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initGridView() {
        this.mProductNameList = new ArrayList<>();
        this.mProductNameList.add("甲");
        this.mProductNameList.add("乙");
        this.mProductNameList.add("丙");
        this.mProductNameList.add("丁");
        this.mProductNameList.add("戊");
        this.mProductNameList.add("己");
        this.mProductNameList.add("庚");
        this.mProductNameList.add("辛");
        this.mProductNameList.add("壬");
        this.mProductNameList.add("葵");

        this.mLeaseProductAdapter = new LeaseProductAdapter(this,this.mProductNameList);
        this.mGridView.setAdapter(this.mLeaseProductAdapter);
    }
}
