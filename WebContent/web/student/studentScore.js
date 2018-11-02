function homeWork() {

	/*初始化数据*/
//	Tools.bind(this, this.initTable());
	
	/* 查询*/
//	$("#sluice_search_btn").on("click", this.searchSluice);
	
	/*新增*/
	$("#sluice_add_btn").on("click",this.initModal);
		
	/*保存*/
    $("#sulice_save_btn").on("click", Tools.bind(this,this.saveSluice));
    
	/*清空数据*/
 //   this.clearData();
    
    /*删除*/
//	$("#sluice_delete_btn").on("click",this.confirmDelete);
	
	/* 修改按钮点击事件，弹出修改模态框*/
	$("#sluice_update_btn").on("click", Tools.bind(this,this.updateModal));
	
    /*行的点击事件*/
	$("#sluice_table").delegate("tbody tr", "click",this.clickTr);
	
	/*表单验证*/
//	this.formValidate();
};


/**
 * 查询
 * 
 */
/*HomeWork.prototype.searchSluice = function() {
		$('#sluice_table').DataTable().ajax.reload();	
};*/


/**
 * 初始化表格
 * 
 */
/*HomeWork.prototype.initTable = function(){
	
	var colum = [
	   {
		"data" : "index"
	}, {
		"data" : "name"
	}, {
		"data" : "type",
			"render":function(data){
		    	switch(data){
		    	case "1": data="节制闸";
		    	break;
		    	case "2": data="进水闸";
		    	break;
		    	case "3": data="退水闸";
		    	break;
		    	case "4": data="冲沙闸";
		    	break;
		    	case "5": data="分洪闸";
		    	break;
		    	case "6": data="挡潮闸";
		    	break;
		    	default:
		    	break;
		    	}
		    		return data;
		    	}
	}, {
		"data" : "building_level"
	},{
		"data" : "position",
			"render":function(data){
				if(data !="" && data != null && data.length>16)
					return '<span title="'+data+'">'+data.substring(0,15)+'...</span>';
				
				return data;
	 		}
	}, {
		"data" : "gate_num",
		"render":function(data){
			if(data !="" && data != null && data.length>16)
				return '<span title="'+data+'">'+data.substring(0,15)+'...</span>';
			
			return data;
 		}
	}, {
		"data" : "gate_width"
	}, {
		"data" : "defign_flow"
	}]
	
	$('#sluice_table').DataTable({
		"bDestroy": true,// 刷新数据
		"language": dataTableLang,//语言
		"dom" : "t" + "<'row row_page '<'col-sm-6'i><'col-sm-6'p>>",	
		"autoWidth":false,//自适应宽度
		"paginate": true,
		"bSort": false,//排序?
		"bProcessing": false,
		"paging" : true,//是否分页
		"bServerSide": true,//服务器端分页
		"bInfo" : true,// 页脚信息
		"pageLength" : pageSize,//每页显示的条数
		"columns" : colum,//对应列
		"ajax": Tools.bind(this,this.querySluice)
	});
};*/


/**
 * 数据查询
 *  @param {object} data 查询条件里面有name水闸名称，callback回调
 */
/*HomeWork.prototype.querySluice = function(data, callback, settings) {
	// 查询参数
	data["type"] = $.trim($("#querytype").val()); // 闸站类型
	data["name"] = $.trim($("#queryname").val());// 闸站名称
			
    var url = path + "/irrigationmanager/sluice/querySluice.action";
	var successAction = function(data){
		if(Tools.isEmpty(data))
		{
			callback(Tools.nullDataTable());
			return;
		}
		callback(data);
	};
	$.ajax({
		"type": 'post',	//post防止中文参数乱码
		"url": url,
		"data":data,
		"dataType": "json",  
		"success": Tools.bind(this,successAction),
		"error": function(e) {
		}
	});
}*/


/**
 * 清空数据
 */
/*HomeWork.prototype.clearData = function(){
	$(".form-control").val("");
	$("#select_channel_id").html('<option value="">--请选择--</option>');
};*/


/**
 * 关闭模态窗
 */
