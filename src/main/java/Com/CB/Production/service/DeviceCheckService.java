package Com.CB.Production.service;


import Com.CB.Production.domain.DeviceCheck;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

public interface DeviceCheckService {
    DeviceCheck get(String deviceCheckId);

    CustomResult insert(DeviceCheck task);

    CustomResult update(DeviceCheck task);

    CustomResult updateNote(DeviceCheck task);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult getList(Integer page, Integer rows, DeviceCheck deviceCheck);

    EUDataGridResult searchDeviceCheckByDeviceCheckId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchDeviceCheckByDeviceName(Integer page, Integer rows, String searchValue);
}
