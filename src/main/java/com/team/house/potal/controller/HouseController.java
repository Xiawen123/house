package com.team.house.potal.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.entity.Users;
import com.team.house.service.HouseService;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller(value = "houseController2")
@RequestMapping("/page")
public class HouseController {

    @Autowired
    private HouseService houseService;

    //发布房子 新增
    @RequestMapping("/addHouse")
    public String addHouse(@RequestParam(value = "pfile") CommonsMultipartFile pfile, House house, HttpSession session, Model model){
        //一个CommonsMultipartFile类的对象就但代表一个表单文件域，一张图片
        //获取文件的信息
    try{
        //1.上传图片
        String path="E:\\images\\";
        //2.生成唯一的文件名： 获取到图片的名称
        String oldName = pfile.getOriginalFilename();//旧名字
        String expaname = oldName.substring(oldName.lastIndexOf("."));//提取.之前的 字符串
        //图片加上唯一的 前缀
        String fileName=System.currentTimeMillis()+ expaname;
        File file = new File(path+fileName);//保存
        pfile.transferTo(file);//上传图片
        //第二步保存信息到数据库
            //设置主键
        house.setId(System.currentTimeMillis()+"");
        //设置图片发布出租的用户
        Users users = (Users) session.getAttribute("userInfo");
        house.setUserId(users.getId());//用户名下的 发布的 图片
        //设置图片
        house.setPath(fileName);
        houseService.addHouse(house);
        return "redirect:getHouse";  //跳转到管理页
    }catch (Exception e){
        e.printStackTrace();
        model.addAttribute("info","上传文件失败..");
    }
        return "redirect:getHouse";
    }
    // 查询用户发布的房子
    @RequestMapping("/getHouse")
    public String getHouse(PageBean page,HttpSession session,Model model){//pageBean只为接收 分页的页码
        //获取用户的id
        Users user = (Users) session.getAttribute("userInfo");
        PageInfo<House> pageInfo = houseService.getHouseByUser(page, user.getId());
        //将结果填充到model
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }

    // 查询单条 数据
    @RequestMapping("/getSingleHouse")
    public String getSingleHouse(String id,Model model){
        System.out.println("$$$$$$$$$$$$$$ 查询单条 数据$$$$$$$$$$$$$$$$$"+id);
        //获取房子的信息
       House house= houseService.getHouse(id);
        //将结果填充到model   //前端页面通过 house调数据   可用于回显
        model.addAttribute("house",house);
        return "updateHouse";
    }

    //修改出租房的信息
    @RequestMapping("/updateHouse")
    public String updateHouse(String delimage,@RequestParam("pfile") CommonsMultipartFile pfile,House house){ //接收页面传递的 请求数据
         //判断用户是否选择文件
        System.out.print("对象是:---------"+pfile);
        String filename = pfile.getOriginalFilename();//得到文件名
        System.out.println("文件名是:------"+filename);
        try{
            if (filename.equals("")){//没有选择图片
                houseService.updateHouse(house); //执行修改
            }else {
                //上传
                //1.上传图片
                String path="E:\\images\\";
                //2.生成唯一的文件名： 获取到图片的名称
                String oldName = pfile.getOriginalFilename();//旧名字
                String expaname = oldName.substring(oldName.lastIndexOf("."));//提取.之前的 字符串
                //图片加上唯一的 前缀
                String fileName=System.currentTimeMillis()+ expaname;
                File file = new File(path+fileName);//保存
                pfile.transferTo(file);//上传图片
                //更新数据 信息
                //设置图片的路径  新的
                house.setPath(fileName);
                houseService.updateHouse(house);  //执行修改
                //删除旧图
                File delfile = new File(path + delimage);
                delfile.delete();
            }
            return "redirect:getHouse";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    //删除出租房
    @RequestMapping("/deleteHouse")
    public String deleteHouse(String id){
        //调业务
        int temp=houseService.deleteHouse(id,1);//状态值传1删除；
        if (temp>0){
            return "redirect:getHouse";
        }else {
            return "error";
        }
    }

    //查询浏览的所有出租房信息
    @RequestMapping("/getBorswerHouse")
    public String getBorswerHouse(HouseCondition condition,Model model){
       //调业务
        PageInfo<House> pageInfo = houseService.getBorswerHouse(condition);
        //存在model里
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("condition",condition);
        System.out.println(pageInfo.toString());
        return "list";
    }

    //查询单条的出租房具体信息
    // 查询单条 数据
    @RequestMapping("/getOneHouse")
    public String getOneHouse(String id,Model model,HttpSession session){
        System.out.println("$$$$$$$$$$$$$$ 查询单条 数据$$$$$$$$$$$$$$$$$"+id);
        //获取房子的信息
        House house = houseService.getOneHouse(id);
        //将结果填充到model   //前端页面通过 house调数据   可用于回显
        model.addAttribute("house",house);
        System.out.println("---------------------"+house.getId());
          session.getAttribute("userInfo");
        return "details";
    }
}
