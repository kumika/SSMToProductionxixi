package Com.CB.Production.service;

import Com.CB.Production.domain.Department;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

import java.util.List;

public interface DepartmentService {
    Department get(String departmentId);

    List<Department> find();

    EUDataGridResult getList(Integer page, Integer rows, Department department);

    CustomResult insert(Department department);

    CustomResult update(Department department);

    CustomResult updateAll(Department department);

    CustomResult updateNote(Department department);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchDepartmentByDepartmentId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchDepartmentByDepartmentName(Integer page, Integer rows, String searchValue);
}
