<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${ctx}/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx }/css/list.css" media="all" />
    <link rel="stylesheet" href="${ctx }/css/font_eolqem241z66flxr.css"
          media="all" />
    <script>
        var ctx = "${ctx}";
    </script>
</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote news_search">
    <form class="layui-form">
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="text" id="name" value="" placeholder="请输入人员姓名"
                       class="layui-input search_input " style="margin-left: 10px">
            </div>

                <div class="layui-input-inline">
                    <select name="companyId" id="companyId">
                        <option value="">请选择所属公司</option>
                        <c:forEach items="${companies}" var="c">
                            <option value="${c.id}">${c.companyName}</option>
                        </c:forEach>
                    </select>
                </div>
            <div class="layui-input-inline">
                <select name="flatsId" id="flatsId">
                    <option value="">请选择所属公寓</option>
                    <c:forEach items="${flats}" var="d">
                        <option value="${d.id}">${d.flatsName}</option>
                    </c:forEach>
                </select>
            </div>

            
        </div>
       
            <a style="margin-left: 10px" class="layui-btn search_btn" lay-submit="" data-type="search"
               lay-filter="search">查询</a>
            <div class="layui-inline" style="margin-left: 10px">
                <a class="layui-btn layui-btn-normal personAdd_btn">添加人员</a>
            </div>
        </div>
    </form>
</blockquote>
    <div class="layui-form">
        <table id="personList" lay-filter="personList"></table>
    </div>

    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-sm" lay-event="edit">
            <i class="layui-icon">&#xe642;</i>
        </a>
        <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">
            <i class="layui-icon">&#xe640;</i>
        </a>
    </script>
    <script type="text/javascript" src="${ctx }/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx }/js/person/personList.js"></script>
    <script type="text/javascript" src="${ctx }/js/pubilc.js"></script>

</body>




</html>