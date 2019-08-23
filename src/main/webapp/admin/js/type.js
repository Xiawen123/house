$(function(){//加载事件
    //使用datagrid绑定数据库
    $('#dg').datagrid({
        url:'getType',
        title:"区域信息",
        toolbar:"#ToolBar",  //指定工具栏
        pagination:true,//开启分页
        pageSize:3,//设置每页多少条
        pageList:[3,6,10,20],//设置选择分页大小的条数
        columns:[[
            {field:'bx',checkbox:true ,width:100},//复选框
            {field:'id',title:'编号',width:350},
            {field:'name',title:'区域名称',width:350},
            {field:'s',title:'操作',width:200,
                formatter: function(value,row,index){
                    // var str="<a href=\"deleteType?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    //发送异步请求Ajax
                    return "<a href=\"javascript:DelType("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                }
            }
        ]]
    });
});
//点击添加，打开窗口
function addDialog(){
    //alert("我要添加区域");
    $('#AddDialog').window('setTitle',"添加区域");
    $('#AddDialog').window('open');
}
//关闭添加对话框
function CloseAddDialog(id){
    $('#'+id).window('close'); //关闭
}

//添加业务：
function SaveType(){
    //1.获取数据，发送异步请求实现添加
    /* $post("addType",{"name":$("name1").val()},function (data) {
         alert(data)
     },"json")*/
    //2.使用easyui插件以异步的方式提交表单
    $('#form1').form('submit', {
        url:"addType",
        success: function(data){ //data表示的字符串
            //将返回的json字符串转化为json对象
            data=$.parseJSON(data);
            if (data.result>0){
                $.messager.alert('友情提示:','添加成功！','info');  //提示框
                $('#AddDialog').window('close'); //关闭
                $("#dg").datagrid('reload');
            }else {
                $.messager.alert('友情提示:','添加失败！','info');  //提示框
                $('#AddDialog').window('close');
            }
        }
    });
}

//显示修改的对话框
function updateDialog(){
    //判断用户选择
    //1.获取dagagrid的选中行
    var SelectRows = $("#dg").datagrid('getSelections');  //返回数组
    if(SelectRows.length==1){
        $('#updateDialog').window('setTitle',"编辑区域");
        $('#updateDialog').window('open');
        //获取当前行的数据:获取主键，查询数据库获取单行数据
        //如果当显示的数据在dagagrid中存在，无需查询数据库，如果当显示的数据在datagrid中不全，需要查询数据库获单条
        //发异步请求查询数据库
        $.post("getOneType",{"id":SelectRows[0].id},function(data){
            $("#form2").form('load',data); //将对象还原表单
        },"json");
        //将信息还原到表单中.
        //$("#form1").form('load',json对象);
        //$("#form2").form('load',{"name":"默认值"});  //name表示表单对象名称
        //$("#form2").form('load',SelectRows[0]);  //{"id":1001,"name":"东城"}
    }else{
        $.messager.alert('>>提示','你没有选择行或者选择多行，给我小心点!','warn');  //提示框
    }
}

//添加业务：
function SaveType1(){
    //1.获取数据，发送异步请求实现添加
    /* $post("addType",{"name":$("name1").val()},function (data) {
         alert(data)
     },"json")*/
    //2.使用easyui插件以异步的方式提交表单
    $('#form2').form('submit', {
        url:"updateType",
        success: function(data){ //data表示的字符串
            //将返回的json字符串转化为json对象
            data=$.parseJSON(data);
            if (data.result>0){
                $.messager.alert('友情提示:','修改成功！','info');  //提示框
                $('#updateDialog').window('close'); //关闭
                $("#dg").datagrid('reload');
            }else {
                $.messager.alert('友情提示:','修改失败！','info');  //提示框
                $('#updateDialog').window('close');
            }
        }
    });
}

//删除单行的
function  DelType(obj) {  //传的是编号
    //发送异步请求实现删除
    $.messager.confirm('提示框', '确定删除吗？', function(r){
        if (r){
            $.post("deleteType",{"id":obj},function(data){// data是json
                if(data.result==1){
                    //实现datagrid的刷新
                    $('#dg').datagrid('reload');
                }else{
                    $.messager.alert('提示框','删除成功！','info');
                }
            },"json");
        }else{
            $.messager.alert('提示框','删除失败！','info');
        }
    });
}

//批量删除
function DeleteByFruitName(){
    //获取选中行
    var SelectRows = $("#dg").datagrid('getSelections');//返回数组
    if(SelectRows.length==0){
        $.messager.alert('提示框','你还没有选中行请选择后再删除','info');
        return;
    }   //确认删除
    $.messager.confirm('提示框', '确认删除吗？',function(flag){
        if(flag){  //为true实现删除
            // 调用服务器接口进行删除
            //获取选中项的值
            var value="";
            for(var i=0;i<SelectRows.length;i++){
                value=value+SelectRows[i].id+",";
            }
            value=value.substring(0,value.length-1);  //去除最后的逗号
            //发送异步请求到服务器
            $.post("delMoreType",{"id":value},function(data){
                if(data.result>0){
                    //实现datagrid的刷新
                    $('#dg').datagrid('reload');
                }else{
                    $.messager.alert('提示框','删除失败!','info');
                }
            },"json");
        }
    });

}

