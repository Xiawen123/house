package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.utils.PageBean;

import java.util.List;

public interface UsersService {
    //查看所有
    PageInfo<Users> getAllUsers(PageBean pageBean);
    //添加业务：新增
    public int addUsers(Users Users);
    //查询单条信息
    public Users getUsers(Integer id);
    //修改
    public  int  updateUsers(Users Users);
    //删除  -----区域街道；
    public  int deleteUsers(Integer id);
    //批量删除   参数数组 传集合
    int delMoreUsers(List<Integer> ids);

    /**
     * 判断用户名是否存在
     * @param name 用户名
     * @return 总行数 如果返回1说明存在 返回0说明不存在
     */
    public int isUsersNameExists(String name);

    /**
     *  登陆时判断用户名密码是否正确
     * @param username  用户名
     * @param password  用户密码
     * @return  当前用户对象(的数据)
     */
    public Users login(String username,String password);



}
