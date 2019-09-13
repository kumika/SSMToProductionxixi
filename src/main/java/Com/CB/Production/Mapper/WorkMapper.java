package Com.CB.Production.Mapper;

import Com.CB.Production.domain.Work;
import Com.CB.Production.domain.WorkExample;
import Com.CB.Production.domain.vo.WorkVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkMapper {
    long countByExample(WorkExample example);

    int deleteByExample(WorkExample example);

    int deleteByPrimaryKey(String workId);

    int insert(Work record);

    int insertSelective(Work record);

    List<WorkVo> selectByExample(WorkExample example);

    WorkVo selectByPrimaryKey(String workId);

    int updateByExampleSelective(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByExample(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);

    /*=================*/

    int deleteBatch(String[] ids);

    List<WorkVo> searchWorkByWorkProduct(String workProduct);

    List<WorkVo> searchWorkByWorkDevice(String workDevice);

    List<WorkVo> searchWorkByWorkProcess(String workProcess);

    List<WorkVo> searchWorkByWorkId(String workId);

    List<WorkVo> find();
}