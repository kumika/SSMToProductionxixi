package Com.CB.Production.Mapper.authority;

import Com.CB.Production.domain.authority.SysUser;
import Com.CB.Production.domain.authority.SysUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    long countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    
    
    
    
    List<SysUser> find(SysUser sysUser);

    int deleteBatch(String[] ids);

    int changeStatus(String[] ids);

    List<SysUser> searchUserByUserId(String userId);

    List<SysUser> searchUserByUserName(String userName);

    List<SysUser> searchUserByRoleName(String roleName);
}