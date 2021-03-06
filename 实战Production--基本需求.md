﻿# 实战Production--基本需求


[![基本需求.jpg](https://i.loli.net/2018/12/19/5c1a652fbb3b3.jpg)](https://i.loli.net/2018/12/19/5c1a652fbb3b3.jpg)



#登陆

**启动项目就是登陆了**

登陆2个验证，验证码的判断，用户名密码的验证

##控制层


思路：
**1   先把验证码给验证了（是传入X和在SESSION上的X进行对比，完成验证）
2   使用shiro工具，就按照它的流程来，拿到subject，然后根据username和password创建令牌token，根据token使用subject的login方法进行登陆**
具体：
```
     *   1      获取用户名，密码，验证码，session 这4个参数
     *   2      从传过来的验证码和直接从session拿的验证码进行对比，判断验证码是否正确，不正确直接返回页面
     *   3      从security Utils拿出subject（类似idea先创建项目project）
     *   4      判断subject是否已经验证
     *   5      把用户名，密码作为参数，创建令牌UsernamePasswordToken，得到令牌对象token
     *   6      把令牌token作为参数，使用subject的方法login，跳转到RealM中的doGetAuthenticationInfo()方法，完成用户名，密码的验证
     *   6.1    怎么跳转到RealM上？（这看配置文件怎么配置了）
```
因为是使用Ajax，所以要记得使用`@ResponseBody`
代码：
```
    @RquestMapper("/ajaxLogin")
    public Map<String, Object> ajaxLogin(@RequestParam String username,
                                         @RequestParam String password,
                                         @RequestParam(required = false) String randomcode,
                                         HttpSession session) {
        //登陆出现错误，在页面上需要显示错误问题
        Map <String, Object> map = CollectionsFactory.newHashMap();
        if(!randomcode.equals("")&& randmcode!= null){
             //取出session的验证码（正确的验证码）
             String  sName = session.getAttribute("validateCode");
             //页面中输入的验证和session中的验证进行对比
             if(sName != null && !sName.equals(randomcode)){
                //如果校验失败，将验证码错误失败信息放入map中
                map.put("msg","randomcode_error");
                //直接返回，不再校验账号和密码
                return map;
             }
        }
        //创建shiro项目，就是从SecurityUtils中拿一个
        Subject currentUser = SecurityUtils.getSubject();
        //判断currentUser是否已经验证
        if(!currentUser.isAuthenticated()){
            //创建令牌Token
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try {
                //进行登陆
                currentUser.login(token);
            } catch (UnknownAccountException ex) {
                map.put("msg", "account_error");
            } catch (IncorrectCredentialsException ex) {
                map.put("msg", "password_error");
            } catch (AuthenticationException ex) {
                map.put("msg", "authentication_error");
            }
        }
        //返回json数据
        return map;
}                                         
```

##业务层
在经过shiro的管理器sessionManager操作，跳转到RealM上进行业务操作，也就是这个Shiro工具给我操作的地方在RealM上，目的重写2个方法，一个是授权，一个是身份验证

**·授权：**

在Controller层中subject的isPermitted（）方法，或者@RequiresPermissions这个注解就开始调用授权方法

**思路：**

     * 1        从 principals获取主身份信息，赋值给用户身份信息对象ActiveUser
     * 2        根据ActiveUser获取User的id，查询并且返回权限列表数据permissionsList
     * 3        创建String类型的授权信息List对象---permissions，并遍历permissionsList，将属性PerCode增加到permissions上
     * 4        创建授权信息对象simpleAuthorizationInfo
     * 5        将上边查询到授权信息permissions填充到simpleAuthorizationInfo对象中
     * 6        返回simpleAuthorizationInfo

代码：
```
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
```

**身份验证**：

关键是要知道数据库上用户和权限对象之间的关系

权限----系统角色权限认证----用户角色----角色----系统用户

[![sys的外键联系.jpg](https://i.loli.net/2018/12/27/5c249ac3e3584.jpg)](https://i.loli.net/2018/12/27/5c249ac3e3584.jpg)


要清楚本项目的**身份安全数据源里的属性**：  



    用户名称    username
    用户状态    userStatus-------数据库中SysUser的locked字段
    角色名称    rolename
    角色状态    roleStatus-------数据库中SysRole的available字段
    菜单        menus-----------数据库中Syspermission表格返回的对象的集合
    
**填充完成这个身份安全数据源，生成simpleAuthenticationInfo对象，就算完成Shiro工具给你操作的部分了，其他的是工具完成。**


**思路：**
realm的认证方法，从数据库查询用户信息

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





**代码：**

```
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
```


##Dao层：


权限----系统角色权限认证----用户角色----角色----系统用户

明白这个表格关系后，我只写特殊的查询，一般的查询，都交给`example`和`criteria`来处理了。在查询的时候，一定要清楚明白自己要查询的对象是什么，返回的对象是什么，**也就是输入是什么变量，输出是什么变量，2个变量在数据库的表格之间有什么关联（外键）**。这样无论查询用到多少个表格都是不怎么重要的了。


###**需求:**
根据用户id取出菜单

根据用户id查询菜单 ，菜单是在系统认证表格上的值，不是字段，这里第一次碰见使用值的，type关键字
         使用了3张表，系统权限《======》系统用户权限《===》系统用户角色，
         要清楚输入的变量是在哪张表，输出的变量是在哪张表，辨析之间的联系


输入变量：**sysUser.getId()**
输出变量：

     List<SysPermission> menu = sysPermissionMapperCustom.findMenuListByUserId(id);

**SQL:**     
```
    <select id="findMenuListByUserId" parameterType="String" resultType="Com.CB.Production.domain.authority.SysPermission">
        SELECT * from sys_permission WHERE  type = 'menu' AND  id IN
          (SELECT sys_permission_id from sys_role_permission WHERE  sys_role_id IN
              (SELECT sys_role_id FROM  sys_user_role WHERE sys_user_id = #{id})
          )
    </select>
```

