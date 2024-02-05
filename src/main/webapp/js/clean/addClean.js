layui.use(['form', 'layer', 'jquery', 'laydate'], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer;
    var $ = layui.jquery, form = layui.form, laydate = layui.laydate;

    laydate.render({
        elem: '#cleanTime',
        type: 'datetime'
    });


    //这样可以实现 选中触发事件
    form.on('select(flatsFilter)', function (data) {
        var val = data.value; //val就是d.id的值 不是名称
        $.ajax({
            type: "get",
            dataType: "json",
            url: ctx + "/clean/getPersonsByFlatsId/" + val,
            success: function (data) {
                console.log(data);

                var options = "<option>请选择</option>";
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    options += "<option value='" + item.id + "'>" + item.name + "</option>";
                }
                $("#personId").html(options);
                form.render('select');
            }
        });

    });


    form.on("submit(addClean)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.2});
        var msg;
        var flatsId = $('#flatsId').val();
        var personId = $('#personId').val();
        if ("" == flatsId || null == flatsId) {
            layer.alert("请选择公寓");
            return false;
        }
        if ("" == personId || null == personId) {
            layer.alert("请选择人员");
            return false;
        }
        $.ajax({
            type: "post",
            url: ctx + "/clean/insertClean",
            data: data.field,
            dataType: "json",
            success: function (d) {
                if (d.code == 0) {
                    msg = "打扫记录添加成功";
                } else {
                    msg = d.msg;
                }
            },
            error: function () {
                layer.msg("添加失败，请检查sql及输出", {icon: 2});
            }

        });

        setTimeout(function () {
            //  top.layer.msg(msg);

            top.layer.msg(msg, {shift: -1, time: 2000}, function () {
                parent.location.reload();  //shift: -1表示提示信息不抖动 2秒后调用回调函数
            });
        }, 2000);  //在点击提交后2秒执行 setTimeout中的function函数
        return false;
    })

});