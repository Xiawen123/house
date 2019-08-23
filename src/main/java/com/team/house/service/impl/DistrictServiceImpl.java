package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;  //区域
    @Autowired
    private StreetMapper streetMapper;   //街道

    @Override  //分页查看所有
    public PageInfo<District> getAllDistrict(int page, int pagesize) {
        //1.开启分页
        PageHelper.startPage(page,pagesize);
        //2.查询所有
        DistrictExample districtExample = new DistrictExample();
        List<District>list=districtMapper.selectByExample(districtExample);
        // 3，获取分页的相关信息
        PageInfo pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override   //添加业务：新增
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override//查询单挑信息
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override //修改
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override//删除
    @Transactional//事务
    public int deleteDistrict(Integer id) {
        //删除区域的同时，删除街道   先删除外键，再删除主键
            //删除区域下的街道，
            streetMapper.delStreetByDistrict(id);
            //删除区域
            districtMapper.deleteByPrimaryKey(id);

        return 1;
    }

    @Override//批量删除
    public int delMoreDistrict(List<Integer> ids) {
        return districtMapper.delMoreDistrict(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }


}
