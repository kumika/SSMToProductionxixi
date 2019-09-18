# 复习项目--安全控制Shiro

这个项目来自SSMToProductionxixi


#shiro配置

一定要实现的配置：
---------


 **1. 添加jar包
 2. 配置web.xml（配置shiro过滤器，shiro和spring整合在一起了）
 3. 配置spring--shiro.xml**

缓存是在spring--shiro.xml里面配置的。


spring--shiro.xml这里面重点在**Realm，这是shiro这个大机器黑匣子所能给你操作的控制台。**

给你控制的是用户的验证`AuthenticationInfo`，用户的授权`AuthorizationInfo`


##验证

得到令牌token，然后根据方法`getPrincipal（）`拿到用户名
根据用户名，查询用户，返回用户对象
返回的用户对象，把它全部的属性赋值给新创建的用户身份信息对象

有关权限对象
根据用户id ，查询用户的权限，返回用户权限对象
返回的用户权限对象，把它全部的属性赋值给用户身份信息对象

将用户身份信息对象，用户密码，当前realm的名字，这3个参数创建一个简易验证信息对象`simpleAuthenticationInfo`

最后返回`simpleAuthenticationInfo`，完成验证。


这里我没有理清楚的就是有关权限的。
现在是根据用户名，查询数据库，返回的对象不为null，则是登录正确，根据返回对象给出的用户id，进行查询用户权限，返回用户使用权限对象。

其实就是一次次进行查询，才能获取用户的权限，用户全部的角色，**没有一次性全部查询到的情况，这个是对设计数据库的表格有非常高要求的**（有也不知道啊）。

当前情况就是你登录正确了，你想要权限列表/对象，好，根据你返回的对象id，再进行一次查询，对权限表进行查询，返回权限列表/对象，放入到`simpleAuthenticationInfo`，返回到页面再根据前端进行判断是否能进入某个网页。

拓展：
你想查看用户的角色，也是登录正确后，根据用户id，对用户角色表格进行查询，返回用户角色列表/对象，，放入到`simpleAuthenticationInfo`，返回到页面，根据前端的判断进行显示。



##授权

输入参考：PrincipalCollection principals

从`principals`获取主身份信息对象`ActiveUser`

根据对象`ActiveUser`，获取用户id

根据用户id，查询用户权限列表/对象，返回权限列表/对象`permissions`

`permissions`增加到`SimpleAuthorizationInfo`对象中

最后返回`SimpleAuthorizationInfo`对象，完成授权。


#总结：

验证，授权，这2个都是需要先获取正确的用户对象，再根据用户对象的信息，进行查询别的需求。
像权限，用户角色，都是需要用户id，才能查询到的。
返回的对象，增加到`SimpleAuthorizationInfo`对象，或者是`simpleAuthenticationInfo`对象

最后返回`SimpleAuthorizationInfo`对象，或者是`simpleAuthenticationInfo`对象，完成验证或者授权


#参考：

https://www.cnblogs.com/zhouguanglin/archive/2018/02/27/8477807.html

https://www.cnblogs.com/learnhow/p/5694876.html

