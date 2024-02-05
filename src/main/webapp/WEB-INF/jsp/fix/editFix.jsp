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
    <input type="hidden" name="id" value="${fix.id}" id="id"/>


    <div class="layui-form-item">
        <label class="layui-form-label">维修日期</label>
        <div class="layui-input-block">
            <input type="text" id="fixTime" class="layui-input" autocomplete="off"
                   name="fixTime" lay-verify="datetime" placeholder="请输入维修日期"
                   value="<fmt:formatDate value="${fix.fixTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">

        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">维修状态</label>
        <div class="layui-input-block">
            <c:if test="${fix.fixStatus==0}">
                <input type="radio" name="fixStatus" value="0" title="维修完毕" checked>
                <input type="radio" name="fixStatus" value="1" title="维修中">
            </c:if>
            <c:if test="${fix.fixStatus==1}">
                <input type="radio" name="fixStatus" value="0" title="维修完毕">
                <input type="radio" name="fixStatus" value="1" title="维修中" checked>
            </c:if>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">公寓名称</label>
        <div class="layui-input-block">
            <select name="flatsId">
                <option value="">请选择</option>
                <c:forEach items="${flats}" var="r">
                    <c:if test="${fix.flatsId==r.id}">
                        <option value="${r.id}" selected>${r.flatsName}</option>
                    </c:if>
                    <c:if test="${fix.flatsId!=r.id}">
                        <option value="${r.id}">${r.flatsName}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">维修人员</label>
        <div class="layui-input-block">
            <select name="fixPeopleId" id="fixPeopleId">
                <option value="">请选择</option>
                <c:forEach items="${persons}" var="p">
                    <c:if test="${fix.fixPeopleId==p.id}">
                        <option value="${p.id}" selected>${p.nickname}</option>
                    </c:if>
                    <c:if test="${fix.fixPeopleId!=p.id}">
                        <option value="${p.id}">${p.nickname}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label ">维修内容</label>
        <div class="layui-input-block">
            <input type="text" id="fixNote" class="layui-input"
                   lay-verify="required" placeholder="请输入维修内容" name="fixNote" value="${fix.fixNote}">
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="updateFix">立即保存</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/js/fix/editFix.js"></script>
<script type="text/javascript" src="${ctx }/js/pubilc.js"></script>
</body>
</html>

