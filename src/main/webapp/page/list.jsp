<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
  <script language="JavaScript">
    $(function () { //加载事件
      $.post("getType",null,function (data) {
        for (var i=0;i<data.length;i++){  //用于 下拉框  追加节点
          //创建节点
          var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
          //追加节点
          $("#type_id").append(node);
        }
        //设置选中项     选中用于回显
        $("#type_id").val(${condition.typeid});
      },"json");
      //1.发送异步请求获取区域，进行显示
      $.post("getDistrict",null,function (data) {
        for (var i=0;i<data.length;i++){  //用于下拉框
          //创建节点
          var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
          //追加节点
          $("#district_id").append(node);
        }
        //设置选中项   回显 did 对应的 区域
        $("#district_id").val(${condition.districtid});
        loadStreet();//自动加载区域    用于二级联动
      },"json");
      //区域添加点击事件 出现街道
      $("#district_id").change(loadStreet);
      //加载街道   代码复用
      function loadStreet() {
        //获取区域的编号
        var did = $("#district_id").val();
          if(did!="") {
        //发送异步请求加载街道数据；
        //清空原有数据项
        $("#street_id>option").remove();
        $.post("getDistrictAllStreet",{"did":did},function (data) {
          for (var i=0;i<data.length;i++){
            //创建节点
            var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
            //追加节点
            $("#street_id").append(node);
          }
          //设置街道选中项  用于区域下的 街道 选中 回显
          $("#street_id").val(${condition.streetid});
        },"json")
      }
      }
    });
    //带条件的分页
    function goPage(pageNum){//
      //1.将页码设置到表单
      $("#setPage").val(pageNum);
      //2.提交表单
      $("#sform").submit();   //js提交表单，相当于点击了提交按钮
    }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
    <DIV class=search>
        <span style="color: red;font-size:20px; font-family: KaiTi" >欢迎:${sessionScope.userInfo.name}</span>&nbsp;&nbsp;&nbsp;
        <LABEL class="ui-green searchs"><a href="/page/login.jsp" title="" style="">登陆</a></LABEL>
        <LABEL class="ui-green searchs"><a href="/page/regs.jsp" title="">退出</a></LABEL>
    </DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=sform method=post action="getBorswerHouse">
    <input  type="hidden" id="setPage" name="page" value="1"/>
    标题:<input type="text" name="title" value="${condition.title}">
    区域:<select name="districtid"  id="district_id">
              <option value="">--请选择--</option>
        </select>
    街道:<select name="streetid"  id="street_id">
              <option value="">--请选择--</option>
        </select>
    类型:<select name="typeid"  id="type_id" >
              <option value="">--请选择--</option>
        </select>
    价格:<input type="tex" name="startPrice" size="6" value="${condition.startPrice}">-<input name="endPrice" type="text" size="6" value="${condition.endPrice}">
    <INPUT  value=搜索房屋 type=submit name=search>
  </FORM></DL>
</DIV>
<DIV class="main wrap">
<TABLE class=house-list><TBODY>
  <c:forEach items="${pageInfo.list}" var="house">
      <TR class=odd>
          <TD class=house-thumb><span>
              <A href="/page/getOneHouse?id=${house.id}" target="_blank"><img src="http://localhost:80/${house.path}" width="100" height="75"></a></span></TD>
          <TD><DL>
              <DT><A href="/page/getOneHouse?id=${house.id}" target="_blank">${house.title}</A></DT>
              <DD>${house.dname}-${house.sname},${house.floorage}平米<BR>联系方式:${house.contact} </DD></DL></TD>
          <TD class=house-type>${house.tname}</TD>
          <TD class=house-price><SPAN>${house.price}</SPAN>元/月</TD></TR>
    </c:forEach>
  </TBODY></TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.prePage==0?1:pageInfo.prePage})">上一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage})">下一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pages})">末页</A></LI></UL>
  <SPAN  class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN>
    <SPAN>跳转到第<input type="text" size="4" value="${pageInfo.pageNum}">页
        <input type="button" value="GO" onclick="goPage(this.previousElementSibling.value)">
    </SPAN>
</DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联】系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
