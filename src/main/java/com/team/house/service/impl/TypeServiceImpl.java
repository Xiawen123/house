package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import com.team.house.mapper.TypeMapper;
import com.team.house.service.TypeService;
import com.team.house.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;  //街道

    @Override  //分页查看所有
    @Transactional(propagation = Propagation.SUPPORTS)  //挂起事务,不其于事务执行
    public PageInfo<Type> getAllType(PageBean pageBean) {
        //1.开启分页
        PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
        //2.查询所有
        TypeExample typeExample = new TypeExample();
        List<Type> list = typeMapper.selectByExample(typeExample);
        // 3，获取分页的相关信息
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override   //添加业务：新增
    public int addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override//查询单挑信息
    public Type getType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override //修改
    public int updateType(Type Type) {
        return typeMapper.updateByPrimaryKeySelective(Type);
    }

    @Override//删除
    public int deleteType(Integer id) {
       //删除区域的同时，删除街道   先删除外键，再删除主
        return typeMapper.deleteByPrimaryKey(id);

    }

    @Override//批量删除
    public int delMoreType(List<Integer> ids) {
        return typeMapper.delMoreType(ids);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }
}