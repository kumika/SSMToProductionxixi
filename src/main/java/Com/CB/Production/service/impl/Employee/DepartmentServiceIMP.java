package Com.CB.Production.service.impl.Employee;

import Com.CB.Production.Mapper.DepartmentMapper;
import Com.CB.Production.domain.Department;
import Com.CB.Production.domain.DepartmentExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceIMP implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public Department get(String departmentId) {
        return departmentMapper.selectByPrimaryKey(departmentId);
    }

    @Override
    public List<Department> find() {
        DepartmentExample example = new DepartmentExample();
        return departmentMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, Department department) {
        //查询部门列表
        DepartmentExample example = new DepartmentExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<Department> list = departmentMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Department> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult insert(Department department) {
        int i = departmentMapper.insert(department);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增部门失败");
        }
    }

    @Override
    public CustomResult update(Department department) {
        int i = departmentMapper.updateByPrimaryKeySelective(department);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改部门信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Department department) {
        int i = departmentMapper.updateByPrimaryKey(department);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改部门信息失败");
        }
    }

    @Override
    public CustomResult updateNote(Department department) {
        int i = departmentMapper.updateNote(department);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改部门职责失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = departmentMapper.deleteByPrimaryKey(id);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = departmentMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult searchDepartmentByDepartmentId(Integer page, Integer rows, String departmentId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Department> list = departmentMapper.searchDepartmentByDepartmentId(departmentId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Department> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchDepartmentByDepartmentName(Integer page, Integer rows, String departmentName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Department> list = departmentMapper.searchDepartmentByDepartmentName(departmentName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Department> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
