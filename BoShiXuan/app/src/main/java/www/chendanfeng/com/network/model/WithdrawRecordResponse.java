package www.chendanfeng.com.network.model;

import java.util.List;

import www.chendanfeng.com.network.BaseResponse;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class WithdrawRecordResponse extends BaseResponse{
    public String withdrawMoney;
    public String withdrawTime;
    public String withdrawBankCard;
    public String withdrawBankName;
    public List<WithdrawRecordDetailModel> data_list;
    public String current_page;
    public String page_size;
    public String total_page;
    public String total_record;
}
