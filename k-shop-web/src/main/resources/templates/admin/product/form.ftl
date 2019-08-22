<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>添加商品</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx!}/hadmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/style.css?v=4.1.0" rel="stylesheet">
    <#--<link href="${ctx!}/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">-->

    <style type="text/css">

        .btn-file {
            position: relative;
            overflow: hidden;
            vertical-align: middle;
        }

        .btn-file > input {
            position: absolute;
            top: 0;
            right: 0;
            width: 100%;
            height: 100%;
            margin: 0;
            font-size: 23px;
            cursor: pointer;
            filter: alpha(opacity=0);
            opacity: 0;

            direction: ltr;
        }

        .fileinput {
            display: inline-block;
            margin-bottom: 9px;
        }

        .fileinput .form-control {
            display: inline-block;
            padding-top: 7px;
            padding-bottom: 5px;
            margin-bottom: 0;
            vertical-align: middle;
            cursor: text;
        }

        .fileinput .thumbnail {
            display: inline-block;
            margin-bottom: 5px;
            overflow: hidden;
            text-align: center;
            vertical-align: middle;
        }

        .fileinput .thumbnail > img {
            max-height: 100%;
        }

        .fileinput .btn {
            vertical-align: middle;
        }

        .fileinput-exists .fileinput-new,
        .fileinput-new .fileinput-exists {
            display: none;
        }

        .fileinput-inline .fileinput-controls {
            display: inline;
        }

        .fileinput-filename {
            display: inline-block;
            overflow: hidden;
            vertical-align: middle;
        }

        .form-control .fileinput-filename {
            vertical-align: bottom;
        }

        .fileinput.input-group {
            display: table;
        }

        .fileinput.input-group > * {
            position: relative;
            z-index: 2;
        }

        .fileinput.input-group > .btn-file {
            z-index: 1;
        }

        .fileinput-new.input-group .btn-file,
        .fileinput-new .input-group .btn-file {
            border-radius: 0 4px 4px 0;
        }

        .fileinput-new.input-group .btn-file.btn-xs,
        .fileinput-new .input-group .btn-file.btn-xs,
        .fileinput-new.input-group .btn-file.btn-sm,
        .fileinput-new .input-group .btn-file.btn-sm {
            border-radius: 0 3px 3px 0;
        }

        .fileinput-new.input-group .btn-file.btn-lg,
        .fileinput-new .input-group .btn-file.btn-lg {
            border-radius: 0 6px 6px 0;
        }

        .form-group.has-warning .fileinput .fileinput-preview {
            color: #8a6d3b;
        }

        .form-group.has-warning .fileinput .thumbnail {
            border-color: #faebcc;
        }

        .form-group.has-error .fileinput .fileinput-preview {
            color: #a94442;
        }

        .form-group.has-error .fileinput .thumbnail {
            border-color: #ebccd1;
        }

        .form-group.has-success .fileinput .fileinput-preview {
            color: #3c763d;
        }

        .form-group.has-success .fileinput .thumbnail {
            border-color: #d6e9c6;
        }

        .input-group-addon:not(:first-child) {
            border-left: 0;
        }

    </style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>添加商品</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/mall/product/addProduct">
                        <input type="hidden" id="productImage" name="productImage" value="${product.productImage}"/>



                        <div class="row">

                            <div class="col-xs-6">
                                <label class="col-sm-4 control-label">商品名称:</label>
                                <div class="col-sm-7">
                                    <input id="productName" name="productName" class="form-control" type="text"
                                           placeholder="请输入商品名称">
                                </div>
                            </div>

                            <div class="col-xs-6">
                                <label class="col-sm-4 control-label">所属类目:</label>
                                <div class="col-sm-8">
                                    <select id="categoryNo" name="categoryNo" class="form-control">
                                    <#list categoryList as category>
                                        <option value="${category.categoryNo}">${category.name}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                        </div>

                        <br/>

                        <div class="row">

                            <div class="col-xs-6">
                                <label class="col-sm-4 control-label">商品简介:</label>
                                <div class="col-sm-7">
                                    <input id="productSummary" name="productSummary" class="form-control" type="text"
                                           placeholder="请输入商品简介">
                                </div>
                            </div>

                            <div class="col-xs-6">
                                <label class="col-sm-4 control-label">商品库存:</label>
                                <div class="col-sm-7">
                                    <input placeholder="请输入商品库存" id="productStock" name="productStock"
                                           class="form-control" type="text" onkeyup="value=value.replace(/[^\d]/g,'')">
                                </div>
                            </div>

                        </div>

                        <br/>


                        <div class="row">

                            <div class="col-xs-6">
                                <label class="col-sm-4 control-label">商品原价格(分):</label>
                                <div class="col-sm-7">
                                    <input id="productOriginalPrice" name="productOriginalPrice" class="form-control"
                                           type="text"
                                           placeholder="请输入商品原价" onkeyup="value=value.replace(/[^\d]/g,'')">
                                </div>
                            </div>

                            <div class="col-xs-6">
                                <label class="col-sm-4 control-label">商品现价(分):</label>
                                <div class="col-sm-7">
                                    <input id="productNowPrice" name="productNowPrice" class="form-control"
                                           type="text"
                                           placeholder="请输入商品现价" onkeyup="value=value.replace(/[^\d]/g,'');">
                                </div>
                            </div>

                        </div>

                        <br/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品现价描述:</label>
                            <div class="col-sm-8">
                                <input id="productNowPriceDescription" name="productNowPriceDescription" value="${product.productNowPriceDescription}"
                                       class="laydate-icon form-control layer-date" placeholder="请输入商品现价描述"
                                >
                            </div>
                        </div>

                        <br/>
                        <div class="row">

                            <div class="col-xs-6">
                                <label class="col-sm-4 control-label">商品上架状态:</label>
                                <div class="col-sm-8">
                                    <select id="productStatus" name="productStatus" class="form-control">
                                    <#list productStatus as dictInfo>
                                        <option value="${dictInfo.dictCode}">${dictInfo.info}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="col-xs-6">
                                <label class="col-sm-4 control-label">商品销售平台:</label>
                                <div class="col-sm-8">
                                    <select id="productSalesType" name="productSalesType" class="form-control">
                                    <#list productSales as dictInfo>
                                        <option value="${dictInfo.dictCode}">${dictInfo.info}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                        </div>


                        <br/>

                        <div class="row">

                            <div class="col-xs-6">
                                <label class="col-sm-4 control-label">是否推荐商品:</label>
                                <div class="col-sm-8">
                                    <select id="productIfRecommend" name="productIfRecommend" class="form-control">
                                    <#list productIfRecommends as dictInfo>
                                        <option value="${dictInfo.dictCode}">${dictInfo.info}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="col-xs-6">
                                <label class="col-sm-4 control-label">可否开发票:</label>
                                <div class="col-sm-8">
                                    <select id="productIfCanInvoice" name="productIfCanInvoice" class="form-control">
                                    <#list productIfCanInvoices as dictInfo>
                                        <option value="${dictInfo.dictCode}">${dictInfo.info}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                        </div>
                        <br/>
                        <div class="row">

                            <div class="col-xs-6">
                                <label class="col-sm-4 control-label">是否实物商品:</label>
                                <div class="col-sm-8">
                                    <select id="productGoodsType" name="productGoodsType" class="form-control">
                                    <#list productGoodsTypes as dictInfo>
                                        <option value="${dictInfo.dictCode}">${dictInfo.info}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="col-xs-6">

                            </div>

                        </div>

                        <br/>

                        <#--<div class="row">-->

                            <#--<div class="col-xs-6">-->
                                <#--<label class="col-sm-4 control-label">推荐开始时间:</label>-->
                                <#--<div class="col-sm-7">-->
                                    <#--<input id="productCommendStartTime" name="productCommendStartTime"-->
                                           <#--class="form-control" type="text"-->
                                           <#--placeholder="推荐商品开始时间">-->
                                <#--</div>-->
                            <#--</div>-->

                            <#--<div class="col-xs-6">-->
                                <#--<label class="col-sm-4 control-label">推荐结束时间:</label>-->
                                <#--<div class="col-sm-7">-->
                                    <#--<input id="productCommendEndTime" name="productCommendEndTime" class="form-control"-->
                                           <#--type="text"-->
                                           <#--placeholder="推荐商品结束时间">-->
                                <#--</div>-->
                            <#--</div>-->

                        <#--</div>-->


                        <br/>


                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品介绍：</label>
                            <div class="col-sm-8">
                                <input id="productDescription" name="productDescription"
                                       class="laydate-icon form-control layer-date" placeholder="请输入商品介绍"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">图片：</label>
                            <div class="col-sm-8">
                                <div class="fileinput fileinput-new" data-provides="fileinput" id="uploadImageDiv">
                                    <div class="fileinput-new thumbnail" style="width: 120px; height: 90px;">
                                        <img id="img" src="" alt=""/>
                                    </div>
                                    <div class="fileinput-preview fileinput-exists thumbnail"
                                         style="max-width: 200px; max-height: 150px;"></div>
                                    <div>
                        <span class="btn default btn-file"> <span
                                class="fileinput-new">选择图片</span>  <input type="file" name="multipartFile"
                                                                          id="multipartFile" accept="image/*"
                                                                          onchange="changeFile(this)"/></span>
                                        <span>请选择1M以内图片</span>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
                                <button class="btn btn-success" onclick="hide()" type="button">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- 全局js -->
