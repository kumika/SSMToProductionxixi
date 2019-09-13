package Com.CB.Production.service.impl.Employee;

import Com.CB.Production.Mapper.EmployeeMapper;
import Com.CB.Production.domain.Employee;
import Com.CB.Production.domain.EmployeeExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.EmployeeVo;
import Com.CB.Production.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceIMP implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;


    @Override
    public EmployeeVo get(String empId) {
        return employeeMapper.selectSingleEmployee(empId);
    }

    @Override
    public List<Employee> find() {
        EmployeeExample example = new EmployeeExample();
        return employeeMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, EmployeeVo employee) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<EmployeeVo> list = employeeMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<EmployeeVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult insert(Employee employee) {
        int i = employeeMapper.insert(employee);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增员工信息失败");
        }
    }

    @Override
    public CustomResult update(Employee employee) {
        int i = employeeMapper.updateByPrimaryKeySelective(employee);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改员工信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Employee employee) {
        int i = employeeMapper.updateByPrimaryKey(employee);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改员工信息失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = employeeMapper.deleteByPrimaryKey(id);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = employeeMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult searchEmployeeByEmployeeId(Integer page, Integer rows, String employeeId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<EmployeeVo> list = employeeMapper.searchEmployeeByEmployeeId(employeeId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<EmployeeVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchEmployeeByEmployeeName(Integer page, Integer rows, String employeeName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<EmployeeVo> list = employeeMapper.searchEmployeeByEmployeeName(employeeName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<EmployeeVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchEmployeeByDepartmentName(Integer page, Integer rows, String departmentName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<EmployeeVo> list = employeeMapper.searchEmployeeByDepartmentName(departmentName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<EmployeeVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
