package Com.CB.Production.shiro;


import Com.CB.Production.domain.authority.SysPermission;
import Com.CB.Production.domain.authority.SysUser;
import Com.CB.Production.domain.customize.ActiveUser;
import Com.CB.Production.domain.vo.RoleVo;
import Com.CB.Production.service.RoleService;
import Com.CB.Production.service.SysService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.Oneway;
import java.util.ArrayList;
import java.util.List;


public class CustomRealm extends AuthorizingRealm{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SysService sysService;

    @Autowired
    RoleService roleService;

    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }



    /**
     * realm的授权方法
     * 在Controller层中subject的isPermitted（）方法，或者@RequiresPermissions这个注解就开始调用授权方法
     * 1        从 principals获取主身份信息，赋值给用户身份信息对象ActiveUser
     * 2        根据ActiveUser获取User的id，查询并且返回权限列表数据permissionsList
     * 3        创建String类型的授权信息List对象---permissions，并遍历permissionsList，将属性PerCode增加到permissions上
     * 4        创建授权信息对象simpleAuthorizationInfo
     * 5        将上边查询到授权信息permissions填充到simpleAuthorizationInfo对象中
     * 6        返回simpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //从 principals获取主身份信息
        //将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();

        //根据身份信息从数据库获取到权限数据
        List<SysPermission> permissionsList = null;

        try {
            permissionsList = sysService.findMenuListByUserId(activeUser.getUserid());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        List<String> permissions = new ArrayList<>();
        if (permissionsList != null) {
            for (SysPermission sysPermission : permissionsList) {
                permissions.add(sysPermission.getPercode());
            }
        }

        //查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    /**
     * realm的认证方法，从数据库查询用户信息
     * 1        token是用户输入的用户名和密码,第一步从token中取出用户名
     * 2        根据用户输入的username从数据库查询，返回用户对象SysUser
     * 3        判断SysUser是否为空，是空就返回null
     * 4        从SysUser拿出password
     * 5        创建用户身份信息ActiveUser对象
     * 6        根据SysUser的属性（Id，name，用户状态Locked），设置给ActiveUser
     * 7        根据SysUser的id，查询并且返回权限对象sysRole
     * 8        sysRole的属性：name，Available 都赋值给ActiveUser
     * 9        根据SysUser的id，查询并且返回菜单对象menus
     * 10       将用户菜单menus设置到activeUser
     * 11       根据activeUser,password,当前RealM类的完整包路径，这3个作为参数，创建身份认证对象---simpleAuthenticationInfo对象
     * 12       返回simpleAuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // token是用户输入的用户名和密码,第一步从token中取出用户名
        String username = (String) token.getPrincipal();//获取用户名，在controller层创建Token的时候，getPrincipal方法返回的值就是Username

        // 第二步：根据用户输入的username从数据库查询
        SysUser sysUser = null;

        try {
            sysUser = sysService.getSysUserByName(username);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        // 如果查询不到返回null
        if (sysUser == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("user not exist!");
            }
            return null;
        }
        String password = sysUser.getPassword();

        // 如果查询到返回认证信息AuthenticationInfo
        //activeUser就是用户身份信息
        ActiveUser activeUser = new ActiveUser();

        activeUser.setUserid(sysUser.getId());
        activeUser.setUsername(sysUser.getUsername());
        activeUser.setUserStatus(sysUser.getLocked());
        //权限对象
        RoleVo sysRole = null;
        try {
            sysRole = roleService.findRoleByUserId(sysUser.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //身份信息进行设置权限名，权限
        activeUser.setRolename(sysRole.getRoleName());
        activeUser.setRoleStatus(sysRole.getAvailable());

        logger.info(activeUser.getUsername());

        //根据用户id取出菜单
        List<SysPermission> menus = null;

        try {
            //通过service取出菜单，
            // 这是根据值查询，不是一般的根据字段查询，卧槽第一次看见这样的查询
            menus = sysService.findMenuListByUserId(sysUser.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        //将用户菜单设置到activeUser
        activeUser.setMenus(menus);

        //ByteSource q = ByteSource.Util.bytes(sysUser.getSalt());
        //将activeUser设置simpleAuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser,password,this.getName());

        return simpleAuthenticationInfo;
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }


}
