package Com.CB.Production.service.impl.Device;

import Com.CB.Production.Mapper.DeviceTypeMapper;
import Com.CB.Production.domain.DeviceType;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.DeviceTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceTypeServiceIMP implements DeviceTypeService {

    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Override
    public DeviceType get(String orderId) {
        return deviceTypeMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<DeviceType> find() {
        List<DeviceType> list = deviceTypeMapper.listType();
        return list;
    }

    @Override
    public CustomResult insert(DeviceType deviceType) {
        int i = deviceTypeMapper.insert(deviceType);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = deviceTypeMapper.deleteBatch(ids);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult update(DeviceType deviceType) {
        int i = deviceTypeMapper.updateByPrimaryKeySelective(deviceType);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改设备种类信息失败");
        }
    }

    @Override
    public CustomResult updateAll(DeviceType deviceType) {
        int i = deviceTypeMapper.updateByPrimaryKey(deviceType);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改设备种类信息失败");
        }
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, DeviceType deviceType) {
        PageHelper.startPage(page, rows);
        List<DeviceType> deviceTypeList = deviceTypeMapper.find(deviceType);

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(deviceTypeList);

        PageInfo<DeviceType> pageInfo = new PageInfo<>(deviceTypeList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchDeviceTypeByDeviceTypeId(Integer page, Integer rows, String DeviceTypeId) {
        PageHelper.startPage(page, rows);
        List<DeviceType> deviceTypeList = deviceTypeMapper.searchDeviceTypeByDeviceTypeId(DeviceTypeId);

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(deviceTypeList);

        PageInfo<DeviceType> pageInfo = new PageInfo<>(deviceTypeList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchDeviceTypeByDeviceTypeName(Integer page, Integer rows, String DeviceTypeName) {
        PageHelper.startPage(page, rows);
        List<DeviceType> deviceTypeList = deviceTypeMapper.searchDeviceTypeByDeviceTypeName(DeviceTypeName);

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(deviceTypeList);

        PageInfo<DeviceType> pageInfo = new PageInfo<>(deviceTypeList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }
}
