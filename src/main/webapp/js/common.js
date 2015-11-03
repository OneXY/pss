$(function(){
	//1.输入查询页面的时候,只要用户输入非数字,就把非数字退掉
	$("#pageNo").keyup(function(){  //keyup事件处理 
	        $(this).val($(this).val().replace(/\D|^0/g,''));  
	}).bind("paste",function(){  //CTR+V事件处理 
	        $(this).val($(this).val().replace(/\D|^0/g,''));  
	}).css("ime-mode", "disabled");  //CSS设置输入法不可用
	
	//2.修改的时候取消不保存
	$("#cancelBtn").click(function(){
		window.history.back();//获取浏览器缓存对象
		//location.href="employee.action";//很多人同时管理数据使用此方法，每次获取实时数据
		$("#actionMessage").html("取消成功");
	});
	
	//3.实现重置的功能,新建和修改
	$("#resetBtn").click(function(){
		$("input[name!='id'][name!='baseQuery.currentPage'][name!='baseQuery.pageSize']").val("");
		$("select").val(-1);
	});
	
	$("#actionMessage").hide(2000,function(){
		$("#actionMessage").remove();
	});
});

function updateDomain(url,id){
//	employee_input.action?id=%{id}
// 	document.forms[0].action=url+"_input.action?id="+id;
// 	document.forms[0].submit();
	$("#domainForm").attr("action",url+"_input.action?id="+id);
	$("#domainForm").submit();
	$("#domainForm").attr("action",url+".action");
}

////点击删除请求，发出ajax删除，后台返回json数据
//function deleteDomain(url,domainId,src){
//	//必须在list.jsp引入jquery-1.8.2.min.js
//	$.get(url, {id:domainId},function(data){
//		//alert("Data Loaded: " + data);
//		//{"success":true,"msg":"删除成功"}
//		if(data.success){//删除成功
//			$("#domainForm").submit();
//		}else{//删除失败
//			alert(data.msg);
//		}	
//	}); 
//}

function download(url){
	$("#domainForm").attr("action",url+"_download.action");
	$("#domainForm").submit();
	$("#domainForm").attr("action",url+".action");
}

function deleteDomain(url,domainId,src){
	$.get(url,{id:domainId},function(data){
		if(data instanceof Object){
			//alert("Data Loaded: " + data);
			//{"success":true,"msg":"删除成功"}
			if(data.success){//删除成功
				console.debug($("#itemTable tr").size());
				if($("#itemTable tr").size()==2){
					$("#domainForm").submit();
				}else{
					$(src).closest("tr").remove();
					$("#totalCount").html($("#totalCount").html()-1);
				}
			}else{//删除失败
				alert(data.msg);
			}
		}else{
			alert("没有权限");
		}
	});
}

var has_showModalDialog = !!window.showModalDialog;//定义一个全局变量判定是否有原生showModalDialog方法  
if(!has_showModalDialog &&!!(window.opener)){         
    window.onbeforeunload=function(){ 
        window.opener.hasOpenWindow = false;        //弹窗关闭时告诉opener：它子窗口已经关闭  
    }  
}  
//定义window.showModalDialog如果它不存在  
if(window.showModalDialog == undefined){  
    window.showModalDialog = function(url,mixedVar,features){  
        if(window.hasOpenWindow){  
            alert("您已经打开了一个窗口！请先处理它");//避免多次点击会弹出多个窗口  
            window.myNewWindow.focus();  
        }  
        window.hasOpenWindow = true;  
        if(mixedVar) var mixedVar = mixedVar;  
        //因window.showmodaldialog 与 window.open 参数不一样，所以封装的时候用正则去格式化一下参数  
        if(features) var features = features.replace(/(dialog)|(px)/ig,"").replace(/;/g,',').replace(/\:/g,"=");  
        //window.open("Sample.htm",null,"height=200,width=400,status=yes,toolbar=no,menubar=no");  
        //window.showModalDialog("modal.htm",obj,"dialogWidth=200px;dialogHeight=100px");   
        var left = (window.screen.width - parseInt(features.match(/width[\s]*=[\s]*([\d]+)/i)[1]))/2;  
        var top = (window.screen.height - parseInt(features.match(/height[\s]*=[\s]*([\d]+)/i)[1]))/2;  
        window.myNewWindow = window.open(url,"_blank",features);
    }  
}  