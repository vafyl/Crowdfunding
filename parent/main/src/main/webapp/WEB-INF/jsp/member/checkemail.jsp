<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
	<style>
#footer {
    padding: 15px 0;
    background: #fff;
    border-top: 1px solid #ddd;
    text-align: center;
}
	</style>
  </head>
  <body>
 <div class="navbar-wrapper">
     <%@include file="/WEB-INF/jsp/common/member_top.jsp"%>
    </div>

    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>实名认证 - 申请</h1>
      </div>

		<ul class="nav nav-tabs" role="tablist">
		  <li role="presentation" ><a href="#"><span class="badge">1</span> 基本信息</a></li>
		  <li role="presentation" ><a href="#"><span class="badge">2</span> 资质文件上传</a></li>
		  <li role="presentation" class="active"><a href="#"><span class="badge">3</span> 邮箱确认</a></li>
		  <li role="presentation"><a href="#"><span class="badge">4</span> 申请确认</a></li>
		</ul>
        
		<form role="form" style="margin-top:20px;">
		  <div class="form-group">
			<label for="memberEmail">邮箱地址</label>
			<input type="text" class="form-control" id="memberEmail" value="${member.email }" placeholder="请输入用于接收验证码的邮箱地址">
		  </div>
          <button type="button" onclick="window.location.href='apply-1.html'" class="btn btn-default">上一步</button>
		  <button type="button" id="nextBtn" class="btn btn-success">下一步</button>
		</form>
		<hr>
    </div> <!-- /container -->
        <div class="container" style="margin-top:20px;">
            <%@include file="/WEB-INF/jsp/common/footer.jsp"%>
        </div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script>
        $('#myTab a').click(function (e) {
          e.preventDefault()
          $(this).tab('show')
        });    
        
        
        $("#nextBtn").click(function(){
        	$.ajax({
        		type : "POST",
        		url  : "${APP_PATH}/member/startProcess.do",
        		data : {
        			"email" : $("#memberEmail").val()
        		},
        		success : function(result) {
        			if ( result.success ) {
        				window.location.href = "${APP_PATH}/member/apply.htm";
        			} else {
        				layer.msg("发送验证码失败", {time:1000, icon:5, shift:6});
        			}
        		}
        	});

        });
	</script>
  </body>
</html>