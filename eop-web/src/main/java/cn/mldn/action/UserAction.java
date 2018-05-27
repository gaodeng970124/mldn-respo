package cn.mldn.action;

import cn.mldn.service.IUserService;
import cn.mldn.util.web.CookieUtil;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;
import cn.mldn.util.web.servlet.ServletObjectUtil;

@Controller
@RequestMapping("/pages/back/*")
public class UserAction extends AbstractAction {

    @Autowired
    IUserService userService;

    /**
     * 修改密码页面
     */
    @RequestMapping("update_password_pre")
    public ModelAndView updatePasswordPre() {
        ModelAndView mav = new ModelAndView(super.getPage("password.update.page"));
        return mav;
    }

    /**
     * 进行密码修改业务
     */
    @RequestMapping("update_password")
    public ModelAndView updatePassword(String oldpwd, String newpwd) throws Exception {
        ModelAndView mav = new ModelAndView(super.getForwardPage());
        if (userService.exitPassword(oldpwd, newpwd, super.getEid())) {
            CookieUtil cookieUtil = new CookieUtil(ServletObjectUtil.getRequest(), ServletObjectUtil.getResponse());
            cookieUtil.cleanMid();
            ServletObjectUtil.getRequest().getSession().invalidate();
            super.setUrlAndMsg(mav, "login.action", "passwordUpdate.success");
            } else {
            super.setUrlAndMsg(mav, "login.action", "passwordUpdate.fail");
        }
        return mav;
    }
}
