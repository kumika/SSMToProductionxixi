package Com.CB.Production.service.impl.quality;

import Com.CB.Production.Mapper.ProcessCountCheckMapper;
import Com.CB.Production.domain.ProcessCountCheck;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.ProcessCountCheckVo;
import Com.CB.Production.service.PCountCheckService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PCountCheckServiceIMP implements PCountCheckService {

    @Autowired
    ProcessCountCheckMapper processCountCheckMapper;

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, ProcessCountCheck processCountCheck) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<ProcessCountCheckVo> list = processCountCheckMapper.find(processCountCheck);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<ProcessCountCheckVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult insert(ProcessCountCheck processCountCheck) {
        int i = processCountCheckMapper.insert(processCountCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增工序计数质检信息失败");
        }
    }

    @Override
    public CustomResult updateAll(ProcessCountCheck processCountCheck) {
        int i = processCountCheckMapper.updateByPrimaryKey(processCountCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改工序计数质检信息失败");
        }
    }

    @Override
    public CustomResult updateNote(ProcessCountCheck processCountCheck) {
        int i = processCountCheckMapper.updateNote(processCountCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改工序计数质检备注失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = processCountCheckMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult searchPCountCheckByPCountCheckId(Integer page, Integer rows, String pCountCheckId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<ProcessCountCheckVo> list = processCountCheckMapper.searchPCountCheckByPCountCheckId(pCountCheckId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<ProcessCountCheckVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
