package Com.CB.Production.service;

import Com.CB.Production.domain.FinalMeasuretCheck;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

public interface MeasureService {
    FinalMeasuretCheck get(String finalMeasuretCheckId);

    EUDataGridResult getList(Integer page, Integer rows, FinalMeasuretCheck finalMeasuretCheck);

    CustomResult insert(FinalMeasuretCheck finalMeasuretCheck);

    CustomResult updateAll(FinalMeasuretCheck finalMeasuretCheck);

    CustomResult updateNote(FinalMeasuretCheck finalMeasuretCheck);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchFMeasureCheckByFMeasureCheckId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchFMeasureCheckByOrderId(Integer page, Integer rows, String searchValue);
}
