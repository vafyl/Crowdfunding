<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH }/css/main.css">
    <link rel="stylesheet" href="${APP_PATH }/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li style="padding-top:8px;">
                    <%@include file="/WEB-INF/jsp/common/userinfo.jsp" %>
                </li>
                <li style="margin-left:10px;padding-top:8px;">
                    <button type="button" class="btn btn-default btn-danger">
                        <span class="glyphicon glyphicon-question-sign"></span> 帮助
                    </button>
                </li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="tree">
                <%@include file="/WEB-INF/jsp/common/menu.jsp" %>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <jsp:include page="/WEB-INF/jsp/common/headmenu.jsp"></jsp:include>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" class="form-inline">
                        <div class="form-group">
                            <label for="leftRoleList">未分配角色列表</label><br>
                            <select id="leftRoleList" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">
                                <c:forEach items="#{leftRoleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="leftToRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right" style="margin-top:20px;"></li>
                                <br>
                                <li id="rightToLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:15px;"></li>
                                <br>
                                <li id="allLeftToRightBtn" class="btn btn-default glyphicon glyphicon glyphicon-forward"  style="margin-top:15px;"></li>
                                <br>
                                <li id="allRightToRightBtn" class="btn btn-default glyphicon glyphicon glyphicon-backward"  style="margin-top:15px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="rightRoleList">已分配角色列表</label><br>
                            <select id="rightRoleList" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">
                                <c:forEach items="${rigthRoleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容001，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH }/script/docs.min.js"></script>
<script type="text/javascript" src="${APP_PATH }/jquery/layer/layer.js"></script>

