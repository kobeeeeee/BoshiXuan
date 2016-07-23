package www.chendanfeng.com.network.model;

import java.util.List;

import www.chendanfeng.com.network.BaseResponse;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class RechargeRecordResponse extends BaseResponse{
    public String orderNo;
    public String rechargeMoney;
    public String rechargeMode;
    public String rechargeTime;

    public List<RechargeRecordDetailModel> data_list;
    public String current_page;
    public String page_size;
    public String total_page;
    public String total_record;
}
