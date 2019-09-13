package Com.CB.Production.service;

import Com.CB.Production.domain.authority.SysRole;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.RoleVo;

import java.util.Collection;
import java.util.List;

public interface RoleService {
    RoleVo findRoleByUserId(String id);

    RoleVo get(String roleId);

    List<RoleVo> find();

    EUDataGridResult getList(Integer page, Integer rows, RoleVo role);

    List<RoleVo> findByRoleNameAndId(String roleName, String roleId);

    CustomResult insert(SysRole role);

    CustomResult update(SysRole role);

    CustomResult updateAll(SysRole role);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchRoleByRoleId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchRoleByRoleName(Integer page, Integer rows, String searchValue);
}
