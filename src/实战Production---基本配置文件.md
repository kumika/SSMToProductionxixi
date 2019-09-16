# 实战Production---基本配置文件


连接数据库
-----

jdbc.properties

```
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306//production_ssm?useUnicode=true&characterEncoding=utf8
jdbc.username=root
jdbc.password=123
```


log4j日志
-------
```
# Global logging configuration\uff0c\u5efa\u8bae\u5f00\u53d1\u73af\u5883\u4e2d\u8981\u7528debug
log4j.rootLogger=DEBUG, stdout

# Console output...
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```



#Spring

**这就分成Dao，service，安全控制shiro**
配置
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-4.0.xsd
		http://www.springframework.org/schema/aop
		http://www.springframework.org/schema/aop/spring-aop-4.0.xsd
		http://www.springframework.org/schema/tx
		http://www.springframework.org/schema/tx/spring-tx-4.0.xsd">
```

##Spring--Dao
```
    <!-- 指明数据库配置文件存放位置 -->
    <context:property-placeholder location="classpath:jdbc.properties"/>

    <!-- druid  -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
        <!-- 基本属性 url、user、password -->
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>

        <!-- 配置初始化大小、最小、最大 -->
        <property name="initialSize" value="3" />
        <property name="minIdle" value="1" />
        <property name="maxActive" value="20" />

        <!-- 配置获取连接等待超时的时间 -->
        <property name="maxWait" value="60000" />

        <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
        <property name="timeBetweenEvictionRunsMillis" value="60000" />

        <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
        <property name="minEvictableIdleTimeMillis" value="300000" />

        <property name="validationQuery" value="SELECT 'x'" />
        <property name="testWhileIdle" value="true" />
        <property name="testOnBorrow" value="false" />
        <property name="testOnReturn" value="false" />

        <!-- mysql 不支持 poolPreparedStatements-->
        <!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
        <!--<property name="poolPreparedStatements" value="true" />-->
        <!--<property name="maxPoolPreparedStatementPerConnectionSize" value="20" />-->

        <!-- 配置监控统计拦截的filters -->
        <property name="filters" value="stat"/>
    </bean>


    <!-- sqlSessionFactory spring和myBatis整合 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>

        <!-- mybatis配置文件 -->
        <property name="configLocation" value="classpath:mybaits/sqlMapConfig.xml"/>
    </bean>


    <!--
    MapperScannerConfigurer：mapper的扫描器，将包下边的mapper接口自动创建代理对象，
    自动创建到spring容器中，bean的id是mapper的类名（首字母小写）

        扫描Dao接口类
     -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 配置扫描包的路径
    如果要扫描多个包，中间使用半角逗号分隔
    要求mapper.xml和mapper.java同名且在同一个目录
     -->
        <property name="basePackage" value="Com.CB.Production.mapper"/>
        <!-- 使用sqlSessionFactoryBeanName -->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    </bean>

    <!-- 事务管理器 -->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>


    <!-- 通知 -->
    <tx:advice id="txAdvice" transaction-manager="txManager">
        <tx:attributes>
            <tx:method name="find*" read-only="true" />
            <tx:method name="get*" read-only="true" />
            <tx:method name="view*" read-only="true" />
            <tx:method name="insert*" propagation="REQUIRED" />
            <tx:method name="update*" propagation="REQUIRED" />
            <tx:method name="delete*" propagation="REQUIRED" />
            <tx:method name="*" propagation="REQUIRED" /><!-- 防止漏网之鱼 -->
        </tx:attributes>
    </tx:advice>

    <!-- 切面 -->
    <aop:config>
        <!-- 切点 管理所有Service的方法 -->
        <aop:pointcut id="transactionPointCut" expression="execution(* Com.CB.Production.service.*.*(..))"/>
        <!-- 增强，进行事务控制 Advisor -->
        <aop:advisor advice-ref="txAdvice" pointcut-ref="transactionPointCut"/>
    </aop:config>
```



##Spring--service

```
    <!-- service包扫描 -->
    <context:component-scan base-package="Com.CB.Production.service"/>
