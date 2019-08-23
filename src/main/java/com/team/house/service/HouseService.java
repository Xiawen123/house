package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.PageBean;

public interface HouseService {
    //发布出租房子
    public int addHouse(House house);


    /**
     *  查询用户的出租房
     * @param pageBean 分页
     * @param userid 用户id
     * @return 出租房信息
     */
    public PageInfo<House> getHouseByUser(PageBean pageBean, Integer userid);

    /**
     * 查询单条信息
     * @param id  参数id
     * @return  返回结果 House
     */
    public House getHouse(String id);

    /**
     * 修改出租房的信息  表单数据提交到控制器  调业务service 操作数据库
     * @param house  参数 实体
     * @return  修改返回 int
     */
     int updateHouse(House house);

    /**
     *逻辑删除
     * @param id  用户删除
     * @param state  用于恢复已经删除的状态
     * @return 影响的函数
     */
     int deleteHouse(String id, Integer state);

    /**
     * 通过审核状态 查询出租房信息
     * 1 表示 已经审核
     * 0 表示 未审核状态
     * @param pageBean 分页的参数
     * @param state  状态
     * @return影响的函数
     */
    PageInfo<House> getHouseIsPass( PageBean pageBean,Integer state);

    /**
     * 修改审核的状态
     * 1 表示 已经审核
     * 0 表示 未审核状态
     * @param id  出租房的编号
     * @param state 状态的信息
     * @return 影响的行数
     */
    int PassHouse(String id, Integer state);

    /**
     * 查询用户浏览的页面
     * @param condition
     * @return
     */
    PageInfo<House> getBorswerHouse(HouseCondition condition);

    /**
     * 查询单条信息 用于浏览页面查看详情
     * @param id  参数id
     * @return  返回结果 House
     */
    public House getOneHouse(String id);


}
