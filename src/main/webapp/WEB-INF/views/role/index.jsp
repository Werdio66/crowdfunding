<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<jsp:include page="/WEB-INF/views/common/top.jsp"/>

<div class="container-fluid">
    <div class="row">

        <jsp:include page="/WEB-INF/views/common/sidebar.jsp"/>

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
                                <input id="condition" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button id="doDeleteBatch" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button id="addBtn" type="button" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input id="selectAll" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>

                            <tbody id="tbody_role">

                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <ul id="navg_role" class="pagination">

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

<!-- addModal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">增加角色</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="name">角色名称</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="请输入角色名称">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="saveBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


<!-- updateModal -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">修改角色</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="name">角色名称</label>
                    <input type="text" class="form-control" value="" id="updateName" name="updateName" placeholder="请输入角色名称">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="updateBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- addPermissionModal -->
<div class="modal fade" id="assignPermissionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="#">给角色分配权限</h4>
            </div>
            <div class="modal-body">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="assignPermissionBtn" type="button" class="btn btn-primary">分配</button>
            </div>
        </div>
    </div>
</div>

<%-- 动态包含 --%>
<jsp:include page="/WEB-INF/views/common/js.jsp"/>
<script type="text/javascript">
    // 加载页面
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
        console.log("发起异步请求");
        // 发起异步请求
        initDate(1);
    });

    var json = {
        pageNum : 1,
        pageSize : 3
    };

    var curentPage = 1;
    // 发起异步请求
    function initDate(pageNum) {
        json.pageNum = pageNum;

        // 弹窗的标志
        var flag = -1;
        $.ajax({
            type : "post",
            url : "${PATH}/role/loadDate",
            // 传输的数据json格式
            data : json,
            // 发起请求之前做得事，表单校验等
            beforeSend : function () {
                // 显示图片
                flag = layer.load(0, 10 * 1000);
                console.log("显示图片");
                return true;
            },
            // 处理请求参数
            success : function (result) {
                console.log("回调函数");
                // 初始化数据
                initShow(result);
                // 分页
                initNavg(result);
                // 当数据加载完成后关闭弹窗
                layer.close(flag);
            }
        });
    }
    // ---------------- 展示数据 ------------------------
    function initShow(result) {
        console.log(result);
        // 每次刷新界面前清除之前的页面
        $("#tbody_role").empty();
        // 得到返回结果中的角色对象
        var role = result.list;
        console.log(role);
        // 遍历role集合
        $.each(role, function (i, e) {
            var tr = $('<tr></tr>');
            tr.append('<td>' + (i + 1) + '</td>');
            tr.append('<td><input class="deleteBatchClass" deleteBatchId="' + e.id + '" type="checkbox"></td>');
            tr.append('<td>' + e.name + '</td>');
            var td = $('<td></td>');
            td.append('<button type="button" roleId="'+e.id+'" class="addPermissionClass btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>');
            td.append('<button type="button" roleId="' + e.id + '" class="updateClass btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>');
            td.append('<button type="button" deleteId="' + e.id + '" class="deleteClass btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>');
            tr.append(td);
            tr.appendTo($("tbody"));
        });
    }

    // ---------------- 分页 ------------------------

    // 声明最后一页
    var lastPage = 1;

    function initNavg(result) {
        console.log("分页");
        // 每次刷新界面前清除之前的页面
        $("#navg_role").empty();
        // 导航数组
        var pageSize = result.navigatepageNums;
        console.log("pageSize = ", pageSize);
        // 拼接第一页
        if (!result.isFirstPage) {
            $(".pagination").append('<li><a onclick="initDate(' + result.prePage + ')">上一页</a></li>');
        }else {
            $(".pagination").append('<li class="disabled"><a href="#">上一页</a></li>');
        }
        // 迭代导航页
        $.each(pageSize, function (i, num) {
            if (result.pageNum === num){
                curentPage = num;
                console.log("高亮，当前页 ：", num);
                $(".pagination").append('<li class="active"><a onclick="initDate(' + num + ')">' + num + '<span class="sr-only">(current)</span></a></li>');
            } else {
                console.log("当前页 ：", num);
                $(".pagination").append('<li><a onclick="initDate(' + num + ')">' + num + '</a></li>');
            }
        });
        // 拼接最后一页
        if (!result.isLastPage) {
            $(".pagination").append('<li><a onclick="initDate(' + result.nextPage + ')">下一页</a></li>');
        }else {
            lastPage = result.pageNum;
            console.log("最后一页 = ", lastPage);
            $(".pagination").append('<li class="disabled"><a href="#">下一页</a></li>');
        }
    }

    // 按条件查询
    $("#queryBtn").click(function () {
        // 将输入的条件存放在json中
        json.condition = $("#condition").val();
        initDate(1);
    });

    // ---------------- 增加 ------------------------

    // 点击新增
    $("#addBtn").click(function () {
        // 调用模态框
        $("#addModal").modal({
            backdrop : false,
            keyboard : false
        });
    });

    $("#saveBtn").click(function () {
        // 拿到新增的name
        var name = $("#addModal input[name='name']").val();
        //
        $.ajax({
            type : 'post',
            url : '${PATH}/role/addRole',
            data : {
                name : name
            },
            beforeSend : function () {
                return true;
            },
            success : function (num) {
                if (1 === num) {
                    layer.msg("保存成功！", {time: 1000}, function () {
                        // 关闭弹窗
                        $("#addModal").modal('hide');
                        // 增加完成后删除刚才输入的数据
                        $("#addModal input[name='name']").prop('value', '');
                        // 增加完成后跳转到新增角色的页面
                        initDate(lastPage + 1);
                    });
                }else if (-1 === num) {
                    layer.msg("您输入的名称为空！", {time: 1000});
                }else {
                    layer.msg("保存失败！", {time: 1000});
                }
            }
        })
    });

    // ---------------- 修改 ------------------------

    function openUpdateWindow(){
        // 调用模态框
        $("#updateModal").modal({
            backdrop : false,
            keyboard : false
        });
    }

    // 要回写的值，便于判断用户是否修改
    var roleName = '';
    var roleId = -1;
    var role = {
        id : roleId
    };
    // 给tbody标签中的class属性为updateClass的增加click事件，
    $("tbody").on('click', '.updateClass', function () {
        // 获取自定义属性的值
        role.id = $(this).attr("roleId");
        console.log("role.id = ", role.id);
        // 传递给后台，查询对应的名称
        $.ajax({
            type : 'post',
            url : '${PATH}/role/getRole',
            data : role,
            beforeSend : function () {
                return true;
            },
            success : function (result) {
                // 回调函数
                roleName = result.name;
                console.log('修改的名称 = ', roleName);
                // 回写
                $("#updateName").prop('value', roleName);
                openUpdateWindow();
            }
        });
    });

    // 对模态框中的保存按钮，增加事件
    $("#updateBtn").click(function () {
        // 取到修改后的值
        var name = $("#updateName").val();
        // 将name存到json中
        role.name = name;
        console.log("用户输入的要修改的值 = ", role.name);
        // 将修改的值传到后台
        $.ajax({
           type : 'post',
           url : '${PATH}/role/doUpdate',
           data : role,
            beforeSend : function () {
                // 没有修改
                if (name === roleName) {
                    layer.msg("请确认是否已经修改名称！", {time : 1000});
                    return false;
                }
                console.log("json = ", role);
                return true;
            },
            success: function (result) {
               // 修改成功
                if (result === 1) {
                    layer.msg("修改成功！", {time : 1000});
                    // 关闭模态框
                    $("#updateModal").modal('hide');
                    // 重新刷新页面
                    console.log("当前页 = ", curentPage);
                    initDate(curentPage);
                }else {
                    layer.msg("修改失败", {time : 1000});
                }
            }
        });
    });

    //----------------------------单个删除------------------------------

    // 为所有的删除按钮增加事件
    $("tbody").on('click', '.deleteClass', function () {
        // 拿到当前对象中的id
        var deleteId = $(this).attr('deleteId');
        layer.confirm("是否确定删除？", {btn : ["确定", "取消"]}, function (index) {
            // 确认后
            layer.close(index);
            console.log("删除对象的id = ", deleteId);
            $.ajax({
                type : 'post',
                url : '${PATH}/role/doDelete',
                data : {
                    id : deleteId
                },
                success : function (msg) {
                    if (msg === 1){
                        layer.msg("删除成功！", {time : 1000});
                        // 刷新页面
                        initDate(curentPage);
                    } else {
                        layer.msg("删除失败！", {time : 1000});
                    }
                }
            });

        }, function (index) {
            layer.close(index);
        })
    });


    //----------------------------批量删除------------------------------

    // 对选择框增加事件
    $("#selectAll").click(function () {
        $("tbody input[type = 'checkbox']").prop('checked', this.checked);
    });

    // 点击删除
    $("#doDeleteBatch").click(function () {
        // 得到删除组件
        var list = $("tbody input[type = 'checkbox']:checked");
        // 没有选择
        if (list.length === 0){
            layer.msg("请选择要删除的角色！");
            return false;
        }

        var ids = '';
        var array = [];
        // 遍历组件找到 id
        $.each(list, function (i, num) {
            // 将每个id存到数组中
            array.push($(num).attr("deleteBatchId"));
        });
        // 将数组中的数用逗号分隔，并返回一个字符串
        ids = array.join(',');
        console.log("删除的所有角色的id ：", ids);

        // 使用ajax将id传到后台
        $.ajax({
            type : 'get',
            url : '${PATH}/role/doDeleteBatch',
            data : {
                ids : ids
            },
            success : function (msg) {
                if (msg === 0){
                    layer.msg("删除失败！", {time : 1000});
                }else {
                    layer.msg("删除成功！", {time : 1000});
                    // 刷新页面
                    initDate(curentPage);
                    // 将选中的复选框取消
                    $('#selectAll').prop('checked', '');
                }
            }
        });
    });

    // =============================为角色增加权限================================

    // 为哪个角色分配权限
    var id = -1;

    // 为这个按钮增加点击事件
    $("tbody").on('click', '.addPermissionClass', function () {
        $("#assignPermissionModal").modal({
            backdrop : false,
            keyboard : false
        });
        id = $(this).attr('roleId');
        initPromiseTree();
    });

    // 权限菜单树
    function initPromiseTree() {
        // 配置
        var setting = {
            check: {
				enable: true
			},
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid"
                },
                key : {
                    url : "#",
                    name : 'title'
                }
            },
            view : {
                addDiyDom: function(treeId,treeNode) {
                      $("#"+treeNode.tId+"_ico").removeClass();//.addClass();
                      $("#"+treeNode.tId+"_span").before("<span class='"+treeNode.icon+"'></span>")
                   }
             }

    };
        // 异步请求加载树
        $.get('${PATH}/permission/loadPermissionTree', {}, function(treeList) {
                $.fn.zTree.init($("#treeDemo"), setting, treeList);
                //展开所有节点
                var ztreeObj = $.fn.zTree.getZTreeObj("treeDemo");
                ztreeObj.expandAll(true);

                // 为了确保回显准确性，需要将回显请求放到树加载完成之后
                // 因为俩个异步请求，将相当于俩个线程，不能保证先后顺序
            // 回显以分配权限
            $.get('${PATH}/role/getPermissionIdByRoleId?roleId=' + id, function (list) {
                console.log('回显哪个角色的权限 id = ', id);
                console.log('回显的 ids = ', list);
                $.each(list, function (i, permissionId) {
                    // 根据结点的属性值查找结点
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    var node = treeObj.getNodeByParam("id", permissionId, null);
                    // 勾选结点
                    treeObj.checkNode(node, true, false);
                })
            })
        });

    }

    // 分配权限
    $("#assignPermissionBtn").click(function () {
        console.log('分配权限的角色 id = ', id);
        // 拿到树对象
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        // 获取被选的树结点
        var nodes = treeObj.getCheckedNodes(true);
        if (nodes.length === 0){
            layer.msg('请选择要分配的权限！');
            return false;
        }
        var json = {
          roleId : id
        };
        // 遍历取出id
        $.each(nodes, function (i, permission) {
            // 将id以数组形式存放到json中
            json['ids[' + i + ']'] = permission.id;
            // ids.push(permission.id);
        });
        console.log('json = ', json);
        $.ajax({
            type : 'post',
            url : '${PATH}/role/assignPermission',
            data : json,
            success : function (msg) {
                if (msg === nodes.length){
                    layer.msg('分配成功！', {time : 1000});
                    $("#assignPermissionModal").modal('hide');
                }else {
                    layer.msg('分配失败！', {time : 1000});
                }
            }
        })
    });

</script>
</body>
</html>
