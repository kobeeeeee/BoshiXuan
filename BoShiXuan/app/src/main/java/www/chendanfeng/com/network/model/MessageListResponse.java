package www.chendanfeng.com.network.model;

import java.util.List;

import www.chendanfeng.com.network.BaseResponse;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class MessageListResponse extends BaseResponse{
    public List<MessageDetailModel> data_list;
    public String total_page;
}
