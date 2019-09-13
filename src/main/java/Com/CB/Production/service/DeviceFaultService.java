package Com.CB.Production.service;

import Com.CB.Production.domain.DeviceFault;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

import java.util.List;

public interface DeviceFaultService {
    DeviceFault get(String orderId);

    List<DeviceFault> find();

    EUDataGridResult searchDeviceFaultByDeviceFaultId(Integer page, Integer rows, String searchValue);

    EUDataGridResult getList(Integer page, Integer rows, DeviceFault deviceFault);

    EUDataGridResult searchDeviceFaultByDeviceName(Integer page, Integer rows, String searchValue);

    CustomResult insert(DeviceFault deviceFault);

    CustomResult update(DeviceFault deviceFault);

    CustomResult deleteBatch(String[] ids);

    CustomResult updateNote(DeviceFault deviceFault);

    CustomResult updateAll(DeviceFault deviceFault);
}
