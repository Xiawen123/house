package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;

import java.util.List;

public interface DistrictService {
    //查看所有 分页
   PageInfo<District> getAllDistrict(int page, int pagesize);
   //添加业务：新增
    public int addDistrict(District district);
    //查询单条信息
    public District getDistrict(Integer id);
    //修改
    public  int  updateDistrict(District district);
    //删除  -----区域街道；
    public  int deleteDistrict(Integer id);
    //批量删除   参数数组 传集合
    int delMoreDistrict(List<Integer> ids);


    //查看所有
   List<District>getAllDistrict();
}