<#include "/admin/common/common.ftl">
<script type="text/javascript">
    $(document).ready(function () {

//        //初始化时间选择器
//        $('#productCommendStartTime').datetimepicker({
//            language: "zh-CN", //汉化
//            todayBtn: "true",  //显示今天按钮
//            autoclose: true,   //选择日期后自动关闭日期选择框
//            todayHighlight: true,   //当天高亮显示
//            minView: "month",   //不显示时分秒
//            format: 'yyyy-mm-dd',
//            showMeridian: 1,
//            pickerPosition: "bottom-left",
//            startDate: new Date(),  //只显示一年的日期365天
//            endDate: new Date()
//        }).on('click', function (e) {
//            $("#productCommendStartTime").datetimepicker("setEndDate", $("#productCommendEndTime").val());
//        });
//        $('#productCommendEndTime').datetimepicker({
//            language: "zh-CN",
//            todayBtn: "true",
//            autoclose: true,
//            todayHighlight: true,
//            minView: "month",
//            format: 'yyyy-mm-dd',
//            showMeridian: 1,
//            pickerPosition: "bottom-left",
//            startDate: new Date(new Date() - 1000 * 60 * 60 * 24 * 365)
//        }).on('click', function (e) {
//            $("#productCommendEndTime").datetimepicker("setStartDate", $("#productCommendStartTime").val());
//        });


        $("#frm").validate({
            rules: {
                productName: {
                    required: true,
                    minlength: 2,
                    maxlength: 30
                },
                productOriginalPrice: {
                    required: true,
                    minlength: 1,
                    maxlength: 10
                },
                productStock: {
                    required: true
                },
                productSummary: {
                    required: true,
                    maxlength: 100
                },
                productNowPrice: {
                    required: true,
                    maxlength: 10
                }
            },
            messages: {},
            submitHandler: function (form) {

                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/mall/product/addProduct",
                    data: $(form).serialize(),
                    success: function (msg) {
                        if (msg.code == 0) {
                            layer.msg(msg.message, {time: 1500}, function () {
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index);
                            });
                        }else{
                            layer.msg(msg.message, {time: 1500}, function () {

                            });
                        }
                    }
                });
            }
        });
    });
</script>

</body>

<script>

    //文件选择框改变的时候就会触发此方法
    function changeFile() {
        if (document.getElementById("multipartFile").value != "") {
            var formData = new FormData(document.getElementById("frm"));//表单id
            $.ajax({
                url: '${ctx!}/mall/product/upload',
                type: 'POST',
                data: formData,
                dataType: "json",
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (msg) {
                    if (msg.code == 0) {
                        $('#img').attr('src', msg.message);
                        $("#productImage").val(msg.message);
                    }

                }
            });

        }

    }

    function hide() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }

</script>


</html>
