package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import com.team.house.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")//表示后所有的控制器请求都在/admin目录下
public class StreetController {
        @Autowired
        private StreetService streetService;

    @RequestMapping("/getDistrictAllStreet")      //查询区域下的街道
    @ResponseBody
    public HashMap<String,Object>getDistrictAllStreet(Integer  did,PageBean pageBean){
        //1.查询所有
        PageInfo<Street> pageInfo =streetService.getDistrictAllStreet(did,pageBean);
        //为了满足datagrid控件实现分，必需返回total键、rows键
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

        @RequestMapping("/getStreet")      //查询所有
        @ResponseBody
        public HashMap<String,Object> selectDis(PageBean pageBean){
            //1.查询所有
            PageInfo<Street> pageInfo =streetService.getAllStreet(pageBean);
            //为了满足datagrid控件实现分，必需返回total键、rows键
            HashMap<String, Object> map = new HashMap<>();
            map.put("total",pageInfo.getTotal());
            map.put("rows",pageInfo.getList());
            return map;
        }

        @RequestMapping("/addStreet")
        @ResponseBody             //添加业务：新增
        public String addStreet(Street street){
            int temp=-1;
            try {
                //调用业务实现添加；
                temp = streetService.addStreet(street);
            }catch (Exception e){
                e.printStackTrace();//都会选择性记录日志
            }
            //返回数据;
            return "{\"result\":"+temp+"}";
        }
        @RequestMapping("/getOneStreet")
        @ResponseBody                 //查询单条信息
        public Street getStreet(Integer id){
            return streetService.getStreet(id);
        }
        @RequestMapping("/updateStreet")
        @ResponseBody                //修改
        public  String  updateStreet(Street street){
            int temp=-1;
            try{
                //调用业务实现修改
                temp =streetService.updateStreet(street);
            }catch (Exception e){
                e.printStackTrace();
            }
            return "{\"result\":"+temp+"}";
        }
        @RequestMapping("/deleteStreet")
        @ResponseBody                 // 删除
        public String delentStreet(Integer id){
            int temp=-1;
            try{
                //调用业务实现修改
                temp=streetService.deleteStreet(id);
            }catch (Exception e){
                e.printStackTrace();
            }
            return "{\"result\":"+temp+"}";
        }
        @RequestMapping("/delMoreStreet")
        @ResponseBody                 //批量删除
        public String delMoreStreet(String id) {//接收编号，名称
            //id=,1,2,3;
            //按 ， 分割字符串
            String[] ary = id.split(",");
            //转为List
            ArrayList<Integer> list = new ArrayList<>();
            for (int i=0;i<ary.length;i++){
                list.add(Integer.parseInt(ary[i]));
            }
            //调用业务
            int temp=streetService.delMoreStreet(list);
            return "{\"result\":"+temp+"}";
        }

    //判断用户是否已存在  异步返回数据
    @RequestMapping("/checkStreetName")
    @ResponseBody
    public Map<String,Object> checkUsersName(String name) {
        int temp = streetService.isStreetNameExists(name);
        Map<String, Object> map = new HashMap<>();
        map.put("result",temp);
        return map;
    }

    }
