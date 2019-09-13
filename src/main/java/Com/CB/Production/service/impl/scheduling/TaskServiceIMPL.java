package Com.CB.Production.service.impl.scheduling;

import Com.CB.Production.Mapper.TaskMapper;
import Com.CB.Production.domain.Task;
import Com.CB.Production.domain.TaskExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.TaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceIMPL implements TaskService {

    @Autowired
    TaskMapper taskMapper;



    @Override
    public Task get(String empId) {
        return taskMapper.selectByPrimaryKey(empId);
    }

    @Override
    public List<Task> find() {
        TaskExample example = new TaskExample();
        return taskMapper.selectByExample(example);
    }

    @Override
    public CustomResult insert(Task task) {
        int i = taskMapper.insert(task);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增生产派工信息失败");
        }
    }

    @Override
    public CustomResult update(Task task) {
        int i = taskMapper.updateByPrimaryKeySelective(task);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "更新生产派工信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Task task) {
        int i = taskMapper.updateByPrimaryKey(task);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "更新生产派工信息失败");
        }
    }

    @Override
    public CustomResult updateNote(Task task) {
        int i = taskMapper.updateNote(task);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "更新生产派工信息失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = taskMapper.deleteByPrimaryKey(id);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "删除生产派工信息失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = taskMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "删除生产派工信息失败");
        }
    }

    /**
     * 查询所有记录
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDataGridResult getList(Integer page, Integer rows) {
        TaskExample example = new TaskExample();
        //分页处理
        PageHelper.startPage(page, rows);
        //查询全部的task的对象
        List<Task> taskList = taskMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(taskList);
        //取记录总条数
        PageInfo<Task> pageInfo = new PageInfo<>(taskList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchTaskByTaskId(Integer page, Integer rows, String taskId) {
        //分页处理
        PageHelper.startPage(page, rows);
        //根据生产派工id查找
        List<Task> taskList = taskMapper.searchTaskByTaskId(taskId);
        //创建一个返回值对象
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(taskList);
        //取记录总条数
        PageInfo<Task> pageInfo = new PageInfo<>(taskList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchTaskByTaskWorkId(Integer page, Integer rows, String taskWorkId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Task> taskList = taskMapper.searchTaskByTaskWorkId(taskWorkId);
        //创建一个返回值对象
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(taskList);
        //取记录总条数
        PageInfo<Task> pageInfo = new PageInfo<>(taskList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchTaskByTaskManufactureSn(Integer page, Integer rows, String taskManufactureSn) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Task> taskList = taskMapper.searchTaskByTaskManufactureSn(taskManufactureSn);
        //创建一个返回值对象
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(taskList);
        //取记录总条数
        PageInfo<Task> pageInfo = new PageInfo<>(taskList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }
}
