package com.team.house.potal.controller;

import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户
 */
@Controller
@RequestMapping("/page")
public class UsersController {

    @Autowired
    public UsersService usersService;

    /**
     * 实现用户注册
     * @param users
     * @return
     */
    @RequestMapping("/reg")
    public String reg(Users users) {
        int temp = usersService.addUsers(users);
        if (temp > 0)
            return "login"; //登入页
        else
            return "regs"; //注册页
    }

    //判断用户是否已存在  异步返回数据
    @RequestMapping("/checkUsersName")
    @ResponseBody
    public Map<String,Object>checkUsersName(String usersname) {
        int temp=usersService.isUsersNameExists(usersname);
        Map<String, Object> map = new HashMap<>();
        map.put("result",temp);
        return map;
    }

    //登陆时判断用户名密码是否正确
    @RequestMapping("/login")
     public String  checkUserName(String username, String password, HttpSession session) {
        System.out.println("---------------------------------------"+username);
        Users user = usersService.login(username, password);
        if (user==null) {
            return "login";
        }else{
            //注意:只要登入，必需使用session保存登入人的信息或者cookie保存
            //session保存的信息在服务器与浏览共存
            session.setAttribute("userInfo",user);
            //设置session的存活时间
            session.setMaxInactiveInterval(1000);
            return "redirect:getHouse";
        }
    }

}
