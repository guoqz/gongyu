layui.use(['form', 'layer', 'jquery', 'laypage', 'table'], function () {
    var form = layui.form, table = layui.table,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage;
    $ = layui.jquery;
    //数据表格
    table.render({
        id: 'flatsList',
        elem: '#flatsList',
        url: ctx + "/flats/getFlatsList", //数据接口
        cellMinWidth: 80,
        limit: 10,//每页条数
        limits: [10, 20, 30, 40],
        cols: [[ //表头
            {field: 'id', title: 'ID', sort: true, align: 'center', width: 120},
            {field: 'flatsPhoto', title: '公寓图片', align: 'center', width: 110, templet: "#imgtmp"},
            {field: 'flatsName', title: '公寓名称', width: 120, align: 'center'},
            {
                field: 'name', title: '公寓成员', width: 360, templet: function (d) {
                    return $.map(d.personList, function (item, index) {
                        return item.name
                    }).join(";")

                }
                , align: 'center'
            },
            {title: '操作', width: 120, toolbar: '#barEdit', align: 'center'}
        ]],
        page: true
    });
    //监听工具条
    table.on('tool(flatsList)', function (obj) {
        var data = obj.data;
        if (obj.event == 'del') {

            layer.confirm('真的删除吗？', function (index) {
                $.ajax({
                    url: ctx + "/flats/deleteFlats/" + data.id,
                    type: "get",
                    dataType: "json",
                    success: function (d) {
                        if (d.code == 0) {
                            table.reload('flatsList', {});//重新发送请求 表格重载
                        } else {
                            layer.msg("此宿舍下有学生，不能删除", {icon: 5});
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
                title: "修改公寓详情",
                area: ['800px', '600px'],
                content: ctx + "/flats/editFlats/" + data.id
            })
        }
    });
    //添加角色
    $(".flatsAdd_btn").click(function () {
        var index = layer.open({
            title: "添加公寓",
            type: 2,
            content: ctx + "/flats/addFlats",
            area: ['560px', '530px']
        });
        //改变窗口大小时 重置窗口的高度 防止超出可视区域 如F12调出debig操作
        $(window).resize(function () {
            layui.layer.full(index);
        })
    });


});