package www.chendanfeng.com.network.model;

import java.util.List;

import www.chendanfeng.com.network.BaseResponse;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class MatterRecordResponse extends BaseResponse{
    public String matterName;
    public String buyTime;
    public String buyMoney;

    public List<MatterRecordDetailModel> data_list;
    public String current_page;
    public String page_size;
    public String total_page;
    public String total_record;
}
