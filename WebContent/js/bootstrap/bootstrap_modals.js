/* =========================================================
 * modals
 * ========================================================= */

Modals = {
	autoHeight: function(mId, h)
	{
		var parentHeight = mId.parent().height();
    	if (parentHeight < 150)
    	{
    		parentHeight = 150;
    	}
    	
    	//计算内容高度
    	var maxBodyHeight=0;
    	if (h)
    	{
    		maxBodyHeight = h;
    	}
    	else
    	{
    		mId.children(".modal-body").children().each(function(){
    			if (!($(this).is("title")) && !($(this).is("script")))
    			{
    				maxBodyHeight += $(this).height();
    			}
    		});
    		maxBodyHeight += 128;
    		
    	}
    	//内容高度小于150时 按150计算
		if (maxBodyHeight < 150)
		{
			maxBodyHeight = 150;
		}
		
    	if (parentHeight>maxBodyHeight)
    	{
    		mId.css("top",mId.parent().offset().top + parentHeight/2-maxBodyHeight/2);
    		mId.height(maxBodyHeight+10);
    	}
    	else
    	{
    		mId.css("top",mId.parent().offset().top);
    		mId.height(parentHeight-10);
    	}
    	mId.children(".modal-body").eq(0).height(mId.height()-128);
	},
	init : function(options)
	{
		var w = window;
		if (options.targetWindow)
		{
			w = options.targetWindow;
		}
		if(w.$("#"+options.modelId).length>0){
			return;
		}
	    var header = "<div class='modal-header' style='color:#000000;'><button class='close' onclick='$(\"#"+options.modelId+"\").modal(\"hide\");' style='color:#000000;'>×</button><h3>"+options.title+"</h3></div>";
	    
	    if (options.parent)
	    {
	    	w.$("#" + options.parent).after("<div class='modal fade' id='"+options.modelId+"'><div class='modal-dialog'><div class='modal-content'></div></div></div>");
	    }
	    else
	    {
	    	var targetDom = w.document.body;
	    	if (options.withinDom)
	    	{
	    		targetDom = w.$(options.withinDom);
	    	}
	    	else
	    	{
	    		if (w.$('#tabs') && w.$('#tabs').length > 0)
	    		{
	    			var targetId = w.$('#tabs').bootstrap_tabs("getActiveTab");
	    			if (targetId && w.$("#"+targetId) && w.$("#"+targetId).length>0 )
	    			{
	    				targetDom = w.$("#"+targetId+" > p");
	    			}
	    			
	    		}
	    	}
	    	
	    	$("<div class='modal fade' id='"+options.modelId+"'><div class='modal-dialog'><div class='modal-content'></div></div></div>").appendTo(targetDom);
	    }
	    
	    if(!options.top){w.$("#"+options.modelId).css("top",w.$("#"+options.modelId).parent().offset().top);}
	    if(options.top){if (typeof(options.top)=="number"){options.top+="px";}w.$("#"+options.modelId).css("top", options.top);}

	    
	    if (options.width){if (typeof(options.width)=="number"){options.width+="px";}w.$("#"+options.modelId).css("width", options.width);}
	    if (options.left){if (typeof(options.left)=="number"){options.left+="px";}w.$("#"+options.modelId).css("left", options.left);}
	    if (!options.left){w.$("#"+options.modelId).css("left", 300);}//left是怎么计算的？
	    w.$("#"+options.modelId).css("height", (options.height || "200")+"px");
	    w.$("#"+options.modelId + " .modal-content").append(header);
	    w.$("#"+options.modelId + " .modal-content").append("<div class='modal-body'></div>");
	    //body样式
	    if (options.bodyCss && typeof options.bodyCss == "object")
	    {
	    	$.each(options.bodyCss, function(o, record){
	    		w.$("#"+options.modelId+ " .modal-body").css(o, record);
	    	});
	    	
	    }
	    if (options.html)
	    {
	    	w.$("#"+options.modelId + " .modal-body").append(options.html);
	    	w.$("#"+options.modelId).on("show", function(){
	    		if (!options.height)
				{
	    			Modals.autoHeight(w.$("#"+options.modelId), options.maxHeight);
				}
				
		    });
	    }
	    else if (options.page)
	    {
	    	w.$("#"+options.modelId + " .modal-body").load(options.page, null, function(data,status,xhr){
				if (xhr && status =="error"){
					if (xhr && xhr.responseText)
					{
						alert(xhr.responseText);
						if (xhr && xhr.status == "444")
						{
							window.top.location.href = basePath+"login.jsp";
						}
					}
					else
					{
						alert("请求失败");
					}
				}
				else
				{
					if (!options.height)
					{
						Modals.autoHeight(w.$("#"+options.modelId), options.maxHeight);
					}
				}
			});
	    }
	    
	    
	    if (options.btn)
	    {
	    	w.$("#"+options.modelId + " .modal-content").append("<div class='modal-footer'></div>");
	    	for (var i=0; i<options.btn.length; i++)
		    {
	    		var btn = "";
	    		if (!options.btn[i].action)
	    		{
	    			btn="<a href='javascript:void(0)' onclick='$(\"#"+options.modelId+"\").modal(\"hide\");' " + (options.btn[i].id?("id="+options.btn[i].id):"") + " class='btn hw_background_btn'>"+(options.btn[i].icon?"<i class='"+options.btn[i].icon+"'></i>":"")+options.btn[i].name+"</a>";
	    		}
	    		else if (typeof(options.btn[i].action) == "string")
	    		{
	    			btn="<a href='"+options.btn[i].action+"' " + (options.btn[i].id?("id="+options.btn[i].id):"") + " class='btn hw_background_btn'>"+(options.btn[i].icon?"<i class='"+options.btn[i].icon+"'></i>":"")+options.btn[i].name+"</a>";
	    		}
	    		else if (typeof(options.btn[i].action) == "function")
	    		{
	    			btn=$("<a href='javascript:void(0);' " + (options.btn[i].id?("id='"+options.btn[i].id+"'"):"") + " class='btn hw_background_btn'>"+(options.btn[i].icon?"<i class='"+options.btn[i].icon+"'></i>":"")+options.btn[i].name+"</a>");
	    			btn.on("click", options.btn[i].action);
	    		}
	    		w.$("#"+options.modelId + " .modal-footer").append(btn);
		    }
	    }
	    w.$("#"+options.modelId).on("hide", function(){
	    	if (options.close)
	    	{
	    		options.close();
	    	}
	    	w.$("#"+options.modelId).remove();
	    });
	    
	}
	
};
