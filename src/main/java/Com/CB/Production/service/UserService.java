package Com.CB.Production.service;

import Com.CB.Production.domain.authority.SysUser;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

import java.util.Collection;
import java.util.List;

public interface UserService {
    SysUser get(String userId);

    EUDataGridResult getList(Integer page, Integer rows, SysUser sysUser);

    List<SysUser> findByUserNameAndId(String username, String id);

    CustomResult insert(SysUser user);

    CustomResult update(SysUser user);

    CustomResult updateAll(SysUser user);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    CustomResult changeStatus(String[] ids);

    EUDataGridResult searchUserByUserId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchUserByUserName(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchUserByRoleName(Integer page, Integer rows, String searchValue);
}
