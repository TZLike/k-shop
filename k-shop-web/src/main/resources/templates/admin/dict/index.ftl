<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>数据字典</title>
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
                    <h5>数据字典管理</h5>
                </div>

                <div class="container-fluid">

                    <form class="form-horizontal m-t" id="frm" method="post">
                        <div class="row">

                            <div class="col-md-4">
                                <label class="col-sm-4 control-label">字典名称:</label>
                                <div class="col-sm-8">
                                    <input id="typeName" name="typeName" class="form-control" type="text"
                                           placeholder="请输入字典名称">
                                </div>
                            </div>


                        </div>
                    </form>
                    <br/>

                    <div class="ibox-content">
                        <div id="toolbar-btn" class="btn-group pull-right" style="padding-bottom:10px;">

                            <button id="btn_save" onclick="search()" class="btn btn-primary" type="submit">
                                <span class="fa fa-search" aria-hidden="true" class="btn-icon-space"></span>
                                查询
                            </button>

                        </div>

                        <div id="toolbar-btn" class="btn-group pull-left" style="padding-bottom:10px;">
<#--                            <@shiro.hasPermission name="system:dict:add">-->
                                <button id="btn_add" onclick="add()" type="button" class="btn btn-info btn-space">
                                    <span class="fa fa-plus-square" aria-hidden="true" class="btn-icon-space"></span>
                                    添加
                                </button>
<#--                            </@shiro.hasPermission>-->

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
    </div>

    <!-- 全局js -->
    <#include "/admin/common/common.ftl">
    <!-- 自定义js -->
    <script src="${ctx!}/hadmin/js/content.js?v=${version!}"></script>
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
                url: "${ctx!}/admin/dict/list",
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
                detailView: true,
                //表示服务端请求
                sidePagination: "server",
                detailFormatter: detailFormatter,
                //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                //设置为limit可以获取limit, offset, search, sort, order
                queryParamsType: "undefined",
                queryParams: function (params) {
                    params.name = $("#typeName").val();
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
                    title: "字典编码",
                    field: "typeCode"
                }, {
                    title: "字典名称",
                    field: "typeName"
                }, {
                    title: "创建时间",
                    field: "createTime",
                    sortable: true
                }, {
                    title: "修改时间",
                    field: "updateTime",
                    sortable: true
                }, {
                    title: "操作",
                    field: "empty",
                    formatter: function (value, row, index) {
                        <#--var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="detail(\'' + row.id + '\')"><i class="fa fa-edit"></i>&nbsp;查看</button> &nbsp;';-->
                        <#--operateHtml = operateHtml + '<@shiro.hasPermission name="system:dict:delete"><button class="btn btn-danger btn-xs" type="button" onclick="del(\'' + row.id + '\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;</@shiro.hasPermission>';-->
                        <#--operateHtml = operateHtml + '<@shiro.hasPermission name="system:dict:edit"><button class="btn btn-info btn-xs" type="button" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i>&nbsp;编辑</button> &nbsp;</@shiro.hasPermission>';-->
                        <#--return operateHtml;-->
                        var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="detail(\'' + row.id + '\')"><i class="fa fa-edit"></i>&nbsp;查看</button> &nbsp;';
                        operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del(\'' + row.id + '\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;';
                        operateHtml = operateHtml + '<button class="btn btn-info btn-xs" type="button" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i>&nbsp;编辑</button> &nbsp;';
                        return operateHtml;
                    }
                }]
            });
        });

        function add() {
            layer.open({
                type: 2,
                title: '字典项管理',
                shadeClose: false,
                shade: [0.5, '#ccc'],
                area: ['550px', '420px'],
                content: '${ctx!}/admin/dict/add',
                end: function (index) {
                    $('#table_list').bootstrapTable("refresh");
                }
            });
        }

        function edit(id) {
            layer.open({
                type: 2,
                title: '类别修改',
                shadeClose: false,
                shade: [0.5, '#ccc'],
                area: ['550px', '420px'],
                content: '${ctx!}/admin/dict/edit/' + id,
                end: function (index) {
                    $('#table_list').bootstrapTable("refresh");
                }
            });
        }

        function detail(id) {
            layer.open({
                type: 2,
                title: '字典明细',
                shadeClose: false,
                shade: [0.5, '#ccc'],
                area: ['750px', '580px'],
                content: '${ctx!}/admin/dict/dictInfo?id=' + id,
                end: function (index) {
                    $('#table_list').bootstrapTable("refresh");
                }
            });
        }

        function del(id) {
            layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/dict/delete/" + id,
                    success: function (msg) {
                        layer.msg(msg.meta.message, {time: 2000}, function () {
                           if(msg.meta.code==1){
                               $('#table_list').bootstrapTable("refresh");
                               layer.close(index);
                           }
                        });
                    }
                });
            });
        }

        function search() {

            $("#table_list").bootstrapTable('refresh');

        }

        function detailFormatter(index, row) {
            var html = [];
            html.push('<p><b>备注:</b> ' + row.remark + '</p>');
            return html.join('');
        }

    </script>


</body>

</html>
