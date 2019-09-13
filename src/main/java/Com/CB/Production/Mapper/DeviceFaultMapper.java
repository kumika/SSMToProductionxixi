package Com.CB.Production.Mapper;

import Com.CB.Production.domain.DeviceFault;
import Com.CB.Production.domain.DeviceFaultExample;
import Com.CB.Production.domain.vo.DeviceFaultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceFaultMapper {
    long countByExample(DeviceFaultExample example);

    int deleteByExample(DeviceFaultExample example);

    int deleteByPrimaryKey(String deviceFaultId);

    int insert(DeviceFault record);

    int insertSelective(DeviceFault record);

    List<DeviceFault> selectByExample(DeviceFaultExample example);

    DeviceFault selectByPrimaryKey(String deviceFaultId);

    int updateByExampleSelective(@Param("record") DeviceFault record, @Param("example") DeviceFaultExample example);

    int updateByExample(@Param("record") DeviceFault record, @Param("example") DeviceFaultExample example);

    int updateByPrimaryKeySelective(DeviceFault record);

    int updateByPrimaryKey(DeviceFault record);






    List<DeviceFault> getData();

    List<DeviceFault> searchDeviceFaultByDeviceFaultId(String deviceFaultId);

    List<DeviceFaultVo> find(DeviceFault deviceFault);

    List<DeviceFault> searchDeviceFaultByDeviceName(String deviceName);

    int deleteBatch(String[] deviceFaultIds);

    int updateNote(DeviceFault deviceFault);
}