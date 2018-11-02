
$(function() {
	
})

var HistogramChart;
var HistogramChartPar;
var PieChart;

var echartsTool={
		/**
		 * 绘制柱状图
		 * divId:柱状图容器的id
		 * titleName:柱状图标题
		 * legendName:图例名称 数组
		 * xAxisTitle:x轴名称
		 * xAxisData:x轴数据 数组
		 * yAxisTitle:y轴名称
		 * seriesTitle:图表标题
		 * seriesData:图表数据 数组
		 */
		flowHistogram:function(divId,titleName,legendName,xAxisTitle,xAxisData,yAxisTitle,seriesTitle,seriesData){
			//echart配置start
			var echartId = document.getElementById(divId);
			HistogramChart = echarts.init(echartId,'macarons');
			// 指定图表的配置项和数据
			option = {
				    color: ['#3398DB'],//柱状图颜色
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    toolbox: {
				        show : true,
				        x:"right",
				        feature : {
				            mark : {show: true},
				            dataView : {optionToContent:function(opt){
		            		 	let axisData = opt.xAxis[0].data; //坐标数据
		            		    let series = opt.series; //折线图数据
		            		    let tdHeads = '<td  style="padding: 0 10px"></td>'; //表头
		            		    let tdBodys = ''; //数据
		            		    series.forEach(function (item) {
		            		        //组装表头....
		            		        tdHeads += `<td scope="col">${item.name}</td>`;
		            		    });
		            		    let table = `<table id="pattern-style-b" summary="Meeting Results" ><tbody><tr>${tdHeads} </tr>`;
		            		    for (let i = 0, l = axisData.length; i < l; i++) {
		            		        for (let j = 0; j < series.length; j++) {
		            		            //组装表数据
		            		        	if (series[j].data[i]==null) {
		            		        		series[j].data[i] = "";
										}
		            		            tdBodys += `<td>${ series[j].data[i]}</td>`;
		            		        }
		            		        table += `<tr><td style="padding: 0 10px">${axisData[i]}</td>${tdBodys}</tr>`;
		            		        tdBodys = '';
		            		    }
		            		    table += '</tbody></table>';
		            		    
		            		    return table;
		            	}, readOnly: true},				        
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    grid: {   //柱状图的位置调节
				        left: '3%',
				        right: '8%',
				        bottom: '3%',
				        containLabel: true
				    },		
				    title: {    //柱状图标题
				        x:"center",
				    	text:titleName,
						textStyle: {
							fontFamily: '微软雅黑',
							color:"#05a1d1",
							fontSize:'16'
						},
					},
				    legend: {  //图例
				    	x:"left",
				        data:legendName,
				        textStyle: {
							fontFamily: '微软雅黑',
							fontSize:'12'
						},
				    },
				    xAxis : [
				        {  
				        	name:xAxisTitle,
				            type : 'category',
				            data :xAxisData,
				            axisTick: {
				                alignWithLabel: true
				            }, 
				            axisLine:{
			                     lineStyle:{
			                    	 fontFamily: '微软雅黑',
			                          color:'#656565',
			                          fontSize:"14"
			                        }
			              },
				        }
				    ],
				    yAxis : [
				        {   
				        	name:yAxisTitle,
				            type : 'value',
				            axisLine:{
			                     lineStyle:{
			                    	 fontFamily: '微软雅黑',
			                          color:'#656565',
			                          fontSize:"14"
			                        }
			              },
				        }
				    ],
				    series : [
				        {
				            name:seriesTitle,
				            type:'bar',
				            stack: 'sum',
				            barCategoryGap: '40%',
				            itemStyle: {
				                normal: {
				                    color:'#3398DB',
				                    label : {
				                        show: true,
				                        position: 'top',
				                        textStyle:{
				                            color:'#656565'
				                        }
				                    },
				                }
				            },
				            data:seriesData
				        }
				    ]
				};
			
			// 使用刚指定的配置项和数据显示图表。
			HistogramChart.setOption(option);
		},
		flowHistogramPar:function(divId,titleName,legendName,xAxisTitle,xAxisData,yAxisTitle,seriesTitle1,seriesData1,seriesTitle2,seriesData2){
			//echart配置start
			var echartId = document.getElementById(divId);
			HistogramChartPar = echarts.init(echartId,'macarons');
			// 指定图表的配置项和数据
			option = {
				    color: ['#3398DB'],//柱状图颜色
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    toolbox: {
				        show : true,
				        x:"right",
				        feature : {
				            mark : {show: true},
				            dataView : {
				            	optionToContent:function(opt){
				            		 	let axisData = opt.xAxis[0].data; //坐标数据
				            		    let series = opt.series; //折线图数据
				            		    let tdHeads = '<td  style="padding: 0 10px">时间</td>'; //表头
				            		    let tdBodys = ''; //数据
				            		    series.forEach(function (item) {
				            		        //组装表头
				            		        tdHeads += `<td scope="col">${item.name}</td>`;
				            		    });
				            		    let table = `<table id="pattern-style-b" summary="Meeting Results" ><tbody><tr>${tdHeads} </tr>`;
				            		    for (let i = 0, l = axisData.length; i < l; i++) {
				            		        for (let j = 0; j < series.length; j++) {
				            		            //组装表数据
				            		        	if (series[j].data[i]==null) {
				            		        		series[j].data[i] = "";
												}
				            		            tdBodys += `<td>${ series[j].data[i]}</td>`;
				            		        }
				            		        table += `<tr><td style="padding: 0 10px">${axisData[i]}</td>${tdBodys}</tr>`;
				            		        tdBodys = '';
				            		    }
				            		    table += '</tbody></table>';
				            		    return table;
				            	},
				            	readOnly:true,
				            	lang:['数据视图','关闭','刷新']
				            	
				            },
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    grid: {   //柱状图的位置调节
				        left: '3%',
				        right: '8%',
				        bottom: '3%',
				        top:"100px",
				        containLabel: true
				    },		
				    title: {    //柱状图标题
				        x:"center",
				    	text:titleName,
				    	textStyle: {
							fontFamily: '微软雅黑',
							color:'#05a1d1',
							fontSize:'16'
						},
					},
				    legend: {  //图例
				        orient : 'vertical',
				    	x:"left",
				        data:legendName,
				        textStyle: {
							fontFamily: '微软雅黑',
							fontSize:'12'
						},
				    },
				    xAxis : [
				        {  
				        	name:xAxisTitle,
				            type : 'category',
				            data :xAxisData,
				            axisTick: {
				                alignWithLabel: true
				            },
				            axisLine:{
			                     lineStyle:{
			                    	 fontFamily: '微软雅黑',
			                          color:'#656565',
			                          fontSize:"14"
			                        }
			              },
				        }
				    ],
				    yAxis : [
				        {   
				        	name:yAxisTitle,
				        	axisLine:{
			                     lineStyle:{
			                    	 fontFamily: '微软雅黑',
			                          color:'#656565',
			                          fontSize:"14"
			                        }
			              },
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:seriesTitle1,
				            type:'bar',
				            barCategoryGap: '40%',
				            itemStyle: {
				                normal: {
				                    color:'#3398DB',
//				                    barBorderColor: '#3398DB',
//				                    barBorderWidth: 6,
//				                    barBorderRadius:0,
				                    label : {
				                        show: true,
				                        position: 'top',
				                        textStyle:{
				                            color:'#656565'
				                        }
				                    }
				                }
				            },
				            data:seriesData1
				        },
				        {
				            name:seriesTitle2,
				            type:'bar',
				            barCategoryGap: '40%',
				            itemStyle: {
				                normal: {
				                    color:'#FF9F15',
//				                    barBorderColor: '#FF9F15',
//				                    barBorderWidth: 6,
//				                    barBorderRadius:0,
				                    label : {
				                        show: true,
				                        position: 'top',
				                        textStyle:{
				                            color:'#656565'
				                        }
				                    }
				                }
				            },
				            data:seriesData2
				        }
				    ]
				};
			// 使用刚指定的配置项和数据显示图表。
			HistogramChartPar.setOption(option);
		},
		/**
		 *绘制饼图
		 * divId:饼图容器的id
		 * titleName:饼图标题
		 * legendName:图例名称 数组
		 * seriesTitle:图表标题
		 * seriesData:图表数据 json数组
		 */
		flowPiechart:function(divId,titleName,legendName,seriesTitle,seriesData){
			//echart配置start
			var echartId = document.getElementById(divId);
			PieChart = echarts.init(echartId,'macarons');
			option = {
				    title : {
				        text:titleName,
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        left: 'left',
				        data: legendName
				    },
				    series : [
				        {
				            name: seriesTitle,
				            type: 'pie',
				            radius : '60%',
				            center: ['50%', '60%'],
				            data:seriesData,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};
			// 使用刚指定的配置项和数据显示图表。
			PieChart.setOption(option);
		},
		/**
		 *绘制环状饼图
		 * divId:饼图容器的id
		 * titleName:饼图标题
		 * legendName:图例名称 数组
		 * seriesTitle:图表标题
		 * seriesData:图表数据 json数组
		 */
		flowAnnularPiechart:function(divId,titleName,legendName,seriesTitle1,seriesTitle2,seriesData1,seriesData2){
			//echart配置start
			var echartId = document.getElementById(divId);
			PieChart = echarts.init(echartId,'macarons');
			option = {
				    title : {
				        text:titleName,
				        x:'center',
				        textStyle: {
							fontFamily: '微软雅黑',
							color:'#05a1d1',
							fontSize:'16'
						},
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    toolbox: {
				        show : true,
				        x:"right",
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},				        
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    legend: {
				        orient: 'vertical',
				        left: 'left',
				        data: legendName,
				        textStyle: {
							fontFamily: '微软雅黑',
							fontSize:'12'
						},
				    },
				    series : [			    	
				        {
				        	 name:seriesTitle1,
				             type:'pie',
				             selectedMode: 'single',
				             radius: [0, '50%'],
				             label: {
				                 normal: {
				                     position: 'inner'
				                 }
				             },
				             labelLine: {
				                 normal: {
				                     show: false
				                 }
				             },
				            data:seriesData1,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        },
				        {
				            name:seriesTitle2,
				            type:'pie',
				            radius: ['60%', '75%'],
				            label: {
				            	 normal: {
				                     formatter: '{b}',
				            	 }
				             },
				            data:seriesData2
				        }
				    ]
				};
			// 使用刚指定的配置项和数据显示图表。
			PieChart.setOption(option);
		},	
		
		/**
		 *绘制柱状图
		 * divId:柱状图容器的id
		 * xarray:x轴数据
		 * yarray:y轴净水量
		 * sumyArray:y轴损耗水量
		 * ywaterUse:y轴水利用系数
		 */
		flowAnnularChart:function(divId,xarray,yarray,sumyArray,ywaterUse,yName,sumyName,title){
			//echart配置start
			var echartId = document.getElementById(divId);
			PieChart = echarts.init(echartId,'macarons');
			option = {
				    title : {
				        text: title,
				        left: 'center',
				        textStyle: {
							fontFamily: '微软雅黑',
							color:'#05a1d1',
							fontSize:'16'
				    },
				    },
				    tooltip : {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'shadow'
				        }
				    },
				    legend: {
				        data:[
				        	sumyName,'',
				        	 yName
				        ],
				    	x:"left",
				    	orient: 'vertical',
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    grid: {  left: '10%',
				        	 right: '10%',
				        	 bottom: '6%'},
				    xAxis : [
				        {
				            type : 'category',
				            data : xarray
				        },
				        {
				            type : 'category',
				            axisLine: {
					        	show:false,
					        	lineStyle:{
			                   	 fontFamily: '微软雅黑',
			                         color:'#656565',
			                         fontSize:"14"
			                       }
			             },
				            axisTick: {show:false},
				            axisLabel: {show:false},
				            splitArea: {show:false},
				            splitLine: {show:false},
				            data : xarray
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            name: '水量',
				            axisLabel:{formatter:'{value} m³'}
				        },
				        {
				            type: 'value',
				            name: '利用率',
				            axisLabel: {
				                formatter: '{value}'
				            }
				        }
				    ],
				    series : [
				        {
				            name: yName,
				            type:'bar',
				            stack: '1',
				            itemStyle: {normal: {color:'#36A9CE', label:{show:true,textStyle:{color:'#E87C25'}}}},
				            data:yarray
				        },
				        {
				            name:sumyName,
				            type:'bar',
				            xAxisIndex:1,
				            itemStyle: {normal: {color:'rgba(252,206,16,0.5)', label:{show:true,formatter:function(p){return p.value > 0 ? (p.value +'+'):'';}}}},
				            data:sumyArray
				        },
				        {
				            name:'水利用系数',
				            type:'line',
				            yAxisIndex: 1,
				            data:ywaterUse
				        }
				    ]
				};
			// 使用刚指定的配置项和数据显示图表。
			PieChart.setOption(option);
		}	
}

