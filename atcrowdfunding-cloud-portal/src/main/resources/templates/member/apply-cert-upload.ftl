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
      <div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			  <div class="container">
				<div class="navbar-header">
				  <a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a>
				</div>
            <div id="navbar" class="navbar-collapse collapse" style="float:right;">
              <ul class="nav navbar-nav">
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i> 张三<span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="member.html"><i class="glyphicon glyphicon-scale"></i> 会员中心</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
                    <li class="divider"></li>
                    <li><a href="index.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
                  </ul>
                </li>
              </ul>
            </div>
			  </div>
			</nav>

      </div>
    </div>

    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>实名认证 - 申请</h1>
      </div>

		<ul class="nav nav-tabs" role="tablist">
		  <li role="presentation" ><a href="#"><span class="badge">1</span> 基本信息</a></li>
		  <li role="presentation" class="active"><a href="#"><span class="badge">2</span> 资质文件上传</a></li>
		  <li role="presentation"><a href="#"><span class="badge">3</span> 邮箱确认</a></li>
		  <li role="presentation"><a href="#"><span class="badge">4</span> 申请确认</a></li>
		</ul>
        
		<form id="uploadForm" action="${APP_PATH}/member/uploadCerts" method="post" role="form" enctype="multipart/form-data" style="margin-top:20px;">
		  <div class="form-group">
		  	<#list certs as cert>
				<input type="hidden" name="mcs[${cert_index}].certid" value="${cert.id}"><!-- 通常把隐藏域放到最上面 -->
				<label for="exampleInputEmail1">${cert.name}</label>
				<input type="file" name="mcs[${cert_index}].file" class="form-control" >
	            <br>
	            <img src="" style="display: none;"><!-- 预览图片 -->
	            <br>
		  	</#list>
		  </div>
          <button type="button" onclick="window.location.href='apply.html'" class="btn btn-default">上一步</button>
		  <button type="button" onclick="doNext()"  class="btn btn-success">下一步</button>
		</form>
		<hr>
    </div> <!-- /container -->
        <div class="container" style="margin-top:20px;">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div id="footer">
                        <div class="footerNav">
                             <a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a rel="nofollow" href="http://www.atguigu.com">服务条款</a> | <a rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a rel="nofollow" href="http://www.atguigu.com">联系我们</a>
                        </div>
                        <div class="copyRight">
                            Copyright ?2017-2017 atguigu.com 版权所有
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/jquery.form.js"></script>
	<script>
        $('#myTab a').click(function (e) {
          e.preventDefault()
          $(this).tab('show')
        });      
        
        $(":file").change(function(event){
			//获取当前选择的文件 event.target.files
			//下面相当于var file = null;
            var files = event.target.files, file;
            if (files && files.length > 0) {
                file = files[0]; 
                //当前窗口的路径URL对象
                var URL = window.URL || window.webkitURL;
                //URL对象动态获取图片文件路径
                var imgURL = URL.createObjectURL(file);
                
                var imgObj = $(this).next().next();
                imgObj.attr("src", imgURL);
                imgObj.show();
            }

        });
        
        function doNext(){
        	//提交表单，上传文件
	    	//AJAX上传文件
	    	$("#uploadForm").ajaxSubmit({
	    		beforeSubmit : function(){ //提交前的回调函数
	    			
	    		},
	    		resetForm: true, //成功提交后，重置所有表单元素的值 
	    		success : function(){ //提交后的回调函数
	    			window.location.href = "${APP_PATH}/member/apply";
	    		}
	    	});
        }
        
	</script>
  </body>
</html>