```



##安全控制Shiro

[![shiro图解.jpg](https://i.loli.net/2018/12/27/5c245b63382ce.jpg)](https://i.loli.net/2018/12/27/5c245b63382ce.jpg)


一共使用了9个bean
**Bean：**

    1   shiro的web过滤器
    2   securityManager安全管理器
    3   realm
    4   凭证匹配器
    5   缓存管理器
    6   会话管理器
    7   sessionIdCookie
    8   rememberMeManager管理器
    9   rememberMeCookie

具体配置代码：
```
    <!-- web.xml中shiro的filter对应的bean -->
    <!-- Shiro 的Web过滤器 -->
    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <property name="securityManager" ref="securityManager"/>

        <!-- loginUrl认证提交地址，如果没有认证将会请求此地址进行认证，请求此地址将由formAuthenticationFilter进行表单认证 -->
        <property name="loginUrl" value="/"/>

        <!-- 认证成功统一跳转到first.action，建议不配置，shiro认证成功自动到上一个请求路径 -->
        <!-- <property name="successUrl" value="/first.action"/> -->
        <!-- 通过unauthorizedUrl指定没有权限操作时跳转页面-->
        <property name="unauthorizedUrl" value="/refuse.jsp"/>

        <!-- 过虑器链定义，从上向下顺序执行，一般将/**放在最下边 -->
        <property name="filterChainDefinitions">
            <value>
                <!-- 对静态资源设置匿名访问 -->
                /images/** = anon
                /js/** = anon
                /styles/** = anon
                <!-- 验证码，可匿名访问 -->
                /validatecode.jsp = anon
                /login.jsp = anon
                /ajaxLogin = anon
                <!-- 请求 logout.action地址，shiro去清除session-->
                /logout = logout
                <!--商品查询需要商品查询权限 ，取消url拦截配置，使用注解授权方式 -->
                <!-- /items/queryItems.action = perms[item:query]
                /items/editItems.action = perms[item:edit] -->
                <!-- 配置记住我或认证通过可以访问的地址 -->
                /home = user
                /index.jsp  = user
                /first.action = user
                /welcome.jsp = user
                /order/edit_judge = authc
                <!-- /** = authc -->
                <!-- /** = authc 所有url都必须认证通过才可以访问-->
                <!-- /** = anon所有url都可以匿名访问 -->
            </value>
        </property>
    </bean>


    <!-- securityManager安全管理器 -->
    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="realm" ref="customRealm"/>
        <!-- 注入缓存管理器 -->
        <!--<property name="cacheManager" ref="cacheManager"/>-->
        <!-- 注入session管理器 -->
        <property name="sessionManager" ref="sessionManager"/>
        <!-- 记住我 -->
        <property name="rememberMeManager" ref="rememberMeManager"/>
    </bean>
    
    
    <!-- realm -->
    <bean id="customRealm" class="Com.CB.Production.shiro.CustomRealm">
        <!-- 将凭证匹配器设置到realm中，realm按照凭证匹配器的要求进行散列 -->
        <property name="credentialsMatcher" ref="credentialsMatcher"/>
    </bean>


    <!-- 凭证匹配器 -->
    <bean id="credentialsMatcher"
          class="org.apache.shiro.authc.credential.SimpleCredentialsMatcher">
    </bean>

    <!-- 缓存管理器 -->
    <bean id="cacheManager" class="org.apache.shiro.cache.ehcache.EhCacheManager">
        <property name="cacheManagerConfigFile" value="classpath:shiro-ehcache.xml"/>
    </bean>

    
    <!-- 会话管理器 -->
    <bean id="sessionManager" class="org.apache.shiro.web.session.mgt.DefaultWebSessionManager">
        <!-- session的失效时长，单位毫秒 ，这里设置为10分钟-->
        <property name="globalSessionTimeout" value="600000"/>
        <!-- 删除失效的session -->
        <property name="deleteInvalidSessions" value="true"/>
        <!-- 指定本系统sessionId,
        默认为: JSESSIONID 问题: 与Servlet容器名冲突,
         如Jetty, Tomcat等默认JSESSIONID,
         当跳出shiro Servlet时
         如Error-page容器会为JSESSIONID重新分配值导致登录会话丢失! -->
        <property name="sessionIdCookie" ref="sessionIdCookie"/>
    </bean>


    <bean id="sessionIdCookie" class="org.apache.shiro.web.servlet.SimpleCookie">
        <!--ycyintang.session.id是哪里的值？-->
        <constructor-arg name="name" value="ycyintang.session.id"/>
    </bean>

    <!-- rememberMeManager管理器，写cookie，取出cookie生成用户信息 -->
    <bean id="rememberMeManager" class="org.apache.shiro.web.mgt.CookieRememberMeManager">
        <property name="cookie" ref="rememberMeCookie"/>
    </bean>

    <!-- 记住我cookie -->
    <bean id="rememberMeCookie" class="org.apache.shiro.web.servlet.SimpleCookie">
        <!-- rememberMe是cookie的名字 -->
        <constructor-arg value="rememberMe"/>
        <!-- 记住我cookie生效时间30天 -->
        <property name="maxAge" value="2592000"/>
    </bean>
    
    
    <!-- 自定义form认证过虑器 -->
    <!-- 基于Form表单的身份验证过滤器，不配置将也会注册此过虑器，表单中的用户账号、密码及loginurl将采用默认值，建议配置 -->
    <!--<bean id="formAuthenticationFilter" class="org.hqu.production_ms.shiro.CustomFormAuthenticationFilter">
        表单中账号的input名称
        <property name="usernameParam" value="username" />
        表单中密码的input名称
        <property name="passwordParam" value="password" />
        记住我input的名称
        <property name="rememberMeParam" value="rememberMe"/>
    </bean> -->
```





#SpringMVC

文件头：
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
	    http://www.springframework.org/schema/mvc
	    http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd
	    http://www.springframework.org/schema/context
	    http://www.springframework.org/schema/context/spring-context.xsd
	    http://www.springframework.org/schema/aop
		http://www.springframework.org/schema/aop/spring-aop-4.0.xsd">
```

**具体代码：**
```
    <context:component-scan base-package="Com.CB.Production.controller"/>

    <!-- 注解驱动  启动注解识别 -->
    <mvc:annotation-driven />

    <mvc:default-servlet-handler/>


    <!-- 开启aop，对类代理 -->
    <aop:config proxy-target-class="true"/>

    <!-- 配置内部资源视图解析器，要求将jstl的包加到classpath -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    <!-- 资源映射 -->
    <mvc:resources location="/WEB-INF/css/" mapping="/css/**"/>
    <mvc:resources location="/WEB-INF/js/" mapping="/js/**"/>
    <mvc:resources location="/WEB-INF/image/" mapping="/image/**"/>
    
    <!-- 000000000000000000000分割线00000000000000000000000000 -->
```




#Mybatis配置文件

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
        <typeAliases>
            <!--
                通过package, 可以直接指定package的名字， mybatis会自动扫描你指定包下面的javabean,
                并且默认设置一个别名，默认的名字为： javabean 的首字母小写的非限定类名来作为它的别名。
                也可在javabean 加上注解@Alias 来自定义别名， 例如： @Alias(user)
                -->
            <package name="Com.CB.Production.domain"/>
            <package name="Com.CB.Production.domain.authority"/>
            <package name="Com.CB.Production.domain.vo"/>
        </typeAliases>


    <!-- 配置分页插件 -->
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageHelper">
            <!-- 设置数据库类型 Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL六种数据库-->
            <property name="dialect" value="mysql"/>
        </plugin>
    </plugins>
</configuration>
```




#web.xml


比较重要的：

    1   spring容器监听器
    2   前端控制器---就是springMVC嘛
    3   安全权限shiroFilter

具体代码：
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.4"
         xmlns="http://java.sun.com/xml/ns/j2ee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">

  <!-- Spring容器监听器 -->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring/spring--*.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>


  <!-- 前端控制器 -->
  <servlet>
    <servlet-name>springMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 加载springmvc配置 -->
    <init-param>
      <!-- 配置文件的地址 如果不配置contextConfigLocation， 默认查找的配置文件名称classpath下的：servlet名称+"-serlvet.xml"
        即：springmvc-serlvet.xml -->
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring/springMVC--servlet.xml</param-value>
    </init-param>
  </servlet>

  <servlet-mapping>
    <servlet-name>springMVC</servlet-name>
    <!-- 可以配置/ ，此工程 所有请求全部由springmvc解析，此种方式可以实现 RESTful方式，需要特殊处理对静态文件的解析不能由springmvc解析
        也可以配置*.do或*.action，所有请求的url扩展名为.do或.action由springmvc解析，此种方法常用 不可以/*，
        如果配置/*，返回jsp也由springmvc解析，这是不对的。 -->
    <url-pattern>/</url-pattern>
  </servlet-mapping>

  <!-- shiro的filter -->
  <!-- shiro过虑器，DelegatingFilterProxy通过代理模式将spring容器中的bean和filter关联起来 -->
  <filter>
    <filter-name>shiroFilter</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    <!-- 设置true由servlet容器控制filter的生命周期 -->
    <init-param>
      <param-name>targetFilterLifecycle</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>

  <filter-mapping>
    <filter-name>shiroFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <!-- post乱码处理 -->
  <filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <!-- 错误页面 -->
  <error-page>
    <exception-type>java.lang.Throwable</exception-type>
    <location>/500.jsp</location>
  </error-page>
  <error-page>
    <error-code>500</error-code>
    <location>/500.jsp</location>
  </error-page>
  <error-page>
    <error-code>404</error-code>
    <location>/404.jsp</location>
  </error-page>

</web-app>
```


