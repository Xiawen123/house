<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Object o=session.getAttribute("userInfo");
  if(o==null)
    out.print("<script>alert('你没有登入，或者登入时间失效，请登入!');location.href='login.jsp';</script>");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>

  <script language="JavaScript">
   $(function () { //加载事件
      $.post("getType",null,function (data) {
         for (var i=0;i<data.length;i++){
           //创建节点
           var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
           //追加节点
           $("#type_id").append(node);
         }
      },"json");

      //1.发送异步请求获取区域，进行显示
      $.post("getDistrict",null,function (data) {
        for (var i=0;i<data.length;i++){
          //创建节点
          var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
          //追加节点
          $("#district_id").append(node);
        }
        loadStreet();//自动加载区域
      },"json");

      //区域添加点击事件 出现街道
      $("#district_id").change(loadStreet);
    function loadStreet() {//加载街道   代码复用
      var did = $("#district_id").val();//获取区域的编号
      //发送异步请求加载街道数据；
      //清空原有数据项
      $("#street_id>option").remove();
       $.post("getDistrictAllStreet",{"did":did},function (data) {//
        for (var i=0;i<data.length;i++){
          //创建节点
             var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
          //追加节点
          $("#street_id").append(node);
          }
            },"json")
          }
    });
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post enctype="multipart/form-data"  action=addHouse>
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field colspan="2">${info}</TD>
  <TR>
  <TR>
    <TD class=field>标　　题：</TD>
    <TD><INPUT id=add_action_title class=text type=text name=title> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=typeId id="type_id">
    </SELECT></TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price> </TD></TR>
  <TR>
    <TD class=field>房产证日期：</TD>
    <TD><INPUT class=text type=date name=pubdate></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text  name=district_id  id="district_id">  </SELECT>
        街：<SELECT class=text  name=streetId   id="street_id">   </SELECT>
    </TD></TR>
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact> </TD></TR>
  <TR>
  <TR>
    <TD class=field>图片：</TD>
    <TD><INPUT id="pic" class=text type=file name=pfile> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description></TEXTAREA></TD></TR></TBODY></TABLE>
<DIV class=buttons><INPUT  value=立即发布 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
