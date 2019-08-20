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
                        <input type="text"   hidden="hidden" name="id" id="id" value="<@shiro.principal type="com.jjk.hadmin.entity.User" property="id"/>"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">原始密码:</label>
                            <div class="col-sm-8">
                                <input id="password" name="password" class="form-control" type="password" maxlength="16" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">最新密码:</label>
                            <div class="col-sm-8">
                                <input id="newPassword" name="newPassword" class="form-control" type="password" maxlength="16" >
                            </div>
                        </div>

                        <div class="form-group" style="float: none;display: block;margin-left: auto;margin-right: auto">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary " type="submit">提交</button>
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
                password: {
                    required: true,
                    minlength: 8,
                    maxlength: 16
                },
                newPassword: {
                    required: true,
                    minlength: 8,
                    maxlength: 16
                }
            },
            messages: {
                newPassword: {
                    required:"新密码格式:8-16位",
                    minlength:"密码最少为8位",
                    maxlength:"密码最多为16位"
                },
                password: {
                    required:"原始密码格式:8-16位",
                    minlength:"原始密码最少为8位",
                    maxlength:"原始密码最多为16位"
                }
            },
            submitHandler:function(form){
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/user/changePassword",
                    data: $(form).serialize(),
                    success: function(msg){
                        layer.msg(msg.message, {time: 1000},function(){
                            if(msg.code == 0){
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
