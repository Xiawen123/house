package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServcieImpl implements HouseService {

  @Autowired
  private HouseMapper houseMapper;

    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house) ;
    }
  //查询用户的出租房
  @Override
  public PageInfo<House> getHouseByUser(PageBean pageBean, Integer userid) {
      //开启分页
    PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
    List<House> house = houseMapper.getHouseByUser(userid);
    return new PageInfo<House>(house);
  }

  @Override//查询house单条信息
  public House getHouse(String id) {
    return houseMapper.getHouseById(id);   //可以得到区域的 id
  }

  //修改出租房信息
  @Override
  public int updateHouse(House house) {
    return houseMapper.updateByPrimaryKeySelective(house);
  }

  //逻辑删除
  @Override
  public int deleteHouse(String id, Integer state) {
      //创建出住房实体
    House house = new House();
    house.setId(id);
    house.setIsdel(state);
    return houseMapper.updateByPrimaryKeySelective(house);
  }

  //查询审核状态
    @Override
    public PageInfo<House> getHouseIsPass(PageBean pageBean, Integer state) {
        //开启分页
       PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
       //查询所有，
        List<House> list = houseMapper.getHouseByIsPass(state);
        return new PageInfo<House>(list);
    }

    //改变审核的状态
  @Override
  public int PassHouse(String id, Integer state) {
    House house = new House();
    //设置id
    house.setId(id);
    //设置审核的状态
    house.setIspass(state);
    return houseMapper.updateByPrimaryKeySelective(house);
  }

  @Override//浏览页面 所有的 已经审核 未删除的
  public PageInfo<House> getBorswerHouse(HouseCondition condition) {
      //开启分页
    PageHelper.startPage(condition.getPage(),condition.getRows());
    //讲条件传递给dao 层
    List<House> list = houseMapper.getBorswerHouse(condition);
    return new PageInfo<House>(list);
  }

//查询单条信息 用于浏览页面查看详情
  @Override
  public House getOneHouse(String id) {
    House house = houseMapper.getOneHouse(id);
    return house;
  }
}
