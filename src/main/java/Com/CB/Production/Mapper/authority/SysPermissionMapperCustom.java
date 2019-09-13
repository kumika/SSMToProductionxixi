package Com.CB.Production.Mapper.authority;

import Com.CB.Production.domain.authority.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapperCustom {
    //根据用户id查询菜单
    List<SysPermission> findMenuListByUserId(String id);
    //根据用户id查询权限id
    String findPermissionByUseriId(@Param("userid") String userid);
    //根据用户id查询url
    List<SysPermission> findPermissionByUserId(@Param("userid") String userid);
}
