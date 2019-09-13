package Com.CB.Production.controller;

import Com.CB.Production.util.CollectionsFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.Map;

import static Com.CB.Production.common.Constants.VALIDATE_CODE;

@Controller
public class LoginController {

    /**
     *      登陆2个验证，验证码的判断，用户名密码的验证
     *   1      获取用户名，密码，验证码，session 这4个参数
     *   2      从传过来的验证码和直接从session拿的验证码进行对比，判断验证码是否正确，不正确直接返回页面
     *   3      从security Utils拿出subject（类似idea先创建项目project）
     *   4      判断subject是否已经验证
     *   5      把用户名，密码作为参数，创建令牌UsernamePasswordToken，得到令牌对象token
     *   6      把令牌token作为参数，使用subject的方法login，跳转到RealM中的doGetAuthenticationInfo()方法，完成用户名，密码的验证
     *   6.1    怎么跳转到RealM上？（这看配置文件怎么配置了）
     * @param username
     * @param password
     * @param randomcode
     * @param session
     * @return
     */

    @RequestMapping("/ajaxLogin")
    @ResponseBody
    public Map<String, Object> ajaxLogin(@RequestParam String username,
                                         @RequestParam String password,
                                         @RequestParam(required = false) String randomcode,
                                         HttpSession session) {
        //登陆出现错误，在页面上需要显示错误问题
        Map<String, Object> map = CollectionsFactory.newHashMap();

        if (randomcode != null && !randomcode.equals("")) {
            //取出session的验证码（正确的验证码）
            String validateCode = (String) session.getAttribute(VALIDATE_CODE);
            //页面中输入的验证和session中的验证进行对比
            if (validateCode != null && !randomcode.equals(validateCode)) {
                //如果校验失败，将验证码错误失败信息放入map中
                map.put("msg", "randomcode_error");
                //直接返回，不再校验账号和密码
                return map;
            }
        }
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
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
}
