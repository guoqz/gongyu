<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/page/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>添加公寓</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${ctx }/layui/css/layui.css" media="all"/>
    <script>
        var ctx = "${ctx}";
    </script>
    <!-- @meida 表示如果文档宽度小于 1240 像素 那么.layui-form-item .layui-inline的宽度等样式改变-->
    <style type="text/css">
        .layui-form-item .layui-inline {
            width: 33.333%;
            float: left;
            margin-right: 0;
        }

        @media ( max-width: 1240px) {
            .layui-form-item .layui-inline {
                width: 100%;
                float: none;
            }
        }
    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width: 80%;">

    <div class="layui-form-item">
        <label class="layui-form-label ">公寓名称</label>
        <div class="layui-input-block">
            <input type="text" id="flatsName" class="layui-input"
                   lay-verify="required" placeholder="请输入公寓名称" name="flatsName">
        </div>
    </div>
    <input type="hidden" name="flatsPhoto" id="flatsPhoto"/>
    <div class="layui-form-item">
        <label class="layui-form-label">公寓图片</label>
        <div class="layui-upload layui-input-block">
            <button type="button" class="layui-btn layui-btn-primary" id="SingleUpload">上传图片</button>
            <img id="simpleImg" width="60px" height="60px">
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addFlats">立即保存</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/js/flats/addFlats.js"></script>
<script type="text/javascript" src="${ctx }/js/pubilc.js"></script>
</body>
</html>