HomeWork.prototype.closeModal = function(){
	$('#sluice_modal').modal("hide");
	
};


/**
 * 新增按钮点击事件，弹出新增模态框
 */
HomeWork.prototype.initModal = function(){
	sluice.clearData();
	$("div.error").remove();
	var trs = $("#sluice_table tbody tr.common_checked_tr");
	if(trs.length>0){
		$(trs.eq(0).closest('tr')).removeClass('common_checked_tr');
	}
	sluice.queryUpName();	  
	$("#myModalLabel").html("新增水闸");
	$('#sluice_modal').modal({show:true,backdrop:'static'});

};


/**
 * 修改按钮点击事件，弹出修改模态框
 */
HomeWork.prototype.updateModal = function(){
	sluice.clearData();
	$("div.error").remove();
	sluice.queryUpName();	  
	$("#myModalLabel").html("修改水闸");
	sluice.clickSelect(1);
};


/** 获取页面上模态文本框的数据 */
HomeWork.prototype.getViewParams = function(){ 
	var sluId;
	var trs = $("#sluice_table tbody tr.common_checked_tr");
	if(trs.length>0){
		var tr = trs.eq(0).closest('tr');
		var data = $('#sluice_table').dataTable().fnGetData(tr);
		sluId  = data.id;//ID	
	}
	
	var slu = {
			id: sluId,
			code: $.trim($("#input_code").val()),		 //水闸编号
	        name: $.trim($("#input_name").val()),        //水闸名称
	        building_level: $("#input_building_level").val(),      //主要建筑物级别
	        gate_num: $("#input_gate_num").val(),       //闸孔数量
	        gate_width: $("#input_gate_width").val(),        //闸孔总净宽
	        position: $("#input_position").val(),     //地理位置
	        defign_flow: $("#input_defign_flow").val(), //过闸流量
	        channel_id: $("#select_channel_id").val(),  //所属渠道编号
	        type: $("#input_type").val()    //水闸类型
	}

	return slu;
};


/**初始化查询上级渠道名称*/
/*HomeWork.prototype.queryUpName= function(){
	var type = "";  //默认查斗渠
	$.ajax({
		type : 'post',
		url : path + '/irrigationmanager/channel/queryUpName.action',
		data:{ type : type},
		success : function(msg) {
			var option = "";
			for (var i = 0; i < msg.length; i++) {
				option += "<option value='" + msg[i].id + "'>"
						+ msg[i].name + "</option>";
			}
			$("#select_channel_id").append(option);
		}
	});
}; */


/**
 * 保存
 * 
 */
/*HomeWork.prototype.saveSluice = function(){
	var isSuccess = sluice.formValidate().form();
	if(!isSuccess){return;}
	
	$("#sulice_save_btn").attr("disabled",true);
	var slu = this.getViewParams();
	var id=slu.id;//ID

	var url= "";
	if(id==null||id==""||id==undefined){
		url=path + "/irrigationmanager/sluice/saveSluice.action"; //添加
	}
	else{
		url=path + "/irrigationmanager/sluice/updateSluice.action";//修改
	}
	var successAction = function(data) {
		$("#sulice_save_btn").removeAttr("disabled");
		if(data != null){
			if(data.msg == "success"){
				Tools.tips("操作成功！");
				this.closeModal();
				this.clearData();//清空表单数据
				$('#sluice_table').DataTable().ajax.reload();
			}else if(data.msg == "error"){
				Tools.tipsMsg("保存异常！");
				
			}
		}else{
			Tools.tipsMsg("异常！");//”warning”, “error”, “success” 和 “info”
		}
	};
	
	$.ajax({
		type : "post",
		url : url,
		data : {
			param : JSON.stringify(slu),
		},
		success : Tools.bind(this,successAction),
		error : function(e) {
			$('#sulice_save_btn').removeAttr("disabled");
			Tools.tipsMsg("异常！");
		}
	});
	
};*/


/**
/ * 行的点击事件
*/
HomeWork.prototype.clickTr = function(){
	$(this).addClass('common_checked_tr').siblings().removeClass('common_checked_tr').end();
};


