package Com.CB.Production.service.impl.scheduling;

import Com.CB.Production.Mapper.WorkMapper;
import Com.CB.Production.domain.Work;
import Com.CB.Production.domain.WorkExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.WorkVo;
import Com.CB.Production.service.WorkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkServiceIMPL implements WorkService {


    @Autowired
    WorkMapper workMapper;




    @Override
    public WorkVo get(String empId) {
        return workMapper.selectByPrimaryKey(empId);
    }

    @Override
    public List<WorkVo> find() {
        WorkExample example = new WorkExample();
        return workMapper.selectByExample(example);
    }

    @Override
    public CustomResult update(Work task) {
        int i = workMapper.updateByPrimaryKeySelective(task);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改作业信息失败");
        }
    }

    @Override
    public CustomResult insert(Work task) {
        int i = workMapper.insert(task);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改作业信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Work task) {
        int i = workMapper.updateByPrimaryKey(task);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改作业信息失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = workMapper.deleteByPrimaryKey(id);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = workMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows) {

        PageHelper.startPage(page, rows);
        List<WorkVo> works = workMapper.find();

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(works);

        PageInfo<WorkVo> pageInfo = new PageInfo<>(works);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchWorkByWorkProduct(Integer page, Integer rows, String workProduct) {
        PageHelper.startPage(page, rows);
        List<WorkVo> works = workMapper.searchWorkByWorkProduct(workProduct);

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(works);

        PageInfo<WorkVo> pageInfo = new PageInfo<>(works);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchWorkByWorkDevice(Integer page, Integer rows, String workDevice) {
        PageHelper.startPage(page, rows);
        List<WorkVo> works = workMapper.searchWorkByWorkDevice(workDevice);

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(works);

        PageInfo<WorkVo> pageInfo = new PageInfo<>(works);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchWorkByWorkProcess(Integer page, Integer rows, String workProcess) {
        PageHelper.startPage(page, rows);
        List<WorkVo> works = workMapper.searchWorkByWorkProcess(workProcess);

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(works);

        PageInfo<WorkVo> pageInfo = new PageInfo<>(works);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchWorkByWorkId(Integer page, Integer rows, String workId) {
        PageHelper.startPage(page, rows);
        List<WorkVo> works = workMapper.searchWorkByWorkId(workId);

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(works);

        PageInfo<WorkVo> pageInfo = new PageInfo<>(works);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }
}
