package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("/getDistrict")
    @ResponseBody
    public HashMap<String,Object>selectDis(int page, int rows){
        //1.查询所有
        PageInfo<District> pageInfo =districtService.getAllDistrict(page, rows);
        //为了满足datagrid控件实现分，必需返回total键、rows键
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("/addDistrict")
   @ResponseBody             //添加业务：新增
    public String addDistrict(District district){
        int temp=-1;
        try {
            //调用业务实现添加；
            temp = districtService.addDistrict(district);
        }catch (Exception e){
            e.printStackTrace();//都会选择性记录日志
        }
        //返回数据;
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/getOneDistrict")
    @ResponseBody                 //查询单条信息
    public District getDistrict(Integer id){
        return districtService.getDistrict(id);
    }
    @RequestMapping("/updateDistrict")
    @ResponseBody                //修改
    public  String  updateDistrict(District district){
        int temp=-1;
        try{
            //调用业务实现修改
            temp =districtService.updateDistrict(district);
        }catch (Exception e){
            e.printStackTrace();
        }
       return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/deleteDistrict")
    @ResponseBody                 // 删除区域的同时，删除街道
    public String delentDistrict(Integer id){
       int temp=-1;
        try{
            //调用业务实现修改
         temp=districtService.deleteDistrict(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/delMoreDistrict")
    @ResponseBody                 //批量删除
    public String delMoreDistrict(String id) {//接收编号，名称
          //id=,1,2,3;
        //按 ， 分割字符串
        String[] ary = id.split(",");
        //转为List
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<ary.length;i++){
                list.add(Integer.parseInt(ary[i]));
        }
        //调用业务
       int temp=districtService.delMoreDistrict(list);
        return "{\"result\":"+temp+"}";
    }

}
