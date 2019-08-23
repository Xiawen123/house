package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import com.team.house.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class UsersControllerHou {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/getUsers")
    @ResponseBody
    public HashMap<String,Object> selectDis(PageBean pageBean){
        System.out.println("pageBean***********************"+pageBean);
        //1.查询所有
        PageInfo<Users> pageInfo =usersService.getAllUsers(pageBean);
        //为了满足datagrid控件实现分，必需返回total键、rows键
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }


    @RequestMapping("/addUsers")
    @ResponseBody             //添加业务：新增
    public String addUsers(Users users){
        int temp=-1;
        try {
            //调用业务实现添加；
            temp = usersService.addUsers(users);
        }catch (Exception e){
            e.printStackTrace();//都会选择性记录日志
        }
        //返回数据;
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/getOneUsers")
    @ResponseBody                 //查询单条信息
    public Users getUsers(Integer id){
        return usersService.getUsers(id);
    }
    @RequestMapping("/updateUsers")
    @ResponseBody                //修改
    public  String  updateUsers(Users users){
        int temp=-1;
        try{
            //调用业务实现修改
            temp =usersService.updateUsers(users);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/deleteUsers")
    @ResponseBody                 // 删除
    public String delentUsers(Integer id){
        int temp=-1;
        try{
            //调用业务实现修改
            temp=usersService.deleteUsers(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/delMoreUsers")
    @ResponseBody                 //批量删除
    public String delMoreUsers(String id) {//接收编号，名称
        //id=,1,2,3;
        //按 ， 分割字符串
        String[] ary = id.split(",");
        //转为List
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<ary.length;i++){
            list.add(Integer.parseInt(ary[i]));
        }
        //调用业务
        int temp=usersService.delMoreUsers(list);
        return "{\"result\":"+temp+"}";
    }
}
