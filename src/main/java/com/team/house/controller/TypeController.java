package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import com.team.house.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/admin") //表示后所有的控制器请求都在/admin目录下
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/getType")      //查询所有
    @ResponseBody
    public HashMap<String,Object> selectDis(PageBean pageBean){
        //1.查询所有
        PageInfo<Type> pageInfo =typeService.getAllType(pageBean);
        //为了满足datagrid控件实现分，必需返回total键、rows键
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("/addType")
    @ResponseBody             //添加业务：新增
    public String addType(Type type){
        int temp=-1;
        try {
            //调用业务实现添加；
            temp = typeService.addType(type);
        }catch (Exception e){
            e.printStackTrace();//都会选择性记录日志
        }
        //返回数据;
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/getOneType")
    @ResponseBody                 //查询单条信息
    public Type getType(Integer id){
        return typeService.getType(id);
    }
    @RequestMapping("/updateType")
    @ResponseBody                //修改
    public  String  updateType(Type Type){
        int temp=-1;
        try{
            //调用业务实现修改
            temp =typeService.updateType(Type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/deleteType")
    @ResponseBody                 // 删除
    public String delentType(Integer id){
        int temp=-1;
        try{
            //调用业务实现修改
            temp=typeService.deleteType(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/delMoreType")
    @ResponseBody                 //批量删除
    public String delMoreType(String id) {//接收编号，名称
        //id=,1,2,3;
        //按 ， 分割字符串
        String[] ary = id.split(",");
        //转为List
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<ary.length;i++){
            list.add(Integer.parseInt(ary[i]));
        }
        //调用业务
        int temp=typeService.delMoreType(list);
        return "{\"result\":"+temp+"}";
    }

}
