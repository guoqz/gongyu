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
<form class="layui-form" style="width: 80%;">
    <!--管理员id 这个必须要有 因为底层sql是根据id来更新的
    但是password和 status其他字段可有可无 但是类型和名称必须一样 不然会绑定错误
    ajax无法进去controller
    -->
    <input type="hidden" name="id" value="${editPerson.id}" id="id"/>


    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" id="name" class="layui-input"
                   name="name" value="${editPerson.name}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" name="email" id="email" id="email" class="layui-input"
                   lay-verify="email" placeholder="请输入邮箱" value="${editPerson.email}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <c:if test="${editPerson.sex=='女'}">
                <input type="radio" name="sex" value="男" title="男">
                <input type="radio" name="sex" value="女" title="女" checked>

            </c:if>
            <c:if test="${editPerson.sex=='男'}">
                <input type="radio" name="sex" value="男" title="男" checked>
                <input type="radio" name="sex" value="女" title="女">

            </c:if>

        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">出生日期</label>
        <div class="layui-input-block">
            <input type="text" id="birthday" class="layui-input" autocomplete="off"
                   name="birthday" lay-verify="date" placeholder="yyyy-MM-dd"
                   value="<fmt:formatDate value="${editPerson.birthday}" pattern="yyyy-MM-dd"/>"
            >
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input type="text" name="address" class="layui-input"
                   lay-verify="required" placeholder="请输入地址" value="${editPerson.address}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" name="phone" class="layui-input"
                   lay-verify="phone" placeholder="请输入手机号" value="${editPerson.phone}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">分配公司</label>
        <div class="layui-input-block">
            <select name="companyId">
                <option value="">请选择</option>
                <c:forEach items="${companies}" var="r">
                    <c:if test="${editPerson.companyId==r.id}">
                        <option value="${r.id}" selected>${r.companyName}</option>
                    </c:if>
                    <c:if test="${editPerson.companyId!=r.id}">
                        <option value="${r.id}">${r.companyName}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">分配公寓</label>
        <div class="layui-input-block">
            <select name="flatsId">
                <option value="">请选择</option>
                <c:forEach items="${flats}" var="r">
                    <c:if test="${editPerson.flatsId==r.id}">
                        <option value="${r.id}" selected>${r.flatsName}</option>
                    </c:if>
                    <c:if test="${editPerson.flatsId!=r.id}">
                        <option value="${r.id}">${r.flatsName}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="updatePerson">立即保存</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/js/person/editPerson.js"></script>
<script type="text/javascript" src="${ctx }/js/pubilc.js"></script>
</body>
</html>
