package Com.CB.Production.service;

import Com.CB.Production.domain.Employee;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.EmployeeVo;

import java.util.List;

public interface EmployeeService {
    EmployeeVo get(String empId);

    List<Employee> find();

    EUDataGridResult getList(Integer page, Integer rows, EmployeeVo employee);

    CustomResult insert(Employee employee);

    CustomResult update(Employee employee);

    CustomResult updateAll(Employee employee);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchEmployeeByEmployeeId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchEmployeeByEmployeeName(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchEmployeeByDepartmentName(Integer page, Integer rows, String searchValue);
}
