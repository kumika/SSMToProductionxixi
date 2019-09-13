package Com.CB.Production.Mapper;

import Com.CB.Production.domain.Process;
import Com.CB.Production.domain.ProcessExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProcessMapper {
    long countByExample(ProcessExample example);

    int deleteByExample(ProcessExample example);

    int deleteByPrimaryKey(String processId);

    int insert(Process record);

    int insertSelective(Process record);

    List<Process> selectByExample(ProcessExample example);

    Process selectByPrimaryKey(String processId);

    int updateByExampleSelective(@Param("record") Process record, @Param("example") ProcessExample example);

    int updateByExample(@Param("record") Process record, @Param("example") ProcessExample example);

    int updateByPrimaryKeySelective(Process record);

    int updateByPrimaryKey(Process record);







    int deleteBatch(String[] ids);

    List<Process> searchProcessByProcessId(String processId);

    List<Process> searchProcessByTechnologyPlanId(String technologyPlanId);
}