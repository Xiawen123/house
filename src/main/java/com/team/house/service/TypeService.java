package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.utils.PageBean;

import java.util.List;

public interface TypeService {
    //查看所有 分页
    PageInfo<Type> getAllType(PageBean pageBean);
    //添加业务：新增
    public int addType(Type Type);
    //查询单条信息
    public Type getType(Integer id);
    //修改
    public  int  updateType(Type Type);
    //删除  -----区域街道；
    public  int deleteType(Integer id);
    //批量删除   参数数组 传集合
    int delMoreType(List<Integer> ids);

    //查看所有
    List<Type> getAllType();


}
