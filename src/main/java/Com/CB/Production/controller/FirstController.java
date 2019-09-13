package Com.CB.Production.controller;

import Com.CB.Production.domain.authority.SysPermission;
import Com.CB.Production.domain.customize.ActiveUser;
import Com.CB.Production.service.SysService;
import Com.CB.Production.util.CollectionsFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static Com.CB.Production.common.Constants.ACTIVE_USER;

@Controller
public class FirstController {

    @Autowired
    SysService sysService;


    //跳转登陆
    @RequestMapping(value = {"/", "/first", "/login"})
    public String firs(Model model) {
        return "login";
    }


    //首页
    /**
     * 这个首页很多东西需要填充，而且是根据角色，权限不同，完成相对的填充，具体复杂的实现是在业务层上
     * 1        从SecurityUtils中获取shiro的项目subject
     * 2        创建身份信息源ActiveUser，并且subject上的principal赋值给ActiveUser
     * （一样的类，变量名不一样，具体看RealM的方法中身份验证的部分）
     * 3        创建权限列表对象 permissionList
     * 4        根据身份信息源上的UserId，使用认证授权服务接口SysService，进行查询权限列表对象permissionList
     * 5        创建String类型的系统权限列表 sysPermissionList
     * 6        判断permissionList是否为null，
     * 然后遍历permissionList，向sysPermissionList 增加permissionList的属性：权限代码字符串percode
     * 7        Model增加attribute，key为：“activeUser” value为ActiveUser
     * 8        session设置attribute，key为“sysPermissionList”，value为sysPermissionList
     * 9        返回home
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        List<SysPermission> permissionList = null;

        //根据身份信息源上的UserId，使用认证授权服务接口SysService，进行查询权限列表对象permissionList
        permissionList = sysService.findPermissionListByUserId(activeUser.getUserid());

        //本来可以直接创建ArrayList列表的，但是体检使用单例模式？
        List<String> sysPermissionList = CollectionsFactory.newArrayList();
        if (permissionList != null) {
            for (int i = 0;i < permissionList.size();i++) {
                sysPermissionList.add(permissionList.get(i).getPercode());
            }
        }
        model.addAttribute(ACTIVE_USER, activeUser);
        session.setAttribute("sysPermissionList", sysPermissionList);
        return "home";
    }
}
