package Com.CB.Production.Mapper;

import Com.CB.Production.domain.FinalMeasuretCheck;
import Com.CB.Production.domain.FinalMeasuretCheckExample;
import Com.CB.Production.domain.vo.FinalMeasuretCheckVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinalMeasuretCheckMapper {
    long countByExample(FinalMeasuretCheckExample example);

    int deleteByExample(FinalMeasuretCheckExample example);

    int deleteByPrimaryKey(String fMeasureCheckId);

    int insert(FinalMeasuretCheck record);

    int insertSelective(FinalMeasuretCheck record);

    List<FinalMeasuretCheck> selectByExample(FinalMeasuretCheckExample example);

    FinalMeasuretCheck selectByPrimaryKey(String fMeasureCheckId);

    int updateByExampleSelective(@Param("record") FinalMeasuretCheck record, @Param("example") FinalMeasuretCheckExample example);

    int updateByExample(@Param("record") FinalMeasuretCheck record, @Param("example") FinalMeasuretCheckExample example);

    int updateByPrimaryKeySelective(FinalMeasuretCheck record);

    int updateByPrimaryKey(FinalMeasuretCheck record);

    
    
    
    
    
    
    
    
    
    
    
    List<FinalMeasuretCheckVo> find(FinalMeasuretCheck finalMeasuretCheck);

    int updateNote(FinalMeasuretCheck finalMeasuretCheck);

    List<FinalMeasuretCheckVo> searchFMeasureCheckByFMeasureCheckId(String fMeasureCheckId);

    List<FinalMeasuretCheckVo> searchFMeasureCheckByOrderId(String orderId);
}