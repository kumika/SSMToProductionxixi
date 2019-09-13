package Com.CB.Production.service.impl;

import Com.CB.Production.domain.authority.SysPermission;
import Com.CB.Production.domain.authority.SysPermissionExample;
import Com.CB.Production.domain.authority.SysUser;
import Com.CB.Production.domain.authority.SysUserExample;
import Com.CB.Production.Mapper.authority.SysPermissionMapper;
import Com.CB.Production.Mapper.authority.SysPermissionMapperCustom;
import Com.CB.Production.Mapper.authority.SysUserMapper;
import Com.CB.Production.service.SysService;
import Com.CB.Production.util.CollectionsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 认证授权服务接口实现类
 */
@Service
public class SysServiceImpl implements SysService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysPermissionMapperCustom sysPermissionMapperCustom;

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public SysUser getSysUserByName(String username) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);

        SysUser sysUser = sysUserMapper.selectByExample(example).get(0);

        return sysUser;
    }

    @Override
    public List<SysPermission> findMenuListByUserId(String id) {
        List<SysPermission> menu = sysPermissionMapperCustom.findMenuListByUserId(id);
        return menu;
    }

    //关键点在于分开查询，我第一个想法就是直接一步到位，3个表查询
    /**
     *  思路：
     *  输入的是用户id，输出的是权限对象，这之间使用3个表格 sys-user-role, sys-role-permission, sys-permission
     *  1       根据UserId, 查询用户权限id
     *  2       根据用户权限id，查询权限对象（使用的是example）
     *
     *  思路：
     *
     *
     *
     * @param userid
     * @return
     */
    @Override
    public List<SysPermission> findPermissionListByUserId(String userid) {
        //根据用户id查询权限id
        String permission = this.sysPermissionMapperCustom.findPermissionByUseriId(userid);
        if (permission != null) {
            String[] permissionIds = permission.split(",");
            List<Long> ids = CollectionsFactory.newArrayList();
            for (int i=0;i < permissionIds.length;i++) {
                ids.add(Long.valueOf(permissionIds[i]));
            }
            SysPermissionExample example = new SysPermissionExample();
            SysPermissionExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);

            List<SysPermission>sysPermissionList =  sysPermissionMapper.selectByExample(example);

            return sysPermissionList;
        }
        //自己的想法：3个表连查
/*        List<SysPermission> sysPermissionList = sysPermissionMapperCustom.findPermissionByUserId(userid);
        if (sysPermissionList != null) {
            return sysPermissionList;
        }*/
        return null;
    }


}
