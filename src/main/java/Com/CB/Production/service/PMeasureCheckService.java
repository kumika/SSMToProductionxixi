package Com.CB.Production.service;

import Com.CB.Production.domain.ProcessMmeasureCheck;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

public interface PMeasureCheckService {
    EUDataGridResult getList(Integer page, Integer rows, ProcessMmeasureCheck processMeasureCheck);

    CustomResult insert(ProcessMmeasureCheck processMeasureCheck);

    CustomResult updateAll(ProcessMmeasureCheck processMeasureCheck);

    CustomResult updateNote(ProcessMmeasureCheck processMeasureCheck);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchPMeasureCheckByPMeasureCheckId(Integer page, Integer rows, String searchValue);
}
