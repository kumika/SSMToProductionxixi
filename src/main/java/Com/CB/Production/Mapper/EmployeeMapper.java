package Com.CB.Production.Mapper;

import Com.CB.Production.domain.Employee;
import Com.CB.Production.domain.EmployeeExample;
import Com.CB.Production.domain.vo.EmployeeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {


  
    
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(String empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(String empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);






    EmployeeVo selectSingleEmployee(String empId);


    List<EmployeeVo> find();

    int deleteBatch(String[] ids);

    List<EmployeeVo> searchEmployeeByEmployeeId(String employeeId);

    List<EmployeeVo> searchEmployeeByEmployeeName(String employeeName);

    List<EmployeeVo> searchEmployeeByDepartmentName(String departmentName);
}