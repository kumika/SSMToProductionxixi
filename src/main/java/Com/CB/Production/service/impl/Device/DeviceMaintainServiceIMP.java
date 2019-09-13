package Com.CB.Production.service.impl.Device;

import Com.CB.Production.Mapper.DeviceMaintainMapper;
import Com.CB.Production.domain.DeviceMaintain;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.DeviceMaintainService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceMaintainServiceIMP implements DeviceMaintainService {

    @Autowired
    DeviceMaintainMapper deviceMaintainMapper;

    @Override
    public DeviceMaintain get(String deviceMaintainId) {
        return deviceMaintainMapper.selectByPrimaryKey(deviceMaintainId);
    }

    @Override
    public CustomResult insert(DeviceMaintain deviceMaintain) {
        int i = deviceMaintainMapper.insert(deviceMaintain);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增设备维修信息失败");
        }
    }

    @Override
    public CustomResult update(DeviceMaintain deviceMaintain) {
        int i = deviceMaintainMapper.updateByPrimaryKeySelective(deviceMaintain);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改设备维修信息失败");
        }
    }

    public CustomResult delete(String deviceMaintainId) throws Exception {
        int i = deviceMaintainMapper.deleteByPrimaryKey(deviceMaintainId);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = deviceMaintainMapper.deleteBatch(ids);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult updateNote(DeviceMaintain deviceMaintain) {
        int i = deviceMaintainMapper.updateNote(deviceMaintain);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增设备维修备注失败");
        }
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, DeviceMaintain deviceMaintain) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceMaintain> list = deviceMaintainMapper.find(deviceMaintain);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceMaintain> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchDeviceMaintainByDeviceMaintainId(Integer page, Integer rows, String deviceMaintainId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceMaintain> list = deviceMaintainMapper.searchDeviceMaintainByDeviceMaintainId(deviceMaintainId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceMaintain> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchDeviceMaintainByDeviceFaultId(Integer page, Integer rows, String deviceFaultId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceMaintain> list = deviceMaintainMapper.searchDeviceMaintainByDeviceFaultId(deviceFaultId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceMaintain> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
