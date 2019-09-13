package Com.CB.Production.service.impl.technology;

import Com.CB.Production.Mapper.ProcessMapper;
import Com.CB.Production.domain.Process;
import Com.CB.Production.domain.ProcessExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.ProcessService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessServiceIMP implements ProcessService {

    @Autowired
    ProcessMapper processMapper;


    @Override
    public Process get(String processId) {
        return processMapper.selectByPrimaryKey(processId);
    }

    @Override
    public List<Process> find() {
        ProcessExample example = new ProcessExample();
        return processMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, Process process) {
        //查询商品列表
        ProcessExample example = new ProcessExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<Process> list = processMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Process> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult insert(Process process) {
        int i = processMapper.insert(process);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增工序信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Process process) {
        int i = processMapper.updateByPrimaryKey(process);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改工序信息失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = processMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult searchProcessByProcessId(Integer page, Integer rows, String processId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Process> list = processMapper.searchProcessByProcessId(processId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Process> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchProcessByTechnologyPlanId(Integer page, Integer rows, String technologyPlanId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Process> list = processMapper.searchProcessByTechnologyPlanId(technologyPlanId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Process> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
