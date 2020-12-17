package com.weapon.controller;

import com.weapon.entity.Account;
import com.weapon.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Security;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model){
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
            Account account = (Account) subject.getPrincipal();
            subject.getSession().setAttribute("account",account);
            return "index";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            model.addAttribute("msg","用户名错误！");
            return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误！");
            e.printStackTrace();
            return "login";
        }
    }

    @GetMapping("/unauth")
    @ResponseBody
    public String unauth(){
        return "未授權,無法訪問";
    }


    /**
     * 銷毀session就可以了
     * @return
     */
    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

}
