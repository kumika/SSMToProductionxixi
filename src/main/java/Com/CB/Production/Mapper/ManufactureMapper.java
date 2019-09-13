package Com.CB.Production.Mapper;

import Com.CB.Production.domain.Manufacture;
import Com.CB.Production.domain.ManufactureExample;
import Com.CB.Production.domain.vo.ManufactureVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManufactureMapper {
    long countByExample(ManufactureExample example);

    int deleteByExample(ManufactureExample example);

    int deleteByPrimaryKey(String manufactureSn);

    int insert(Manufacture record);

    int insertSelective(Manufacture record);

    List<ManufactureVo> selectByExample(ManufactureExample example);

    ManufactureVo selectByPrimaryKey(String manufactureSn);

    int updateByExampleSelective(@Param("record") Manufacture record, @Param("example") ManufactureExample example);

    int updateByExample(@Param("record") Manufacture record, @Param("example") ManufactureExample example);

    int updateByPrimaryKeySelective(Manufacture record);

    int updateByPrimaryKey(Manufacture record);


    /*======================================================*/
    List<ManufactureVo> find();

    /*批量删除*/
    int deleteBatch(String[] ids);

    List<ManufactureVo> searchManufactureByManufactureSN(String manufactureSn);

    List<ManufactureVo> searchManufactureByManufactureOrderId(String manufactureOrderId);

    List<ManufactureVo> searchManufactureByManufactureTechnologyName(String manufactureTechnologyName);
}