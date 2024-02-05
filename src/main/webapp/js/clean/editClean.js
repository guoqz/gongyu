layui.use(['form', 'layer', 'jquery', 'table', 'laydate'], function () {
    var layer = layui.layer, $ = layui.jquery, form = layui.form, table = layui.table, laydate = layui.laydate;


    laydate.render({
        elem: '#cleanTime',
        type: 'datetime',//可选择：年、月、日、时、分、秒
    });

    //这样可以实现 选中触发事件
    form.on('select(flatsFilter)', function (data) {
        var val = data.value; //val就是d.flatsId的值 不是名称

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


    form.on("submit(updateClean)", function (data) {
        var index = top.layer.msg("数据提交中，请稍候", {icon: 16, time: false, shade: 0.2});
        var msg;
        $.ajax({
            type: "post",
            url: ctx + "/clean/updateClean",
            data: data.field,
            dataType: "json",
            success: function (d) {
                if (d.code == 0) {
                    msg = "打扫记录更新成功";
                    // flag=true;
                } else {
                    msg = d.msg;
                }
            },
            error: function () {
                layer.msg("错误，请检查sql及输出", {icon: 2});
                // layer.closeAll();
            }
        });
        setTimeout(function () {
            top.layer.msg(msg, {shift: -1, time: 2000}, function () {
                parent.location.reload();  //shift: -1表示提示信息不抖动 2秒后调用回调函数
            });
        }, 2000);  //在点击提交后2秒执行 setTimeout中的function函数
        return false;

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





