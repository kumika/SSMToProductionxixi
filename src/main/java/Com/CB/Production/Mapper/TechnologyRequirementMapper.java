package Com.CB.Production.Mapper;

import Com.CB.Production.domain.TechnologyRequirement;
import Com.CB.Production.domain.TechnologyRequirementExample;
import Com.CB.Production.domain.vo.TechnologyRequirementVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechnologyRequirementMapper {
    long countByExample(TechnologyRequirementExample example);

    int deleteByExample(TechnologyRequirementExample example);

    int deleteByPrimaryKey(String technologyRequirementId);

    int insert(TechnologyRequirement record);

    int insertSelective(TechnologyRequirement record);

    List<TechnologyRequirement> selectByExample(TechnologyRequirementExample example);

    TechnologyRequirement selectByPrimaryKey(String technologyRequirementId);

    int updateByExampleSelective(@Param("record") TechnologyRequirement record, @Param("example") TechnologyRequirementExample example);

    int updateByExample(@Param("record") TechnologyRequirement record, @Param("example") TechnologyRequirementExample example);

    int updateByPrimaryKeySelective(TechnologyRequirement record);

    int updateByPrimaryKey(TechnologyRequirement record);






    int updateRequirement(TechnologyRequirement technologyRequirement);

    int deleteBatch(String[] ids);

    List<TechnologyRequirementVo> find(TechnologyRequirementVo technologyRequirement);

    List<TechnologyRequirementVo> searchTechnologyRequirementByTechnologyRequirementId(String technologyRequirementId);

    List<TechnologyRequirementVo> searchTechnologyRequirementByTechnologyName(String technologyName);
}