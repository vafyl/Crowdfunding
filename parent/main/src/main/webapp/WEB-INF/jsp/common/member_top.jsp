<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${APP_PATH}/index.htm" style="font-size:32px;">广筹网-创意产品众筹平台</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse" style="float:right;">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i> ${sessionScope.member.username }<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="member.html"><i class="glyphicon glyphicon-scale"></i> 会员中心</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
                            <li class="divider"></li>
                            <li onclick="clearCookie()"><a href="${APP_PATH }/logout.do"><i class="glyphicon glyphicon-off"></i> 注销</a></li>
                            <li><a href="${APP_PATH }/logout.do"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

</div>