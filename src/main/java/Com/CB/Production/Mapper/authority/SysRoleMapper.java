package Com.CB.Production.Mapper.authority;

import Com.CB.Production.domain.authority.SysRole;
import Com.CB.Production.domain.authority.SysRoleExample;
import Com.CB.Production.domain.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<RoleVo> selectByExample(SysRoleExample example);

    RoleVo selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") RoleVo record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") RoleVo record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);









    int deleteBatch(String[] ids);

    List<RoleVo> searchRoleByRoleName(String roleName);

    List<RoleVo> searchRoleByRoleId(String roleId);
}