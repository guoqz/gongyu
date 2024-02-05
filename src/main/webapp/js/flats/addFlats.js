var $;
var $form;
var form;
layui.config({
    base: ctx + "/js/"
}).use(['form', 'layer', 'jquery', 'upload'], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage;
    $ = layui.jquery;
    upload = layui.upload;
    form = layui.form;


    upload.render({  //这里是上传一张图片
        elem: "#SingleUpload",
        url: ctx + "/flats/singleUpload",
        done: function (res, index, upload) {
            //假设code=0代表上传成功
            if (res.code == 0) {
                layer.msg("文件异步加载成功！", {icon: 1});
                $("#simpleImg").attr("src", res.image);
                $("#flatsPhoto").val(res.image);
                $("#SingleUpload").addClass("layui-btn-disabled");
                $("#SingleUpload").off("click");
            }
        }
    });


    $("#flatsName").blur(function () {
        $.ajax({
            type: "get",
            dataType: "json",
            url: ctx + "/flats/checkFlatsName/" + $("#flatsName").val(),
            success: function (data) {
                if (data.code != 0) {
                    top.layer.msg(data.msg);//top.layer 表示在最顶层弹出窗口
                    $("#flatsName").val(""); //清空原来的输入
                    $("#flatsName").focus();//焦点重新定位到username
                }
            }
        });
    });
    form.on("submit(addFlats)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候。', {icon: 16, time: false, shade: 0.8});
        var msg;
        $.ajax({
            type: "post",
            url: ctx + "/flats/insertFlats",
            data: data.field,
            dataType: "json",
            success: function (d) {
                if (d.code == 0) {
                    msg = "添加成功!";
                } else {
                    msg = d.msg;
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                layer.alert("获取数据失败! 先检查sql 及 Tomcat Localhost Log 的输出");
            }


        });

        setTimeout(function () {
            top.layer.msg(msg);
            /*
            top.layer.close(index);
            layer.closeAll("iframe");
            setTimeout(function () {  //添加一个 延时 否则接着刷新 看不到提示的信息
                //刷新父页面
                top.location.reload();
            },1000);
            */
            top.layer.msg("添加成功", {shift: -1, time: 2000}, function () {
                parent.location.reload();  //shift: -1表示提示信息不抖动 2秒后调用回调函数
            });
        }, 2000);  //在点击提交后2秒执行 setTimeout中的function函数

        return false;
    })
});


