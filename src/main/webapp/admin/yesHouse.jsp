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
    <script language="JavaScript" src="js/yesHouse.js">

    </script>
</head>
<body>
<!--显示所有区域-->
<table id="dg"></table>

<!--制作工具栏-->
<div id="ToolBar">
    <div>
        户型:<input type="text" id="name" name="name">
        面积:<input type="text" id="tel" name="telephone">
        最第价格:<input type="text" id="startage" name="startAge">
        最高价格:<input type="text" id="endage" name="endAge">
        <a href="javascript:userSearch()" class="easyui-linkbutton"
           iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<!--添加对话框-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form method="post" action="getDistrict"  id="form1" name="form1">
        区域名称:<input type="text" name="name" id="name1">
    </form>
</div>

<!--添加对话框的按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDistrict()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseAddDialog('AddDialog')"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<!--修改对话框-->
<div id="updateDialog" class="easyui-dialog" buttons="#updateDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form method="post" action="addDistrict" id="form2" name="form1">
        <input type="hidden" name="id" >
        区域名称:<input type="text" name="name" id="name2">
    </form>
</div>
<!--修改对话框的按钮-->
<div id="updateDialogButtons">
    <a href="javascript:SaveDistrict1()" class="easyui-linkbutton"
       iconCls="icon-ok">更新</a> <a href="javascript:CloseAddDialog('updateDialog')"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>


</body>
</html>
