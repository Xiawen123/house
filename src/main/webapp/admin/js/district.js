$(function(){//加载事件
    //使用datagrid绑定数据库
    $('#dg').datagrid({
        url:'getDistrict',
        title:"区域信息",
        toolbar:"#ToolBar",  //指定工具栏
        pagination:true,//开启分页
        pageSize:3,//设置每页多少条
        pageList:[3,6,10,20],//设置选择分页大小的条数
        columns:[[
            {field:'bx',checkbox:true ,width:100},//复选框
            {field:'id',title:'编号',width:200},
            {field:'name',title:'区域名称',width:200},
            {field:'s',title:'操作',width:200,
                formatter: function(value,row,index){
                    var str="<a href='javascript:DelDirstrict("+row.id+");'>删除</a>|<a href='javascript:updateDialog()'>修改</a>|<a href='javascript:OpenshowStreetDialog("+row.id+","+index+")'>查看街道</a>";
                    return str;
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
function SaveDistrict(){
    //1.获取数据，发送异步请求实现添加
    /* $post("addDistrict",{"name":$("name1").val()},function (data) {
         alert(data)
     },"json")*/
    //2.使用easyui插件以异步的方式提交表单
    $('#form1').form('submit', {
        url:"addDistrict",
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
        $.post("getOneDistrict",{"id":SelectRows[0].id},function(data){
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
function SaveDistrict1(){
    //1.获取数据，发送异步请求实现添加
    /* $post("addDistrict",{"name":$("name1").val()},function (data) {
         alert(data)
     },"json")*/
    //2.使用easyui插件以异步的方式提交表单
    $('#form2').form('submit', {
        url:"updateDistrict",
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
function  DelDirstrict(obj) {  //传的是编号
    //发送异步请求实现删除
    $.messager.confirm('提示框', '确定删除吗？', function(r){
        if (r){
            $.post("deleteDistrict",{"id":obj},function(data){// data是json
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

//批量删除区域
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
            $.post("delMoreDistrict",{"id":value},function(data){
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

/*打开街道显示的窗口*/
function OpenshowStreetDialog(did){//  did 是接收 row.id的值
    //id表示接收区域编号
    //打开窗口
    $("#showStreetDialog").window("open").window("setTitle","街道信息");
    //发请求绑定数据
    $('#aaa').datagrid({
        url:'getDistrictAllStreet?did='+did,
        title:"街道信息",
        pagination:true,
        pageList:[3,6,9,15,20],
        pageSize:3,
        columns:[[
            {field:"cb",checkbox:true},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'街道名称',width:100,
                formatter: function(value,row,index){
                    var str="<input class='StreetName' type='text' value='"+value+"'>";
                    return str;
                }},
            {field:'s',title:'操作',width:200,
                formatter: function(value,row,index){
                    var str="<a href='javascript:updateStreet("+row.id+","+index+")'>更新</a>";
                    return str;
                }
            }
        ]]
    });
//当前区域下添加街道
    var districtId = $("#districtname").val(did);
    $("#button").click(function () {
        $.post("addStreet",{"name":$("#StreetName").val(),"districtId":districtId.val()},function (data){
            //将返回的json字符串转化为json对象
       /*     data=$.parseJSON(data);*/
            if(data.result>0){
                $("#aaa").datagrid("reload");
               $.messager.alert('提示框','新增成功!','info');
            }else{
                $.messager.alert('提示框','新增失败!','info');
            }
        },"json");
    });
}
//判断当前区域下的街道新增，街道名是否重复！
$(function () {
    $("#StreetName").blur(function () {
        $.post("checkStreetName",{"name":$("#StreetName").val()},function (data) {
            if (data.result==1){
                $("#showStreet").html("用户名已被占用！").css("color","red");
            } else {
                $("#showStreet").html("ok").css("color","green");}
        },"json")
    })
})

//更新当前区域的街道 修改街道
function  updateStreet(sid,StreetIndex) { //当前行 和下标 判断值
    var StreetName = $(".StreetName").eq(StreetIndex).val();//当前街道的名字
    $.post("updateStreet",{"id":sid,"name":StreetName},function (data) {//回调函数
        if (data.result>0) {
            $('#aaa').datagrid("reload");//刷新 到 当前页
            $.messager.alert('提示框','已更新!','info');
        }else {
            $.messager.alert('提示', '修改失败！', 'error');
        }
    },"json")
}

