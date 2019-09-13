package Com.CB.Production.Mapper;

import Com.CB.Production.domain.DeviceCheck;
import Com.CB.Production.domain.DeviceCheckExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceCheckMapper {
    long countByExample(DeviceCheckExample example);

    int deleteByExample(DeviceCheckExample example);

    int deleteByPrimaryKey(String deviceCheckId);

    int insert(DeviceCheck record);

    int insertSelective(DeviceCheck record);

    List<DeviceCheck> selectByExample(DeviceCheckExample example);

    DeviceCheck selectByPrimaryKey(String deviceCheckId);

    int updateByExampleSelective(@Param("record") DeviceCheck record, @Param("example") DeviceCheckExample example);

    int updateByExample(@Param("record") DeviceCheck record, @Param("example") DeviceCheckExample example);

    int updateByPrimaryKeySelective(DeviceCheck record);

    int updateByPrimaryKey(DeviceCheck record);


    /*===============================*/
    int updateNote(DeviceCheck task);

    int deleteBatch(String[] ids);

    List<DeviceCheck> find(DeviceCheck deviceCheck);

    List<DeviceCheck> searchDeviceCheckByDeviceCheckId(String deviceCheckId);

    List<DeviceCheck> searchDeviceCheckByDeviceName(String deviceName);
}