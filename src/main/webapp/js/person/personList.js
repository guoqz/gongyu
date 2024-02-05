layui.use(['form', 'jquery', 'table', 'laydate'], function () {
    var layer = layui.layer;
    var $ = layui.jquery;
    var form = layui.form;
    var table = layui.table;
    var laydate = layui.laydate;  //时间选择控件
    active = {  //定义一个数组 点击查询的时候会用到
        search: function () {  //实际上就是点击查询调用的函数
            var name = $('#name');
            var companyId = $('#companyId option:selected');
            var flatsId = $('#flatsId option:selected');

            table.reload('personList', {
                page: {curr: 1},
                where: {
                    name: name.val(),
                    companyId: companyId.val(),
                    flatsId: flatsId.val(),
                }
            });
        }
    };
    $(".search_btn").click(function () {
        var type = $(this).data('type');//jsp 查询 按钮中  data-type="search" 所以这里var type=search
        active[type] ? active[type].call(this) : ''; //查看active中有没有 search数组 有的话就调用其函数
    });

    table.render({
        id: 'personList',
        elem: '#personList',
        url: ctx + "/person/getAllPersons",
        limit: 10,
        limits: [10, 20, 30, 40],
        cols: [[
            {field: 'id', title: '人员序号', align: 'center', width: 80},
            {field: 'name', title: '人员姓名', align: 'center', width: 110},
            {field: 'sex', title: '性别', align: 'center', templet: '#sexTpl', width: 60},
            {
                field: 'birthday',
                title: '生日',
                align: 'center',
                templet: '<div>{{ formatTime(d.birthday,"yyyy-MM-dd")}}</div>',
                width: 110
            },
            {field: 'phone', title: '电话', align: 'center', width: 120},
            {field: 'companyName', title: '所属公司', width: 120, templet: '<div>{{d.company.companyName}}</div>'},
            {field: 'flatsName', title: '所属公寓', width: 120, templet: '<div>{{d.flats.flatsName}}</div>'},
            {field: 'email', title: 'E-mail', align: 'center', width: 180},
            {field: 'address', title: '地址', align: 'center', width: 160},
            {title: '操作', align: 'center', width: 180, toolbar: "#barDemo"}  //注意！！！！
            // 宽度设置大一点 否则 删除图标显示不出来 然后会出现下拉符号 显示
            // 删除符号 但此时点击就不会有效果了 所以width设大一点 都显示出来
        ]],
        page: true,
        loading: true

    });


    table.on('tool(personList)', function (obj) {
        var data = obj.data;
        if (obj.event === 'delete') {
            layer.confirm('确定要删除' + data.name + '么？', function (index) {
                $.ajax({
                    url: ctx + "/person/deletePersonById/" + data.id,
                    type: "POST",
                    dataType: "json",
                    success: function (d) {
                        if (d.code == 0) {
                            layer.msg("删除成功", {icon: 1});
                            obj.del();//下面没有重新加载table
                            // 这里删除了不会自动刷新 但页面中 这一项没有了
                        } else {
                            layer.msg("权限不足，删除失败", {icon: 5});
                        }
                    },
                    error: function () {
                        layer.msg("删除失败，检查sql及输出", {icon: 5});
                    }
                })
                layer.close(index);
            });
        } else if (obj.event == 'edit') {
            var editIndex = top.layer.open({
                type: 2,
                title: "编辑人员",
                area: ['450px', '600px'],
                content: ctx + "/person/editPerson/" + data.id //controller中只是跳转jsp 所以这里不用success判断
            });
        }
    })

    $(".personAdd_btn").click(function () {
        var addIndex = top.layer.open({
            title: "添加人员",
            type: 2,
            area: ['550px', '590px'],
            content: ctx + "/person/addPerson"
        });
    });
});

// 格式化时间
function formatTime(datetime, fmt) {
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
