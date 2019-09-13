package Com.CB.Production.Mapper;

import Com.CB.Production.domain.Custom;
import Com.CB.Production.domain.CustomExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomMapper {
    long countByExample(CustomExample example);

    int deleteByExample(CustomExample example);

    int deleteByPrimaryKey(String customId);

    int insert(Custom record);

    int insertSelective(Custom record);

    List<Custom> selectByExample(CustomExample example);

    Custom selectByPrimaryKey(String customId);

    int updateByExampleSelective(@Param("record") Custom record, @Param("example") CustomExample example);

    int updateByExample(@Param("record") Custom record, @Param("example") CustomExample example);

    int updateByPrimaryKeySelective(Custom record);

    int updateByPrimaryKey(Custom record);


    /*=============自己增加的方法==================================================*/
    //<!-- 修改备注-->
    int updateNote(Custom custom);
    //批量删除
    int deleteBatch(String[] ids);
    //批量修改状态
    int changeStatus(String[] ids);
    ///根据客户id查找
    List<Custom> searchCustomByCustomId(String customId);

    List<Custom> searchCustomByCustomName(String customName);
}