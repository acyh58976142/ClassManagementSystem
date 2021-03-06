//行政处罚组织名称
var initOrganizationName = "泰兴市水务局";

/**
 * 分页条数
 */
var pageSize = 15;
var start;
var end;
$(function() {

	// 全选，全不选操作
	$(document).delegate(".checkboxAll", "click", function() {
		var childrenBox = $(".checkboxOne").toArray();
		if ($(".checkboxAll").prop("checked")) {
			for (var i = 0; i < childrenBox.length; i++) {
				$(childrenBox[i]).prop("checked", true);
			}
		} else {
			for (var i = 0; i < childrenBox.length; i++) {
				$(childrenBox[i]).prop("checked", false);
			}
		}
	});
});

var Tools = {
	// 提示窗，2秒后消失，不带任何按钮
	tips : function(content) {
		layer.msg(content);
	},

	// 提示窗，只有提示信息，如：成功，失败，异常等,带确定按钮
	tipsMsg : function(content) {
		layer.alert(content);
	},

	// 提示窗，只有提示信息，如：成功，失败，异常等,带确定按钮,点击确定执行方法
	tipsMsgWithFunction : function(content, confirmMethod, isCancel) {
		layer.alert(content, {
			closeBtn : 0
		}, function() {
			confirmMethod();
			if (isCancel) {
				layer.closeAll();
			}
		});
	},

	// 对话提示窗
	// msg ： 提示窗提示内容，如：确认删除？确认提交？等；
	// confirmMethod : 确认按钮点击后执行的事件
	// isCancel ： boolean点击确认后是否关闭提示窗，false,不关闭，true：关闭；如果传值为
	// fasle，则需要通过另外的提示信息去关闭
	// 使用方式： Tools.tipsConfirm("确认删除？", 方法名称， true);
	tipsConfirm : function(msg, confirmMethod, isCancel) {
		layer.confirm(msg, {
			btn : [ '确认', '取消' ]
		}, function() {// 第一个按钮执行事件
			confirmMethod();
			if (isCancel) {
				layer.closeAll();
			}
		});
	},

	/**
	 * 预览
	 */
	// 对话提示窗
	tipsfileConfirm : function(msg, confirmMethod, downloadfile, isCancel) {
		layer.confirm(msg, {
			btn : [ '预览', '下载', '取消' ]
		}, function() {// 第一个按钮执行事件
			confirmMethod();
			if (isCancel) {
				layer.closeAll();
			}
		}, function() {// 第二个按钮执行事件
			downloadfile();
			if (isCancel) {
				layer.closeAll();
			}
		});
	},

	// 打开加载层，主要用于请求发送时，避免重复操作时使用
	tipsLoading : function() {
		layer.load(2);
	},

	// 关闭加载层，主要用于请求完成后关闭加载层
	tipsLoaded : function() {
		layer.closeAll();
	},
	/*************laydate 5.0版本重写 Start****************************/
	//需要引入新layDate.js
	// 只选择年份
	// id 时间选择框ID
	loadYearDate : function(id) {
		laydate.render({
			  elem:id,//指定元素
			  type:"year",//设置类型  年选择器
			  theme:"#0EBCEA",//设置背景主题颜色
			  choose : function(datas) {
				$(id).blur();
			}
		});
	},
	loadYearDateCallback : function(id,callbackFunction) {
		laydate.render({
			  elem:id,//指定元素
			  type:"year",
			  theme:"#0EBCEA",
			  choose : function(datas) {
				$(id).blur();
			   },
			  done: function(value){
				callbackFunction(value);
			}
		});
	},
	/**
	 * 只选择年。月份
	 * id 时间选择框ID
	 */
	loadYearMonthDate : function(id) {
		laydate.render({
			  elem:id,//指定元素
			  type:"month",//设置类型  年、 月选择器
			  theme:"#0EBCEA",//设置背景主题颜色
			  choose : function(datas) {
				$(id).blur();
			}
		});
	},
	// HH:mm:ss
	loadTime: function(id) {
		laydate.render({
			  elem:id,//指定元素
			  type:"time",//时间选择器
			  theme:"#0EBCEA",
			  choose : function(datas) {
				$(id).blur();
			}
		});
	},
	//MM-dd HH:mm:ss
	loadDateTimeNoYear: function(id) {
		laydate.render({
			  elem:id,//指定元素
			  type:"datetime",//时间选择器
			  format : "MM-dd HH:mm:ss",// 时间格式
			  isyear : false,
			  theme:"#0EBCEA",
			  choose : function(datas) {
				$(id).blur();
			}
		});
	},
	
	// 时间范围
	// 选中开始时间，结束时间不能小于开始时间，选中结束时间，开始时间不能大于结束时间
	// startId 开始ID
	// endId 结束ID
	// dateType 选择器类型
	loadDateRange : function(startId, endId,dateType) {
		if ((null == dateType || "" == dadateTypete || undefined == dateType)
				&& dateType !="datetime") {
			dateType = "date";
		}
		start ={
			elem : startId,
			type:dateType,//选择器
			theme:"#0EBCEA",
			choose: function(value){
				end.min = value; // 开始日选好后，重置结束日的最小日期
				end.start = value // 将结束日的初始值设定为开始日
				laydate.render(end);
			  }
		};
		end ={
			elem : endId,
			type:dateType,//选择器
			theme:"#0EBCEA",
			choose: function(value){
				start.max = value; // 结束日选好后，重置开始日的最大日期
				console.log(start);
				laydate.render(start);
			  }
		};
	        laydate.render(start);  
	        laydate.render(end);  
	},
	
	//时间 yyyy-MM-dd
	loadDateNew: function(id,callback) {
		laydate.render({
			  elem:id,//指定元素
			  type:"date",//时间选择器
			  format : "yyyy-MM-dd",// 时间格式
			  theme:"#0EBCEA",
			  done : function(datas,a,e) {
				
				callback(datas);
			}
		});
	},
  /*************laydate 5.0版本重写 end****************************/
	
	// 时间 YYYY-MM-DD
	// id 时间选择框ID
	loadDate : function(id) {
		// 时间插件 laydate 官网地址：http://www.layui.com/laydate/
		// 设置laydate皮肤样式，皮肤在/assets/js/laydate/skins下面，需要什么用什么文件名称
		laydate.skin('tianlan');
		laydate({
			elem : id,// 时间控件id
			format : "YYYY-MM-DD",// 时间格式
			istime : false,// 是否显示时分秒选择框
			issure : true, // 是否显示确认
			festival : true,// 显示节假日
			istoday : true, // 是否显示今天
			isclear : false,
			choose : function(datas) {
				$(id).blur();
			}
		});
	},
	
	// 时间 YYYY-MM-DD
	// id 时间选择框ID
	loadMonthDate : function(id) {
		// 时间插件 laydate 官网地址：http://www.layui.com/laydate/
		// 设置laydate皮肤样式，皮肤在/assets/js/laydate/skins下面，需要什么用什么文件名称
		laydate.skin('tianlan');
		laydate({
			elem : id,// 时间控件id
			format : "YYYY-MM",// 时间格式
			istime : false,// 是否显示时分秒选择框
			issure : true, // 是否显示确认
			festival : false,// 显示节假日
			istoday : false, // 是否显示今天
			isclear : false,
			choose : function(datas) {
				$(id).blur();
			}
		});
	},
	// 时间选择后执行方法callBackFunction
	// format : YYYY-MM-DD 或 YYYY-MM-DD hh:mm:ss
	// id 时间选择框ID
	// 时间选择后执行事件 callBackFunction
	loadLayDateCallBack : function(id, format, callBackFunction) {
		// 时间插件 laydate 官网地址：http://www.layui.com/laydate/
		// 设置laydate皮肤样式，皮肤在/assets/js/laydate/skins下面，需要什么用什么文件名称
		laydate.skin('tianlan');
		laydate({
			elem : id,// 时间控件id
			format : format,// 时间格式
			istime : false,// 是否显示时分秒选择框
			issure : true, // 是否显示确认
			festival : true,// 显示节假日
			istoday : true, // 是否显示今天
			isclear : false,
			choose : function(datas) {
				callBackFunction();
			}
		});
	},

	// 时间 YYYY-MM-DD hh:mm:ss
	// id 时间选择框ID
	loadDateTime : function(id) {
		// 时间插件 laydate 官网地址：http://www.layui.com/laydate/
		// 设置laydate皮肤样式，皮肤在/assets/js/laydate/skins下面，需要什么用什么文件名称
		laydate.skin('tianlan');
		laydate({
			elem : id,// 时间控件id
			format : "YYYY-MM-DD hh:mm:ss",// 时间格式
			istime : true,// 是否显示时分秒选择框
			issure : true, // 是否显示确认
			festival : true,// 显示节假日
			isclear : false,
			istoday : true, // 是否显示今天
		});
	},

	// 限定时间段，向前、向后多少天
	// id 时间选择框ID
	// before 向前推多少天
	// after 向后推多少天
	loadPeriodDate : function(id, before, after) {
		// 时间插件 laydate 官网地址：http://www.layui.com/laydate/
		// 设置laydate皮肤样式，皮肤在/assets/js/laydate/skins下面，需要什么用什么文件名称
		laydate.skin('tianlan');
		laydate({
			elem : id,// 时间控件id
			format : "YYYY-MM-DD",// 时间格式
			istime : false,// 是否显示时分秒选择框
			issure : true, // 是否显示确认
			festival : true,// 显示节假日
			istoday : true, // 是否显示今天
			isclear : false,
			min : laydate.now(-before), // -1代表昨天，-2代表前天，以此类推
			max : laydate.now(+after)
		// +1代表明天，+2代表后天，以此类推
		});
	},
	loadDatePeriodMaxAndMinTime : function(startId, startTime, endId, endTime,
			format) {
		laydate.skin('tianlan');
		if ((null == format || "" == format || undefined == format)
				&& format != "YYYY-MM-DD" && format != "YYYY-MM-DD hh:mm:ss") {
			format = "YYYY-MM-DD";
		}
		var isTime = false;
		if (format == "YYYY-MM-DD hh:mm:ss") {
			isTime = true;
		}
		var start = {
			elem : startId,
			format : format,
			start : startTime,
			min : startTime, // 设定最小日期为当前日期
			max : endTime, // 最大日期
			istime : isTime,
			istoday : false,
			choose : function(datas) {
				end.min = datas; // 开始日选好后，重置结束日的最小日期
				end.start = datas // 将结束日的初始值设定为开始日
			}
		};
		var end = {
			elem : endId,
			format : format,
			start : startTime,
			min : startTime,
			max : endTime,
			istime : isTime,
			istoday : false,
			choose : function(datas) {
				start.max = datas; // 结束日选好后，重置开始日的最大日期
			}
		};
		laydate(start);
		laydate(end);
	},

	// 时间范围
	// 选中开始时间，结束时间不能小于开始时间，选中结束时间，开始时间不能大于结束时间
	// startId 开始ID
	// endId 结束ID
	// format 格式化
	loadRangeDate : function(startId, endId, format) {
		// 时间插件 laydate 官网地址：http://www.layui.com/laydate/
		// 设置laydate皮肤样式，皮肤在/assets/js/laydate/skins下面，需要什么用什么文件名称
		laydate.skin('tianlan');
		if ((null == format || "" == format || undefined == format)
				&& format != "YYYY-MM-DD" && format != "YYYY-MM-DD hh:mm:ss") {
			format = "YYYY-MM-DD";
		}
		var isTime = false;
		if (format == "YYYY-MM-DD hh:mm:ss") {
			isTime = true;
		}
		var start = {
			elem : startId,
			format : format,
			istime : isTime,
			istoday : true,
			isclear: true,
			choose : function(datas) {
				end.min = datas; // 开始日选好后，重置结束日的最小日期
				end.start = datas // 将结束日的初始值设定为开始日
			}
		};
		var end = {
			elem : endId,
			format : format,
			istime : isTime,
			istoday : true,
			isclear : true,
			choose : function(datas) {
				start.max = datas; // 结束日选好后，重置开始日的最大日期
			}
		};
		laydate(start);
		laydate(end);
	},
	bind : function(scope, funct) {
		return function() {
			return funct.apply(scope, arguments);
		};
	},
	// 初始化DataTable的空对象
	nullDataTable : function() {
		var obj = new Object();
		obj.iTotalRecords = 0;
		obj.iTotalDisplayRecords = 0;
		obj.eEcho = "0";
		obj.sColumns = "0";
		obj.aaData = new Array();
		return obj;
	},
	isEmpty : function(data) {
		if (data == null || data == "" || data == undefined) {
			return true;
		}
		return false;
	},

	// 类似bootstarp的model层，这里model层的内容是自己写
	// title 显示的标题
	// width 窗体显示宽度
	// height 窗体显示高度
	// content 窗体显示内容
	// confirmMethod 确定按钮点击事件
	// isCancel
	// boolean值，参数为：true，则点击确定后，关闭窗体，参数为false，则点击确定后窗体不关闭，这时需要另外的一个提示或其他的去关闭
	//
	// 调用方式： Tools.showModel("标题", 500, 400, "<input type='text'
	// id='testmodelinput'>", 单击确定方法, false);
	showModel : function(title, width, height, content, confirmMethod, isCancel) {
		layer.open({
			type : 1,
			skin : 'layui-layer-rim',
			title : title,
			area : [ width + 'px', height + 'px' ],
			content : content,
			btn : [ '确认', '取消' ],
			yes : function() {
				confirmMethod();
				if (isCancel) {
					layer.closeAll();
				}
			},
			btn2 : function() {
				layer.closeAll();
			}
		});
	},
	/*
	 * elem: 'id', //需显示日期的元素选择器 event: 'click', //触发事件 format: 'YYYY-MM-DD
	 * hh:mm:ss', //日期格式 istime: false, //是否开启时间选择 isclear: true, //是否显示清空
	 * istoday: true, //是否显示今天 issure: true, //是否显示确认 festival: true, //是否显示节日
	 * min: '1900-01-01 00:00:00', //最小日期 max: '2099-12-31 23:59:59', //最大日期
	 * start: '2014-6-15 23:00:00', //开始日期 fixed: false, //是否固定在可视区域
	 * zIndex:99999999, //css z-index choose: function(dates){ //选择好日期的回调函数
	 * //dates为当前选择的时间 //Execute the method after the selection }
	 */

	/**
	 * 打印方法
	 * 
	 * 参数htmlInfo为需要打印的内容代码
	 * 
	 * 1、如果htmlInfo不传值（例如：Tools.printHTML()）,打印的为当前页面可见的所有信息
	 * 
	 * 2、htmlInfo传值（例如： 需要打印的html是 var printHtml = "<table>
	 * <tr>
	 * <td>a</td>
	 * <td>a</td>
	 * </tr>
	 * <tr>
	 * <td>b</td>
	 * <td>b</td>
	 * </tr>
	 * </table>"）; 调用方法为：Tools.printHTML(printHtml);
	 * 
	 */
	printHTML : function(htmlInfo) {
		if (null == htmlInfo || "" == htmlInfo || undefined == htmlInfo) {
			htmlInfo = window.document.body.innerHTML
		}
		var htmlJson = {
			"head" : window.document.head.innerHTML,
			"body" : htmlInfo
		}
		var printUrl = path + "/web/print/print.jsp";
		window.localStorage["pringHtml"] = JSON.stringify(htmlJson);
		window.open(printUrl, '_blank');
	},
	getLocationParamString:function(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	},
	randomNum:function(n) {
	    var rnd = "";
	    for (var i = 0; i < n; i++) {
	        rnd += Math.floor(Math.random() * 10);
	    }
	    return rnd;
	},
	randomInRange:function(min,max) {
		var diff=max-min;
		var num = Math.random()*diff + min;
		num = parseInt(num, 10);
	    return num;
	},
	str2Byte:function(str) {  
        var bytes = new Array();  
        var len, c;  
        len = str.length;  
        for(var i = 0; i < len; i++) {  
            c = str.charCodeAt(i);  
            if(c >= 0x010000 && c <= 0x10FFFF) {  
                bytes.push(((c >> 18) & 0x07) | 0xF0);  
                bytes.push(((c >> 12) & 0x3F) | 0x80);  
                bytes.push(((c >> 6) & 0x3F) | 0x80);  
                bytes.push((c & 0x3F) | 0x80);  
            } else if(c >= 0x000800 && c <= 0x00FFFF) {  
                bytes.push(((c >> 12) & 0x0F) | 0xE0);  
                bytes.push(((c >> 6) & 0x3F) | 0x80);  
                bytes.push((c & 0x3F) | 0x80);  
            } else if(c >= 0x000080 && c <= 0x0007FF) {  
                bytes.push(((c >> 6) & 0x1F) | 0xC0);  
                bytes.push((c & 0x3F) | 0x80);  
            } else {  
                bytes.push(c & 0xFF);  
            }  
        }  
        return bytes;  
  
  
    },  
    byte2Str:function(arr) {  
        if(typeof arr === 'string') {  
            return arr;  
        }  
        var str = '',  
            _arr = arr;  
        for(var i = 0; i < _arr.length; i++) {  
            var one = _arr[i].toString(2),  
                v = one.match(/^1+?(?=0)/);  
            if(v && one.length == 8) {  
                var bytesLength = v[0].length;  
                var store = _arr[i].toString(2).slice(7 - bytesLength);  
                for(var st = 1; st < bytesLength; st++) {  
                    store += _arr[st + i].toString(2).slice(2);  
                }  
                str += String.fromCharCode(parseInt(store, 2));  
                i += bytesLength - 1;  
            } else {  
                str += String.fromCharCode(_arr[i]);  
            }  
        }  
        return str;  
    },
    filePreview:function(serverUrl, fileUrl, fileName) {
    	//验证服务地址或文件地址是否为空，如不为空
    	if ((fileUrl != null || fileUrl != "" || undefined != fileUrl) && (serverUrl != null || serverUrl != "" || undefined != serverUrl)) {
    		var fileLoadURL = "";
    		var fileViewURL = "";
    		var fileCheckExists = "";
    		var fileViewExists = "";
    		var index=fileUrl.lastIndexOf(".");
    		//获取文件后缀
    		var suffix = fileUrl.substring((fileUrl.lastIndexOf(".") + 1));
    		var checkSuffix = "";
    		if(null != suffix || undefined != suffix || "" != suffix){
    			checkSuffix = suffix.toLowerCase();
    			//验证后缀是否可以预览
    			var fileType = 0;
    			if(checkSuffix=="doc"||checkSuffix=="docx"||checkSuffix=="ppt"||checkSuffix=="pptx"||checkSuffix=="xls"||checkSuffix=="xlsx"){
    				fileViewURL = serverUrl+"/web/changePDF/viewer.html?file=/view"+fileUrl.substring(0,index)+".pdf";
    				fileViewExists = serverUrl + "/view" + fileUrl.substring(0,index)+".pdf";
    				fileLoadURL = serverUrl+"/quality/file/loadServerFile.action?name=" + encodeURIComponent(encodeURI(fileName)) + "&filePath=" + fileUrl;
    				fileType = 1;
    			}else if(checkSuffix=="pdf"){
    				fileViewURL = serverUrl+"/web/changePDF/viewer.html?file=/view"+fileUrl;
    				fileViewExists = serverUrl + "/view" + fileUrl;
    				fileLoadURL = serverUrl+"/quality/file/loadServerFile.action?name=" + encodeURIComponent(encodeURI(fileName)) + "&filePath=" + fileUrl;
    				fileType = 1;
    			//常用的图片格式
    			}else if(checkSuffix == "bmp" || checkSuffix == "jpg" || checkSuffix == "jpeg" || checkSuffix == "png" || checkSuffix == "gif"){
    				fileViewURL = "/view"+fileUrl;
    				fileViewExists = serverUrl + "/view" + fileUrl;
    				fileLoadURL = serverUrl+"/quality/file/loadServerFile.action?name=" + encodeURIComponent(encodeURI(fileName)) + "&filePath=" + fileUrl;
    				fileType = 2;
    			}else{
    				fileType = 0;
    				fileLoadURL = serverUrl+"/quality/file/loadServerFile.action?name=" + encodeURIComponent(encodeURI(fileName)) + "&filePath=" + fileUrl;
    			}
    			$.ajax({
					url : serverUrl + "/quality/file/fileExists.action",
					data:{
						"strFileName" : fileUrl
					},
					async : false,
					success : function(data) {
						if(200 == data.pdf){
							window.open(fileViewURL);
						}else{
							window.open(serverUrl+"/web/changePDF/viewer.html");
						}
						layer.closeAll();
					}
				});
    		}
    	}
    	else{
    		Tools.tipsMsg("文件不存在！");
    	}
    },
	fileDownloadOrView : function(serverUrl, fileUrl, fileName) {
		//验证服务地址或文件地址是否为空，如不为空
		if ((fileUrl != null || fileUrl != "" || undefined != fileUrl) && (serverUrl != null || serverUrl != "" || undefined != serverUrl)) {
			var fileLoadURL = "";
			var fileViewURL = "";
			var fileCheckExists = "";
			var fileViewExists = "";
			var index=fileUrl.lastIndexOf(".");
			//获取文件后缀
			var suffix = fileUrl.substring((fileUrl.lastIndexOf(".") + 1));
			var checkSuffix = "";
			if(null != suffix || undefined != suffix || "" != suffix){
				checkSuffix = suffix.toLowerCase();
				//验证后缀是否可以预览
				var fileType = 0;
				if(checkSuffix=="doc"||checkSuffix=="docx"||checkSuffix=="ppt"||checkSuffix=="pptx"||checkSuffix=="xls"||checkSuffix=="xlsx"){
					fileViewURL = serverUrl+"/web/changePDF/viewer.html?file=/view"+fileUrl.substring(0,index)+".pdf";
					fileViewExists = serverUrl + "/view" + fileUrl.substring(0,index)+".pdf";
					fileLoadURL = serverUrl+"/quality/file/loadServerFile.action?name=" + encodeURIComponent(encodeURI(fileName)) + "&filePath=" + fileUrl;
					fileType = 1;
				}else if(checkSuffix=="pdf"){
					fileViewURL = serverUrl+"/web/changePDF/viewer.html?file=/view"+fileUrl;
					fileViewExists = serverUrl + "/view" + fileUrl;
					fileLoadURL = serverUrl+"/quality/file/loadServerFile.action?name=" + encodeURIComponent(encodeURI(fileName)) + "&filePath=" + fileUrl;
					fileType = 1;
				//常用的图片格式
				}else if(checkSuffix == "bmp" || checkSuffix == "jpg" || checkSuffix == "jpeg" || checkSuffix == "png" || checkSuffix == "gif"){
					fileViewURL = "/view"+fileUrl;
					fileViewExists = serverUrl + "/view" + fileUrl;
					fileLoadURL = serverUrl+"/quality/file/loadServerFile.action?name=" + encodeURIComponent(encodeURI(fileName)) + "&filePath=" + fileUrl;
					fileType = 2;
				}else{
					fileType = 0;
					fileLoadURL = serverUrl+"/quality/file/loadServerFile.action?name=" + encodeURIComponent(encodeURI(fileName)) + "&filePath=" + fileUrl;
				}
				//验证是否给出预览提示
				//验证是否给出预览提示
				if(1 == fileType){
					layer.confirm("请选择文件打开方式", {
						btn : [ '预览', '下载', '取消' ]
					}, function() {
						// 第一个按钮执行事件,打开一个新的标签页预览
						$.ajax({
							url : serverUrl + "/quality/file/fileExists.action",
							data:{
								"strFileName" : fileUrl
							},
							async : false,
							success : function(data) {
								if(200 == data.pdf){
									window.open(fileViewURL);
								}else{
									window.open(serverUrl+"/web/changePDF/viewer.html");
								}
								layer.closeAll();
							}
						});
					}, function() {
						// 第二个按钮执行事件，连接到下载地址
						//验证文件是否存在
						$.ajax({
							url : serverUrl + "/quality/file/fileExists.action",
							data:{
								"strFileName" : fileUrl
							},
							async : false,
							success : function(data) {
								if(200 == data.file){
									location.href = fileLoadURL;
									layer.closeAll();
								}else{
									Tools.tipsMsg("文件不存在！");
								}
							}
						});
					});
				}else if(2 == fileType){
					layer.confirm("请选择文件打开方式", {
						btn : [ '预览', '下载', '取消' ]
					}, function() {
						// 第一个按钮执行事件,打开一个新的标签页预览
						$.ajax({
							url : serverUrl + "/quality/file/fileExists.action",
							data:{
								"strFileName" : fileUrl
							},
							async : false,
							success : function(data) {
								if(200 == data.file){
									window.open(fileViewURL);
								}else{
									window.open(serverUrl+"/web/changePDF/viewer.html");
								}
								layer.closeAll();
							}
						});
					}, function() {
						// 第二个按钮执行事件，连接到下载地址
						//验证文件是否存在
						$.ajax({
							url : serverUrl + "/quality/file/fileExists.action",
							data:{
								"strFileName" : fileUrl
							},
							async : false,
							success : function(data) {
								if(200 == data.file){
									location.href = fileLoadURL;
									layer.closeAll();
								}else{
									Tools.tipsMsg("文件不存在！");
								}
							}
						});
					});
				}else{
					layer.confirm("确定要下载该文件？", {
						btn : [ '确认', '取消' ]
					}, function() {// 第一个按钮执行事件
						//验证文件是否存在
						$.ajax({
							url : serverUrl + "/quality/file/fileExists.action",
							data:{
								"strFileName" : fileUrl
							},
							async : false,
							success : function(data) {
								if(200 == data.file){
									location.href = fileLoadURL;
									layer.closeAll();
								}else{
									Tools.tipsMsg("文件不存在！");
								}
							}
						});
					});
				}
			}
		}else{
			Tools.tipsMsg("文件不存在！");
		}
	}
}
// DataTable页条汉化
var dataTableLang = {
	"sProcessing" : "加载中...",
	"sLengthMenu" : "显示_MENU_条 ",
	"sZeroRecords" : "暂无内容",
	"sInfo" : "第_START_ 到 _END_ 条 &nbsp;&nbsp;共  _TOTAL_ 条",
	"sInfoEmpty" : "第 0 到 0 条   共 0 条",
	"sInfoFiltered" : "(全部记录数 _MAX_ 条)",
	"sInfoPostFix" : "",
	"sSearch" : "搜索",
	"sUrl" : "",
	"oPaginate" : {
		"sFirst" : "首页",
		"sPrevious" : "上一页 ",
		"sNext" : "下一页 ",
		"sLast" : "尾页 "
	}
}

var errorPlacement = function(error, element) {// error为错误提示对象，element为出错的组件对象
	if (element.parent().find("div.error") != null) {
		element.parent().find("div.error").remove();
	}
	if (error.html() != "") {
		element
				.parent()
				.append(
						"<div class=\"common-info error\"><span class=\" common_wrong\">"
								+ error.html()
								+ "</span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div>");
	}
}

// 时间格式化 YYYY-MM-DD hh:mm:ss
// 需要得到的时间转换成Date
// 调用方式 Date.format("YYYY-MM-DD hh:mm:ss")
// 时间格式根据需要填入，例如： YYYY-MM-DD , hh:mm:ss, YYYY-MM-DD hh:mm, MM-DD等
Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1,
		"D+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	};
	if (/(Y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
