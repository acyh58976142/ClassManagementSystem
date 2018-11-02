/*日期插件input_queryDate  */
			$("#birthDate").datetimepicker(
					{
				        language: 'zh-CN',//显示中文
				        format: 'yyyy-mm-dd',//显示格式
				        minView: "month",//设置只显示到月份
				        initialDate: new Date(),
				        autoclose: true,//选中自动关闭
				        todayBtn: true,//显示今日按钮
					}		
			)
			$("#graduationTime").datetimepicker(
					{
				        language: 'zh-CN',//显示中文
				        format: 'yyyy-mm-dd',//显示格式
				        minView: "month",//设置只显示到月份
				        initialDate: new Date(),
				        autoclose: true,//选中自动关闭
				        todayBtn: true,//显示今日按钮
					}		
			)
			
/**
 * 行的点击事件
 */
$("#student_table").delegate("tbody tr", "click",function(){
		$(this).addClass('common_checked_tr').siblings().removeClass('common_checked_tr').end();
});
			
			/**
			 * 新增按钮点击事件，弹出新增模态框
			 */
			$("#student_add_btn").click(function(){
				$(".form-control").val("");//清空表单数据
				$(".error").remove();
								
			/*	$(".equipInfo_upload").show();*/
				var trs = $("#student_table tbody tr.common_checked_tr");
				if(trs.length>0){
					$(trs.eq(0).closest('tr')).removeClass('common_checked_tr');
				}
				$("#myModalLabel").html("新增学生信息");
				$('#student_modal').modal({show:true,backdrop:'static'});
			});	
			
			
			/**
			 * 修改按钮点击事件，弹出修改模态框
			 */
			 $("#student_update_btn").click(function(){
					
					$(".form-control").val("");//清空表单数据
					
					$("div.error").remove();	  
					
					
					var trs = $("#student_table tbody tr.common_checked_tr");
					if(trs.length<=0){
						alert("请选择一条数据!");
					}else{
						var tr = trs.eq(0).closest('tr');
						var id = trs.children().eq(0).html();
							
						$.ajax({
							"type": 'post',	//post防止中文参数乱码
							"url": "client/StudentServlet?method=getStudentById", 
							"data":{id :id},
							/* "dataType": "text",   */
							"success" :function(data){
								
								if(data == null || data =="")
								{
									return;
								}
								
								/* var score=JSON.stringify(data);  */  
							    var student = JSON.parse(data);   
							    /*     var score = data; */
							    /* var object = new Date(score.scoreDate);
								var tt = object.format("YYYY-MM-DD"); */
							    $("#studentId").val(student.id);
								$("#studentName").val(student.studentName);	
								$("#studentNumber").val(student.studentNumber);	
								$("#team").val(student.team);       
								$("#birthDate").val(student.birthDate);
								$("#graduationStudy").val(student.graduationStudy);					
								$("#profession").val(student.profession);       
								$("#graduationTime").val(student.graduationTime);       
								$("#studentMobilephone").val(student.studentMobilephone);   
								$("#studentEmail").val(student.studentEmail);   
																
								var img = "upload/"+ student.imgPath;
								$(".modalimg").attr("src",img);
								
								$('#student_modal').modal({show:true,backdrop:'static'});
							},
							"error": function(e) {
								alert("查询异常!");
							}
						});	
						
						$("#myModalLabel").html("修改学生信息");
						$('#student_modal').modal({show:true,backdrop:'static'});
					}
					
				});
			
			
			/**
			 * 模态框保存按钮点击事件
			 */
		    $("#student_save_btn").click(function(){
		    	
		    	var isSuccess = formValidate().form();
		    	if(!isSuccess){return;}
		    	
		    	
		    	$('#student_save_btn').attr("disabled",true);
		    	
		    	var trs = $("#student_table tbody tr.common_checked_tr");
		    	var id = trs.children().eq(0).html();
		    	
		    	
				var filepath = $(".modalimg")[0].src;
				var arr = filepath.split('/');
				var relfile=arr[arr.length-1];
				
				
				/*var relfile2 = filepath.lastIndexOf("-");
					alert(relfile2);	
				var relfile = filepath.substring(51);
				alert(relfile);*/
				
				
		    	
				
		    	var studentName =$("#studentName").val();
		    	var studentNumber =$("#studentNumber").val();
		    	var team =$("#team").val();
		    	var birthDate =$("#birthDate").val();
		    	var graduationStudy =$("#graduationStudy").val();
		    	var profession =$("#profession").val();
		    	var graduationTime =$("#graduationTime").val();
		    	var studentMobilephone =$("#studentMobilephone").val();
		    	var studentEmail =$("#studentEmail").val();
		    	
		    	var url= "";
		    	if(id==null||id==""||id==undefined){
		    		url= "client/StudentServlet?method=saveStudent"; //添加
		    	}
		    	else{
		    		url= "client/StudentServlet?method=updateStudent";//修改
		    	}
		    
		    	
		    	$.ajax({
		    		type : "post",
		    		url : url,
		    		dataType : "text",
		    		data : {
		    			    id : id,
		    			    studentName : studentName,
		    			    studentNumber : studentNumber,
		    			    team : team,
		    			    birthDate : birthDate,
		    			    graduationStudy : graduationStudy,
		    			    profession : profession,
		    			    graduationTime : graduationTime,
		    			    studentMobilephone : studentMobilephone,
		    			    studentEmail : studentEmail,
		    			    relfile : relfile
		    		},
		    		success : function(result) {
		    			if(result!=0){
		    				
		    				$('#student_save_btn').removeAttr("disabled");
			    			
		    				alert("操作成功!");
		    				$('#student_modal').modal("hide");
		    				$(".form-control").val("");//清空表单数据
		    				window.location.reload();
		    				/* $('#stationInfo_table').DataTable().ajax.reload(); */
		    				
		    			}else{
		    				
		    				alert("系统错误：学号重复，保存失败！");
		    				
		    			}
		    		
		    			
		    	},
		    		error : function(result) {
		    			$('#student_save_btn').removeAttr("disabled");
		    			alert("保存异常!");
		    		}
		    	});
		    	
		    });			
		    
		    
		    /**
			 * 删除按钮点击事件
			 */
			$("#student_delete_btn").click(function(){
				
				var trs = $("#student_table tbody tr.common_checked_tr");
				if(trs.length<=0){
					alert("请选择一条数据!");
				}else{
					var id = trs.children().eq(0).html();
				
					
					var flag = confirm("确定要删除吗?");
					if(!flag){return false;}
					$.ajax({
						"type": 'post',	//post防止中文参数乱码
						"url": "client/StudentServlet?method=delStudent",
						"data":{id :id},
						"dataType": "text",  
						"success": function(result){
								if(!(result == 0)){
									alert("删除成功!");
									window.location.reload();
								/* 	$('#score_table').DataTable().ajax.reload(); */
								}else {
									alert("删除失败!");
									/* $('#score_table').DataTable().ajax.reload(); */
								}
						},
						"error": function(e) {
							alert("删除异常!");
						}
					});			    
				}
			})	
				
				/**
				 * 查看按钮点击事件，弹出修改模态框
				 */
				 $("#lookstudent").click(function(){
						
						$(".form-control").val("");//清空表单数据
						$("div.error").remove();	  
						$(".error").remove();
						
						var trs = $("#student_table tbody tr.common_checked_tr");
						if(trs.length<=0){
							alert("请选择一条数据!");
						}else{
							var tr = trs.eq(0).closest('tr');
							var id = trs.children().eq(0).html();
								
							$.ajax({
								"type": 'post',	//post防止中文参数乱码
								"url": "client/StudentServlet?method=getStudentById", 
								"data":{id :id},
								/* "dataType": "text",   */
								"success" :function(data){
									
									if(data == null || data =="")
									{
										return;
									}
									
									/* var score=JSON.stringify(data);  */  
								    var student = JSON.parse(data);   
								    /*     var score = data; */
								    /* var object = new Date(score.scoreDate);
									var tt = object.format("YYYY-MM-DD"); */
								    $("#studentIdB").val(student.id);
									$("#studentNameB").val(student.studentName);	
									$("#studentNumberB").val(student.studentNumber);	
									$("#teamB").val(student.team);       
									$("#birthDateB").val(student.birthDate);
									$("#graduationStudyB").val(student.graduationStudy);					
									$("#professionB").val(student.profession);       
									$("#graduationTimeB").val(student.graduationTime);       
									$("#studentMobilephoneB").val(student.studentMobilephone);   
									$("#studentEmailB").val(student.studentEmail);   
																	
									var img = "upload/"+ student.imgPath;
									$(".modalimgB").attr("src",img);
									
									$('#student_modalB').modal({show:true,backdrop:'static'});
								},
								"error": function(e) {
									alert("查询异常!");
								}
							});	
							
							/*$("#myModalLabelB").html("查看学生信息");*/
							$('#student_modalB').modal({show:true,backdrop:'static'});
						}
						
					});
				
		    
		    /*上传图片  */
			$(".equipInfoAdd_uploadFile").click(function(e){
				
				var thiz = e.currentTarget;
				var prev = $(thiz).prev();
				var fileInfo = $(thiz).closest(".equipInfoAdd_loadFile").find(".equipInfoAdd_file");
				// 上传附件之前  当前是否已经上传文件,存在时提示
				
				if($(fileInfo).text() == ""){
					$("#upload").click();
				}else{
					alert("已存在图片,请删除后再执行该操作!");
				}
				
			})
			
			$(".equipInfo_upload").change(function(e){
				
				var thiz = e.currentTarget;
				/*var fileSize = 0;
				var filemaxsize = 1024*2;*/	
				var file = $(thiz).val();
				var relfile = file.substring(12);
				
				var isnext = false;
				var filetypes =[".jpg",".png",".gif",".jpeg",".bmp"];
				var fileend = file.substring(file.lastIndexOf("."));
				if(file == ''){
					alert('请选择需要上传的文件');
					return;
				}
				for(var i =0; i<filetypes.length;i++){
			        if(filetypes[i]==fileend){
			            isnext = true;
			            break;
			        }
			    }
				 if(!isnext){
					 alert("只能上传.jpg .png .jpeg .gif .bmp类型的文件!");
			         return;
			     }
				 
				    var filePath = $("#upload").val();
					var relfile = file.substring(12);
					alert(filePath);
				   	$(".modalimg").attr("src",filePath);   /* 将图片路径存入src中，显示出图片 */
		            
				
				 /*fileSize = $(thiz).files[0].size; 
				 if(fileSize>filemaxsize){
					 Tools.tipsMsg("附件大小不能大于"+filemaxsize/1024+"M！");   
			         return ;
			     }*/
				/*var imagSize = document.getElementById("upload").files.size;  
				if(imagSize < 1024*1024*0.1){
					Tools.tipsMsg('图片大小在3M以内');
					return;
				}*/
				var options={   
			         url : "client/UploadServlet?method=uploadFileimages",
			         type :'post',
			         data : null,
			         dataType : "json",
			         success : function(result){
			        	   // 清空文件
			        	   $(thiz).val("");
			        	    
			        	     
			    	 		var img = "upload/"+result;
			    	 		$(".modalimg").attr("src",img);
			    	 		var fileInfo = $(".equipInfoAdd_loadFile").find(".equipInfoAdd_file");
			    	 		var btn = $(".equipInfoAdd_loadFile").find("button");
			    	 		var label = $(fileInfo).prev();
			    	 		$(fileInfo).attr("filePath",result);
			    	 		//$(fileInfo).attr("newName",data.newName);
			    	 		$(fileInfo).after("<button class='btn btn-info equipInfoAdd_delFile  btn-line-height'><i class='fa fa-trash-o'></i>&nbsp;删除图片</button>");
			    	 		$(btn).hide();

			    		 	alert('上传文件成功');
			    	 	
			         },
			         "error": function(e){
			        	 alert('上传文件失败,服务器端发生异常!');
						} 
			    };
				
			   $("#uploadForm").ajaxSubmit(options); 
				
			})
			
						
					/**
				 * 删除图片
				 */
				 $(".equipInfoAdd_delFile").click(function(){
					 
					 
					 
				 })
				 
				 
				 /**
				  * 验证事件
				  */
			 function formValidate(){
				
				 // 电话号码验证
		         $.validator.addMethod("isMobile", function(value, element) {
		         var length = value.length;
		         var mobile = /^((13|14|15|16|17|18|19)\d{9})$|^(\d{3,4}-\d{7,8})$|^\d{8}$/;  
		         return this.optional(element) || (mobile.test(value));
		         },"请输入正确的手机号和固定电话");
		       /*  //时间不能大于今天
		         $.validator.addMethod("", function(value, element) {	
					var  = $("#").val();//保质期结束
					var  = $("#").val();//保质期开始
					 = .replace(/-/g,"/");
					 = .replace(/-/g,"/");
					 var returnVal = false; 
		            if(new Date(input_guaranteeend_date)>=new Date(input_guaranteestart_date) || input_guaranteeend_date==""){  
		                returnVal = true;  
		            }  
		             return returnVal;  
		        },"保质期结束时间要大于保质期开始时间"); */
		         $.validator.setDefaults({  
		    		 errorPlacement :	function(error, element) { 
		    		     error.appendTo(element.parent()); 
		    		 }
		    		 }); 
				 validator = $("#modalForm").validate({//"验证"
						focusInvalid:false,
				        rules: {
				        	studentName:{
				        		required: true,
				        		maxlength:32
				        	},
				        	studentNumber:{
				        		required: true,
				        		number : true,
				        		maxlength:32
				        		
						    },
						    team:{
						    	required: true,
						    	maxlength:16
				        	},
				        	graduationStudy:{
				        		required: true,
						    	 maxlength:32
				        	},	
				        	profession:{
				        		required: true,
				        		maxlength:32
				        	},
				        	studentMobilephone:{
				        		isMobile:true,
						    	maxlength:64
						    },
						    studentEmail:{
				        		 maxlength:64
						    },
						    birthDate:{
						    	required: true
						    },
						    graduationTime:{
						    	required: true
						    },	    
				        },
				    });
			
			return validator;
			}
				 
			 
			function AutomateExcel(){ 
				var elTable = document.getElementById("student_table"); //要导出的table id。
				var oRangeRef = document.body.createTextRange();  
				oRangeRef.moveToElementText(elTable); 
				oRangeRef.execCommand("Copy"); 
				var appExcel = new ActiveXObject("Excel.Application"); 
				appExcel.Workbooks.Add().Worksheets.Item(1).Paste(); 
				appExcel.Visible = true;   
				appExcel = null; 
				}
			/*
			 * 
			 */
		$("#student_excel").click(function(){
			
			AutomateExcel();
			
		})	
			/*
			 * 
			 */	 
	/*	$("#student_excel").click(function(){
			
			var set = [];
			$('#student_table').each(function(index) {
			    console.log(index)
			    
			    var table = [];
			    $(this).find('tr').each(function() {
			        var row = [];
			        $(this).find('th,td').each(function() {
			            row.push($(this).text().trim());
			        });
			        table.push(row);
			       
			    });
			 
			    set.push(table);
			    
			   
			})	 
			 	 
			 alert(set);
			
			$.ajax({
	    		type : "post",
	    		url : "client/StudentServlet?method=downExcel",  
	    		dataType : "text",
	    		data : {
	    			   "set" : set
	    		},
	    		success : function(data) {
	    		
	    			
	    				alert("导出成功!");
	    				
	    			
	    	},
	    		error : function(e) {
	    			
	    			alert("导出失败!");
	    		}
	    	});
		})*/