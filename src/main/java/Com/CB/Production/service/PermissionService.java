package Com.CB.Production.service;

import Com.CB.Production.domain.authority.SysRolePermission;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

import java.util.List;

public interface PermissionService {
    SysRolePermission get(String permissionId);

    List<SysRolePermission> find();

    SysRolePermission getByRoleId(String roleId);

    EUDataGridResult getList(Integer page, Integer rows, SysRolePermission sysRolePermission);

    CustomResult insert(SysRolePermission sysRolePermission);

    CustomResult update(SysRolePermission sysRolePermission);

    CustomResult updateByRoleId(String roleId, String permission);

    CustomResult updateAll(SysRolePermission sysRolePermission);

    CustomResult delete(String id);
}
