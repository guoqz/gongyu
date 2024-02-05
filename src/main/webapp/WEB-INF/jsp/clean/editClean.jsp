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
    <input type="hidden" name="id" value="${clean.id}" id="id"/>


    <div class="layui-form-item">
        <label class="layui-form-label">打扫日期</label>
        <div class="layui-input-block">
            <input type="text" id="cleanTime" class="layui-input" autocomplete="off"
                   name="cleanTime" lay-verify="datetime" placeholder="yyyy-MM-dd hh:mm:ss"
                   value="<fmt:formatDate value="${clean.cleanTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">

        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">打扫评分</label>
        <div class="layui-input-block">
            <c:if test="${clean.cleanLevel==0}">
                <input type="radio" name="clean_level" value="0" title="优秀" checked>
                <input type="radio" name="clean_level" value="1" title="良好">
                <input type="radio" name="clean_level" value="2" title="不及格">
            </c:if>
            <c:if test="${clean.cleanLevel==1}">
                <input type="radio" name="clean_level" value="0" title="优秀">
                <input type="radio" name="clean_level" value="1" title="良好" checked>
                <input type="radio" name="clean_level" value="2" title="不及格">
            </c:if>
            <c:if test="${clean.cleanLevel==2}">
                <input type="radio" name="clean_level" value="0" title="优秀">
                <input type="radio" name="clean_level" value="1" title="良好">
                <input type="radio" name="clean_level" value="2" title="不及格" checked>
            </c:if>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">公寓名称</label>
        <div class="layui-input-block">
            <select name="flatsId" id="flats_id" lay-filter="flatsFilter">
                <option value="">请选择</option>
                <c:forEach items="${flats}" var="r">
                    <c:if test="${clean.flatsId==r.id}">
                        <option value="${r.id}" selected>${r.flatsName}</option>
                    </c:if>
                    <c:if test="${clean.flatsId!=r.id}">
                        <option value="${r.id}">${r.flatsName}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">人员姓名</label>
        <div class="layui-input-block">
            <select name="personId" id="personId">
                <option value="">请选择</option>
                <c:forEach items="${persons}" var="r">
                    <c:if test="${clean.personId==r.id}">
                        <option value="${r.id}" selected>${r.name}</option>
                    </c:if>
                    <c:if test="${clean.personId!=r.id}">
                        <option value="${r.id}">${r.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="updateClean">立即保存</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/js/clean/editClean.js"></script>
<script type="text/javascript" src="${ctx }/js/pubilc.js"></script>
</body>
</html>