<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });

    //双击移动  右移
    //不能直接加dblclick  未来事假无法加效果
    $("#leftRoleList").delegate("option","dblclick",function(){
        var selectedOptions = $("#leftRoleList option:selected");

        jsonObj = {
            "userid" : ${param.userid}  //param
        };
        $.each(selectedOptions,function (i,n) {
            jsonObj["ids["+i+"]"]  = this.value;
        });
        var index = -1;
        $.ajax({
            type : "post",
            data : jsonObj,
            url : "${APP_PATH }/user/doAssignRole.do",
            beforeSend : function () {
                index = layer.load("角色分配中...", {time: 0.25*1000});
                return true;
            },
            success : function (result) {
                index.close;
                if(result.success) {
                    $("#rightRoleList").append(selectedOptions.clone());
                    selectedOptions.remove();
                }else {
                    layer.msg(result.message, {time: 1000, icon: 5, shift: 6});
                }
            },
            error : function () {
                layer.msg("角色分配失败...", {time: 1000, icon: 5, shift: 6});

            }
        });



    });
    //双击移动  左移动
    $("#rightRoleList").delegate("option","dblclick",function(){
        var selectedOptions = $("#rightRoleList option:selected");
        jsonObj = {
            "userid" : ${param.userid}  //param
        }
        $.each(selectedOptions,function (i,n) {
            jsonObj["ids["+i+"]"]  = this.value;
        });
        var index = -1;
        $.ajax({
            type : "post",
            data : jsonObj,
            url : "${APP_PATH}/user/doUnAssignRole.do",
            beforeSend : function () {
                index = layer.load("取消角色中...", {time: 0.25*1000});
                return true;
            },
            success : function (result) {
                index.close;
                if(result.success) {
                    $("#leftRoleList").append(selectedOptions.clone());
                    selectedOptions.remove();
                }else {
                    layer.msg(result.message, {time: 1000, icon: 5, shift: 6});
                }
            },
            error : function () {
                layer.msg("取消角色失败...", {time: 1000, icon: 5, shift: 6});

            }
        });
    });


    //左边列表框选择移到右边列表框
    $("#leftToRightBtn").click(function () {
       var selectedOptions = $("#leftRoleList option:selected");

        jsonObj = {
            "userid" : ${param.userid}  //param
        };
        $.each(selectedOptions,function (i,n) {
            jsonObj["ids["+i+"]"]  = this.value;
        });
        var index = -1;
        $.ajax({
          type : "post",
          data : jsonObj,
          url : "${APP_PATH }/user/doAssignRole.do",
          beforeSend : function () {
              index = layer.load("角色分配中...", {time: 0.25*1000});
              return true;
          },
          success : function (result) {
              index.close;
              if(result.success) {
                  $("#rightRoleList").append(selectedOptions.clone());
                  selectedOptions.remove();
              }else {
                  layer.msg(result.message, {time: 1000, icon: 5, shift: 6});
              }
          },
          error : function () {
              layer.msg("角色分配失败...", {time: 1000, icon: 5, shift: 6});

          }
       });
    });

    //右边列表框选择移到左边列表框
    $("#rightToLeftBtn").click(function () {
        var selectedOptions = $("#rightRoleList option:selected");
        jsonObj = {
            "userid" : ${param.userid}  //param
        }
        $.each(selectedOptions,function (i,n) {
            jsonObj["ids["+i+"]"]  = this.value;
        });
        var index = -1;
        $.ajax({
            type : "post",
            data : jsonObj,
            url : "${APP_PATH}/user/doUnAssignRole.do",
            beforeSend : function () {
                index = layer.load("取消角色中...", {time: 0.25*1000});
                return true;
            },
            success : function (result) {
                index.close;
                if(result.success) {
                    $("#leftRoleList").append(selectedOptions.clone());
                    selectedOptions.remove();
                }else {
                    layer.msg(result.message, {time: 1000, icon: 5, shift: 6});
                }
            },
            error : function () {
                layer.msg("取消角色失败...", {time: 1000, icon: 5, shift: 6});

            }
        });
    });

    //全部分配
    $("#allLeftToRightBtn").click(function () {
        var allOptions = new Array();
        $("#leftRoleList option").each(function () {
            //$(this).val() --->获取option的每个值
            allOptions.push(this.value);
        });

        jsonObj = {
            "userid" : ${param.userid}  //param
        }
        $.each(allOptions,function (i,n) {
            jsonObj["ids["+i+"]"]  = allOptions[i];
        });
        var index = -1;
        $.ajax({
            type : "post",
            data : jsonObj,
            url : "${APP_PATH}/user/doAllRightAssignRole.do",
            beforeSend : function () {
                index = layer.load("角色分配中...", {time: 0.25*1000});
                return true;
            },
            success : function (result) {
                index.close;
                if(result.success) {
                    var $options = $("#leftRoleList option");
                    $options.appendTo("#rightRoleList");
                }else {
                    layer.msg(result.message, {time: 1000, icon: 5, shift: 6});
                }
            },
            error : function () {
                layer.msg("角色分配失败...", {time: 1000, icon: 5, shift: 6});

            }
        });
    });


    //全部取消分配
    $("#allRightToRightBtn").click(function () {
        var allOptions = new Array();
        $("#rightRoleList option").each(function () {
            //$(this).val() --->获取option的每个值
            allOptions.push(this.value);
        });

        jsonObj = {
            "userid" : ${param.userid}  //param
        }
        $.each(allOptions,function (i,n) {
            jsonObj["ids["+i+"]"]  = allOptions[i];
        });
        var index = -1;
        $.ajax({
            type : "post",
            data : jsonObj,
            url : "${APP_PATH}/user/doUnAllRightAssignRole.do",
            beforeSend : function () {
                index = layer.load("取消角色分配中...", {time: 0.25*1000});
                return true;
            },
            success : function (result) {
                index.close;
                if(result.success) {
                    var $options = $("#rightRoleList option");
                    $options.appendTo("#leftRoleList");
                }else {
                    layer.msg(result.message, {time: 1000, icon: 5, shift: 6});
                }
            },
            error : function () {
                layer.msg("角色分配失败...", {time: 1000, icon: 5, shift: 6});

            }
        });
    });
</script>
</body>
</html>
