package Com.CB.Production.Mapper;

import Com.CB.Production.domain.MaterialConsume;
import Com.CB.Production.domain.MaterialConsumeExample;
import Com.CB.Production.domain.vo.MaterialConsumeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialConsumeMapper {
    long countByExample(MaterialConsumeExample example);

    int deleteByExample(MaterialConsumeExample example);

    int deleteByPrimaryKey(String consumeId);

    int insert(MaterialConsume record);

    int insertSelective(MaterialConsume record);

    List<MaterialConsume> selectByExample(MaterialConsumeExample example);

    MaterialConsume selectByPrimaryKey(String consumeId);

    int updateByExampleSelective(@Param("record") MaterialConsume record, @Param("example") MaterialConsumeExample example);

    int updateByExample(@Param("record") MaterialConsume record, @Param("example") MaterialConsumeExample example);

    int updateByPrimaryKeySelective(MaterialConsume record);

    int updateByPrimaryKey(MaterialConsume record);

    
    
    
    
    List<MaterialConsumeVo> find(MaterialConsumeVo materialConsume);

    int updateNote(MaterialConsume materialConsume);

    int deleteBatch(String[] ids);

    List<MaterialConsumeVo> searchMaterialConsumeByConsumeId(String consumeId);

    List<MaterialConsumeVo> searchMaterialConsumeByMaterialId(String materialId);

    List<MaterialConsumeVo> searchMaterialConsumeByWorkId(String workId);
}