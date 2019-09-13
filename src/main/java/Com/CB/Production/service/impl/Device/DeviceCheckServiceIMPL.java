package Com.CB.Production.service.impl.Device;

import Com.CB.Production.Mapper.DeviceCheckMapper;
import Com.CB.Production.domain.DeviceCheck;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.DeviceCheckService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceCheckServiceIMPL implements DeviceCheckService {

    @Autowired
    DeviceCheckMapper deviceCheckMapper;


    @Override
    public DeviceCheck get(String deviceCheckId) {
        return deviceCheckMapper.selectByPrimaryKey(deviceCheckId);
    }

    @Override
    public CustomResult insert(DeviceCheck task) {
        int i = deviceCheckMapper.insert(task);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增设备例检失败");
        }
    }

    @Override
    public CustomResult update(DeviceCheck task) {
        int i = deviceCheckMapper.updateByPrimaryKeySelective(task);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改设备例检失败");
        }
    }

    @Override
    public CustomResult updateNote(DeviceCheck task) {
        int i = deviceCheckMapper.updateNote(task);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改例检结果失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = deviceCheckMapper.deleteBatch(ids);

        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "删除例检结果失败");
        }
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, DeviceCheck deviceCheck) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceCheck> deviceCheckList = deviceCheckMapper.find(deviceCheck);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(deviceCheckList);
        //取记录总条数
        PageInfo<DeviceCheck> pageInfo = new PageInfo<>(deviceCheckList);
        result.setTotal(pageInfo.getTotal());
        return result;

    }

    @Override
    public EUDataGridResult searchDeviceCheckByDeviceCheckId(Integer page, Integer rows, String deviceCheckId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceCheck> deviceCheckList = deviceCheckMapper.searchDeviceCheckByDeviceCheckId(deviceCheckId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(deviceCheckList);
        //取记录总条数
        PageInfo<DeviceCheck> pageInfo = new PageInfo<>(deviceCheckList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchDeviceCheckByDeviceName(Integer page, Integer rows, String deviceName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceCheck> deviceCheckList = deviceCheckMapper.searchDeviceCheckByDeviceName(deviceName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(deviceCheckList);
        //取记录总条数
        PageInfo<DeviceCheck> pageInfo = new PageInfo<>(deviceCheckList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
