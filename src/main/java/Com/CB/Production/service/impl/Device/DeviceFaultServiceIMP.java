package Com.CB.Production.service.impl.Device;

import Com.CB.Production.Mapper.DeviceFaultMapper;
import Com.CB.Production.domain.DeviceFault;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.DeviceFaultVo;
import Com.CB.Production.service.DeviceFaultService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeviceFaultServiceIMP implements DeviceFaultService {

    @Autowired
    DeviceFaultMapper deviceFaultMapper;

    @Override
    public DeviceFault get(String orderId) {
        return deviceFaultMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<DeviceFault> find() {
        List<DeviceFault> list = deviceFaultMapper.getData();
        return list;
    }

    @Override
    public EUDataGridResult searchDeviceFaultByDeviceFaultId(Integer page, Integer rows, String deviceFaultId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceFault> list = deviceFaultMapper.searchDeviceFaultByDeviceFaultId(deviceFaultId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceFault> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, DeviceFault deviceFault) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceFaultVo> list = deviceFaultMapper.find(deviceFault);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceFaultVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchDeviceFaultByDeviceName(Integer page, Integer rows, String deviceName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceFault> list = deviceFaultMapper.searchDeviceFaultByDeviceName(deviceName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceFault> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult insert(DeviceFault deviceFault) {
        int i = deviceFaultMapper.insert(deviceFault);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增设备故障失败");
        }
    }

    @Override
    public CustomResult update(DeviceFault deviceFault) {
        int i = deviceFaultMapper.updateByPrimaryKeySelective(deviceFault);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改设备故障信息失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] deviceFaultIds) {
        int i = deviceFaultMapper.deleteBatch(deviceFaultIds);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult updateNote(DeviceFault deviceFault) {
        int i = deviceFaultMapper.updateNote(deviceFault);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增设备故障备注失败");
        }
    }

    @Override
    public CustomResult updateAll(DeviceFault deviceFault) {
        int i = deviceFaultMapper.updateByPrimaryKey(deviceFault);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改设备故障信息失败");
        }
    }


    public CustomResult delete(String deviceFaultId) throws Exception {
        int i = deviceFaultMapper.deleteByPrimaryKey(deviceFaultId);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }
}
