package Com.CB.Production.service;

import Com.CB.Production.domain.authority.SysPermission;
import Com.CB.Production.domain.authority.SysUser;

import java.util.List;

/**
 * 认证授权服务接口
 */
public interface SysService {
    SysUser getSysUserByName(String username);

    List<SysPermission> findMenuListByUserId(String id);

    List<SysPermission> findPermissionListByUserId(String userid);
}
