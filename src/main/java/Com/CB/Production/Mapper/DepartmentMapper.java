package Com.CB.Production.Mapper;

import Com.CB.Production.domain.Department;
import Com.CB.Production.domain.DepartmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    long countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(String departmentId);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(String departmentId);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);





    int updateNote(Department department);

    int deleteBatch(String[] ids);

    List<Department> searchDepartmentByDepartmentId(String departmentId);

    List<Department> searchDepartmentByDepartmentName(String departmentName);
}