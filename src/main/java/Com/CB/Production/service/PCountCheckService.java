package Com.CB.Production.service;

import Com.CB.Production.domain.ProcessCountCheck;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

public interface PCountCheckService {
    EUDataGridResult getList(Integer page, Integer rows, ProcessCountCheck processCountCheck);

    CustomResult insert(ProcessCountCheck processCountCheck);

    CustomResult updateAll(ProcessCountCheck processCountCheck);

    CustomResult updateNote(ProcessCountCheck processCountCheck);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchPCountCheckByPCountCheckId(Integer page, Integer rows, String searchValue);
}
