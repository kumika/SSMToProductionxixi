package Com.CB.Production.service;

import Com.CB.Production.domain.Device;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

import java.util.List;

public interface DeviceService {
    Device get(String deviceId);

    List<Device> find();

    CustomResult insert(Device device);

    CustomResult update(Device device);

    CustomResult deleteBatch(String[] ids);

    CustomResult updateNote(Device device);

    CustomResult updateAll(Device device);

    EUDataGridResult getList(Integer page, Integer rows, Device device);

    EUDataGridResult searchDeviceByDeviceId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchDeviceByDeviceName(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchDeviceByDeviceTypeName(Integer page, Integer rows, String searchValue);
}
