<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>类目列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx!}/hadmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>类目列表</h5>
                </div>

                <div class="container-fluid" style="margin-top: 15px;">

                    <form class="form-horizontal m-t" id="frm" method="post">

                        <div class="col-xs-4">
                            <label class="col-sm-4 control-label">状态:</label>
                            <div class="col-sm-8">
                                <select id="status" name="status" class="form-control">
                                    <#list infos as c>
                                        <option value="${c.dictCode}">${c.info}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                </div>


                </form>
                <br/>
                <div class="ibox-content">

                    <div id="toolbar-btn" class="btn-group pull-left" style="padding-bottom:10px;">
                        <button id="btn_add" onclick="add()" type="button" class="btn btn-info btn-space">
                            <span class="fa fa-search" aria-hidden="true" class="btn-icon-space"></span>
                            添加
                        </button>
                        <br/>
                    </div>
                    <div id="toolbar-btn" class="btn-group pull-right" style="padding-bottom:10px;">
                        <button id="btn_add" onclick="search()" type="button" class="btn btn-info btn-space">
                            <span class="fa fa-search" aria-hidden="true" class="btn-icon-space"></span>
                            查询
                        </button>
                        <br/>
                    </div>
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <!-- Example Card View -->
                            <div class="example-wrap">
                                <div class="example">
                                    <table id="table_list"></table>
                                </div>
                            </div>
                            <!-- End Example Card View -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <#include "/admin/common/common.ftl">
    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function () {
            //初始化表格,动态从服务器加载数据
            $("#table_list").bootstrapTable({
                //使用get请求到服务器获取数据
                method: "GET",
                //必须设置，不然request.getParameter获取不到请求参数
                contentType: "application/x-www-form-urlencoded",
                //获取数据的Servlet地址
                url: "${ctx!}/admin/category/list",
                //表格显示条纹
                striped: true,
                //启动分页
                pagination: true,
                //每页显示的记录数
                pageSize: 10,
                //当前第几页
                pageNumber: 1,
                //记录数可选列表
                pageList: [5, 10, 15, 20, 25],
                //是否启用详细信息视图
//                detailView: true,
//                detailFormatter: detailFormatter,
                //表示服务端请求
                sidePagination: "server",
                //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                //设置为limit可以获取limit, offset, search, sort, order
                queryParamsType: "undefined",
                queryParams: function (params) {
                    params.status = $("#status").val();
                    return params;
                },
                //json数据解析
                responseHandler: function (res) {
                    return {
                        "rows": res.list,
                        "total": res.total
                    };
                },
                //数据列
                columns: [{
                    title: "类目名称",
                    field: "name"
                }, {
                    title: "状态",
                    field: "status"
                }, {
                    title: "创建时间",
                    field: "createTime",
                    sortable: true
                }, {
                    title: "修改时间时间",
                    field: "updateTime",
                    sortable: true
                }, {
                    title: "操作",
                    field: "empty",
                    formatter: function (value, row, index) {
                        var operateHtml = "";
                        operateHtml = operateHtml + '<@shiro.hasPermission name="admin:category:edit"><button class="btn btn-primary btn-xs" type="button" onclick="edit(\'' + row.id + '\')"><i class="fa fa-remove"></i>&nbsp;编辑</button> &nbsp;</@shiro.hasPermission>';

                        if (row.sInt == 0) {
                            operateHtml = operateHtml + '<@shiro.hasPermission name="admin:category:up"><button class="btn btn-success btn-xs" type="button" onclick="onSale(\'' + row.id + '\')"><i class="fa fa-remove"></i>&nbsp;上架</button> &nbsp;</@shiro.hasPermission>';
                        } else if (row.sInt == 1) {
                            operateHtml = operateHtml + '<@shiro.hasPermission name="admin:category:down"><button class="btn btn-info btn-xs" type="button" onclick="offSale(\'' + row.id + '\')"><i class="fa fa-remove"></i>&nbsp;下线</button> &nbsp;</@shiro.hasPermission>';

                        }
                        operateHtml = operateHtml + '<@shiro.hasPermission name="admin:category:delete"><button class="btn btn-danger btn-xs" type="button" onclick="del(\'' + row.id + '\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;</@shiro.hasPermission>';


                        return operateHtml;
                    }
                }]
            });
        });

        function add() {
            layer.open({
                type: 2,
                title: '类目添加',
                shadeClose: true,
                shade: [0.5, '#ccc'],
                area: ['700px', '600px'],
                content: '${ctx!}/admin/category/add',
                end: function (index) {
                    $('#table_list').bootstrapTable("refresh");
                }
            });
        }

        function edit(id) {
            layer.open({
                type: 2,
                title: '类目编辑',
                shadeClose: true,
                shade: false,
                area: ['650px', '600px'],
                content: '${ctx!}/admin/category/edit/' + id,
                end: function (index) {
                    $('#table_list').bootstrapTable("refresh");
                }
            });
        }


        function onSale(id) {
            layer.confirm('确定恢复此类目吗?', {icon: 3, title: '提示'}, function (index) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/category/up/" + id + "/1",
                    success: function (msg) {
                        layer.msg(msg.message, {time: 2000}, function () {
                            $('#table_list').bootstrapTable("refresh");
                            layer.close(index);
                        });
                    }
                });
            });
        }

        function offSale(id) {
            layer.confirm('确定下架此类目吗?', {icon: 3, title: '提示'}, function (index) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/category/down/" + id + "/2",
                    success: function (msg) {
                        layer.msg(msg.message, {time: 2000}, function () {
                            $('#table_list').bootstrapTable("refresh");
                            layer.close(index);
                        });
                    }
                });
            });
        }

        function del(id) {
            layer.confirm('确定删除此类目吗?', {icon: 3, title: '提示'}, function (index) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/category/del/" + id,
                    success: function (msg) {
                        layer.msg(msg.message, {time: 2000}, function () {
                            $('#table_list').bootstrapTable("refresh");
                            layer.close(index);
                        });
                    }
                });
            });
        }

        function search() {
            $("#table_list").bootstrapTable('refresh');

        }
    </script>


</body>

</html>
