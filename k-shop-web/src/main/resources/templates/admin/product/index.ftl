<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户列表</title>
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
                    <h5>商品列表</h5>
                </div>

                <div class="container-fluid" style="margin-top: 15px;">

                    <form class="form-horizontal m-t" id="frm" method="post">
                        <div class="row">

                            <div class="col-xs-4">
                                <label class="col-sm-4 control-label">商品名称:</label>
                                <div class="col-sm-7">
                                    <input id="productName" name="productName" class="form-control" type="text"
                                           placeholder="请输入商品名称">
                                </div>
                            </div>

                            <div class="col-xs-4">
                                <label class="col-sm-4 control-label">所属类目:</label>
                                <div class="col-sm-8">
                                    <select id="categoryNo" name="categoryNo" class="form-control">
                                        <option value="">请选择..</option>
                                    <#list categoryList as category>
                                        <option value="${category.categoryNo}">${category.name}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="col-xs-4">
                                <label class="col-sm-4 control-label">商品上架状态:</label>
                                <div class="col-sm-8">
                                    <select id="productStatus" name="productStatus" class="form-control">
                                        <option value="">请选择..</option>
                                    <#list productStatus as dictInfo>
                                        <option value="${dictInfo.dictCode}">${dictInfo.info}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                        </div>

                        <br/>
                        <div class="row">

                            <div class="col-xs-4">
                                <label class="col-sm-4 control-label">商品销售平台:</label>
                                <div class="col-sm-7">
                                    <select id="productSalesType" name="productSalesType" class="form-control">
                                        <option value="">请选择..</option>
                                    <#list productSales as dictInfo>
                                        <option value="${dictInfo.dictCode}">${dictInfo.info}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="col-xs-4">
                                <label class="col-sm-4 control-label">是否推荐商品:</label>
                                <div class="col-sm-7">
                                    <select id="productIfRecommend" name="productIfRecommend" class="form-control">
                                        <option value="">请选择..</option>
                                    <#list productIfRecommends as dictInfo>
                                        <option value="${dictInfo.dictCode}">${dictInfo.info}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="col-xs-4">
                                <label class="col-sm-4 control-label">可否开发票:</label>
                                <div class="col-sm-8">
                                    <select id="productIfCanInvoice" name="productIfCanInvoice" class="form-control">
                                        <option value="">请选择..</option>
                                    <#list productIfCanInvoices as dictInfo>
                                        <option value="${dictInfo.dictCode}">${dictInfo.info}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                        </div>

                        <br/>
                        <div class="row">

                            <div class="col-xs-4">
                                <label class="col-sm-4 control-label">商品类型:</label>
                                <div class="col-sm-7">
                                    <select id="productGoodsType" name="productGoodsType" class="form-control">
                                        <option value="">请选择..</option>
                                    <#list productGoodsTypes as dictInfo>
                                        <option value="${dictInfo.dictCode}">${dictInfo.info}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="col-xs-4">

                            </div>

                            <div class="col-xs-4">

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
                    method: "POST",
                    //必须设置，不然request.getParameter获取不到请求参数
                    contentType: "application/x-www-form-urlencoded",
                    //获取数据的Servlet地址
                    url: "${ctx!}/mall/product/list",
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
                detailFormatter: detailFormatter,
                    //表示服务端请求
                    sidePagination: "server",
                    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                    //设置为limit可以获取limit, offset, search, sort, order
                    queryParamsType: "undefined",
                    queryParams: function (params) {
                        params.productId = $("#productId").val();
                        params.productName = $("#productName").val();
                        params.productStatus = $("#productStatus").val();
                        params.categoryNo = $("#categoryNo").val();
                        params.productIfRecommend = $("#productIfRecommend").val();
                        params.productSalesType = $("#productSalesType").val();
                        params.productIfCanInvoice = $("#productIfCanInvoice").val();
                        params.productGoodsType = $("#productGoodsType").val();
                        return params;
                    },
                    //json数据解析
                    responseHandler: function (res) {
                        return {
                            "rows": res.list,
                            "total": res.navigatePages
                        };
                    },
                    //数据列
                    columns: [{
                        title: "商品名称",
                        field: "productName"
                    }, {
                        title: "所属类目",
                        field: "categoryName"
//                        width: "60px"

                    }, {
                        title: "库存",
                        field: "productStock"
//                        width: "30px"

                    }, {
                        title: "状态",
                        field: "productStatusText",
//                        width: "35px"
                        formatter: function (value, row, index) {
                            if (row.productStatus == '0')
                                return '<span class="label label-info">' + value + '</span>';
                            return '<span class="label label-danger">' + value + '</span>';
                        }
                    }, {
                        title: "商品图片",
                        field: "productImage",
                        formatter: function (value, row, index) {
                            var operateHtml = '<img width="120" height="95" src=' + value + '>';
                            return operateHtml;
                        }
                    },
                        {
                            title: "单价(元)",
                            field: "productOriginalPrice",
//                            width: "40px"
                            formatter: function (value, row, index) {
                                return value / 100;
                            }
                        }, {
                            title: "现价(元)",
                            field: "productNowPrice",
//                            width: "40px"
                            formatter: function (value, row, index) {
                                return value / 100;
                            }
                        },
                        {
                            title: "可否开发票",
                            field: "productIfCanInvoiceText"
                        },
                        {
                            title: "商品类型",
                            field: "productGoodsTypeText"
                        },
                        {
                            title: "推荐商品",
                            field: "productIfRecommendText"
                        },
                        {
                            title: "销售平台",
                            field: "productSalesTypeText"
                        },
                        {
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
                                var operateHtml = '<@shiro.hasPermission name="admin:product:edit"><button class="btn btn-primary btn-xs" type="button" onclick="detail(\''+ row.productId+'\')"><i class="fa fa-edit"></i>&nbsp;编辑</button> &nbsp;</@shiro.hasPermission>';
                                if (row.productStatus == 3 || row.productStatus == 2) {
                                    operateHtml = operateHtml + '<@shiro.hasPermission name="admin:product:up"><button class="btn btn-success btn-xs" type="button" onclick="onSale(\''+ row.productId+'\')"><i class="fa fa-remove"></i>&nbsp;上架</button> &nbsp;</@shiro.hasPermission>';
                                } else if (row.productStatus == 1) {
                                    operateHtml = operateHtml + '<@shiro.hasPermission name="admin:product:down"><button class="btn btn-info btn-xs" type="button" onclick="offSale(\''+ row.productId+'\')"><i class="fa fa-remove"></i>&nbsp;下架</button> &nbsp;</@shiro.hasPermission>';

                                }
                                operateHtml = operateHtml + '<@shiro.hasPermission name="admin:product:delete"><button class="btn btn-danger btn-xs" type="button" onclick="del(\''+ row.productId+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;</@shiro.hasPermission>';

                                return operateHtml;
                            }
                        }]
                });
            });

            function detail(productId) {
                layer.open({
                    type: 2,
                    title: '商品详情',
                    shadeClose: false,
                    shade: [0.5, '#ccc'],
                    area: ['893px', '600px'],
                    content: '${ctx!}/admin/product/detail/' + productId,
                    end: function (index) {
                        $('#table_list').bootstrapTable("refresh");
                    }
                });
            }

            function add() {
                layer.open({
                    type: 2,
                    title: '商品添加',
                    shadeClose: true,
                    shade: false,
                    area: ['790px', '600px'],
                    content: '${ctx!}/admin/product/add',
                    end: function (index) {
                        $('#table_list').bootstrapTable("refresh");
                    }
                });
            }
            function onSale(productId) {
                layer.confirm('确定上架此商品吗?', {icon: 3, title: '提示'}, function (index) {
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${ctx!}/admin/product/up/" + productId + "/1",
                        success: function (msg) {
                            layer.msg(msg.message, {time: 2000}, function () {
                                $('#table_list').bootstrapTable("refresh");
                                layer.close(index);
                            });
                        }
                    });
                });
            }

            function del(productId) {
                layer.confirm('确定删除此商品吗?', {icon: 3, title: '提示'}, function (index) {
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${ctx!}/admin/product/del/" + productId,
                        success: function (msg) {
                            layer.msg(msg.message, {time: 2000}, function () {
                                $('#table_list').bootstrapTable("refresh");
                                layer.close(index);
                            });
                        }
                    });
                });
            }

            function offSale(productId) {
                layer.confirm('确定下架此商品吗?', {icon: 3, title: '提示'}, function (index) {
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${ctx!}/admin/product/down/" + productId + "/2",
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


            function detailFormatter(index, row) {
                var html = [];
                html.push('<p><b>商品简介:</b> ' + row.productSummary + '</p>');
                return html.join('');
            }
        </script>


</body>

</html>
