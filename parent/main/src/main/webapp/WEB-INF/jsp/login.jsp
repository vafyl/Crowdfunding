<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
    <form id="loginForm" action="${APP_PATH}/doLogin.do" method="post" class="form-signin" role="form">
        ${exception.message}
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="floginacct" name="loginacct" placeholder="请输入登录账号"  autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="fuserpswd" name="userpswd" placeholder="请输入登录密码"  style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>

        <div class="form-group has-success has-feedback">
            <select id="ftype" class="form-control" name="type">
                <option value="member" selected>会员</option>
                <option value="user" >管理</option>
            </select>
        </div>
        <div id="verify-wrap" class="verify-wrap"></div>
        <div class="layui-input-block">
            <div id="slider"></div>
        </div>
        <div id="loginSlider" class="slider"></div>
        <div class="checkbox">
            <label>
                <input id="rememberme" type="checkbox" value="1"> 记住我</label>
            <br>
            <label>
                <a href="${APP_PATH}/forget.htm">忘记密码</a>
            </label>
            <label style="float:right">
                <a href="${APP_PATH}/reg.htm">我要注册</a>
            </label>
        </div>
        <a id="doSubmit" class="btn btn-lg btn-success btn-block" > 登录</a>
    </form>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/jquery/layer/layer.js"></script>
<script src="${APP_PATH}/jquery/jq-slideVerify.js"></script>
<script>
    //滑块验证
    $(function(){
        // console.log(parseFloat('1px'))
        var SlideVerifyPlug = window.slideVerifyPlug;
        var slideVerify = new SlideVerifyPlug('#verify-wrap',{
            wrapWidth:'300',//设置 容器的宽度 ，默认为 350
            initText:'请按住滑块，向右滑动',  //设置  初始的 显示文字
            sucessText:'验证通过',//设置 验证通过 显示的文字
            getSucessState:function(res){
                //当验证完成的时候 会 返回 res 值 true，
                console.log(res);
            }
        });

        var loginacct = $("#floginacct");
        var userpswd = $("#fuserpswd");

        var loginacctre = /^[a-z0-9_-]{3,11}$/;
        var userpswdre = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
        $("#floginacct").blur(function () {
            if(!loginacctre.test(loginacct.val())){
                layer.tips('请输入3~11位的字母或数字', '#floginacct', {
                    tips: [2, '#0FA6D8'], time: 2000
                });
                return false;
            }
        });
        $("#fuserpswd").blur(function () {
            if(!userpswdre.test(userpswd.val())){
                layer.tips('密码长度为8~16位且必须包含字母和数字', '#fuserpswd', {
                    tips: [2, '#0FA6D8'], time: 2000
                });
                return false;
            }
        });
        //这个可以拿到 当前验证状态  是否完成
        $("#doSubmit").click(function () {
            if (!slideVerify.slideFinishState){
                //未验证不提交
                layer.msg("请拖动滑块！",{time:1000, icon:5, shift:6});
                return false;
            } else {

                var type = $("#ftype");

                var loadingIndex = -1;
                var flag = $("#rememberme")[0].checked;//是否被选中

                if(!loginacctre.test(loginacct.val())){
                    layer.tips('请输入3~11位的字母或数字', '#floginacct', {
                        tips: [2, '#0FA6D8'], time: 2000
                    });
                    loginacct.focus();
                    return false;
                } else if(!userpswdre.test(userpswd.val())){
                    layer.tips('密码长度为8~16位且必须包含字母和数字', '#fuserpswd', {
                        tips: [2, '#0FA6D8'], time: 2000
                    });
                    userpswd.focus();
                    return false;
                }

                //ajxa异步请求
                $.ajax({
                    type : "post",
                    data : {
                        "loginacct" : loginacct.val(),
                        "userpswd" : userpswd.val(),
                        "type" : type.val(),
                        "rememberme" : flag?"1":"0"
                    },

                    url: "${APP_PATH}/doLogin.do",
                    beforeSend : function () {
                        //表单校验
                        loadingIndex = layer.msg('登录中...', {icon: 16});
                        return true;
                    },
                    success : function (result) {
                        layer.close(loadingIndex);
                        if (result.success) {
                            if("member"==result.data){
                                //会员页面
                                window.location.href="${APP_PATH}/member.htm";
                            }else if ("user"==result.data) {
                                //主页面（后台页面）
                                window.location.href="${APP_PATH}/main.htm";
                            }else {
                                layer.msg("登录类型不合法！",{time:1000, icon:5, shift:6});
                            }
                        }
                        else {
                            layer.msg(result.message,{time:1000, icon:5, shift:6});
                        }
                    },
                    error : function () {
                        layer.msg("登录失败！！！",{time:1000, icon:5, shift:6});
                    }
                });

                /*$("#loginForm").submit();
                var type = $(":selected").val();
                if ( type == "user" ) {
                    window.location.href = "main.html";
                } else {
                    window.location.href = "index.html";
                }*/
            }
        });
    });

</script>
</body>
</html>