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
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-inline">
            <input class="layui-input" type="text" name="name" id="name"
                   lay-verify="required" placeholder="请输入人员姓名" value="">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex" value="男" title="男" checked>
            <input type="radio" name="sex" value="女" title="女">

        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">出生日期</label>
        <div class="layui-input-inline">
            <input type="text" id="birthday" name="birthday" class="layui-input"
                   lay-verify="required" placeholder="请输入出生日期">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-inline">
            <input type="text" id="email" name="email" class="layui-input"
                   lay-verify="email" placeholder="请输入邮箱" value="">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-inline">
            <input type="text" id="phone" name="phone" class="layui-input"
                   lay-verify="phone" placeholder="请输入手机号" value="">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">家庭住址</label>
        <div class="layui-input-inline">
            <input type="text" id="address" name="address" class="layui-input"
                   lay-verify="required" placeholder="请输入家庭住址" value="">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">所属公司</label>
        <div class="layui-input-block">
            <select name="companyId">
                <option value="">请选择</option>
                <c:forEach items="${companies}" var="c">
                    <option value="${c.id}">${c.companyName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">分配公寓</label>
        <div class="layui-input-block">
            <select name="flatsId">
                <option value="">请选择</option>
                <c:forEach items="${flats}" var="d">
                    <option value="${d.id}">${d.flatsName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addPerson">立即提交</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/js/person/addPerson.js"></script>
<script type="text/javascript" src="${ctx }/js/pubilc.js"></script>

</body>
</html>