<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 表单验证 jQuery Validation</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx!}/hadmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${ctx!}/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>完整验证表单</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/user/edit">
                        <input type="hidden" id="uid" name="uid" value="${user.uid}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">账户名：</label>
                            <div class="col-sm-8">
                                <input id="userName" name="userName" class="form-control" type="text"
                                       placeholder="请输入账号名"
                                       value="${user.userName}" <#if user?exists> readonly="readonly"</#if> >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">昵称：</label>
                            <div class="col-sm-8">
                                <input id="nickName" name="nickName" class="form-control" placeholder="请输入昵称"
                                       type="text"
                                       value="${user.nickName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">性别：</label>
                            <div class="col-sm-8">
                                <select name="sex" class="form-control">
                                    <#list infos as sex>
                                        <option value="${sex.dictCode}"
                                                <#if sex.dictCode == user.sex>selected="selected"</#if>>${sex.info}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">电话：</label>
                            <div class="col-sm-8">
                                <input id="telephone" name="telephone" placeholder="请输入手机号" class="form-control"
                                       value="${user.telephone}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">E-mail：</label>
                            <div class="col-sm-8">
                                <input id="email" name="email" placeholder="请输入邮箱" class="form-control"
                                       value="${user.email}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态：</label>
                            <div class="col-sm-8">
                                <select name="locked" class="form-control">
                                    <#list userInfos as user_status>
                                        <option value="${user_status.dictCode}"
                                                <#if user_status.dictCode == user.locked>selected="selected"</#if>>${user_status.info}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">描述：</label>
                            <div class="col-sm-8">
                                <input id="description" name="description" class="form-control" placeholder="请输入描述"
                                       value="${user.description}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
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
                userName: {
                    required: true,
                    minlength: 4,
                    maxlength: 10
                },
                nickName: {
                    required: true,
                    minlength: 2,
                    maxlength: 10
                },
                sex: {
                    required: true
                },
                birthday: {
                    date: true,
                    required: true
                },
                telephone: {
                    required: true
                },
                email: {
                    email: true,
                    required: true
                },
                locked: {
                    required: true
                },
                description: {
                    required: true,
                    maxlength: 40
                }
            },
            messages: {},
            submitHandler: function (form) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/user/edit",
                    data: $(form).serialize(),
                    success: function (msg) {
                        layer.msg(msg.meta.message, {time: 2000}, function () {
                            if (msg.meta.code == 1) {
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index);
                            }
                        });
                    }
                });

            }
        });
    });
</script>

</body>

</html>
