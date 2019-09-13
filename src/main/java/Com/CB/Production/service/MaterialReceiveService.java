package Com.CB.Production.service;

import Com.CB.Production.domain.MaterialReceive;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

public interface MaterialReceiveService {
    MaterialReceive get(String receiveId);

    CustomResult insert(MaterialReceive materialReceive);

    CustomResult update(MaterialReceive materialReceive);

    CustomResult updateAll(MaterialReceive materialReceive);

    CustomResult updateNote(MaterialReceive materialReceive);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult getList(Integer page, Integer rows);

    EUDataGridResult searchMaterialReceiveByReceiveId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchMaterialReceiveByMaterialId(Integer page, Integer rows, String searchValue);
}
