<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title> - 登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="${ctx!}/hadmin/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/style.css" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/login.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-12">
        <#if message?exists >
            <div class="alert alert-danger">
            ${message!}
            </div>
        </#if>
            <form method="post" action="${ctx!}/admin/login" id="frm">
                <input type="text" class="form-control uname" name="username" id="username" value="leek" placeholder="用户名"/>
                <input type="password" class="form-control pword m-b" name="password"  value="12345678"  id="password" placeholder="密码" maxlength="16"/>
                <button class="btn btn-success btn-block">登录</button>
            </form>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="${ctx!}/hadmin/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx!}/hadmin/js/bootstrap.min.js?v=3.3.6"></script>

<!-- 自定义js -->
<script src="${ctx!}/hadmin/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${ctx!}/hadmin/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctx!}/hadmin/js/plugins/validate/messages_zh.min.js"></script>
<script type="text/javascript">
    $().ready(function () {
        // 在键盘按下并释放及提交后验证提交表单
        $("#frm").validate({
            rules: {
                username: {
                    required: true,
                    minlength: 2
                },
                password: {
                    required: true,
                    minlength: 8,
                    maxlength: 16
                }
            },
            messages: {
                username: {
                    required: "请输入用户名",
                    minlength: "用户名必需由两个字母组成"
                },
                password: {
                    required: "请输入密码",
                    minlength: "密码长度不能小于 8位且不超过16位",
                    maxlength: "密码长度不能小于 8位且不超过16位"
                }
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    });

</script>
</body>

</html>
