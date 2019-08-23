package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.service.HouseService;
import com.team.house.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class HouseController {

    @Autowired
    private HouseService houseService;

    //查询未通过的审核状态
    @RequestMapping("/getNoPassHouse")
    @ResponseBody
    public Map<String,Object>getNoPassHouse(PageBean page){
        PageInfo<House> houses = houseService.getHouseIsPass(page, 0);//0 表示未审核
        //封装返回的结果集
        Map<String, Object> map = new HashMap<>();
        map.put("total",houses.getTotal());
        map.put("rows",houses.getList());
        return map;
    }

    //查询已审核的状态
    @RequestMapping("/getYesPassHouse")
    @ResponseBody
    public Map<String,Object>YesPassHouse(PageBean page){
        PageInfo<House> houses = houseService.getHouseIsPass(page, 1);//0 表示未审核
        //封装返回的结果集
        Map<String, Object> map = new HashMap<>();
        map.put("total",houses.getTotal());
        map.put("rows",houses.getList());
        return map;
    }

    //修改审核的状态 为 通过审核
    @RequestMapping("/goYesPassHouse")
    @ResponseBody
    public Map<String,Object>YesPassHouse(String id){
        int temp = houseService.PassHouse(id, 1);//1.表示 通过审核
        //封装返回的数据
        Map<String, Object>map = new HashMap<>();
        map.put("result",temp);
        return map;
    }
    //修改审核的状态 为 未通过审核
    @RequestMapping("/goNoPassHouse")
    @ResponseBody
    public Map<String,Object>NoPassHouse(String id){
        int temp = houseService.PassHouse(id, 0);//0 表示未通过审核
        //封装返回的数据
        Map<String, Object>map = new HashMap<>();
        map.put("result",temp);
        return map;
    }

}


