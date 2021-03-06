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
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 菜单树</h3>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>

                </div>
            </div>
        </div>
    </div>
</div>

    <!-- addModal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">增加菜单</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="name">菜单名称</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="请输入菜单名称">
                </div>
                <div class="form-group">
                    <label for="name">菜单url</label>
                    <input type="text" class="form-control" id="url" name="url" placeholder="请输入菜单url">
                </div>
                <div class="form-group">
                    <label for="name">菜单图标</label>
                    <input type="text" class="form-control" id="icon" name="icon" placeholder="请输入菜单图标">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="saveBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

        <!-- addModal -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">修改菜单</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="name">菜单名称</label>
                    <input type="text" class="form-control" id="updateName" name="name" placeholder="请输入菜单名称">
                </div>
                <div class="form-group">
                    <label for="name">菜单url</label>
                    <input type="text" class="form-control" id="updateUrl" name="url" placeholder="请输入菜单url">
                </div>
                <div class="form-group">
                    <label for="name">菜单图标</label>
                    <input type="text" class="form-control" id="updateIcon" name="icon" placeholder="请输入菜单图标">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="updateBtn" type="button" class="btn btn-primary">修改</button>
            </div>
        </div>
    </div>
</div>
<%-- 动态包含 --%>
<jsp:include page="/WEB-INF/views/common/js.jsp"/>

<script type="application/javascript">

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
        initTree();
    });

    function initTree() {
        // 配置
        var setting = {
            data: {
                simpleData: {
                     enable: true,
                     idKey: "id",
                     pIdKey: "pid"
                },
                key : {
                    url : "#"
                }
            },
            view : {
                addDiyDom: function(treeId,treeNode) {
                      $("#"+treeNode.tId+"_ico").removeClass();//.addClass();
                      $("#"+treeNode.tId+"_span").before("<span class='"+treeNode.icon+"'></span>")
                   },
                addHoverDom: function(treeId, treeNode){
							var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
							// aObj.attr("href", "#");
							if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
							var s = '<span id="btnGroup'+treeNode.tId+'">';
							if ( treeNode.level === 0 ) { // 根结点
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="addBtn('+treeNode.id+')">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level === 1 ) {    // 分支结点
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="updateBtn('+treeNode.id+')" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								if (treeNode.children.length === 0) { // 没有子结点
									s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="deleteBtn('+treeNode.id+')" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
								}
								// 有子结点
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="addBtn('+treeNode.id+')" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level === 2 ) { // 叶子结点
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="updateBtn('+treeNode.id+')" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="deleteBtn('+treeNode.id+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
							}

							s += '</span>';
							aObj.after(s);
						},
						removeHoverDom: function(treeId, treeNode){
							$("#btnGroup"+treeNode.tId).remove();
						}

             }

    };
        // 异步请求加载树
        $.get('${PATH}/menu/loadTree', {}, function(treeList) {
                treeList.push({"id":0,"pid":0,"name":"根菜单","icon" : "glyphicon glyphicon-th-list"});
                $.fn.zTree.init($("#treeDemo"), setting, treeList);
                //展开所有节点
                var ztreeObj = $.fn.zTree.getZTreeObj("treeDemo");
                ztreeObj.expandAll(true);

        });
    }


    // ==========================增加菜单========================================

    // 点击弹窗
    function addBtn(pid) {
        console.log('addBtn');

        $("#addModal").modal({
            show : true,
            backdrop : false,
            keyboard : false
        });
        // 点击保存
    $("#saveBtn").click(function() {
        var name = $("#addModal input[name='name']").val();
        var url = $("#addModal input[name='url']").val();
        var icon = $("#addModal input[name='icon']").val();
        console.log(name, '   ', url, '    ', icon, '     ', pid);
        var json = {
            pid : pid,
            name : name,
            url : url,
            icon : icon
        };
        // 发送到后端
        $.ajax({
            type : 'post',
            url : '${PATH}/menu/addMenu',
            data : json,
            beforeSend : function() {
                if (!name || !url || !icon) {
                    layer.msg("数据不能为空，请填空完整！", {time : 1000});
                    return false;
                }
                return true;
            },
            success : function(result) {
                if (result === 1){
                    layer.msg("增加成功！", {time : 1000});
                    // 关闭弹窗
                    $("#addModal").modal('hide');
                    // 重新刷新界面
                    initTree();
                }else {
                    layer.msg("增加失败！", {time : 1000});
    }
            }
        });
    });
    }


    // ==========================修改菜单========================================
    var updateId = -1;
    function updateBtn(id) {
        updateId = id;
        $.ajax({
            type : 'get',
            url : '${PATH}/menu/getMenuById',
            data : {
                id : id
            },
            success : function(result) {
                console.log("查询的菜单 ：", result);
                var name = result.name;
                var url = result.url;
                var icon = result.icon;
                showUpdateView(name, url, icon);
                console.log("name = ", name);
                console.log("url = ", url);
                console.log("icon = ", icon);
            }
        })
    }
    var oldName = '';
    var oldUrl = '';
    var oldIcon = '';
    function showUpdateView(name, url, icon) {
        oldName = name;
        oldUrl = url;
        oldIcon = icon;
        // 回写
        $("#updateName").prop('value', name);
        $("#updateUrl").prop('value', url);
        $("#updateIcon").prop('value', icon);
        // 弹窗
        $("#updateModal").modal({
            backdrop : false,
            keyboard : false
        });
    }

    $("#updateBtn").click(function() {
        var name = $("#updateModal input[name='name']").val();
        var url = $("#updateModal input[name='url']").val();
        var icon = $("#updateModal input[name='icon']").val();
        console.log("oldName", oldName);
        console.log("oldUrl", oldUrl);
        console.log("oldIcon", oldIcon);
        if (name === oldName && url === oldUrl && icon === oldIcon){
            layer.msg("请确认是否已经修改", {time : 1400});
            return false;
        }
        var menuJson = {
            id : updateId,
            name : name,
            url : url,
            icon : icon
        };
        console.log(menuJson);
        $.ajax({
            type : 'post',
            url : '${PATH}/menu/updateMenu',
            data : menuJson,
            success : function(msg) {
                if (msg === 1){
                    layer.msg("修改成功", {time : 1400});
                    // 关闭弹窗
                    $("#updateModal").modal('hide');
                    // 删除缓存的值
                    $("#updateName").prop('value', '');
                    $("#updateUrl").prop('value', '');
                    $("#updateIcon").prop('value', '');
                    initTree();
                }
            }
        })
    });
    // ==========================删除菜单========================================

    function deleteBtn(id) {
        //
        console.log("删除的 id = ", id);
        layer.confirm("请确认是否删除？", {btn : ['确定', '取消']}, function(index) {
        // 确认删除后继续执行
        layer.close(index);
        $.ajax({
            type : 'get',
            url : '${PATH}/menu/deleteById',
            data : {
                id : id
            },
            success : function(msg) {
                if (msg === 1){
                    layer.msg("删除成功！", {time : 1000});
                    // 刷新菜单
                    initTree();
                }else {
                    layer.msg("删除失败！", {time : 1000});
                    initTree();
                }
            }
        })
        }, function(index) {
        // 关闭窗口
        layer.close(index);
        });
    }

</script>
</body>
</html>
