package Com.CB.Production.Mapper;

import Com.CB.Production.domain.Technology;
import Com.CB.Production.domain.TechnologyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechnologyMapper {
    long countByExample(TechnologyExample example);

    int deleteByExample(TechnologyExample example);

    int deleteByPrimaryKey(String technologyId);

    int insert(Technology record);

    int insertSelective(Technology record);

    List<Technology> selectByExample(TechnologyExample example);

    Technology selectByPrimaryKey(String technologyId);

    int updateByExampleSelective(@Param("record") Technology record, @Param("example") TechnologyExample example);

    int updateByExample(@Param("record") Technology record, @Param("example") TechnologyExample example);

    int updateByPrimaryKeySelective(Technology record);

    int updateByPrimaryKey(Technology record);

    
    
    
    
    
    
    
    int deleteBatch(String[] ids);

    List<Technology> searchTechnologyByTechnologyId(String technologyId);

    List<Technology> searchTechnologyByTechnologyName(String technologyName);
}