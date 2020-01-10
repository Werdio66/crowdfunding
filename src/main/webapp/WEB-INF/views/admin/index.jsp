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

                    <form id="queryForm" class="form-inline" role="form" style="float:left;" action="${PATH}/admin/index" method="post">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" name="condition" value="${param.condition}" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning" onclick="$('#queryForm').submit()"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>

                    <button id="doDeleteBatch" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${PATH}/admin/toAdd'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox" id="selectAll"></th>
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
                                    <td><input type="checkbox" adminId="${admin.id}"></td>
                                    <td>${admin.loginacct}</td>
                                    <td>${admin.username}</td>
                                    <td>${admin.email}</td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
                                        <button type="button" class="btn btn-primary btn-xs" onclick="window.location.href='${PATH}/admin/toUpdate?id=${admin.id}&pageNum=${adminPage.pageNum}'"><i class="glyphicon glyphicon-pencil"></i></button>
                                        <button type="button" adminId="${admin.id}" class="deleteAdmin  btn btn-danger btn-xs" onclick=""><i class="glyphicon glyphicon-remove"></i></button>
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
                                            <li><a href="${PATH}/admin/index?condition=${param.condition}&pageNum=${adminPage.prePage}">上一页</a></li>
                                        </c:if>

                                        <c:forEach items="${adminPage.navigatepageNums}" var="num">
                                            <c:if test="${adminPage.pageNum == num}">
                                                <li class="active"><a href="${PATH}/admin/index?condition=${param.condition}&pageNum=${num}">${num}<span class="sr-only">(current)</span></a></li>
                                            </c:if>
                                            <c:if test="${adminPage.pageNum != num}">
                                                 <li><a href="${PATH}/admin/index?condition=${param.condition}&pageNum=${num}">${num} </a></li>
                                            </c:if>
                                        </c:forEach>


                                        <c:if test="${adminPage.isLastPage}">
                                            <li class="disabled"><a href="#">下一页</a></li>
                                        </c:if>
                                        <c:if test="${!adminPage.isLastPage}">
                                            <li><a href="${PATH}/admin/index?condition=${param.condition}&pageNum=${adminPage.nextPage}">下一页</a></li>
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

    // 当点击时选中所有的用户
    $("#selectAll").click(function () {
        // 对tbody标签中的input标签中type为checkbox的标签，设置属性，attr用于自定义属性
        // $("tbody input[type = 'checkbox']").attr('checked', this.checked);
        $("tbody input[type = 'checkbox']").prop('checked', this.checked);
    });

    // 批量删除
    $("#doDeleteBatch").click(function () {
        // 拿到所有选中的用户
        var checkboxList = $("tbody input[type = 'checkbox']:checked");
        console.log(checkboxList);
        // 没有选择删除对象
        if (checkboxList.length === 0) {
            layer.msg("请选择要删除的用户！", [btn = 5], 1000);
            return false;
        }

        var ids = '';
        var array = [];
        // 对集合进行遍历
        $.each(checkboxList, function (i, e) {
            // 得到每一个选中的box绑定的 id
            var adminId = $(e).attr("adminId");
            /*// 将id封装成字符串
            if (i !== 0){
                ids += ',';
            }
            ids += adminId;*/
            array.push(adminId);
        });
        ids = array.join(",");
        console.log(ids);
        // 跳转
        window.location.href = '${PATH}/admin/doDeleteBatch?pageNum=${adminPage.pageNum}&ids=' + ids;
    });

    // 处理删除
    $(".deleteAdmin").click(function () {

        var id = $(this).attr("adminId");
        layer.confirm("是否确定删除？", {btn : ["确定", "取消"]}, function (index) {
            // 确认后
            window.location.href = '${PATH}/admin/doDelete?pageNum=${adminPage.pageNum}&id=' + id;
            layer.close(index);
        }, function (index) {
            layer.close(index);
        })
    });

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
</script>
</body>
</html>
