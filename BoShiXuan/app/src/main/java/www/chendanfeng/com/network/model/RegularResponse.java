package www.chendanfeng.com.network.model;

import java.util.List;

import www.chendanfeng.com.network.BaseResponse;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public class RegularResponse extends BaseResponse{
    public String regularProductName;
    public String regularProductIncome;
    public String regularProductDay;
    public String regularProductMin;
    public List<RegularDetailModel> financeproduct_list;
}
