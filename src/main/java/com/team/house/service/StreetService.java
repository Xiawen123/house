package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.utils.PageBean;

import java.util.List;

public interface StreetService {
    //查看区域下的街道 分页
    PageInfo<Street> getDistrictAllStreet( Integer did,PageBean pageBean);
    //查看所有
    PageInfo<Street> getAllStreet(PageBean pageBean);
    //添加业务：新增
    public int addStreet(Street Street);
    //查询单条信息
    public Street getStreet(Integer id);
    //修改
    public  int  updateStreet(Street Street);
    //删除  -----区域街道；
    public  int deleteStreet(Integer id);
    //批量删除   参数数组 传集合
    int delMoreStreet(List<Integer> ids);
   // 判断区域下的街道添加 时是否重复！
    int isStreetNameExists(String name);
    //查看区域下的街道
    List<Street> getDistrictAllStreet( Integer did);

}
