<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审核管理</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript" src="js/noHouse.js">

    </script>
</head>
<body>
<!--显示所有区域-->
<table id="dg"></table>

<div id="ToolBar">
    <div>
        名字:<input type="text" id="name" name="name">
        电话:<input type="text" id="tel" name="telephone">
        开始年龄:<input type="text" id="startage" name="startAge">
        结尾年龄:<input type="text" id="endage" name="endAge">
        <a href="javascript:userSearch()" class="easyui-linkbutton"
           iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>





</body>
</html>
