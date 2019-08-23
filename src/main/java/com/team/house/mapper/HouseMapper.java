package com.team.house.mapper;

import com.team.house.entity.House;
import com.team.house.entity.HouseExample;
import com.team.house.utils.HouseCondition;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询用户发布的出租房
    public List<House> getHouseByUser(Integer userid);

    //查询 出租房的信息
     House getHouseById(String id);  //返回House 实体

    /**查询所有的出租房的     未审核的信息
     *  ispass=1表示已经审核，
     *  ipass=0表是未审核
     * @param ispass  是否审核的状态
     * @return
     */
    public List<House> getHouseByIsPass(Integer ispass);

    //查询用户浏览的出租房信息
     List<House>getBorswerHouse(HouseCondition condition);

    /**
     * 查询单条出租房 详情页面 数据
     * @param id  当前数据的 id
     * @return  house
     */
     House getOneHouse(String id);

}