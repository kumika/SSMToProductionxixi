package Com.CB.Production.Mapper;

import Com.CB.Production.domain.MaterialReceive;
import Com.CB.Production.domain.MaterialReceiveExample;
import Com.CB.Production.domain.vo.MaterialReceiveVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialReceiveMapper {
    long countByExample(MaterialReceiveExample example);

    int deleteByExample(MaterialReceiveExample example);

    int deleteByPrimaryKey(String receiveId);

    int insert(MaterialReceive record);

    int insertSelective(MaterialReceive record);

    List<MaterialReceive> selectByExample(MaterialReceiveExample example);

    MaterialReceive selectByPrimaryKey(String receiveId);

    int updateByExampleSelective(@Param("record") MaterialReceive record, @Param("example") MaterialReceiveExample example);

    int updateByExample(@Param("record") MaterialReceive record, @Param("example") MaterialReceiveExample example);

    int updateByPrimaryKeySelective(MaterialReceive record);

    int updateByPrimaryKey(MaterialReceive record);

    
    
    
    int updateNote(MaterialReceive materialReceive);

    int deleteBatch(String[] ids);

    List<MaterialReceiveVo> find();

    List<MaterialReceiveVo> searchMaterialReceiveByReceiveId(String receiveId);

    List<MaterialReceiveVo> searchMaterialReceiveByMaterialId(String materialId);
}