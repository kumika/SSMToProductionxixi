package Com.CB.Production.service;

import Com.CB.Production.domain.FinalCountCheck;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

public interface FCountCheckService {
    FinalCountCheck get(String finalCountCheckId);

    CustomResult insert(FinalCountCheck finalCountCheck);

    CustomResult updateAll(FinalCountCheck finalCountCheck);

    CustomResult updateNote(FinalCountCheck finalCountCheck);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult getList(Integer page, Integer rows, FinalCountCheck finalCountCheck);

    EUDataGridResult searchFCountCheckByFCountCheckId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchFCountCheckByOrderId(Integer page, Integer rows, String searchValue);
}
