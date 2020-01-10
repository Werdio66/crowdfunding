<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <%-- 静态包含css页面 --%>
    <%@include file="/WEB-INF/views/common/css.jsp"%>
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
    </style>
</head>

<body>

<%@include file="/WEB-INF/views/common/top.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/views/common/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${PATH}/admin/toAdd'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${adminPage.list}" var="admin" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td><input type="checkbox"></td>
                                    <td>${admin.loginacct}</td>
                                    <td>${admin.username}</td>
                                    <td>${admin.email}</td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
                                        <button type="button" class="btn btn-danger btn-xs" onclick="window.location.href='${PATH}/admin/toUpdate?id=${admin.id}&pageNum=${adminPage.pageNum}'"><i class="glyphicon glyphicon-pencil"></i></button>
                                        <button type="button" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-remove"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <ul class="pagination">

                                        <c:if test="${adminPage.isFirstPage}">
                                            <li class="disabled"><a href="#">上一页</a></li>
                                        </c:if>
                                        <c:if test="${!adminPage.isFirstPage}">
                                            <li><a href="${PATH}/admin/index?pageNum=${adminPage.prePage}">上一页</a></li>
                                        </c:if>

                                        <c:forEach items="${adminPage.navigatepageNums}" var="num">
                                            <c:if test="${adminPage.pageNum == num}">
                                                <li class="active"><a href="${PATH}/admin/index?pageNum=${num}">${num}<span class="sr-only">(current)</span></a></li>
                                            </c:if>
                                            <c:if test="${adminPage.pageNum != num}">
                                                 <li><a href="${PATH}/admin/index?pageNum=${num}">${num}</a></li>
                                            </c:if>
                                        </c:forEach>


                                        <c:if test="${adminPage.isLastPage}">
                                            <li class="disabled"><a href="#">下一页</a></li>
                                        </c:if>
                                        <c:if test="${!adminPage.isLastPage}">
                                            <li><a href="${PATH}/admin/index?pageNum=${adminPage.nextPage}">下一页</a></li>
                                        </c:if>

                                    </ul>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- 动态包含 --%>
<jsp:include page="/WEB-INF/views/common/js.jsp"/>
<script type="text/javascript">

    $("")

    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });
    $("tbody .btn-success").click(function(){
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function(){
        window.location.href = "edit.html";
    });
</script>
</body>
</html>
