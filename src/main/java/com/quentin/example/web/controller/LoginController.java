package com.quentin.example.web.controller;

import com.quentin.example.common.BussinessCode;
import com.quentin.example.domain.BussinessMsg;
import com.quentin.example.utils.BussinessMsgUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 10:42 2018/2/8
 * @Version 1.0
 */
@Controller
@Slf4j
public class LoginController extends BasicContrller {

    @RequestMapping("/")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @RequestMapping("/main")
    ModelAndView mainIndex() {
        ModelAndView modelAndView = new ModelAndView("main_index");
        return modelAndView;
    }

    @PostMapping(value = "/login")
    public BussinessMsg login(String username, String password, HttpServletRequest request) {
        long start = System.currentTimeMillis();
        password = new SimpleHash("md5", password, ByteSource.Util.bytes(username + "1qazxsw2"), 2).toHex();
        //1.用户名不能为空
        if (StringUtils.isEmpty(username)) {
            log.info("登陆验证失败,原因:用户名不能为空");
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_NAME_NULL);
        }
        //2.密码不能为空
        if (StringUtils.isEmpty(password)) {
            log.info("登陆验证失败,原因:密码不能为空");
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_PASS_NULL);
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            if (subject.isAuthenticated()) {
                request.getSession().setAttribute("LOGIN_NAME", getCurrentUser());
                return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_SUCCESS);
            }
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_FAIL);
        } catch (IncorrectCredentialsException ice) {
            log.info("登陆验证失败,原因:用户名或密码不匹配");
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_FAIL);
        } catch (AccountException e) {
            log.info("登陆验证失败,原因:用户名或密码不匹配");
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_FAIL);
        } catch (Exception e) {
            log.info("登陆验证失败,原因:系统登陆异常", e);
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_ERROR);
        } finally {
            log.info("登陆验证处理结束,用时" + (System.currentTimeMillis() - start) + "毫秒");
        }
    }
}
