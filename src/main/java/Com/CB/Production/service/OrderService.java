package Com.CB.Production.service;

import Com.CB.Production.domain.COrder;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.COrderVo;

import java.util.List;

public interface OrderService {
    COrderVo get(String orderId);

    List<COrderVo> find();

    EUDataGridResult getList(Integer page, Integer rows, COrderVo cOrderVo);

    CustomResult insert(COrder cOrder);

    CustomResult update(COrder cOrder);

    CustomResult updateAll(COrder cOrder);

    CustomResult updateNote(COrder cOrder);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    CustomResult changeStatus(String[] ids);

    EUDataGridResult searchOrderByOrderId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchOrderByCustom(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchOrderByProductName(Integer page, Integer rows, String searchValue);
}