/**
 * 选择点击事件
 * @param clickSelect（0删除，1给模态框赋值）
 */
/*HomeWork.prototype.clickSelect = function(flag){
	var trs = $("#sluice_table tbody tr.common_checked_tr");
	if(trs.length<=0){
		Tools.tipsMsg("请选择水闸！");
	}else{
		var tr = trs.eq(0).closest('tr');
		var data = $('#sluice_table').dataTable().fnGetData(tr);
		var id = data.id;
	    if(flag==0){
	      sluice.deleteSluice(id);
	    }
	    else if(flag==1){
	      
	      sluice.bindData(id); 
	    }	
	}
};*/


/**
 * 给模态框赋值
 * @param {string} id 水闸主键 
 */
/*HomeWork.prototype.bindData = function(id){
	var success = function(data){
		if(data == null || data =="")
		{
			return;
		}
		var slu = data;
	
		$("#input_code").val(slu.code);		   //水闸编号
		$("#input_name").val(slu.name);         //水闸名称
		$("#input_type").val(slu.type);         //水闸名称
		$("#input_building_level").val(slu.building_level);   //主要建筑物级别
		$("#input_gate_num").val(slu.gate_num);    //闸孔数量
		$("#input_gate_width").val(slu.gate_width);    //闸孔总净宽
		$("#input_position").val(slu.position);    //地理位置
		$("#input_defign_flow").val(slu.design_flow);    //过闸流量
		$("#select_channel_id").val(slu.channel_id);     //所属渠道编号
		

	    $('#sluice_modal').modal({show:true,backdrop:'static'});
	 
	};
	
	var url = path + "/irrigationmanager/sluice/queryOneSluice.action";
	$.ajax({
		type : "post",
		url : url,
		data : {
			id : id
		},
		success : Tools.bind(this,success),
		error : function(e) {
			Tools.tipsMsg("异常！");
		}
	});
};*/


/**
 * 删除
 *  @param {string} id 渠道主键
 */
/*HomeWork.prototype.confirmDelete = function(){	
	
	sluice.clickSelect(0);
};
HomeWork.prototype.deleteSluice=function(id){
	
	Tools.tipsConfirm("确定要删除吗?",function(){
			$.ajax({
				"type": 'post',	//post防止中文参数乱码
				"url": path+"/irrigationmanager/sluice/deleteSluice.action",
				"data":{id :id},
				"dataType": "json",  
				"success": function(data){
					if(data.state=='success'){
						Tools.tips("删除成功！");
						$('#sluice_table').DataTable().ajax.reload();
					}else if(data.state=='error'){
						Tools.tipsMsg("删除失败！");  
						$('#sluice_table').DataTable().ajax.reload();
						}
		        },
				"error": function(e) {
					Tools.tipsMsg("异常！");
				}
			});
		},true);
};*/


/**
 *表单验证
 */              
/*HomeWork.prototype.formValidate = function(){
		 $.validator.setDefaults({ errorPlacement : errorPlacement });  
		 $.validator.addMethod("tnsNumber", function(value, element) {
		        var tns =  /^\d+(\.\d{2}|\.\d{1})?$/;
		        return this.optional(element) || (tns.test(value));
			    },"小数点后最多保留两位");
		 validator = $("#modalForm").validate({//"验证"
				focusInvalid:false,
		        rules: {
		        	input_name:{
		        		required: true,
		        		maxlength:64
		        	},
		        	input_code:{				    
				        maxlength:32
				    },
				    input_defign_flow:{
				    	required: true,
				    	number : true,
				    	tnsNumber:true,
				    	max : 9999999999.99
		        	},
		        	input_building_level:{
		        		digits : true,
		        		max:5,
		        		min:1
		        	},	
		        	input_gate_num:{
		        		digits : true,
				    	max : 1000
		        		
				    },
				    input_gate_width:{
				    	number : true,
				    	tnsNumber:true,
				    	max : 9999999999.99
		        	},
		        	input_position:{
		        		maxlength:256
				    },
				    input_channel_id:{
				    	number : true
		        	},	
		        	
				    
		        },
		    });
		  
	return validator;
};*/