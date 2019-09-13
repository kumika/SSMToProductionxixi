package Com.CB.Production.Mapper;

import Com.CB.Production.domain.TechnologyPlan;
import Com.CB.Production.domain.TechnologyPlanExample;
import Com.CB.Production.domain.vo.TechnologyPlanVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechnologyPlanMapper {
    long countByExample(TechnologyPlanExample example);

    int deleteByExample(TechnologyPlanExample example);

    int deleteByPrimaryKey(String technologyPlanId);

    int insert(TechnologyPlan record);

    int insertSelective(TechnologyPlan record);

    List<TechnologyPlan> selectByExample(TechnologyPlanExample example);

    TechnologyPlan selectByPrimaryKey(String technologyPlanId);

    int updateByExampleSelective(@Param("record") TechnologyPlan record, @Param("example") TechnologyPlanExample example);

    int updateByExample(@Param("record") TechnologyPlan record, @Param("example") TechnologyPlanExample example);

    int updateByPrimaryKeySelective(TechnologyPlan record);

    int updateByPrimaryKey(TechnologyPlan record);








    int deleteBatch(String[] ids);

    List<TechnologyPlan> find(TechnologyPlanVo technologyPlan);

    List<TechnologyPlan> searchTechnologyPlanByTechnologyPlanId(String technologyPlanId);

    List<TechnologyPlan> searchTechnologyPlanByTechnologyName(String technologyName);
}