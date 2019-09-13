package Com.CB.Production.service;

import Com.CB.Production.domain.DeviceMaintain;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

public interface DeviceMaintainService {
    DeviceMaintain get(String deviceMaintainId);

    CustomResult insert(DeviceMaintain deviceMaintain);

    CustomResult update(DeviceMaintain deviceMaintain);

    CustomResult deleteBatch(String[] ids);

    CustomResult updateNote(DeviceMaintain deviceMaintain);

    EUDataGridResult getList(Integer page, Integer rows, DeviceMaintain deviceMaintain);

    EUDataGridResult searchDeviceMaintainByDeviceMaintainId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchDeviceMaintainByDeviceFaultId(Integer page, Integer rows, String searchValue);
}
