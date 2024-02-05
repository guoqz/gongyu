layui.config({
    base: ctx + "/js/"
}).use(['form', 'layer', 'jquery', 'excel', 'laypage', 'table'], function () {
    var form = layui.form, table = layui.table,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage;
    $ = layui.jquery;
    var excel = layui.excel;


    //数据表格
    table.render({
        id: 'fixList',
        elem: '#fixList',
        url: ctx + "/fix/getFixList", //数据接口
        cellMinWidth: 80,
        limit: 10,//每页条数
        limits: [10, 20, 30, 40],
        cols: [[ //表头
            {field: 'id', title: 'ID', sort: true, align: 'center', width: 120},

            {
                field: 'fixTime',
                title: '维修时间',
                align: 'center',
                templet: '<div>{{ formatTime(d.fixTime,0,"yyyy-MM-dd hh:mm:ss")}}</div>'
            },

            {field: 'flatsName', title: '公寓名称', align: 'center', templet: '<div>{{d.flats.flatsName}}</div>'},
            {field: 'nickname', title: '维修人员姓名', align: 'center', templet: '<div>{{d.fixPeople.nickname}}</div>'},
            {field: 'fixStatus', title: '维修状况', align: 'center', width: 110, templet: '#fixTpl'},
            {field: 'fixNote', title: '维修内容', align: 'center', width: 120},
            {title: '操作', toolbar: '#barEdit', align: 'center'}
        ]],
        page: true
    });
    //监听工具条
    table.on('tool(fixList)', function (obj) {
        var data = obj.data;
        if (obj.event == 'del') {

            layer.confirm('真的删除吗？', function (index) {
                $.ajax({
                    url: ctx + "/fix/deleteFix/" + data.id,
                    type: "get",
                    dataType: "json",
                    success: function (d) {
                        if (d.code == 0) {
                            table.reload('fixList', {});//重新发送请求 表格重载
                        } else {
                            layer.msg("查看sql输出，有错误", {icon: 5});
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        layer.alert("获取数据失败! 先检查sql 及 Tomcat Localhost Log 的输出");
                    }
                })
                layer.close(index); //删除上面的确认窗口index是function的参数
            });
        } else if (obj.event == 'edit') {
            layer.open({
                type: 2,
                title: "编辑角色",
                area: ['380px', '600px'],
                content: ctx + "/fix/editFix/" + data.id
            })
        }
    });
    //添加记录
    $(".fixAdd_btn").click(function () {
        var index = layer.open({
            title: "添加维修记录",
            type: 2,
            content: ctx + "/fix/addFix",
            area: ['380px', '550px']
        });
        //改变窗口大小时 重置窗口的高度 防止超出可视区域 如F12调出debig操作
        $(window).resize(function () {
            layui.layer.full(index);
        })
    });

});


//格式化时间 后台穿过来的是 CST时间格式 前台转化成yyyy-MM-dd hh:mm:ss 格式
function formatTime(datetime, datetime1, fmt) {
    if (datetime == datetime1) //因为登录后 loginTime 和logoutTime一样 这样 转换logoutTime的时候显示为空
    {
        return "";
    }
    if (datetime == null || datetime == 0) {
        return "";
    }
    if (parseInt(datetime) == datetime) {
        if (datetime.length == 10) {
            datetime = parseInt(datetime) * 1000;
        } else if (datetime.length == 13) {
            datetime = parseInt(datetime);
        }
    }
    datetime = new Date(datetime);
    var o = {
        "M+": datetime.getMonth() + 1, // 月份
        "d+": datetime.getDate(), // 日
        "h+": datetime.getHours(), // 小时
        "m+": datetime.getMinutes(), // 分
        "s+": datetime.getSeconds(), // 秒
        "q+": Math.floor((datetime.getMonth() + 3) / 3), // 季度
        "S": datetime.getMilliseconds()
        // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1,
                (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
                    .substr(("" + o[k]).length)));
    return fmt;
}