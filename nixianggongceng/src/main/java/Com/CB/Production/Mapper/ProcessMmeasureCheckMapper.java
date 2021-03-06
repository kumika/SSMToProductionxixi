package Com.CB.Production.Mapper;

import Com.CB.Production.domain.ProcessMmeasureCheck;
import Com.CB.Production.domain.ProcessMmeasureCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessMmeasureCheckMapper {
    long countByExample(ProcessMmeasureCheckExample example);

    int deleteByExample(ProcessMmeasureCheckExample example);

    int deleteByPrimaryKey(String pMeasureCheckId);

    int insert(ProcessMmeasureCheck record);

    int insertSelective(ProcessMmeasureCheck record);

    List<ProcessMmeasureCheck> selectByExample(ProcessMmeasureCheckExample example);

    ProcessMmeasureCheck selectByPrimaryKey(String pMeasureCheckId);

    int updateByExampleSelective(@Param("record") ProcessMmeasureCheck record, @Param("example") ProcessMmeasureCheckExample example);

    int updateByExample(@Param("record") ProcessMmeasureCheck record, @Param("example") ProcessMmeasureCheckExample example);

    int updateByPrimaryKeySelective(ProcessMmeasureCheck record);

    int updateByPrimaryKey(ProcessMmeasureCheck record);
}