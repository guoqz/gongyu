<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/page/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${ctx}/layui/css/layui.css">
    <script>
        var ctx = "${ctx}";
    </script>
</head>
<body>
<form class="layui-form" style="width: 80%;" id="auf">

    <div class="layui-form-item">
        <label class="layui-form-label">打扫日期</label>
        <div class="layui-input-inline">
            <input type="text" id="cleanTime" name="cleanTime" class="layui-input"
                   lay-verify="required" placeholder="请输入打扫日期">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">打扫评分</label>
        <div class="layui-input-inline">
            <input type="radio" name="cleanLevel" value="0" title="优秀">
            <input type="radio" name="cleanLevel" value="1" title="良好" checked>
            <input type="radio" name="cleanLevel" value="2" title="不及格">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">公寓名称</label>
        <div class="layui-input-block">
            <select name="flatsId" id="flatsId"
                    lay-filter="flatsFilter">
                <option value="">请选择</option>
                <c:forEach items="${flats}" var="d">
                    <option value="${d.id}">${d.flatsName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">打扫人姓名</label>
        <div class="layui-input-block">
            <select name="personId" id="personId">
                <option value="">请选择</option>
                <c:forEach items="${persons}" var="s">
                    <option value="${s.id}">${s.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addClean">立即提交</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/js/clean/addClean.js"></script>
<script type="text/javascript" src="${ctx }/js/pubilc.js"></script>

</body>
</html>