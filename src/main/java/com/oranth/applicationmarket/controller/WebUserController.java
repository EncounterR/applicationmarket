package com.oranth.applicationmarket.controller;


import com.alibaba.fastjson.JSON;
import com.oranth.applicationmarket.entity.ActiveUser;
import com.oranth.applicationmarket.entity.WebUser;
import com.oranth.applicationmarket.service.IWebUserService;
import io.swagger.annotations.Api;
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
 *  前端控制器
 * </p>
 *
 * @author linsiteng
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/web-user")
@Api(value = "用户类",description = "用户接口类")
public class WebUserController {

    private static Logger logger = LoggerFactory.getLogger(WebUserController.class);
    @Autowired
    IWebUserService webUserService;



    /**
     * 新增管理员信息
     * @param user 账号
     * @param pwd 密码
     * @return
     */
    @ApiIgnore
    @PostMapping("/save")
    public String save( @ApiParam(name = "user",value = "账号")@RequestParam(value="user") String user,
                        @ApiParam(name = "pwd",value = "密码")@RequestParam(value="pwd") String pwd){
        WebUser webUser = new WebUser();
        webUser.setUserName(user);
        webUser.setUserPassword(pwd);
        webUser.setRoleId(1);
        boolean b = webUserService.addWebUser(webUser);
        return ""+b;
    }

    @ApiIgnore
    @PostMapping("/update")
    public String update(
            @ApiParam(name = "userId",value = "账号id")@RequestParam(value="userId") Integer userId,
            @ApiParam(name = "userName",value = "账号")@RequestParam(value="userName") String userName,
            @ApiParam(name = "userPassword",value = "密码")@RequestParam(value="userPassword") String userPassword,
            @ApiParam(name = "permissionsId",value = "角色")@RequestParam(value="permissionsId") Integer permissionsId){
        WebUser webUser = new WebUser();
        webUser.setUserId(userId);
        webUser.setUserName(userName);
        webUser.setUserPassword(userPassword);
        webUser.setRoleId(permissionsId);
        boolean b = webUserService.editWebUser(webUser);
        return ""+b;
    }

    @ApiIgnore
    @PostMapping("/del/{id}")
    public String del( @ApiParam(name = "id",value = "路径上的id")@PathVariable(value="id") Integer id){
        boolean b = webUserService.delWebUser(id);
        return b+"";
    }

    @PostMapping("/login")
    public String login(
            @ApiParam(name = "user",value = "账号")@RequestParam(value="user") String user,
            @ApiParam(name = "pwd",value = "密码")@RequestParam(value="pwd") String pwd, HttpSession session
    ){
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
            System.out.println("-----------"+session.getAttribute("activeUser"));
            return "login";
        } catch (UnknownAccountException ex) {
            String message ="用户名密码错误";
            return message;
        } catch (IncorrectCredentialsException ex) {
            String message ="用户名密码错误";
            return message;
        }
    }

    @RequestMapping("admin")
    public ModelAndView toadmin(){
        return new ModelAndView("admin");
    }

    /**
     * 获取全部管理员信息
     * @return
     */
    @ApiIgnore
    @PostMapping("/query")
    public String query1(HttpSession session){
        List<WebUser> list = webUserService.allWebUser();
        return JSON.toJSONString(list);
    }

    /**
     * 根据id获取管理员信息
     * @param id
     * @return
     */
    @ApiIgnore
    @GetMapping("/query/{id}")
    public ModelAndView queryById(@ApiParam(name = "id",value = "路径上的id")@PathVariable(value="id") Integer id){
        WebUser webUser=webUserService.getWebUserById(id);
        ModelAndView mv=new ModelAndView("webUserEdit");
        mv.addObject("webUser",webUser);
        return mv;
    }

    /**
     * 用户退出，清空session中的值
     * @return
     */
    @PostMapping("/loginout")
    public String loginOut(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "/";
    }
}
