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
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 找回密码</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="floginacct" placeholder="请输入登录账号"  autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>

        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="femail" placeholder="请输入邮箱地址"  style="margin-top:10px;">
            <span class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" size="14"  id="femailcheck" placeholder="请输入四位验证码" style="margin-top: 5px; height: 40px ">
            <input type="button" id="sendemailBtn" value="获取邮箱验证码" class="btn btn-lg btn-success" style="float: right;" >
        </div>

        <a id="nextBtn" class="btn btn-lg btn-success btn-block" > 下一步</a>
    </form>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/jquery/layer/layer.js"></script>
<script>
    var countdown=5;        //初始值
    function settime(val) {
        if (countdown == 0) {
            val.removeAttribute("disabled");
            val.value="获取邮箱验证码";
            countdown = 5;
            return false;
        } else {
            val.setAttribute("disabled", true);
            val.value="重新发送(" + countdown + ")";
            countdown--;
        }
        setTimeout(function() {
            settime(val);
        },1000);
    };

    var femail = $("#femail");
    var loginacct = $("#floginacct");
    var emailcheck = $("#femailcheck");


    var emailre = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
    var loginacctre = /^[a-z0-9_-]{3,11}$/;
    var emailcheckre = /^\d{4}$/;

    $("#floginacct").blur(function () {
        if(!loginacctre.test(loginacct.val())){
            layer.tips('请输入3~11位的字母或数字', '#floginacct', {
                tips: [2, '#0FA6D8'], time: 3000
            });
            return false;
        }
    });

    $("#femail").blur(function () {
        if(!emailre.test(femail.val())){
            layer.tips('邮箱格式不正确', '#femail', {
                tips: [2, '#0FA6D8'], time: 3000
            });
            return false;
        }
    });

    $("#femailcheck").blur(function () {
        if (!emailcheckre.test(emailcheck.val())) {
            layer.tips('验证码为4位有效数字', '#femailcheck', {
                tips: [2, '#0FA6D8'], time: 3000
            });
            return false;
        }
    });



    $("#sendemailBtn").click(function () {
        if($.trim(femail.val())==""){
            layer.msg("邮箱不能为空！！", {time:1000, icon:5, shift:6});
            femail.focus();
            return false;
        }
        else if(!emailre.test(femail.val())){
            layer.msg("邮箱格式不正确", {time:1000, icon:5, shift:6});
            femail.focus();
            return false;
        }
        settime(this);
        var email = $("#femail");
        var loadingIndex = -1;
        $.ajax({
            type : "post",
            data : {
                "email" : femail.val()
            },
            url: "${APP_PATH}/doSendEmail.do",
            beforeSend : function () {
                //表单校验
                loadingIndex = layer.msg('发送中...', {icon: 16});
                return true;
            },
            success : function (result) {
                layer.close(loadingIndex);
                if (result.success) {
                    layer.msg(result.message,{time:1000, icon:5, shift:6});
                }
                else {
                    layer.msg(result.message,{time:1000, icon:5, shift:6});
                }
            },
            error : function () {
                layer.msg("发送失败！！！",{time:1000, icon:5, shift:6});
            }
        });

    });

    $("#nextBtn").click(function () {

        //邮箱校验 正则

        if(!loginacctre.test(loginacct.val())){
            layer.tips('请输入3~11位的字母或数字', '#floginacct', {
                tips: [2, '#0FA6D8'], time: 2000
            });
            loginacct.focus();
            return false;
        } else if(!emailre.test(femail.val())){
            layer.tips('邮箱格式不正确', '#femail', {
                tips: [2, '#0FA6D8'], time: 2000
            });
            femail.focus();
            return false;
        } else if (!emailcheckre.test(emailcheck.val())) {
            layer.tips('验证码为4位有效数字', '#femailcheck', {
                tips: [2, '#0FA6D8'], time: 2000
            });
            emailcheck.focus();
            return false;
        }

        $.ajax({
            type : "POST",
            data : {
                "email" : femail.val(),
                "loginacct" : loginacct.val(),
                "emailcheck" : emailcheck.val(),
            },
            url : "${APP_PATH}/doFindPwd.do",
            beforeSend : function() {
                return true ;
            },
            success : function(result){
                if(result.success){
                    window.location.href="${APP_PATH}/reset.htm";
                    layer.msg(result.success, {time:1000, icon:5, shift:6});
                }else{
                    layer.msg(result.message, {time:1000, icon:5, shift:6});
                }
            },
            error : function(){
                layer.msg("找回密码失败！！", {time:1000, icon:5, shift:6});
            }
        });
    });
</script>