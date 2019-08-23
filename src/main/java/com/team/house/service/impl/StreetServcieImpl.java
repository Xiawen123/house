package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.StreetService;
import com.team.house.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StreetServcieImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;  //街道

    @Override
    public PageInfo<Street>  getDistrictAllStreet(Integer did, PageBean pageBean) {
        //1.开启分页
        PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
        //2.查询所有
        StreetExample streetExample = new StreetExample();
        //添加条件
        StreetExample.Criteria criteria=streetExample.createCriteria();
         //通过区域编号查找街道
        criteria.andDistrictIdEqualTo(did);
        List<Street> list = streetMapper.selectByExample(streetExample);
        // 3，获取分页的相关信息
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override  //分页查看所有 分页
    @Transactional(propagation = Propagation.SUPPORTS)  //挂起事务,不其于事务执行
    public PageInfo<Street> getAllStreet(PageBean pageBean) {
        //1.开启分页
        PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
        //2.查询所有
        StreetExample streetExample = new StreetExample();
        List<Street> list = streetMapper.selectByExample(streetExample);
        // 3，获取分页的相关信息
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override   //添加业务：新增
    public int addStreet(Street street) {
        return streetMapper.insertSelective(street);
    }

    @Override//查询单挑信息
    public Street getStreet(Integer id) {
        return streetMapper.selectByPrimaryKey(id);
    }

    @Override //修改
    public int updateStreet(Street street) {
        return streetMapper.updateByPrimaryKeySelective(street);
    }
    @Override//删除
    public int deleteStreet(Integer id) {
        //删除区域的同时，删除街道   先删除外键，再删除主
        return  streetMapper.deleteByPrimaryKey(id);
    }
    @Override//批量删除
    public int delMoreStreet(List<Integer> ids) {
        return streetMapper.delMoreStreet(ids);
    }

    @Override // 判断区域下的街道添加 时是否重复！
    public int isStreetNameExists(String name) {
        return streetMapper.getStreetCount(name);
    }

    @Override//查看所有
    public List<Street> getDistrictAllStreet(Integer did) {
        //2.查询所有
        StreetExample streetExample = new StreetExample();
        //添加条件
        StreetExample.Criteria criteria=streetExample.createCriteria();
        //通过区域编号查找街道
        criteria.andDistrictIdEqualTo(did);
        List<Street> list = streetMapper.selectByExample(streetExample);
        return list;
    }
}
