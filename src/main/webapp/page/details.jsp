<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
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
      },"json");
      //1.发送异步请求获取区域，进行显示
      $.post("getDistrict",null,function (data) {
        for (var i=0;i<data.length;i++){  //用于下拉框
          //创建节点
          var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
          //追加节点
          $("#district_id").append(node);
        }
        loadStreet();//自动加载区域    用于二级联动
      },"json");
      //区域添加点击事件 出现街道
      $("#district_id").change(loadStreet);
      //加载街道   代码复用
      function loadStreet() {
        //获取区域的编号
        var did = $("#district_id").val();
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
        },"json")
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

<DIV class="main wrap">
  <TABLE class=house-list><TBODY>
  <br><br>
 <%-- <c:forEach items="${pageInfo.list}" var="house">--%>
  <H1>标题:${house.title}</H1>
  <DIV class=subinfo></DIV>
 <br>
  <DIV class=houseinfo>
    <P>户　　型：<SPAN>${house.tname}</SPAN></P>
    <P>面　　积：<SPAN>${house.floorage}m<SUP>2</SUP></SPAN></P>
    <P>区　　域：<SPAN>${house.dname}</SPAN></P>
    <P>位　　置：<SPAN>${house.sname}</SPAN></P>
    <P>联系方式：<SPAN>${house.contact}</SPAN></P>
    <P>联 系 人：<SPAN>${house.contact}</SPAN></P>
    <P><span><A href="details.htm" target="_blank"><img src="http://localhost:80/${house.path}" width="100" height="75"></a></span></P>
  </DIV>
 <%-- </c:forEach>--%>
  </DIV>
<DIV id=footer class=wrap>
  <DL>
    <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
    <DD>关于我们 · 联】系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
