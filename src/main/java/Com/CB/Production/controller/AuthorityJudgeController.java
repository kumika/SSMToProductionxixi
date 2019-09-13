package Com.CB.Production.controller;

import Com.CB.Production.domain.customize.ActiveUser;
import Com.CB.Production.util.CollectionsFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static Com.CB.Production.common.Constants.NO_PERMISSION;

/**
 * 权限判断controller
 */
@RestController
public class AuthorityJudgeController {

	private static final Logger logger = LoggerFactory.getLogger(AuthorityJudgeController.class);

	@RequestMapping("*/*_judge")
	public Map<String,Object> authorityJudge(HttpServletRequest request) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		
		//根据uri,使用shiro判断相应权限
		//真的是卧槽了，完全是为了实现统一处理异常这个功能，从而使用uri进行判断权限
		//真实的情况是这样的吗
		String uri = request.getRequestURI();
		String[] names = uri.split("/");
		String featureName = names[2];
		String operateName = names[3].split("_")[0];
		Map<String,Object> map = CollectionsFactory.newHashMap();
		if(!activeUser.getUserStatus().equals("1")){
			if (logger.isDebugEnabled()) {
				logger.debug(NO_PERMISSION, "账户已被锁定！");
			}
			map.put("msg", "您的账户已被锁定，请切换账户登录！");
		}else if(!activeUser.getRoleStatus().equals("1")){
			if (logger.isDebugEnabled()) {
				logger.debug(NO_PERMISSION, "角色已被锁定!");
			}
			map.put("msg", "当前角色已被锁定，请切换账户登录！");
		}else{
			if (logger.isDebugEnabled()) {
				logger.debug(NO_PERMISSION, "没有权限!");
			}
			if(!subject.isPermitted(featureName+":"+operateName)){
				map.put("msg", "您没有权限，请切换用户登录！");
			}
		}
		return map;
	}
}
