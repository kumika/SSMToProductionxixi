package Com.CB.Production.Mapper;

import Com.CB.Production.domain.DeviceMaintain;
import Com.CB.Production.domain.DeviceMaintainExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMaintainMapper {
    long countByExample(DeviceMaintainExample example);

    int deleteByExample(DeviceMaintainExample example);

    int deleteByPrimaryKey(String deviceMaintainId);

    int insert(DeviceMaintain record);

    int insertSelective(DeviceMaintain record);

    List<DeviceMaintain> selectByExample(DeviceMaintainExample example);

    DeviceMaintain selectByPrimaryKey(String deviceMaintainId);

    int updateByExampleSelective(@Param("record") DeviceMaintain record, @Param("example") DeviceMaintainExample example);

    int updateByExample(@Param("record") DeviceMaintain record, @Param("example") DeviceMaintainExample example);

    int updateByPrimaryKeySelective(DeviceMaintain record);

    int updateByPrimaryKey(DeviceMaintain record);

    
    
    
    
    
    int deleteBatch(String[] ids);

    int updateNote(DeviceMaintain deviceMaintain);

    List<DeviceMaintain> find(DeviceMaintain deviceMaintain);

    List<DeviceMaintain> searchDeviceMaintainByDeviceMaintainId(String deviceMaintainId);

    List<DeviceMaintain> searchDeviceMaintainByDeviceFaultId(String deviceFaultId);
}