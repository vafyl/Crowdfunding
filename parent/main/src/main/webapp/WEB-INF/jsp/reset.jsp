<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/login.css">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="${APP_PATH}/index.htm" style="font-size:32px;">广筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form method="post"  class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 设置新密码</h2>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="fuserpswd" placeholder="请设置新密码"  style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="fagainuserpswd" placeholder="再次输入密码"  style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <a id="finalBtn" class="btn btn-lg btn-success btn-block" > 完成</a>
    </form>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/jquery/layer/layer.js"></script>
<script>
    var userpswd = $("#fuserpswd");
    var againuserpswd = $("#fagainuserpswd");


    var userpswdre = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;

    $("#fuserpswd").blur(function () {
        if(!userpswdre.test(userpswd.val())){
            layer.tips('密码长度为8~16位且必须包含字母和数字', '#fuserpswd', {
                tips: [2, '#0FA6D8'], time: 3000
            });
            return false;
        }
    });
    $("#fagainuserpswd").blur(function () {
        if(userpswd.val() != againuserpswd.val()){
            layer.tips('密码不一致！！', '#fagainuserpswd', {
                tips: [2, '#0FA6D8'], time: 3000
            });
            return false;
        }
    });
    $("#finalBtn").click(function () {
        if(!userpswdre.test(userpswd.val())){
            layer.tips('密码长度为8~16位且必须包含字母和数字', '#fuserpswd', {
                tips: [2, '#0FA6D8'], time: 3000
            });
            userpswd.focus();
            return false;
        }else if(userpswd.val() != againuserpswd.val()){
            layer.tips('密码不一致！！', '#fagainuserpswd', {
                tips: [2, '#0FA6D8'], time: 3000
            });
            againuserpswd.focus();
            return false;
        }

        $.ajax({
            type : "POST",
            data : {
                "userpswd" : userpswd.val(),
                "againuserpswd" : againuserpswd.val()
            },
            url : "${APP_PATH}/doReset.do",
            beforeSend : function() {
                return true ;
            },
            success : function(result){
                if(result.success){
                    layer.msg(result.message, {time:1000, icon:5, shift:6});
                    window.location.href="${APP_PATH}/main.htm";
                }else{
                    layer.msg(result.message, {time:1000, icon:5, shift:6});
                }
            },
            error : function(){
                layer.msg("注册失败！！", {time:1000, icon:5, shift:6});
            }
        });
    });
</script>