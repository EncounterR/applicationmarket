package com.oranth.applicationmarket.controller;


import com.alibaba.fastjson.JSON;
import com.oranth.applicationmarket.entity.ActiveUser;
import com.oranth.applicationmarket.entity.WebUser;
import com.oranth.applicationmarket.service.IWebUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author linsiteng
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/web-user")
@Api(value = "用户类", description = "用户接口类")
public class WebUserController {

    private static Logger logger = LoggerFactory.getLogger(WebUserController.class);
    @Autowired
    IWebUserService webUserService;


    /**
     * 新增管理员信息
     *
     * @param user 账号
     * @param pwd  密码
     * @return
     */
    @ApiOperation("新增用户信息")
    @PostMapping("/save")
    public String save(@ApiParam(name = "user", value = "账号") @RequestParam(value = "user") String user,
                       @ApiParam(name = "pwd", value = "密码") @RequestParam(value = "pwd") String pwd) {
        WebUser webUser = new WebUser();
        webUser.setUserName(user);
        webUser.setUserPassword(pwd);
        webUser.setRoleId(1);
        boolean b = webUserService.addWebUser(webUser);
        return "" + b;
    }

    @ApiOperation("更新用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@ApiParam(name = "oldPassword", value = "旧密码") @RequestParam(value = "oldPassword") String oldPassword, @ApiParam(name = "password", value = "新密码") @RequestParam(value = "password") String password, HttpSession session) {
        boolean flat = false;
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        WebUser webUser = webUserService.getWebUserById(activeUser.getUserid());
        if (webUser.getUserPassword().equals(oldPassword)) {
            webUser.setUserPassword(password);
            boolean b = webUserService.editWebUser(webUser);
            if (b) {
                flat = true;
            }
        }
        return "" + flat;
    }

    @ApiOperation(value = "删除用户信息", notes = "根据url的id来指定删除对象")
    @DeleteMapping("/{id}")
    public String del(@ApiParam(name = "id", value = "用户id") @PathVariable(value = "id") Integer id) {
        boolean b = webUserService.delWebUser(id);
        return b + "";
    }

    @ApiOperation(value = "用户登陆", notes = "用户执行登陆")
    @PostMapping("/login")
    public String login(
            @ApiParam(name = "user", value = "账号") @RequestParam(value = "user") String user,
            @ApiParam(name = "pwd", value = "密码") @RequestParam(value = "pwd") String pwd, HttpSession session
    ) {
        System.out.println("------------------执行登陆操作");
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(user, pwd);
        // 执行认证登陆
        try {
            subject.login(token);
            System.out.println("认证成功");
            // 在认证成功后将activeUser存入session
            subject = SecurityUtils.getSubject();
            // 身份
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            session.setAttribute("activeUser", activeUser);
            return "login";
        } catch (UnknownAccountException ex) {
            String message = "用户名密码错误";
            return message;
        } catch (IncorrectCredentialsException ex) {
            String message = "用户名密码错误";
            return message;
        }
    }


    /*@RequestMapping("admin")
    public ModelAndView toadmin() {
        return new ModelAndView("admin");
    }*/

    /**
     * 获取全部管理员信息
     *
     * @return
     */
    @ApiOperation("获取全部用户信息")
    @GetMapping("/query")
    public String query1() {
        List<WebUser> list = webUserService.allWebUser();
        return JSON.toJSONString(list);
    }

    /**
     * 根据id获取管理员信息
     *
     * @param id
     * @return
     */
    @ApiIgnore
    @ApiOperation("根据id获取用户信息")
    @GetMapping("/query/{id}")
    public ModelAndView queryById(@ApiParam(name = "id", value = "用户id") @PathVariable(value = "id") Integer id) {
        WebUser webUser = webUserService.getWebUserById(id);
        ModelAndView mv = new ModelAndView("webUserEdit");
        mv.addObject("webUser", webUser);
        return mv;
    }

    /**
     * 根据用户名查询信息
     *
     * @param username
     * @return
     */
    @ApiOperation(value = "查询该用户信息", notes = "根据url上的username查询用户信息")
    @GetMapping("/query2/{username}")
    public String qyeryByUsername(@ApiParam(name = "username", value = "用户名") @PathVariable(value = "username") String username) {
        WebUser webUser = webUserService.getWebUserByUsername(username);
        return JSON.toJSONString(webUser);
    }

    /**
     * 用户退出，清空session中的值
     *
     * @return
     */
    @ApiOperation(value = "用户退出", notes = "当前用户退出，并清空session")
    @PostMapping("/loginout")
    public String loginOut() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "/";
    }


}
