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
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='form.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
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
            tr.append('<td><input type="checkbox"></td>');
            tr.append('<td>' + e.name + '</td>');
            var td = $('<td></td>');
            td.append('<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>');
            td.append('<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>');
            td.append('<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>');
            tr.append(td);
            tr.appendTo($("tbody"));
        });
    }

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
            $(".pagination").append('<li class="disabled"><a href="#">下一页</a></li>');
        }
    }

    $("#queryBtn").click(function () {
        // 将输入的条件存放在json中
        json.condition = $("#condition").val();
        initDate(1);
    });

</script>
</body>
</html>
