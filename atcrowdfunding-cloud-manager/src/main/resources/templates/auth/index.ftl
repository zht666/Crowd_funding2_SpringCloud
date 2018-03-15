<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/font-awesome.min.css">
	<link rel="stylesheet" href="/css/main.css">
	<link rel="stylesheet" href="/css/pagination.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 流程管理</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> 张三 <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="index.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
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
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed" >
						<a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;display:none;">
							<li style="height:30px;">
								<a href="user.html"><i class="glyphicon glyphicon-user"></i> 用户维护</a> 
							</li>
							<li style="height:30px;">
								<a href="role.html"><i class="glyphicon glyphicon-king"></i> 角色维护</a> 
							</li>
							<li style="height:30px;">
								<a href="permission.html"><i class="glyphicon glyphicon-lock"></i> 许可维护</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon-ok"></i> 业务审核 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;display:none;">
							<li style="height:30px;">
								<a href="auth_cert.html"><i class="glyphicon glyphicon-check"></i> 实名认证审核</a> 
							</li>
							<li style="height:30px;">
								<a href="auth_adv.html"><i class="glyphicon glyphicon-check"></i> 广告审核</a> 
							</li>
							<li style="height:30px;">
								<a href="auth_project.html"><i class="glyphicon glyphicon-check"></i> 项目审核</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item">
						<span><i class="glyphicon glyphicon-th-large"></i> 业务管理 <span class="badge" style="float:right">7</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="cert.html"><i class="glyphicon glyphicon-picture"></i> 资质维护</a> 
							</li>
							<li style="height:30px;">
								<a href="type.html"><i class="glyphicon glyphicon-equalizer"></i> 分类管理</a> 
							</li>
							<li style="height:30px;">
								<a href="process.html" style="color:red"><i class="glyphicon glyphicon-random"></i> 流程管理</a> 
							</li>
							<li style="height:30px;">
								<a href="advertisement.html"><i class="glyphicon glyphicon-hdd"></i> 广告管理</a> 
							</li>
							<li style="height:30px;">
								<a href="message.html"><i class="glyphicon glyphicon-comment"></i> 消息模板</a> 
							</li>
							<li style="height:30px;">
								<a href="project_type.html"><i class="glyphicon glyphicon-list"></i> 项目分类</a> 
							</li>
							<li style="height:30px;">
								<a href="tag.html"><i class="glyphicon glyphicon-tags"></i> 项目标签</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed" >
						<a href="param.html"><i class="glyphicon glyphicon-list-alt"></i> 参数管理</a> 
					</li>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
			  
<form id="uploadForm" action="/process/upload" method="post" enctype="multipart/form-data" style="display:none;">
	<input type="file" name="procDefFile">
</form>
<button type="button" id="uploadBtn" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-upload"></i> 上传流程定义文件</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th>流程名称</th>
                  <th>流程版本</th>
                  <th>任务名称</th>
                  <th>会员名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody id="pdTbody">
              
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<div id="Pagination" class="pagination"></div>
					 </td>
				 </tr>

			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="/jquery/jquery-2.1.1.min.js"></script>
    <script src="/jquery/jquery.pagination.js"></script>
    <script src="/jquery/jquery.form.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
	<script src="/script/docs.min.js"></script>
	<script src="/layer/layer.js"></script>
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
			    
			    pageQuery(0);
            });
            
			function pageQuery(pageIndex){
            	
	            var loadingIndex = 0;
	            var pageno = pageIndex + 1;
	            var pagesize = 10;
	            var dataObj = {pageno : pageno, pagesize : pagesize};

	            $.ajax({
            		type : "POST",
            		url  : "${APP_PATH}/auth/pageQuery",
            		data : dataObj,
            		beforeSend : function(){
            			loadingIndex = layer.msg('处理中', {icon: 16});
            		},
            		success : function(result){
            			layer.close(loadingIndex);
            			if(result.success){
            				var taskPage = result.data;
            				var tasks = taskPage.datas;
            				
							var content = "";
							$.each(tasks, function(index, task){
								content += '<tr>';
				                content += '  <td>'+(index+1)+'</td>';
				                content += '  <td>'+task.pdname+'</td>';
				                content += '  <td>'+task.pdversion+'</td>';
				                content += '  <td>'+task.name+'</td>';
				                content += '  <td>'+task.membername+'</td>';
				                content += '  <td>';
								content += '      <button type="button" onclick="forwardPage(\''+task.memberid+'\', \''+task.id+'\')" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
								content += '  </td>';
				                content += '</tr>';
							});
							$("#pdTbody").html(content);//html替换原来的内容，append在原来的内容之后追加
							
							//利用分页插件分页查询
							$("#Pagination").pagination(taskPage.totalPagesize, {
								num_edge_entries: 1, //边缘页数
								num_display_entries: 4, //主体页数
								callback: pageQuery,
								prev_text:"上一页",
								next_text:"下一页",
								link_to:"javascript:;",
								current_page:pageIndex,
								defaultflg:false,
								items_per_page:pagesize //每页显示2项
							});
							
							/*
							var pageContent = "";//分页操作
							if(pageno != 1){
								pageContent += '<li><a href="javascript:;" onclick="pageQuery(1)">首页</a></li>';
								pageContent += '<li><a href="javascript:;" onclick="pageQuery('+ (pageno-1) +')">上一页</a></li>';
							}
							
							for(var i = 1; i <= userPage.totalPageno; i++){
								if(pageno == i){
									pageContent += '<li class="active"><a href="javascript:;">'+i+'</a></li>';
								}else{
									pageContent += '<li><a href="javascript:;" onclick="pageQuery('+ i +')">'+i+'</a></li>';
								}
							}
							
							if(pageno != userPage.totalPageno && userPage.totalPageno != 0){
								pageContent += '<li><a href="javascript:;" onclick="pageQuery('+ (pageno+1) +')">下一页</a></li>';
								pageContent += '<li><a href="javascript:;" onclick="pageQuery('+ userPage.totalPageno +')">末页</a></li>';
							}
							$(".pagination").html(pageContent);
							*/
							
            			}else{
            				layer.msg("用户分页查询失败", {time:2000, icon:5, shift:6}, function(){
          				    });
            			}
            		}
            	});
            }
			
			function forwardPage(memberid, taskid){
				window.location.href = "${APP_PATH}/auth/detail?memberid=" + memberid + "&taskid=" + taskid;
			}
			
        </script>
  </body>
</html>
