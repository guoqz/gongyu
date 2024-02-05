layui.use(['form', 'layer', 'jquery', 'laypage', 'table'], function () {
    var form = layui.form, table = layui.table,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage;
    $ = layui.jquery;
    //数据表格
    table.render({
        id: 'companyList',
        elem: '#companyList',
        url: ctx + "/company/getCompanyList", //数据接口
        cellMinWidth: 80,
        limit: 10,//每页条数
        limits: [10, 20, 30, 40],
        cols: [[ //表头
            {field: 'id', title: 'ID', sort: true, align: 'center', width: 120},
            {field: 'companyName', title: '公司名称', align: 'center'},
            {field: 'companyAddress', title: '公司地址', align: 'center'},
            {
                field: 'name', title: '员工名单', width: 160, templet: function (d) {
                    return $.map(d.personList, function (item, index) {
                        return item.name
                    }).join(";")

                }
                , align: 'center'
            },
            {title: '操作', toolbar: '#barEdit', align: 'center'}
        ]],
        page: true
    });
    //监听工具条
    table.on('tool(companyList)', function (obj) {
        var data = obj.data;
        if (obj.event == 'del') {

            layer.confirm('真的删除吗？', function (index) {
                $.ajax({
                    url: ctx + "/company/delCompany/" + data.id,
                    type: "get",
                    dataType: "json",
                    success: function (d) {
                        if (d.code == 0) {
                            table.reload('companyList', {});//重新发送请求 表格重载
                        } else {
                            layer.msg("此班级下有人员，不能删除", {icon: 5});
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
                title: "编辑公司",
                area: ['380px', '600px'],
                content: ctx + "/company/editCompany/" + data.id
            })
        }
    });
    //添加公司
    $(".companyAdd_btn").click(function () {
        var index = layer.open({
            title: "添加公司",
            type: 2,
            content: ctx + "/company/addCompany",
            area: ['380px', '550px']
        });
        //改变窗口大小时 重置窗口的高度 防止超出可视区域 如F12调出debig操作
        $(window).resize(function () {
            layui.layer.full(index);
        })
    });

});