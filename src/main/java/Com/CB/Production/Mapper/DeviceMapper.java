package Com.CB.Production.Mapper;

import Com.CB.Production.domain.Device;
import Com.CB.Production.domain.DeviceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    long countByExample(DeviceExample example);

    int deleteByExample(DeviceExample example);

    int deleteByPrimaryKey(String deviceId);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceExample example);

    Device selectByPrimaryKey(String deviceId);

    int updateByExampleSelective(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByExample(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    
    
    
    
    
    
    
    
    
    List<Device> getData();

    int deleteBatch(String[] deviceIds);

    int updateNote(Device device);

    List<Device> searchDeviceByDeviceId(String deviceId);

    List<Device> searchDeviceByDeviceName(String deviceName);

    List<Device> searchDeviceByDeviceTypeName(String deviceTypeName);

    List<Device> find(Device device);
}