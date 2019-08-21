<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>添加banner</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx!}/hadmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hadmin/css/style.css?v=4.1.0" rel="stylesheet">
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
                    <h5>添加商品类目</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/mall/banner/edit">
                        <input type="hidden" id="imgUrl" name="imgUrl" value="${category.imgUrl}"/>
                        <input type="hidden" id="id" name="id" value="${category.id}"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">类目名称：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" value="${category.name}"
                                       >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">类目状态：</label>
                            <div class="col-sm-8">
                                <select name="status" class="form-control">
                                <#--categories-->
                                <#list infos as c>
                                    <option value="${c.dictCode}" <#if c.dictCode == category.status>selected="selected"</#if>>${c.info}</option>
                                </#list>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">图片：</label>
                            <div class="col-sm-8">
                                <div class="fileinput fileinput-new" data-provides="fileinput" id="uploadImageDiv">
                                    <div class="fileinput-new thumbnail" style="width: 120px; height: 90px;">
                                        <img id="img" src="${category.imgUrl}" alt=""/>
                                    </div>
                                    <div class="fileinput-preview fileinput-exists thumbnail"
                                         style="max-width: 200px; max-height: 150px;"></div>
                                    <div>
                        <span class="btn default btn-file"> <span
                                class="fileinput-new">选择图片</span>
                            <input type="file" name="multipartFile"
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
                name: {
                    required: true,
                    minlength: 2,
                    maxlength: 30
                },

                imgUrl: {
                    date: true,
                    required: true
                }
            },
            messages: {},
            submitHandler: function (form) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/category/add",
                    data: $(form).serialize(),
                    success: function (msg) {
                        layer.msg(msg.meta.message, {time: 1000}, function () {
                            if(msg.meta.code==1){
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

<script>

    //文件选择框改变的时候就会触发此方法
    function changeFile() {
        if (document.getElementById("multipartFile").value != "") {
            var formData = new FormData(document.getElementById("frm"));//表单id
            $.ajax({
                url: '${ctx!}/picture/upload',
                type: 'POST',
                data: formData,
                dataType: "json",
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (msg) {
                    if (msg.meta.code == 1) {
                        $('#img').attr('src', msg.data.url);
                        $("#imgUrl").val(msg.data.url);
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
