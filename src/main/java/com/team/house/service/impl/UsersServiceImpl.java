package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UsersService;
import com.team.house.utils.MD5Utils;
import com.team.house.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageInfo<Users> getAllUsers(PageBean pageBean) {
        //开启分页
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        UsersExample e = new UsersExample();
        UsersExample.Criteria criteria = e.createCriteria();
        criteria.andIsadminEqualTo(0);//管理员；
        //添加搜索条件
        if (pageBean.getName() != null) {
            criteria.andNameLike("%" + pageBean.getName() + "%");
        }
        if (pageBean.getTelephone() != null) {
            criteria.andTelephoneLike("%" + pageBean.getTelephone() + "%");
        }
        if (pageBean.getStartAge() != null) {
            criteria.andAgeGreaterThanOrEqualTo(pageBean.getStartAge());
        }
        if (pageBean.getEndAge() != null) {
            criteria.andAgeLessThanOrEqualTo(pageBean.getEndAge());
        }
        List<Users> list = usersMapper.selectByExample(e);
        return new PageInfo<Users>(list);
    }

    @Override   //添加业务：新增
    public int addUsers(Users users) {
        //设置注册用户
        users.setIsadmin(new Integer(0));
        //采用MD5对密码加密
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }

    @Override//查询单挑信息
    public Users getUsers(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override //修改
    public int updateUsers(Users Users) {
        return usersMapper.updateByPrimaryKeySelective(Users);
    }

    @Override//删除
    public int deleteUsers(Integer id) {
        //删除区域的同时，删除街道   先删除外键，再删除主
        return usersMapper.deleteByPrimaryKey(id);
    }

    @Override // 批量删除
    public int delMoreUsers(List<Integer> ids) {
        return 0;
    }

    @Override//判断用户是否存在
    public int isUsersNameExists(String name) {
        return usersMapper.getUsersCount(name);
    }
    //登陆时判断用户名密码是否正确
    @Override
    public Users login(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //指定注册用户
        //criteria.andIsadminNotEqualTo(0);
        //指定用户名密码
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));//对密码加密 去对比
        //执行
        List<Users>  list=usersMapper.selectByExample(usersExample);
        if(list.size()==0)
            return null;    //没查到返回null
        else
            return list.get(0);  //查到 返回数据
    }
}
