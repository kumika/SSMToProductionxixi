package Com.CB.Production.Mapper;

import Com.CB.Production.domain.DeviceType;
import Com.CB.Production.domain.DeviceTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceTypeMapper {
    long countByExample(DeviceTypeExample example);

    int deleteByExample(DeviceTypeExample example);

    int deleteByPrimaryKey(String deviceTypeId);

    int insert(DeviceType record);

    int insertSelective(DeviceType record);

    List<DeviceType> selectByExample(DeviceTypeExample example);

    DeviceType selectByPrimaryKey(String deviceTypeId);

    int updateByExampleSelective(@Param("record") DeviceType record, @Param("example") DeviceTypeExample example);

    int updateByExample(@Param("record") DeviceType record, @Param("example") DeviceTypeExample example);

    int updateByPrimaryKeySelective(DeviceType record);

    int updateByPrimaryKey(DeviceType record);





    int deleteBatch(String[] ids);

    List<DeviceType> find(DeviceType deviceType);

    List<DeviceType> searchDeviceTypeByDeviceTypeId(String deviceTypeId);

    List<DeviceType> searchDeviceTypeByDeviceTypeName(String deviceTypeName);

    List<DeviceType> listType();
}