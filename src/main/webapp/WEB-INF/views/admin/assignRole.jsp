<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
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
	</style>
  </head>

  <body>

    <%@include file="/WEB-INF/views/common/top.jsp"%>

    <div class="container-fluid">
      <div class="row">
        <%@include file="/WEB-INF/views/common/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">分配角色</li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
				<form role="form" class="form-inline">
				  <div class="form-group">
					<label for="exampleInputPassword1">未分配角色列表</label><br>
					<select id="unAssignList" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">
						<c:forEach items="${unAssignedList}" var="role">
							<option value="${role.id}">${role.name}</option>
						</c:forEach>
                    </select>
				  </div>
				  <div class="form-group">
                        <ul>
                            <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                            <br>
                            <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                        </ul>
				  </div>
				  <div class="form-group" style="margin-left:40px;">
					<label for="">已分配角色列表</label><br>
					<select id="assignList" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">
						<c:forEach items="${assignedList}" var="role">
							<option value="${role.id}">${role.name}</option>
						</c:forEach>
                    </select>
				  </div>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
  <%-- 动态包含 --%>
<jsp:include page="/WEB-INF/views/common/js.jsp"/>
        <script type="text/javascript">
			// 分配角色
			$("#toRightBtn").click(function () {
				var unAssignList = $("#unAssignList option:selected");
				console.log(unAssignList);
				var ids = [];
				$.each(unAssignList, function (i, role) {
					ids.push(role.value);
				});
				// 加入分隔符，并转换为字符串类型
				var strId = ids.join(',');
				console.log('要分配角色的 id = ', ids);

				var json = {
					id : ${id},
					ids : strId
				};
				console.log("json = ", json);
				// 将要分配角色的id交给后台
				$.ajax({
					type : 'post',
					url : '${PATH}/admin/assignRole',
					data : json,
					beforeSend : function () {
						if (ids.length === 0){
							layer.msg("请选择要分配的角色！", {time : 1000});
							return false;
						}
						return  true;
					},
					success : function (count) {
						if (count === ids.length){
							layer.msg("分配成功！");
							$("#assignList").append(unAssignList.clone());
							unAssignList.remove();
						}else {
							layer.msg("分配失败！");
						}
					}
				})
			});
			
			// 取消分配
			$("#toLeftBtn").click(function () {
				// 拿到要取消分配的元素
				var assignList = $("#assignList option:selected");
				var ids = [];
				// 遍历找到要取消分配的角色的id
				$.each(assignList, function (i, role) {
					ids.push(role.value);
				});
				// 将数组转换为字符串
				var idList = ids.join(",");
				// 要传递的数据
				var json = {
					id : ${id},
					ids : idList
				};
				// 发送到后台处理
				$.ajax({
					type : 'post',
					url : '${PATH}/admin/unAssignRole',
					data : json,
					beforeSend : function () {
						if (ids.length === 0){
							layer.msg("请选择要取消分配的角色！", {time : 1000});
							return false;
						}
						return  true;
					},
					success : function (count) {
						if (count === ids.length){
							layer.msg("取消分配成功！", {time : 1000});
							// 将取消分配的角色的克隆移动到已分配
							$("#unAssignList").append(assignList.clone());
							// 删除移动前的角色
							assignList.remove();
						}else {
							layer.msg("取消分配失败！", {time : 1000});
						}
					}
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

