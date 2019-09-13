package Com.CB.Production.service;

import Com.CB.Production.domain.DeviceType;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

import java.util.List;

public interface DeviceTypeService {
    DeviceType get(String orderId);

    List<DeviceType> find();

    CustomResult insert(DeviceType deviceType);

    CustomResult deleteBatch(String[] ids);

    CustomResult update(DeviceType deviceType);

    CustomResult updateAll(DeviceType deviceType);

    EUDataGridResult getList(Integer page, Integer rows, DeviceType deviceType);

    EUDataGridResult searchDeviceTypeByDeviceTypeId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchDeviceTypeByDeviceTypeName(Integer page, Integer rows, String searchValue);
}
