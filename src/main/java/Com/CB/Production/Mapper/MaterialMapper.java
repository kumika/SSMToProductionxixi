package Com.CB.Production.Mapper;

import Com.CB.Production.domain.Material;
import Com.CB.Production.domain.MaterialExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialMapper {
    long countByExample(MaterialExample example);

    int deleteByExample(MaterialExample example);

    int deleteByPrimaryKey(String materialId);

    int insert(Material record);

    int insertSelective(Material record);

    List<Material> selectByExample(MaterialExample example);

    Material selectByPrimaryKey(String materialId);

    int updateByExampleSelective(@Param("record") Material record, @Param("example") MaterialExample example);

    int updateByExample(@Param("record") Material record, @Param("example") MaterialExample example);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);





    int updateNote(Material material);

    int deleteBatch(String[] ids);

    List<Material> searchMaterialByMaterialId(String materialId);

    List<Material> searchMaterialByMaterialType(String materialType);
}