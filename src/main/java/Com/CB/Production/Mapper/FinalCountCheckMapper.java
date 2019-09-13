package Com.CB.Production.Mapper;

import Com.CB.Production.domain.FinalCountCheck;
import Com.CB.Production.domain.FinalCountCheckExample;
import Com.CB.Production.domain.vo.FinalCountCheckVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinalCountCheckMapper {
    long countByExample(FinalCountCheckExample example);

    int deleteByExample(FinalCountCheckExample example);

    int deleteByPrimaryKey(String fCountCheckId);

    int insert(FinalCountCheck record);

    int insertSelective(FinalCountCheck record);

    List<FinalCountCheck> selectByExample(FinalCountCheckExample example);

    FinalCountCheck selectByPrimaryKey(String fCountCheckId);

    int updateByExampleSelective(@Param("record") FinalCountCheck record, @Param("example") FinalCountCheckExample example);

    int updateByExample(@Param("record") FinalCountCheck record, @Param("example") FinalCountCheckExample example);

    int updateByPrimaryKeySelective(FinalCountCheck record);

    int updateByPrimaryKey(FinalCountCheck record);

    
    
    
    
    
    
    int updateNote(FinalCountCheck finalCountCheck);

    int deleteBatch(String[] ids);

    List<FinalCountCheckVo> find(FinalCountCheck finalCountCheck);

    List<FinalCountCheckVo> searchFCountCheckByFCountCheckId(String fCountCheckId);

    List<FinalCountCheckVo> searchFCountCheckByOrderId(String orderId);
}