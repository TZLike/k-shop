<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 表单验证 jQuery Validation</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx!}/hadmin/css/bootstrap.min.css?v=${version!}" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/font-awesome.css?v=${version!}" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/animate.css?v=${version!}" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/style.css?v=${version!}" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/role/edit">


                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称/值:</label>
                            <div class="col-sm-8">
                                <input id="info" name="info" class="form-control" type="text" value="${dictInfo.info}" placeholder="请输入名称/值" >
                            </div>
                        </div>

                        <div class="form-group">

                            <#if (dictInfo.typeCode)??>

                                <input type="hidden" id="typeCode" name="typeCode" value="${dictInfo.typeCode}">
                              <#else >

                                  <input type="hidden" id="typeCode" name="typeCode" value="${typeCode}">
                              </#if>


                            <input type="hidden" id="id" name="id" value="${dictInfo.id}">
                            <label class="col-sm-3 control-label">业务编码:</label>
                            <div class="col-sm-8">
                                <input id="dictCode" name="dictCode" class="form-control" type="text" value="${dictInfo.dictCode}" placeholder="请输入业务编码(如:0)" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注</label>
                            <div class="col-sm-8">
                                <input id="remark" name="remark" class="form-control" type="text" value="${dictInfo.remark}" placeholder="请输入备注(选填)" >
                            </div>
                        </div>

                        <div class="form-group" style="float: none;display: block;margin-left: auto;margin-right: auto">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
                                <button class="btn btn-primary" onclick="hide()" type="button">取消</button>
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
        $("#frm").validate({
            rules: {

                info: {
                    required: true,
                    minlength: 1,
                    maxlength: 30
                }, dictCode: {
                    required: true,
                    minlength: 1,
                    maxlength: 30
                }
            },
            messages: {},
            submitHandler:function(form){
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/dict/info/edit",
                    data: $(form).serialize(),
                    success: function(msg){
                        layer.msg(msg.meta.message, {time: 1500},function(){
                            if(msg.meta.code == 1){
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index);
                            }

                        });
                    }
                });
            }
        });
    });

    function  hide(){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>

</body>

</html>
