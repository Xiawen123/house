$(function(){//加载事件
    //使用datagrid绑定数据库
    $('#dg').datagrid({
        url:'getNoPassHouse',
        title:"未审核的房屋信息",
        toolbar:"#ToolBar",  //指定工具栏
        pagination:true,//开启分页
        pageSize:6,//设置每页多少条
        pageList:[6,10,20],//设置选择分页大小的条数
        columns:[[
            {field:'bx',checkbox:true ,width:100},//复选框
            {field:'id',title:'编号',width:100},
            {field:'title',title:'标　　题',width:100},
            {field:'tname',title:'户　　型',width:200},
            {field:'floorage',title:'面　　积',width:100},
            {field:'price',title:'价　　格',width:100},
            {field:'dname',title:'区　　域',width:100},
            {field:'sname',title:'街　　道',width:100},
            {field:'contact',title:'联系方式',width:100},
            {field:'s',title:'操作',width:100,
                formatter: function(value,row,index){
                    //发送异步请求Ajax
                    return "<a href=\"javascript:goPass("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">确定审核</a>";
                }
            }
        ]]
    });
});


//实现搜索功能  区间搜索
function userSearch() {
    //一起发送到当前控制所指定的服务器地址进行处理
    //$("#dg").datagrid("load",传查询条件:{键:值,键:值});
    var name=$("#name").val();
    var telephone=$("#tel").val();
    var startage=$("#startage").val();
    var endage=$("#endage").val();
    alert(name+telephone+startage+endage)
    $('#dg').datagrid('load',{
        name: name,
        telephone: telephone,
        startAge:startage,
        endAge:endage
    });
}
//使用异步请求，实现调用审核的服务器接口
function goPass(id) {
    //发送异步请求
    $.post("goYesPassHouse",{"id":id},function (data) {
        if (data.result>0){
            //刷新页面
            $("#dg").datagrid('load')
        } else {
           $.messager.alert("温馨提示","审核失败","warn")
        }
    },"json")
}

